(ns example.lambda
  (:gen-class :implements
              [com.amazonaws.services.lambda.runtime.RequestStreamHandler])
  (:require [paulbutcher.ring-lambda-adapter :refer [handle-request]]
            [example.lambda-app :refer [app]]))

(defn -handleRequest [_ is os _] (handle-request app is os))
