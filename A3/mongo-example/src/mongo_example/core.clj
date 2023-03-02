;; Using MongoDB

;; Connecting to the Database

(ns mongo-example.core
  (:require [monger.core :as m]
            [monger.collection :as mc])
  (:import
   org.bson.types.ObjectId
   [com.mongodb MongoOptions]))

;;connects to a local instance
(m/connect)

;;connect to myhost.com on port 5001
(m/connect {:host "myhost.com" :port 5001})

;;connect using custom options
(m/connect (m/server-address "127.0.0.1" 27017)
           (m/mongo-options
            {:threads-allowed-to-block-for-connection-multiplier 300}))


;; (defn connect! [& [params]]
;;   ((partial monger.core/connect) params)
;;   (monger.core/set-db! (monger.core/get-db "local")))

(def db (monger.core/get-db (monger.core/connect) "local"))

;; Inserting Records

(monger.collection/insert db "users" { :first_name "John" :last_name "Doe" })


(monger.collection/insert db "users" { :first_name "John" :last_name "Doe" })

(monger.collection/insert db
 "users"
 {:_id (ObjectId.) :first_name "John" :last_name "Doe"})


(monger.collection/insert-and-return db "users"
  { :_id (ObjectId.) :first_name "John" :last_name "Lennon" })


(monger.collection/insert-batch db
 "users"
  [{ :first_name "John" :last_name "Doe" }
   { :first_name "Jane" :last_name "Smith" }])



;; Selecting Records

(monger.collection/find-maps db "users" {:first_name "John"})


(monger.collection/find-one-as-map db "users"
  {:first_name "John"})


(monger.collection/find-map-by-id db "users"
  (ObjectId. "640007edcec48a4cc2157dbb"))


(monger.collection/find-maps db "products" { :price { "$gt" 300 "$lte" 5000 } })



;; Updating Records

(monger.collection/update db "users" { :first_name "John" } { :last_name "Doe" })
;;update existing or insert a new record
(monger.collection/update db "users" { :first_name "John" } { :last_name "Doe" } { :upsert true })



;; Deleting Records

;;remove ALL documents
(monger.collection/remove db "users")

;;remove documents with the specified key
(monger.collection/remove db "users" { :language "English" })