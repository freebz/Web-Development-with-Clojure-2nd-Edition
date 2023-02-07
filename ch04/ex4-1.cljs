;; JavaScript Interop

(defn log [& items]
  (.log js/console (apply str items)))


(defn init []
  (let [canvas (.getElementById js/document "canvas")
        ctx    (.getContext canvas "2d")
        width  (.-width canvas)
        height (.-height canvas)]
    (.log js/console (str "width: " width " height: " height))
    ;;set a property
    (set! (.-fillStyle ctx) "black")
    (.fillRect ctx 0 0 width height)))
