;; Accessing the Database

(ns db-examples.core
  (:require [clojure.java.jdbc :as sql]))


;; Defining a Parameter Map

(def db {:subprotocol "postgresql"
         :subname "//localhost/reporting"
         :user "admin"
         :password "admin"})


;; Specifying the Driver Directly

(def db
  {:datasource
   (doto (PGPoolingDataSource.)
     (.setServerName   "localhost")
     (.setDatabaseName "my_website")
     (.setUser         "admin")
     (.setPassword     "admin")
     (.setMaxConnections 10))})


;; Defining a JNDI String

(def db {:name "jdbc/myDatasource"})


;; Creating Tables

(defn create-users-table! []
  (sql/db-do-commands db
    (sql/create-table-ddl
     :users
     [:id "varchar(32) PRIMARY KEY"]
     [:pass "varchar(100)"])))


;; Selecting Records

(defn get-user [id]
  (first (sql/query db ["select * from users where id = ?" id])))

(get-user "foo")


;; Inserting Records

(defn add-user! [user]
  (sql/insert! db :users user))

(add-user! {:id "foo" :pass "bar"})


(defn add-users! [& users]
  (apply sql/insert! db :users users))

(add-users!
 {:id "foo1" :pass "bar"}
 {:id "foo2" :pass "bar"}
 {:id "foo3" :pass "bar"})


(sql/insert! db :users [:id] ["bar"] ["baz"])


;; Updating Existing Records

(defn set-pass! [id pass]
  (sql/update!
   db
   :users
   {:pass pass}
   ["id=?" id]))

(set-pass! "foo" "bar")


;; Deleting Records

(defn remove-user! [id]
  (sql/delete! db :users ["id=?" id]))

(remove-user! "foo")


;; Transactions

(sql/with-db-transaction [t-conn db]
  (sql/update!
   t-conn
   :users
   {:pass "bar"}
   ["id=?" "foo"])
  
  (sql/updqte!
   t-conn
   :users
   {:pass "baz"}
   ["id=?" "bar"]))
