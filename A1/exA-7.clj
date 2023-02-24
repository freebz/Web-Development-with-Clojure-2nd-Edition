;; Threading Expressions

(reduce + (interpose 5 (map inc (range 10))))


(->> (range 10) (map inc) (interpose 5) (reduce +))
