
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
        "]" (list msg)  )
  )
)

(parse "3:ABC'")  ; "ABC"
(parse "3:123^")  ; 123.0
(parse "3:123#")  ; 123
(parse "11:derp:harold}")


; experimental experiments

(hash-map (str (hash-map :a 5)))
(hash-map "a:5")

(defn parse-map [string]
  (def hashy {})
  (doseq [key-vals (clojure.string/split string #",")]
    (let [key-val (clojure.string/split key-vals #":")]
       (def hashy (assoc hashy (first key-val) (second key-val)))
    )
      )
  hashy
  )

(parse-map "a:5,b:6")