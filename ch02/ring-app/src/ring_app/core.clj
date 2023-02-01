(ns ring-app.core
  (:require [ring.adapter.jetty :as jetty]
            [compojure.core :as compojure :refer [defroutes context GET]]
            ;[ring.util.response :as response]
            [ring.util.http-response :as response]
            [ring.middleware.reload :refer [wrap-reload]]
            [ring.middleware.format :refer [wrap-restful-format]]))

;; (defn handler [request-map]
;;   {:status 200
;;    :headers {"Content-Type" "text/html"}
;;    :body (str "<html><body> your IP is: "
;;               (:remote-addr request-map)
;;               "</body></html>")})

;; (defn handler [request]
;;   (response/response
;;    (str "<html><body> your IP is: "
;;         (:remote-addr request)
;;         "</body></html>")))

;; (defn handler [request]
;;   (response/ok
;;    (str "<html><body> your IP is: "
;;         (:remote-addr request)
;;         "</body></html>")))

;; (defn handler [request]
;;   (response/ok
;;    {:result (-> request :params :id)}))

(defn response-handler [request]
  (response/ok
   (str "<html><body> your IP is: "
        (:remote-addr request)
        "</body></html>")))

;; (def handler
;;   (compojure/routes
;;    (compojure/GET "/" request response-handler)
;;    (compojure/GET "/:id" [id] (str "<p>the id is: " id "</p>" ))
;;    (compojure/POST "/json" [id] (response/ok {:result id}))))

(compojure/defroutes handler
  (compojure/GET "/" request response-handler)
  (compojure/GET "/:id" [id] (str "<p>the id is: " id "</p>"))
  (compojure/POST "/json" [id] (response/ok {:result id})))

(defn display-profile [id]
  ;;TODO: display user profile
  )

(defn display-settings [id]
  ;;TODO: display user account settings
  )

(defn change-password [id]
  ;;TODO: display the page for setting a new password
  )

;; (defroutes user-routes
;;   (GET "/user/:id/profile" [id] (display-profile id))
;;   (GET "/user/:id/settings" [id] (display-settings id))
;;   (GET "/user/:id/change-password" [id] (change-password id)))

(def user-routes
  (context "/user/:id" [id]
    (GET "/user/:id/profile" [id] (display-profile id))
    (GET "/user/:id/settings" [id] (display-settings id))
    (GET "/user/:id/change-password" [id] (change-password id))))

(defn wrap-nocache [handler]
  (fn [request]
    (-> request
        handler
        (assoc-in [:headers "Pragma"] "no-cache"))))

(defn wrap-formats [handler]
  (wrap-restful-format
   handler
   {:formats [:json-kw :transit-json :transit-msgpack]}))

(defn -main []
  (jetty/run-jetty
   (-> #'handler wrap-nocache wrap-reload wrap-formats)
   {:port 3000
    :join? false}))
