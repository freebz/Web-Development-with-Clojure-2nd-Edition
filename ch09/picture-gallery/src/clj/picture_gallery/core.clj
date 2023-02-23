(ns picture-gallery.core
  (:require [picture-gallery.handler :refer [app init destroy]]
            [luminus.repl-server :as repl]
            [luminus.http-server :as http]
            [picture-gallery.db.migrations :as migrations]
            [config.core :refer [env]]
            [org.httpkit.server :as http-kit])
  (:gen-class))

;; (defn start-http-server [port]
;;   (init)
;;   (reset! http-server
;;           (http-kit/run-server
;;            app
;;            {:port port})))

;; (defn stop-http-server []
;;   (when @http-server
;;     (destroy)
;;     (@http-server :timeout 100)
;;     (reset! http-server nil)))

(defn parse-port [port]
  (when port
    (cond
      (string? port) (Integer/parseInt port)
      (number? port) port
      :else          (throw (Exception. (str "invalid port value: " port))))))

(defn http-port [port]
  ;;default production port is set in
  ;;env/prod/resources/config.edn
  (parse-port (or port (env :port))))

(defn stop-app []
  (repl/stop)
  (http/stop destroy)
  (shutdown-agents))

(defn start-app
  "e.g. lein run 3000"
  [[port]]
  (let [port (http-port port)]
    (.addShutdownHook (Runtime/getRuntime) (Thread. stop-app))
    (when-let [repl-port (env :nrepl-port)]
      (repl/start {:port (parse-port repl-port)}))
    (http/start {:handler app
                 :init    init
                 :port    port})))

(defn -main [& args]
  (cond
    (some #{"migrate" "rollback"} args)
    (do (migrations/migrate args) (System/exit 0))
    :else
    (start-app args)))
  
