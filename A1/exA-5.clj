;; Higher-Order Functions

(map #(+ % %) [1 2 3 4 5])
;; => (2 4 6 8 10)


(filter even? [1 2 3 4 5])
;; => (2 4)


(filter even?
  (map #(* 3 %) [1 2 3 4 5]))
;; => (6 12)


(defn concat-fields [& fields]
  (clojure.string/join ", " (remove empty? fields)))

(concat-fields "" "1 Main street" "Toronto" nil "Canada")
;; => "1 Main street, Toronto, Canada"


(concat-fields) ;; => ""
(concat-fields nil) ;; => ""
(concat-fields "") ;; => ""
