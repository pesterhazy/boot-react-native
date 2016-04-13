(ns mattsum.simple-example.core
  (:require [reagent.core :as reag :refer [atom]]
            [cljs.test :as test]))

#_(enable-console-print!)

;; we need set! for advanced compilation

(set! js/React (js/require "react-native/Libraries/react-native/react-native.js"))
(defonce react (js/require "react-native/Libraries/react-native/react-native.js"))

(def view (reag/adapt-react-class (.-View react)))
(def text (reag/adapt-react-class (.-Text react)))
(def image (reag/adapt-react-class (.-Image react)))
(def input (reag/adapt-react-class (.-TextInput react)))

(defn testf
  []
  (println "TESTING")
  "RETURN TESTING")

(defn root-view
  []
  [view {:style {:margin-top 22
                 :margin-left 8
                 :margin-right 8}}
   [text {:style {:font-family "futura"
                  :font-size 24
                  :text-align "center"
                  }}
    "Clojure Berlin Demo"]
   #_[view {:style {:background-color :red :height 100}}]
   #_[image {:style {:width 380 :height 380}
             :source {:uri "http://photos1.meetupstatic.com/photos/event/1/e/3/6/highres_438427734.jpeg"}}]
   #_[input {:style {:height 40 :width 300
                     :margin-left 20}
             :on-change-text (fn [v] (prn v))
             :background-color "pink"
             :placeholder "potatoes"
             :default-value "potatoes"}]
   ])

(defn mount-root []
  (reag/render [root-view] 1))

(defn ^:export main
  []
  (enable-console-print!)
  (js/console.log "MAIN")
  (.registerComponent (.-AppRegistry react)
                      "SimpleExampleApp"
                      #(reag/reactify-component root-view)))

(test/deftest testingt
  (test/is (= 1 2) "ERROR"))

(defn on-js-reload
  []
  (test/run-tests)
  (js/console.log "JS RELOADING")
  (mount-root))
