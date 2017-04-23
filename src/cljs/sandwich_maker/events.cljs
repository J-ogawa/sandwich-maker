(ns sandwich-maker.events
    (:require [re-frame.core :as re-frame]
              [sandwich-maker.db :as db]))

(re-frame/reg-event-db
 :initialize-db
 (fn  [_ _]
   db/default-db))

(re-frame/reg-event-db
  :select-bans
  (fn [db [_ bans]]
    (assoc-in db [:selected :bans] bans)))

(re-frame/reg-event-db
  :next-step
  (fn [db [_ next-step]]
    (assoc-in db [:step] next-step)))
