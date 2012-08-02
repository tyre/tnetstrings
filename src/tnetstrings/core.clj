(ns tnetstrings.core)


(defn parse-map [string]
 (def hashy {})
 (doseq [key-vals (clojure.string/split string #",")]
  (let [key-val (clojure.string/split key-vals #":" 2)
   value (second key-val)]
   (def hashy (assoc hashy (first key-val)
    (if (= "{" (first (second key-val)))
     (recur (subs value 1 (- (.length value) 1)))
     value)
    )
   )
   )
  hashy
  )
 )


 (defn parse [payload]
  (let [
   payload-size-length (.indexOf payload ":")
   payload-size (Integer/parseInt (subs payload 0 payload-size-length))
   type-pos (+ payload-size-length payload-size 1)
   msg (subs payload (+ 1 payload-size-length)
        (+ payload-size (+ 1 payload-size-length)))
   ]

   (case (subs payload type-pos (+ type-pos 1))
    "'" (str msg)
    "#" (Integer/parseInt msg)
    "^" (Float/parseFloat msg)
    "!" (boolean msg)
    "~" nil
    "}" (parse-map msg)
    "]" (clojure.string/split msg #",")
    )
   )
  )