;; Calling Out to Java

;; Importing Classes

(ns myns
  (:import java.io.File))


(ns myns
  (:import [java.io File FileInputStream FileOutputStream]))



;; Instantiating Classes

(new File ".")


(File. ".")
