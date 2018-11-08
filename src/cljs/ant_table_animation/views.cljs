(ns ant-table-animation.views
  (:require
   [re-frame.core :as re-frame]
   [ant-table-animation.subs :as subs]
   [ant-table-animation.events :as events]
   [antizer.reagent :as ant]
   [reagent.core :as reagent]
   [rc-animate]
   ))

(.log js/console rc-animate)

(def animate (reagent/adapt-react-class rc-animate))

(def animateBody
  (fn [props]
    (reagent/as-element [animate (assoc props :transition-name "move" :component "tbody")])))

(defn orders
  []
  (let [orders @(re-frame/subscribe [::subs/orders])
        width 80]
    [ant/table
     {:columns
      [{:title "Product Name" :dataIndex :product :width width}
       {:title "Quantity" :dataIndex :quantity :width width}
       {:title "Unit Price" :dataIndex :price :width width}
       {:title "Actions" :dataIndex "actions" :width width
        :render
        #(reagent/as-element
          [ant/button
           {:icon "delete" :type "danger"
            :on-click
            (fn []
              (re-frame/dispatch [::events/order-deleted (.-product %2)]))}])}]
      :dataSource orders
      :size "small"
      :components {:body {:wrapper (reagent/reactify-component animateBody)}}
      :pagination {:page-size 20}
      :scroll {:y 300}}]))

(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div
     [:h1 "Hello from " @name]
     [orders]
     ]))
