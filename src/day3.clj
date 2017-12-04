(ns day3)

(def squares 347991)

(defn direction [x y]
  (cond
    (= [x y] [0 0]) :right
    (and (> y 0) (<= x y) (> (+ x y) 0)) :left
    (and (< x 0) (<= y (- x)) (> y x)) :down
    (and (< y 0) (>= x y) (<= x (- y))) :right
    :default :up))

(defn move [[x y]]
  (let [dir (direction x y)]
    (case dir
      :right [(inc x) y]
      :left [(dec x) y]
      :down [x (dec y)]
      :up [x (inc y)])))

;; Part 1

(defn steps [n]
  (loop [pos [0 0] cnt 1]
    (if (>= cnt n)
      pos
      (recur (move pos) (inc cnt)))))

(defn distance [[x y]]
  (+ (Math/abs x) (Math/abs y)))

(defn part-1 []
  (distance (steps squares)))

;; Part 2

(defn neighbors [[x y]]
  [[(inc x) y]
   [(dec x) y]
   [x (inc y)]
   [x (dec y)]
   [(dec x) (inc y)]
   [(dec x) (dec y)]
   [(inc x) (inc y)]
   [(inc x) (dec y)]])

(defn sum [pos sums]
  (if (= pos [0 0])
    1
    (reduce + (vals (select-keys sums (neighbors pos))))))

(defn part-2 []
  (loop [pos [0 0] sums {}]
    (let [s (sum pos sums)]
      (if (> s squares)
        s
        (recur (move pos) (assoc sums pos s))))))
