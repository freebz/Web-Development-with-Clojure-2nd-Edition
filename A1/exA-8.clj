;; Structuring the Code

(println
 (filter #(= (mod % 2) 0)
   (map #(* % %) (range 1 6))))


(->> (range 1 6)
     (map #(* % %))
     (filter #(= (mod % 2) 0))
     (println))
