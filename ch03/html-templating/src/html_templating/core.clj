;; HTML Templating using Selmer

(ns html-templating.core
  (:require [selmer.parser :as selmer]
            [selmer.filters :as filters]))

(selmer/render "Hello {{name}}" {:name "World"})


;; Creating Templates

(selmer/render-file "hello.html" {:name "World"})

;(selmer.parser/set-resource-path! "/var/html/templates/")

(selmer/render-file "hello.html" {:items (range 10)})

(selmer/render "<p>Hello {{user.first}} {{user.last}}</p>"
               {:user {:first "John" :last "Doe"}})


;; Using Filters

(filters/add-filter! :empty? empty?)

(selmer/render "{% if files|empty? %}no files{% else %}files{% endif %}"
  {:files []})


(filters/add-filter! :foo
  (fn [x] [:safe (.toUpperCase x)]))

(selmer/render "{{x|foo}}" {:x "<div>I'm safe</div>"})


;; Defining Custom Tags

(selmer/add-tag!
 :image
 (fn [args context-map]
   (str "<img src=" (first args) "/>")))

(selmer/render "{% image \"http://foo.com.jpg\" %}" {})


(selmer/add-tag!
 :uppercase
 (fn [args context-map content]
   (.toUpperCase (get-in content [:uppercase :content])))
 :enduppercase)

(selmer/render "{% uppercase %}foo {{bar}} baz{% enduppercase %}" {:bar "injected"})


;; Error Handling

(selmer/render "{{content|safea}}" {})

(defn renderer []
  (wrap-error-page
   (fn [template]
     {:status 200
      :body (selmer/render-file template {})})))

((renderer) "hello.html")

((renderer) "error.html")
