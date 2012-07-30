
(defn parse [payload]
  (let [
        payload-size-length (.indexOf payload ":")
        payload-size (Integer/parseInt (subs payload 0 payload-size-length))
        type-pos (+ payload-size-length payload-size 1)
    msg (subs payload (+ 1 payload-size-length) (+ 2 payload-size))
       ]

    (case (subs payload type-pos (+ type-pos 1))
        "'" (str msg)
        "#" (Integer/parseInt msg)
        "^" (Float/parseFloat msg)
        "!" (boolean msg)
        "~" nil
        "}" (hash-map msg)
        "]" (list msg)  )
  )
)

(parse "3:ABC'")  ; "ABC"
(parse "3:123^")  ; 123.0
(parse "3:123#")  ; 123
(parse "4:derp}") ; No value supplied for key: derp



; experimental experiments

(hash-map (str (hash-map :a 5)))
(hash-map "a:5")

(defn parse-map [string]
  (hash-map (clojure.string/split string #","))
)

(parse-map "a:5")