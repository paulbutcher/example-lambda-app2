(ns build
  (:refer-clojure :exclude [test])
  (:require [clojure.tools.build.api :as b]))

(def main 'paulbutcher.lambda)
(def class-dir "target/classes")

(defn- uber-opts [opts]
  (assoc opts
         :main main
         :uber-file "target/standalone.jar"
         :basis (b/create-basis {})
         :class-dir class-dir
         :src-dirs ["src"]
         :ns-compile [main]))

(defn uber "Build an uberjar" [opts]
  (b/delete {:path "target"})
  (let [opts (uber-opts opts)]
    (println "\nCopying source...")
    (b/copy-dir {:src-dirs ["resources" "src"] :target-dir class-dir})
    (println (str "\nCompiling " main "..."))
    (b/compile-clj opts)
    (println "\nBuilding JAR...")
    (b/uber opts)))
