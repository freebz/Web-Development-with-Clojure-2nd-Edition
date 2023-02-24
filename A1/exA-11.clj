;; The :use Keyword

(ns myns
  (:use colors))

(hex->rgb "#33d24f")



(ns myns
  (:use [colors :only [rgb->hex]]))

(defn hex-str [c]
  (println "I don't do much yet"))
