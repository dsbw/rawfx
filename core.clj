(ns ezclj.core
  (:require [cljfx.api :as fx])
  (:gen-class :extends javafx.application.Application)
  (:import (javafx.application Application Platform)
           (javafx.fxml FXMLLoader)
           (javafx.scene Scene Group)))

(def _thread (atom nil))
(def _app (atom nil))
(def _scene (atom nil))
(def _stage (atom nil))

(defn -start [app stage]
  (reset! _app app)
  (.setTitle stage "Hello From Clojure!")
  (let [f (Scene. (FXMLLoader/load (.getResource (.getClass app) "sample.fxml")) 800 600)
        ss (.getStylesheets f)
        _ (.add ss (-> (.getClass app) (.getResource "default.css") (.toExternalForm)))
        _ (.setScene stage f)]
    (reset! _scene f))
  (.show stage)
  (reset! _stage stage))


(defn -main [& args]
  (reset! _thread
          (Thread. #(Application/launch ezclj.core (into-array String [])))))

(defn u [fn]
  (Platform/runLater fn))
