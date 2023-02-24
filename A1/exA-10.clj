;; Namespaces

(ns colors)

(defn hex->rgb [[_ & rgb]]
  (map #(->> % (apply str "0x") (Long/decode))
       (partition 2 rgb)))

(defn hex-str [n]
  (-> (format "%2s" (Integer/toString n 16))
      (clojure.string/replace " " "0")))

(defn rgb->hex [color]
  (apply str "#" (map hex-str color)))
