;; Dynamic Variables

(declare ^:dynamic *foo*)

(println *foo*)
;; =>#<Unbound Unbound: #'bar/*foo*>



(binding [*foo* "I exist!"]
  (println *foo*))
;; =>"I exist!"
