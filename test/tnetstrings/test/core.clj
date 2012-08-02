(ns tnetstrings.test.core
 (:use [tnetstrings.core])
 (:use [clojure.test]))

(deftest int-parse
 (is 123 (parse "3:123#")))

(deftest float-parse
 (is 123.0 (parse "3:123^")))

(deftest string-parse
 (is "ABC" (parse "3:ABC'")))

(deftest hash-parse
 (is {"frat" "star" "derp" "harold"} (parse "21:derp:harold,frat:star}")))

(deftest array-parse
 (is ["a" "b" "c" "d" "e" "f"] (parse "11:a,b,c,d,e,f]")))