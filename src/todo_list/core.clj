(ns todo-list.core
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.reload :refer [wrap-reload]]
            [compojure.core :refer [defroutes GET]]
            [compojure.route :refer [not-found]]
            [ring.handler.dump :refer [handle-dump]]
            [hiccup.core :refer :all]
            [hiccup.page :refer :all]
            [todo-list.handlers.base-routes :as base-routes]))


(defroutes app
  (GET "/" [] base-routes/welcome)
  (GET "/goodbye" [] base-routes/goodbye)
  (GET "/about" [] base-routes/about)
  (GET "/hello/:name" [] base-routes/hello)
  (GET "/calculator/:a/:op/:b" [] base-routes/calculator)
  (GET "/hiccup-info" [] base-routes/trying-hiccup)
  (not-found "<h1>This is not the page you are looking for</h1>
              <p>Sorry, the page you requested was not found!</p>"))

(defn -main
  "A very simple web server using Ring & Jetty"
  [port-number]
  (jetty/run-jetty app
                   {:port (Integer. port-number)}))

(defn -dev-main
  "A very simple web server using Ring & Jetty that reloads code changes via the development profile of Leiningen"
  [port-number]
  (jetty/run-jetty (wrap-reload #'app)
                   {:port (Integer. port-number)}))
