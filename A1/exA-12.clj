;; The :require Keyword

(ns myns
  (:require colors))

(colors/hex->rgb "#324a9b")



(ns myotherns
  (:require [colors :as c]))

(c/hex->rgb "#324a9b")



(ns myns
  (:require [colors :refer :all]))



(ns myns
  (:require [colors :refer [rgb->hex]]))
