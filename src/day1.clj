(ns day1)

(def input (slurp "resources/day1_input.txt"))

(defn part-1 []
  (->> (partition 2 1 [(first input)] input)
       (filter (partial apply =))
       (map first)
       (map #(Character/digit % 10))
       (reduce +)))

(defn part-2 []
  (->> (map vector input (nthrest (cycle input) (/ (count input) 2)))
       (filter (partial apply =))
       (map first)
       (map #(Character/digit % 10))
       (reduce +)))
