(ns reporting-example.routes.home
  (:require [ring.util.response :as response]
            [compojure.core :refer [defroutes GET]]
            [reporting-example.reports :as reports]
            [reporting-example.layout :as layout]))

(defn home []
  (layout/render "home.html"))

(defn about []
  (layout/render "about.html"))

(defn write-response [report-bytes]
  (with-open [in (java.io.ByteArrayInputStream. report-bytes)]
    (-> (response/response in)
        (response/header "Content-Disposition" "filename=document.pdf")
        (response/header "Content-Length" (count report-bytes))
        (response/content-type "application/pdf")) ))

(defn generate-report [report-type]
  (try
    (let [out (java.io.ByteArrayOutputStream.)]
      (condp = (keyword report-type)
             :table (reports/table-report out)
             :list  (reports/list-report out))
      (write-response (.toByteArray out)))
    
    (catch Exception ex
      (layout/render "home.html" {:error (.getMessage ex)}))))

(defroutes home-routes
  (GET "/" [] (home))
  (GET "/about" [] (about))
  (GET "/:report-type" [report-type] (generate-report report-type)))