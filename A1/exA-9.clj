;; Destructuring Data

(let [[small big] (split-with #(< % 5) (range 10))]
  (println small big))

;; =>(0 1 2 3 4) (5 6 7 8 9)



(defn print-user [[name address phone]]
  (println name address phone))

(print-user ["Bob" "12 Jarvis street, Toronto" "416-987-3417"])



(defn foo [& args]
  (println args))

(foo "a" "b" "c")
;; =>(a b c)



(defn foo [first-arg & [second-arg]]
  (println (if second-arg
             "two arguments were passed in"
             "one argument was passed in")))

(foo "bar")
;; =>"one argument was passed in"

(foo "bar" "baz")
;; =>"two arguments were passed in"



(let [{foo :foo bar :bar} {:foo "foo" :bar "bar"}]
  (println foo bar))



(let [{[a b c] :items id :id} {:id "foo" :items [1 2 3]}]
  (println id " has the following items " a b c))



(defn login [{:keys [user pass]}]
  (and (= user "bob") (= pass "secret")))

(login {:user "bob" :pass "secret"})



(defn register [{:keys [id pass repeat-pass] :as user}]
  (cond
    (nil? id) "user id is required"
    (not= pass repeat-pass) "re-entered password doesn't match"
    :else user))
