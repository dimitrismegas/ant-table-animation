(ns ant-table-animation.views
  (:require
   [re-frame.core :as re-frame]
   [ant-table-animation.subs :as subs]
   [antizer.reagent :as ant]
   ))

(defn orders
  []
  (let [orders @(re-frame/subscribe [::subs/orders])
        width 80]
    [ant/table
     {:columns [{:title "Product Name" :dataIndex :product :width width}
                {:title "Quantity" :dataIndex :quantity :width width}
                {:title "Unit Price" :dataIndex :price :width width}]
      :dataSource orders
      :size "small"
      :pagination {:page-size 20}
      :scroll {:y 300}}]))

(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div
     [:h1 "Hello from " @name]
     [orders]
     ]))
