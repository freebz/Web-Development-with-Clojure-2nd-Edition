;; Clutch Library

(ns couch-example.core
  (:require [com.ashafa.clutch :as couch]))



;; Connecting to the Database

;; (def db "http://localhost:5984/clutchtest")

;; (def db "http://user:pass@localhost:5984/clutchtest")

(def db (assoc (cemerick.url/url "https://localhost:5984/" "clutchtest")
               :username "user"
               :password "pass"))



;; Storing Documents

(couch/with-db db
  (couch/put-document {:foo "bar"}))

(couch/with-db db
  (couch/put-document
   {:_id "user" :username "foo" :pass "$dfsdf#23434"}))

(couch/with-db db
  (couch/put-document
   {:_id "user" :_rev "<revision number>" :username "foo" :pass "$dfsdf#23434"}))



;; Retrieving a Single Document

(couch/with-db db
  (couch/get-document "user"))

(couch/with-db db
  (let [doc (couch/get-document "user")]
    (couch/put-document
     (assoc doc :username "bar")))
  (println (couch/get-document "user")))



;; Retrieving Multiple Documents

(couch/with-db db
  (couch/all-documents))

(couch/with-db db
  (couch/all-documents {:include_docs true}))

(couch/with-db db
  (couch/all-documents
   {:include_docs true}
   {:keys ["doc1" "doc2" "doc3"]}))



;; Deleting Documents

(couch/with-db db
  (couch/delete-document "user"))