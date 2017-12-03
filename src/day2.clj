(ns day2
  (:require [clojure.string :as str]
            [clojure.math.combinatorics :refer [combinations]]))

(def input (slurp "resources/day2_input.txt"))

(defn parse-input [data]
  (map #(map read-string (str/split % #"\t")) (str/split-lines data)))

(defn part-1 []
  (reduce + (map #(- (apply max %) (apply min %)) (parse-input input))))

(defn part-2 []
  (->> (parse-input input)
    (mapcat #(remove ratio? (map (fn [[a b]] (/ b a)) (map sort (combinations % 2)))))
    (reduce +)))
