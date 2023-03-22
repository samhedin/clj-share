(ns core
  (:use compojure.core
        [ring.util.response :only (response)])
  (:require [clj-http.client :as client]
            [org.httpkit.server :as app-server]
            [ring.middleware.json :as rjson]
            [clojure.java.shell :as shell]
            [ring.middleware.session :as session]
            [ring.middleware.cookies :as cookies]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer :all]
            [clojure.pprint :as pp]
            [clojure.string :as str]
            [clojure.data.json :as json])
  (:gen-class))


(defonce app-server-instance (atom nil))

(defn- handler
  [_req]
  {:status 200
   :body   "Hello world!\n"})

(defroutes app-routes
  (GET "/" [] handler))

(defn -main
  []
  (reset! app-server-instance
          (app-server/run-server app-routes {:port (or (Long/parseLong (System/getenv "PORT")) 3000)})))

(defn stop-app-server
  "Gracefully shutdown the server, waiting 100ms"
  []
  (when-not (nil? @app-server-instance)
    (@app-server-instance :timeout 100)
    (reset! app-server-instance nil)
    (println "INFO: Application server stopped")))

(defn restart-app-server
  "Convenience function to stop and start the application server"
  []
  (stop-app-server)
  (-main))

(comment
  ;; start application
  (-main)
  ;; stop application
  (stop-app-server)
  ;; restart application
  (restart-app-server))
