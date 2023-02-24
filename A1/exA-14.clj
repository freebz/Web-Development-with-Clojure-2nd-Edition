;; Polymorphism

;; Multimethods

(defmulti area :shape)

(defmethod area :circle [{:keys [r]}]
  (* Math/PI r r))

(defmethod area :rectangle [{:keys [l w]}]
  (* l w))

(defmethod area :default [shape]
  (throw (Exception. (str "unrecognized shape: " shape))))

(area {:shape :circle :r 10})
;; => 314.1592653589793

(area {:shape :rectangle :l 5 :w 10})
;; => 50



(defmulti encounter
  (fn [x y] [(:role x) (:role y)]))

(defmethod encounter [:manager :boss] [x y]
  :promise-unrealistic-deadlines)

(defmethod encounter [:manager :developer] [x y]
  :demand-overtime)

(defmethod encounter [:developer :developer] [x y]
  :complain-about-poor-management)

(encounter {:role :manager} {:role :boss})
;; => :promise-unrealistic-deadlines



(defprotocol Foo
  "Foo doc string"
  (bar [this b] "bar doc string")
  (baz [this] [this b] "baz doc string"))



(deftype Bar [data]
  Foo
  (bar [this param]
    (println data param))
  (baz [this]
    (println (class this)))
  (baz [this param]
    (println param)))



(let [b (Bar. "some data")]
  (.bar b "param")
  (.baz b)
  (.baz b "baz with param"))

;; some data param
;; Bar
;; baz with param



(extend-protocol Foo String
  (bar [this param] (println this param)))

(bar "hello" "world")
;; =>"hello world"
