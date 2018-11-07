(defproject ant-table-animation "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.10.238"]
                 [reagent "0.8.1"]
                 [re-frame "0.10.5" :exclusions [reagent]]
                 [antizer "0.3.1"]]

  :plugins [[lein-cljsbuild "1.1.7"]]

  :min-lein-version "2.5.3"

  :source-paths ["src/clj" "src/cljs"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"]

  :figwheel {:css-dirs ["resources/public/css"]}

  :profiles
  {:dev
   {:dependencies [[binaryage/devtools "0.9.10"]
                   [cider/piggieback "0.3.9"]
                   [figwheel-sidecar "0.5.16"]
                   [day8.re-frame/re-frame-10x "0.3.3"]]

    :plugins      [[lein-figwheel "0.5.16"]]}
   :prod { }
   }

  :cljsbuild
  {:builds
   [{:id           "dev"
     :source-paths ["src/cljs"]
     :figwheel     {:on-jsload "ant-table-animation.core/mount-root"}
     :compiler     {:closure-defines {re-frame.trace.trace_enabled_QMARK_ true}
                    :main                 ant-table-animation.core
                    :output-to            "resources/public/js/compiled/app.js"
                    :output-dir           "resources/public/js/compiled/out"
                    :asset-path           "js/compiled/out"
                    :source-map-timestamp true
                    :preloads             [devtools.preload, day8.re-frame-10x.preload]
                    :external-config      {:devtools/config {:features-to-install :all}}
                    :infer-externs true
                    :npm-deps false
                    :foreign-libs [{:file "dist/index_bundle.js"
                                    :provides ["rc-animate" "rc-animate-child"]
                                    :global-exports {rc-animate Animate
                                                     rc-animate-child AnimateChild}}]
                    }}

    {:id           "min"
     :source-paths ["src/cljs"]
     :compiler     {:main            ant-table-animation.core
                    :output-to       "resources/public/js/compiled/app.js"
                    :optimizations   :advanced
                    :closure-defines {goog.DEBUG false}
                    :pretty-print    false}}


    ]}
  )
