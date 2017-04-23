(ns sandwich-maker.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub
 :name
 (fn [db]
   (:name db)))

(re-frame/reg-sub
  :step
  (fn [db _]
    (:step db)))

(re-frame/reg-sub
  :selected
  (fn [db _]
    (:selected db)))

(re-frame/reg-sub
  :selected-bans
  (fn [db _]
      (:bans (:selected db))))
