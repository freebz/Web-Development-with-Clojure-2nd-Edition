;; Using Yesql

-- select user by id
SELECT *
FROM users
WHERE id = :id


(ns db-examples.yesql
  (:require [db-examples.core :refer [db]]
            [clojure.java.jdbc :as sql]
            [yesql.core :refer [defquery defqueries]]))

(defquery find-user "find_user.sql")

(find-user {:id "foo"} {:connection db})



(defquery find-user-with-connection "find_user.sql" {:connection db})

(find-user-with-connection {:id "foo"})



(defquery select-date "select_date.sql" {:connection db})

(select-date
 {}
 {:result-set-fn first
  :row-fn :now
  :identifiers identity})



-- select active users by country
SELECT *
FROM users
WHERE (
  country = ?
  OR
  country_code = ?
)
AND active = :active

(active-users-by-country {:? ["CA" "US"] :active true})



-- find users with a matching ID
   SELECT *
   FROM user
   WHERE user_id IN (:id)

(find-users {:id ["foo" "bar" "baz"]})



-- add user
INSERT INTO users
(id, pass)
VALUES (:id, :pass)

(defquery add-user! "add_user.sql" {:connection db})

(add-user! {:id "a-new-user" :pass "foo"})


(defquery add-user<! "add_user.sql" {:connection db})

(add-user<! {:id "another-user" :pass "foo"})



-- name: add-user!
-- add user
INSERT INTO users
(id, pass)
VALUES (:id, :pass)

-- name: select-user
-- select user by id
SELECT *
FROM users
WHERE id = :id

-- name: select-date
-- select current date
SELECT now()::date;


(defqueries "queries.sql" {:connection db})


(defn find-users-transaction []
  (sql/with-db-transaction [t-conn db]
    {:limeys (find-user {:id "foo"} {:connection t-conn})
     (:yanks (find-user {:id "bar"} {:connection t-conn}))})
