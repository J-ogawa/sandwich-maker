(ns sandwich-maker.views
  (:require [re-frame.core :as re-frame]))

(def steps
  [:bans :topping])

(defn next-step [current]
  (first (next (last (split-with (complement #{current}) steps)))))

(def bans
  {:option [:white :wheat :sesame]
   :type :choice})

(defn bans-selector
  []
  (fn []
    (let [selected @(re-frame/subscribe [:selected-bans])
          step @(re-frame/subscribe [:step])]
      (if (= step :bans)
        [:ul.selector
         (for [elem (:option bans)] ^{:key elem}
           [:li.option
            {
             :on-click #(re-frame/dispatch [:select-bans elem])
             :style {:background-color (if (= selected elem) :red :blue)}
             }
            elem])]))))

(defn status []
  (let [selected (re-frame/subscribe [:selected])
        step (re-frame/subscribe [:step])]
    (fn []
      [:div [:div (str @selected)] [:div @step]])))

(defn menu []
  (let [selected (re-frame/subscribe [:selected])
        step (re-frame/subscribe [:step])]
    (fn []
      [:div
       [bans-selector]
       [:div
        {
         :on-click #(re-frame/dispatch [:next-step (next-step @step)])
         :style {:background-color :purple}
         }
        "next"]
       ]
      )))

(defn main-panel []
  (fn []
    [:div
     [status]
     [menu]]))
