(ns sandwich-maker.views
  (:require [re-frame.core :as re-frame]))

(def bans
  {:option [:white :wheat :sesame]
   :type :choice})

(defn bans-selector
  []
  (let [selected (re-frame/subscribe [:selected])]
    (fn []
      [:div
       "bans: "
       (for [elem (:option bans)]
         [:div {:on-click #(re-frame/dispatch [:select-bans elem])} elem])])))

(defn menu []
  (fn []
    [:div
     [bans-selector]
     ]))

(defn main-panel []
  (let [selected (re-frame/subscribe [:selected])]
    (fn []
      [:div (str @selected) [menu]])))
