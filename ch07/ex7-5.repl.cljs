;; Generating the Reports

(pdf
 [{:header "Wow that was easy"}
  [:list
   [:chunk {:style :bold} "a bold item"]
   "another item"
   "yet another item"]
  [:paragraph "I'm a paragraph!"]]
 "doc.pdf")



(employee-template (take 2 (db/read-employees)))

;; (["Albert Einstein" "Engineer" "Ulm" "Germany"]
;;  ["Alfred Hitchcock" "Movie Director" "London" "UK"])



(pdf
 [{:header "Employee List"}
  (into [:table
         {:border false
          :cell-border false
          :header [{:color [0 150 150]} "Name" "Occupation" "Place" "Country"]}]
        (employee-template (db/read-employees)))]
 "report.pdf")


(pdf
 [{}
  [:heading {:size 10} "Employees"]
  [:line]
  [:spacer]
  (employee-template-paragraph (db/read-employees))]
 "report.pdf")
