;; The Test API

(deftest collections-test
  (testing "Collections"
    (is (coll? {}))
    (is (coll? #{}))
    (is (coll? []))
    (is (coll? '()))))
