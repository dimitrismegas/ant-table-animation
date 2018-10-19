(ns ant-table-animation.events
  (:require
   [re-frame.core :as re-frame]
   [ant-table-animation.db :as db]
   ))

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))

(re-frame/reg-event-db
 ::order-deleted
 (fn [db [_ product]]
   (assoc db :orders (remove #(= product (:product %)) (:orders db)))))
