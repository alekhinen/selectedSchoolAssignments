;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname hw11-alekhine-kaminsky) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ())))
;; Nick Alekhine  - alekhine.n@husky.neu.edu
;; Tyler Kaminsky - kaminsky.t@husky.neu.edu
;; Homework 11


;; Problem 1 ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; A
;; prime?: NatNum -> Boolean
;; to determine if the given natural number is prime
;; example: see tests
(define (prime? n)
  (cond [(or (zero? n)
             (zero? (sub1 n))) false]
        [else (local [(define (prime.v2? m n)
                        (cond [(zero? (sub1 m)) true]
                              [(= (/ n m) (round (/ n m))) false]
                              [else (prime.v2? (sub1 m) n)]))]
                (prime.v2? (round (sqrt n)) n))]))

;; tests
(check-expect (prime? 2) true)
(check-expect (prime? 5) true)
(check-expect (prime? 7) true)
(check-expect (prime? 4) false)
(check-expect (prime? 1) false)


;; B
;; list-primes: NatNum -> [Listof NatNum]
;; to produce a list of prime numbers up to n
;; example: see tests
(define (list-primes n)
  (filter prime? (build-list n add1)))

;; tests
(check-expect (list-primes 10) (list 2 3 5 7))
(check-expect (list-primes 20) (list 2 3 5 7 11 13 17 19))


;; Problem 2 ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; A
;; make-palindrome: string -> string
;; to make a palindrome of a given string by mirroring around
;; the last letter
;; example: see tests
(define (make-palindrome s)
  (implode (append (explode s) (rest (reverse (explode s))))))

;; tests
(check-expect (make-palindrome "fundies") "fundieseidnuf")


;; B
;; is-palindrome?: string -> boolean
;; to determine whether the given string is a palindrome
;; example: see tests
(define (is-palindrome? s)
  (string=? s (implode (reverse (explode s)))))

;; tests
(check-expect (is-palindrome? "swag") false)
(check-expect (is-palindrome? "fundieseidnuf") true)


;; Problem 3 ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; A
;; fibonacci: NatNum -> number
;; to return the corresponding fibonacci number from a given number
;; example: see tests
(define (fibonacci n)
  (cond [(zero? n) 0]
        [(zero? (sub1 n)) 1]
        [else (+ (fibonacci (sub1 n)) (fibonacci (sub1 (sub1 n))))]))

;; tests
(check-expect (fibonacci 0) 0)
(check-expect (fibonacci 1) 1) 
(check-expect (fibonacci 2) 1) 
(check-expect (fibonacci 11) 89)



;; B
;; fibonacci.v2: NatNum -> number
;; to return the corresponding fibonacci number from a given number
;; using an accumulator
;; example: see tests
(define (fibonacci.v2 n)
  (local [(define (fib-helper a b n)
            (cond [(zero? n) a]
                  [else (fib-helper b (+ a b) (sub1 n))]))]
    (fib-helper 0 1 n)))

;; tests
(check-expect (fibonacci.v2 0) 0)
(check-expect (fibonacci.v2 1) 1)
(check-expect (fibonacci.v2 2) 1)
(check-expect (fibonacci.v2 11) 89)


;; C
;; list-fibonacci: NatNum -> [Listof NatNum]
;; to create a list of fibonacci numbers from 0 to n
;; example: see tests
(define (list-fibonacci n)
  (build-list n fibonacci.v2))

;; tests
(check-expect (list-fibonacci 5) '(0 1 1 2 3))


;; Problem 4 ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; A WordTree is one of:
;; -String
;; -(make-node WordTree String WordTree)
(define-struct node (left word right))

;; define constants
(define holidays (make-node
                  (make-node "Christmas" "Labor Day" "MLK Day")
                  "Patriots Day"
                  (make-node "Presidents Day" "Thanksgiving" "Veterans Day")))
(define movies (make-node
                (make-node "Argo" "Flight" "Life of Pi")
                "Lincoln"
                "Skyfall"))


;; A
;; word-in-tree?: WordTree String -> Boolean
;; to determine if a given string is in a wordtree
;; example: see tests
(define (word-in-tree? awt s)
  (cond [(and (string? awt)
              (string=? awt s)) true]
        [(string? awt) false]
        [else (or (string=? s (node-word awt))
                  (word-in-tree? (node-left awt) s)
                  (word-in-tree? (node-right awt) s))]))

;; tests
(check-expect (word-in-tree? movies "Argo") true)
(check-expect (word-in-tree? holidays "Thanksgiving") true)
(check-expect (word-in-tree? movies "Swag") false)


;; B
;; word-in-tree?.bin: WordTree String -> Boolean
; to determine if a given string iis in a wordtree
;; example: see tests
(define (word-in-tree?.bin awt s)
  (cond [(and (string? awt)
              (string=? awt s)) true]
        [(string? awt) false]
        [(string<? s (node-word awt)) (word-in-tree? (node-left awt) s)]
        [(string=? s (node-word awt)) true]
        [else (word-in-tree? (node-right awt) s)]))

;; tests
(check-expect (word-in-tree?.bin movies "Argo") true)
(check-expect (word-in-tree?.bin holidays "Thanksgiving") true)
(check-expect (word-in-tree?.bin movies "Swag") false)
(check-expect (word-in-tree?.bin "swag" "swag") true)
(check-expect (word-in-tree?.bin "lolz" "swag") false)