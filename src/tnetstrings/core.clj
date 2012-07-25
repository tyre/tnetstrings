(ns tnetstrings.core
  :require [clojure/string :as str])

  (defn parse [payload] 
      (def payload-size-length (.indexOf payload ":"))
      (def payload-size
        (Integer/parseInt (subs payload 0 payload-size-length)))
    (subs payload (+ 1 payload-size-length)  (+ 2 payload-size))
  )

