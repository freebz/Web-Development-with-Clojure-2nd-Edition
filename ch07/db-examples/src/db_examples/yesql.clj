(ns db-examples.yesql
  (:require [db-examples.core :refer [db]]
            [clojure.java.jdbc :as sql]
            [yesql.core :refer [defquery defqueries]]))

(defquery find-user "find_user.sql")

(defquery find-user-with-connection "find_user.sql" {:connection db})

(defquery select-date "select_date.sql" {:connection db})

(defquery add-user! "add_user.sql" {:connection db})

(defquery add-user<! "add_user.sql" {:connection db})

(defqueries "queries.sql" {:connection db})

(defn find-users-transaction []
  (sql/with-db-transaction [t-conn db]
    {:limeys (find-user {:id "foo"} {:connection t-conn})
     (:yanks (find-user {:id "bar"} {:connection t-conn}))}))