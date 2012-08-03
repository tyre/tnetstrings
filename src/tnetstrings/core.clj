(ns tnetstrings.core)

; Dear Chris, Make this recursive. Love, Clojure
(defn parse-map [string]
 (def hashy {})
 (doseq [key-vals (clojure.string/split string #",")]
  (let [[cay value :as key-val] (clojure.string/split key-vals #":")]
   (def hashy (assoc hashy cay value))
   )
  )
 hashy
 )

; (defn parse-map [string]
;  (let [
;   key-vals (clojure.string/split string #",")
;   [cay value :as key-val] (clojure.string/split key-vals #":")
;   ]
;   (when (= "{" (first value))
;    ({} cay (parse-map value))
;    {cay value}
;    )
;   )
;  )

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
   "!" (Boolean/valueOf msg)
   "~" nil
   "}" (parse-map msg)
   "]" (clojure.string/split msg #",")
   )
  )
 )
