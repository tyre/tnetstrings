
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

; Dear Chris, Make this recursive. Love, Clojure
(defn parse-map [string]
  (def hashy {})
  (doseq [key-vals (clojure.string/split string #",")]
    (let [key-val (clojure.string/split key-vals #":")]
       (def hashy (assoc hashy (first key-val) (second key-val)))
    )
      )
  hashy
  )


(parse "3:ABC'")        ; "ABC"
(parse "3:123^")        ; 123.0
(parse "3:123#")        ; 123
(parse "11:derp:harold}")   ; {"derp" "harold"}
(parse "11:a,b,c,d,e,f]")   ; ["a" "b" "c" "d" "e" "f"]