;; Task B: Login and Logout

(str "Basic "
     (.encodeToString
      (java.util.Base64/getEncoder)
      (.getBytes "user:pass")))
