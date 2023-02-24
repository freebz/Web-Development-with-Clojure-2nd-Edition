;; Calling Methods

(let [f (File. ".")]
  (println (.getAbsolutePath f)))



(str File/separator "foo" File/separator "bar")

(Math/sqrt 256)



(.getBytes (.getAbsolutePath (File. ".")))

(.. (File. ".") getAbsolutePath getBytes)
