(ns clj-mapfilter.core
  (:require [clojure.string :as str] [clojure.walk :as walk]))

(defn -main []
  (println "Please enter a category:")
  (println "Valid options: Furniture, Alcohol, Toiletries, Shoes, Food, Jewelry")
  (let
    [purchases (slurp "purchases.csv")
     purchases (str/split-lines purchases)
     purchases (map (fn [line]
                      (str/split line #",")) purchases)
     headers (first purchases)
     purchases (rest purchases)
     purchases (map (fn [line]
                      (zipmap headers line)) purchases)
     purchases (walk/keywordize-keys purchases)
     category (read-line)
     purchases (filter (fn [line]
                         (= (get line :category) category)) purchases)
     file-text (pr-str purchases)]
    (spit "filtered_purchases.edn" file-text)))
