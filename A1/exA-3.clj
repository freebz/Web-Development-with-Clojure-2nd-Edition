;; Anonymous Functions

(fn [arg] (println arg))


((fn [arg] (println arg)) "hello")

;; => "hello"


#(println %)


#(println %1 %2 %3)
