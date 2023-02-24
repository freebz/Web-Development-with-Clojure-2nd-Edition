;; What about Global State?

(def global-val (atom nil))


(println (deref global-val))
;; =>nil


(reset! global-val 10)
(println @global-val)
;; =>10
(swap! global-val inc)
(println @global-val)
;; =>11



(def names (ref []))

(dosync
 (ref-set names ["John"])
 (alter names #(if (not-empty %)
                 (conj % "Jane") %)))
