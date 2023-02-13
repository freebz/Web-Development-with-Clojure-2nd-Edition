;; Serializing and Deserializing Data Based on Its Type

(in-ns 'reporting-example.db.core)

(mount.core/start #'reporting-example.db.core/*db*)

(jdbc/insert!
 @*db*
 :employee
 [:name :occupation :place :country]
 ["Albert Einstein", "Engineer", "Ulm", "Germany"]
 ["Alfred Hitchcock", "Movie Director", "London", "UK"]
 ["Wernher Von Braun", "Rocket Scientist", "Wyrzysk", "Poland"]
 ["Sigmund Freud", "Neurologist", "Pribor", "Czech Republic"]
 ["Mahatma Gandhi", "Lawyer", "Gujarat", "India"]
 ["sachin Tendulkar", "Cricket Player", "Mumbai", "India"]
 ["Michael Schumacher", "F1 Racer", "Cologne", "Germany"])



(conman/bind-connection *db* "sql/queries.sql")

(read-employees)

;; ({:name "Albert Einstein",
;;   :occupation "Engineer",
;;   :place "Ulm",
;;   :country "Germany"}
;;  {:name "Alfred Hitchcock",
;;   :occupation "Movie Director",
;;   :place "London",
;;   :country "UK"}
;;  ...)
