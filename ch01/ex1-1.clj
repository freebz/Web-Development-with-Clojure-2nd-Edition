;;switch to the namespace
(use 'guestbook.db.core)

;;check if we have any existing data
(get-messages)
;;output: ()

;;create a test message
(save-message! {:name "Bob"
                :message "Hello World"
                :timestamp (java.util.Date.)})
;;output 1

;;check that the message is saved correctly
(get-messages)
;; ({:id 1,
;;   :name "Bob",
;;   :message "Hello World",
;;   :timestamp #inst "2023-01-18T07:43:15.514000000-00:00"})
