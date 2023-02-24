;; Named Functions

(def square (fn ([x] (* x x))))


(defn square [x]
  (* x x))


(defn bmi [height weight]
  (println "height:" height)
  (println "weight:" weight)
  (/ weight (* height height)))


(declare down)

(defn up [n]
  (if (< n 10)
    (down (+ 2 n))
    n))

(defn down [n]
  (up (dec n)))
