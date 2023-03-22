(defproject chat "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
 :pom-plugins
  [[com.google.cloud.tools/jib-maven-plugin "2.1.0"
    (:configuration
      [:from [:image "gcr.io/distroless/java:11"]]
      [:to [:image "gcr.io/MY-GCP-PROJECT/SERVICE"]]
      [:container
       [:mainClass "core"]
       [:creationTime "USE_CURRENT_TIMESTAMP"]])]]

  :dependencies [[org.clojure/clojure "1.11.1"]
                 [clj-http "3.12.3"]
                 [org.clojure/data.json "2.4.0"]
                 [http-kit/http-kit "2.3.0"]
                 [compojure "1.7.0"]
                 [ring/ring-jetty-adapter "1.9.6"]
                 [ring/ring-defaults "0.3.4"]
                 [ring/ring-core "1.9.6"]
                 [ring/ring-json "0.5.1"]]
  :main ^:skip-aot core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
