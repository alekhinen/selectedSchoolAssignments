;; Nick Alekhine 
;; alekhine.n@husky.neu.edu

#| HOW TO RUN THE GAME:
- Download the Racket IDE - http://racket-lang.org/ 

- Open this file in the IDE and set the language to Intermediate Student
  Language With List Abbreviations

- Run and play


|#



;; -------------------------- Table of Contents --------------------------


;; Problem 3
;;  1. Libraries
;;  2. Constants
;;  3. Data Definitions
;;     3.1 player
;;     3.2 vehicle
;;     3.3 logg
;;     3.4 turtle
;;     3.5 moving-object
;;     3.6 LOMO (list-of-moving-objects)
;;     3.7 world
;;  4. Constants
;;  5. Functions
;;     5.1 Drawing
;;          5.1.1 draw-lomo
;;          5.1.2 draw-vehicle
;;          5.1.3 draw-logg
;;          5.1.4 draw-turtle
;;          5.1.5 draw-frog
;;          5.1.6 draw-world
;;     5.2 Movement
;;          5.2.1 posn-move
;;          5.2.2 in-range? 
;;          5.2.3 posn-adjuster
;;          5.2.4 move-lomo
;;          5.2.5 move-player
;;          5.2.6 move-when-on-turtle-logg
;;     5.3 Collision
;;          5.3.1 posn=? 
;;          5.3.2 posn-within?
;;          5.3.3 logg-collide?
;;          5.3.4 turtle-collide? 
;;          5.3.5 vehicles-collide?
;;          5.3.6 logg-turtle-collide?
;;          5.3.7 player-container
;;     5.4 Key Handling
;;          5.4.1 key-handler
;;     5.5 End Game States
;;          5.5.1 end-game-message
;;          5.5.2 at-end?
;;          5.5.3 game-over?
;;     5.6 Game Loop
;;          5.6.1 world-tick
;;          5.6.2 big-bang
;; Problem 4




;; PROBLEM 3 ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; 1 -------------------------- get libraries --------------------------

(require 2htdp/image)
(require 2htdp/universe)


;; 2 -------------------------- define constants --------------------------

;; All images copyright Martha L Hamlin March 2012
;; For educational non-commercial use only

;; Due to a bug, racket derived languages can't support traditional 
;; image libraries.  To work around this please copy the following definitions
;; into the top of your homework. 

;; Live frog image, 40x40 pixels 
(define frog-img .)


;; Runover frog image, 40x40 pixels
(define runover-frog-img .)


;; sunk frog or turtle image, 40x40 pixels
(define sunk-img .)


;; a turtle image, 40x40 pixels
(define turtle-img .)

;; a sinking turtle, 40x40 pixels
(define sinking-turtle-img .)


;; a single log unit image, 40x60 pixels
;; designed to stack to make longer logs
(define log-img .)

;; a double long log image, 40x100 pixels
(define log2-img (overlay/offset log-img 
                                 -40 0
                                 log-img))

;; a triple long log image, 40x140 pixels
(define log3-img (overlay/offset log2-img 
                                 -60 0
                                 log-img))

;; a four long log image, 40x180 pixels
(define log4-img (overlay/offset log2-img
                                 -80 0
                                 log2-img))



;; a truck image, 40x60 pixels
(define truck-img .)


;; a car image, 40x40 pixels
(define car-img .)



;;A simple background, 400x600 pixels
;; Y-pos 0-80 pixels Ground
;; Y-pos 80-280 pixels River
;;       Equal to five 40 pixel tall rows
;; Y-pos 280-320 pixels Ground
;; Y-pos 320-520 pixels Road
;;       Equal to five 40 pixel tall rows
;; Y-pos 520-600 pixels Ground

(define background-img 
  .)


;; frog size
(define FROG_WIDTH 18)
(define FROG_HEIGHT 18)

;; vehicle size
(define VEHICLE_WIDTH 20)
(define VEHICLE_HEIGHT 20)
(define LOGG_WIDTH 20)
(define LOGG_HEIGHT 20)
(define TURTLE_WIDTH 15)
(define TURTLE_HEIGHT 20)

;; movement
(define VEHICLE_MOVEMENT 5)
(define LOGG_MOVEMENT 5)
(define TURTLE_MOVEMENT 5)
(define PLAYER_MOVEMENT 40)


;; -------------------------- / define constants --------------------------

;; 3 -------------------------- data definitions --------------------------

;; CONSTAINTS
;; a direction (seen in player and vehicle) can only be one of
;; "up", "down", "left", "right"


;; 3.1
;; a player is a (make-player posn string)
(define-struct player (position direction))

;; player-temp: player -> ?
#;(define (player-temp p)
    ...(player-position p)...
    ...(player-direction p)...)


;; 3.2
;; a vehicle is a (make-vehicle posn string)
(define-struct vehicle (position direction))

;; vehicle-temp: vehicle -> ?
#;(define (vehicle-temp v)
    ...(vehicle-position v)...
    ...(vehicle-direction v)...)


;; 3.3
;; a log is a (make-log posn string number)
(define-struct logg (position direction size))
;; CONSTRAINT: size may only be one of: 1, 2, 3, 4

;; log-temp: log -> ?
#;(define (logg-temp l)
    ...(logg-position l)...
    ...(logg-direction l)...
    ...(logg-size l)...)


;; 3.4
;; a turtle is a (make-turtle posn string)
(define-struct turtle (position direction))

;; turtle-temp: t -> ?
#;(define (turtle-temp t)
    ...(turtle-direction t)...
    ...(turtle-position t)...)


;; 3.5
;; a moving-object is one of
;;   - vehicle
;;   - log
;;   - turtle

;; mo-temp: moving-object -> ?
#;(define (mo-temp m)
    (cond
      [(turtle? m)...]
      [(logg? m)...]
      [else ...]))


;; 3.6
;; a LOMO (list of moving objects) is one of
;;   - empty
;;   - (cons moving-object LOMO)

;; lomo-temp: LOMO -> ?
#;(define (lomo-temp alomo)
    (cond
      [(empty? alomo)...]
      [else ...(first alomo)...(lomo-temp (rest alomo))...]))


;; 3.7
;; a world is a (make-world player LOMO)
;; the LOMO represents the set of objects moving across the screen
(define-struct world (player objects))

;; world-temp: world -> ?
#;(define (world-temp w)
    ...(world-player w)...
    ...(world-objects w)...)

;; -------------------------- / data definitions --------------------------





;; 4 -------------------------- define constants --------------------------

(define P1 (make-player (make-posn 13 15) "left"))
(define P2 (make-player (make-posn 100 200) "up"))
(define P3 (make-player (make-posn 240 580) "up"))

(define V1 (make-vehicle (make-posn 50 50) "right"))
(define V2 (make-vehicle (make-posn 100 80) "left"))

;; water lane #1
(define WL1-1 (make-logg (make-posn 20 100) "right" 2))
(define WL1-2 (make-logg (make-posn 160 100) "right" 2))
(define WL1-3 (make-logg (make-posn 240 100) "right" 1))
(define WL1-4 (make-logg (make-posn 340 100) "right" 1))

;; water lane #2
(define WL2-1 (make-turtle (make-posn 40 140) "left"))
(define WL2-2 (make-turtle (make-posn 250 140) "left"))
(define WL2-3 (make-turtle (make-posn 320 140) "left"))
(define WL2-4 (make-turtle (make-posn 100 140) "left"))

;; water lane #3
(define WL3-1 (make-logg (make-posn 60 180) "right" 3))
(define WL3-2 (make-logg (make-posn 260 180) "right" 2))

;; water lane #4
(define WL4-1 (make-turtle (make-posn 10 220) "left"))
(define WL4-2 (make-turtle (make-posn 80 220) "left"))
(define WL4-3 (make-turtle (make-posn 160 220) "left"))
(define WL4-4 (make-turtle (make-posn 240 220) "left"))

;; water lane #5
(define WL5-1 (make-logg (make-posn 0 260) "right" 4))
(define WL5-2 (make-logg (make-posn 300 260) "right" 1))

;; road lane #1
(define VT1-1 (make-vehicle (make-posn 50 340) "right"))
(define VT1-2 (make-vehicle (make-posn 250 340) "right"))
(define VT1-3 (make-vehicle (make-posn 450 340) "right"))
(define VT1-4 (make-vehicle (make-posn 650 340) "right"))

;; road lane #2
(define VT2-1 (make-vehicle (make-posn 700 380) "left"))
(define VT2-2 (make-vehicle (make-posn 550 380) "left"))
(define VT2-3 (make-vehicle (make-posn 300 380) "left"))
(define VT2-4 (make-vehicle (make-posn 150 380) "left"))

;; road lane #3
(define VT3-1 (make-vehicle (make-posn 50 420) "right"))
(define VT3-2 (make-vehicle (make-posn 250 420) "right"))
(define VT3-3 (make-vehicle (make-posn 500 420) "right"))
(define VT3-4 (make-vehicle (make-posn 750 420) "right")) 

;; road lane #4
(define VB1-1 (make-vehicle (make-posn 50 460) "right"))
(define VB1-2 (make-vehicle (make-posn 120 460) "right"))
(define VB1-3 (make-vehicle (make-posn 380 460) "right"))
(define VB1-4 (make-vehicle (make-posn 600 460) "right"))

;; road lane #5
(define VB2-1 (make-vehicle (make-posn 300 500) "left"))
(define VB2-2 (make-vehicle (make-posn 200 500) "left"))
(define VB2-3 (make-vehicle (make-posn 100 500) "left"))


(define VS1 (list V1 V2))
(define VS2 (list WL1-1 WL1-2       WL1-4
                  WL2-1 WL2-2 WL2-3 WL2-4
                  WL3-1 WL3-2
                  WL4-1 WL4-2 WL4-3 WL4-4
                  WL5-1 WL5-2
                  VT1-1 VT1-2 VT1-3 VT1-4
                  VT2-1 VT2-2 VT2-3 VT2-4
                  VT3-1 VT3-2 VT3-3 VT3-4
                  VB1-1 VB1-2 VB1-3 VB1-4
                  VB2-1 VB2-2 VB2-3))

(define W1 (make-world P1 VS1)) 
(define W2 (make-world P3 VS2))

;; -------------------------- / define constants --------------------------






;; 5 -------------------------- function definitions --------------------------






;; 5.1 ---------- drawing functions ----------

;; 5.1.1 
;; draw-lomo: LOMO image -> image
;; to draw a list-of-moving-objects onto a scene
;; example: see tests
(define (draw-lomo alomo scn)
  (local [(define (draw-moving-object mo ascn)
            (cond
              [(vehicle? mo) (draw-vehicle mo ascn)]
              [(logg? mo) (draw-logg mo ascn)]
              [else (draw-turtle mo ascn)]))]
    (foldr draw-moving-object scn alomo)))

;; tests
(check-expect (draw-lomo VS1 background-img) (place-image car-img
                                                          50 50
                                                          (place-image truck-img
                                                                       100 80
                                                                       background-img)))
(check-expect (draw-lomo empty background-img) background-img)


;; 5.1.2
;; draw-vehicle: vehicle image -> image
;; to draw a given vehicle onto a scene
;; example: see tests
(define (draw-vehicle v scn)
  (cond 
    [(string=? "left" (vehicle-direction v)) 
     (place-image truck-img
                  (posn-x (vehicle-position v))
                  (posn-y (vehicle-position v))
                  scn)]
    [else 
     (place-image car-img
                  (posn-x (vehicle-position v))
                  (posn-y (vehicle-position v))
                  scn)]))

;; tests
(check-expect (draw-vehicle V1 background-img) (place-image car-img
                                                            50 50
                                                            background-img))
(check-expect (draw-vehicle V2 background-img) (place-image truck-img
                                                            100 80
                                                            background-img))


;; 5.1.3
;; draw-logg: logg image -> image
;; to draw a given logg onto a scene
;; example: see tests
(define (draw-logg l scn)
  (local [(define (log-sizer lsize)
            (cond
              [(= 1 lsize) log-img]
              [(= 2 lsize) log2-img]
              [(= 3 lsize) log3-img]
              [else log4-img]))]
    (place-image (log-sizer (logg-size l))
                 (posn-x (logg-position l))
                 (posn-y (logg-position l))
                 scn)))

;; tests
(check-expect (draw-logg (make-logg (make-posn 100 100)
                                    "right"
                                    3)
                         background-img)
              (place-image log3-img
                           100 100
                           background-img))

;; 5.1.4
;; draw-turtle: turtle image -> image
;; to draw a given turtle onto a scene
(define (draw-turtle t scn)
  (local [(define (turtle-dir td)
            (cond 
              [(string=? "right" td) (rotate 270 turtle-img)]
              [(string=? "left" td) (rotate 90 turtle-img)]
              [(string=? "down" td) (rotate 180 turtle-img)]
              [else turtle-img]))]
    (place-image (turtle-dir (turtle-direction t))
                 (posn-x (turtle-position t))
                 (posn-y (turtle-position t))
                 scn)))

;; tests 
(check-expect (draw-turtle (make-turtle (make-posn 100 100)
                                        "left")
                           background-img)
              (place-image (rotate 90 turtle-img)
                           100 100
                           background-img))
(check-expect (draw-turtle (make-turtle (make-posn 100 100)
                                        "right")
                           background-img)
              (place-image (rotate -90 turtle-img)
                           100 100
                           background-img))
(check-expect (draw-turtle (make-turtle (make-posn 100 100)
                                        "down")
                           background-img)
              (place-image (rotate 180 turtle-img)
                           100 100
                           background-img))
(check-expect (draw-turtle (make-turtle (make-posn 100 100)
                                        "up")
                           background-img)
              (place-image turtle-img
                           100 100
                           background-img))

;; 5.1.5
;; draw-frog: player -> image
;; to draw the correct frog dependent on the player direction
;; example: see tests
(define (draw-frog p)
  (cond
    [(string=? (player-direction p) "left") (rotate 90 frog-img)]
    [(string=? (player-direction p) "right") (rotate -90 frog-img)]
    [(string=? (player-direction p) "down") (rotate 180 frog-img)]
    [else frog-img])) 

;; tests
(check-expect (draw-frog (make-player (make-posn 180 200)
                                      "up")) 
              frog-img)
(check-expect (draw-frog (make-player (make-posn 200 200)
                                      "right")) 
              (rotate -90 frog-img))
(check-expect (draw-frog (make-player (make-posn 200 200)
                                      "down"))
              (rotate 180 frog-img))


;; 5.1.6
;; draw-world: world -> image
;; to draw the world onto the scene
(define (draw-world w)
  (place-image (draw-frog (world-player w))
               (posn-x (player-position (world-player w)))
               (posn-y (player-position (world-player w)))
               (draw-lomo (world-objects w) background-img)))

;; ---------- / drawing functions ----------






;; 5.2 ---------- movement functions ----------

;; 5.2.1
;; posn-move: posn string number -> posn
;; to move a posn in the given direction, dir, by the given amount, n
;; example: see tests
(define (posn-move p dir n)
  (cond
    [(string=? dir "left")
     (make-posn (- (posn-x p) n)
                (posn-y p))]
    [(string=? dir "right")
     (make-posn (+ (posn-x p) n)
                (posn-y p))]
    [(string=? dir "up")
     (make-posn (posn-x p)
                (- (posn-y p) n))]
    [(string=? dir "down")
     (make-posn (posn-x p) 
                (+ (posn-y p) n))]))

;; tests
(check-expect (posn-move (make-posn 50 100) "left" 30)
              (make-posn 20 100)) 


;; 5.2.2
;; in-range?: posn number number number number -> boolean
;; to determine whether a given posn is within a range
;; given by numbers
;; example: see tests
(define (in-range? p x-upper x-lower y-upper y-lower)
  (and (>= x-upper (posn-x p) x-lower)
       (>= y-upper (posn-y p) y-lower)))

;; tests
(check-expect (in-range? (make-posn 40 40) 80 20 100 30) true)
(check-expect (in-range? (make-posn 100 80) 140 110 90 60) false)
(check-expect (in-range? (make-posn 200 40) 220 180 140 90) false)
(check-expect (in-range? (make-posn 140 140) 200 140 140 100) true)


;; 5.2.3
;; posn-adjuster: posn string number -> posn
;; to change the position based on direction and movement
;; example: see tests
(define (posn-adjuster p dir movement)
  (cond
    [(not (in-range? (posn-move p dir movement) 450 -50 520 60))
     (cond 
       [(string=? "left" dir) (make-posn 450 (posn-y p))]
       [(string=? "right" dir) (make-posn -50 (posn-y p))]
       [(string=? "up" dir) (make-posn (posn-x p) 520)]
       [(string=? "down" dir) (make-posn (posn-x p) 80)])]
    [else (posn-move p dir movement)]))

;; tests
(check-expect (posn-adjuster (make-posn 100 100) "up" 10)
              (make-posn 100 90))
(check-expect (posn-adjuster (make-posn 100 100) "down" 20)
              (make-posn 100 120))
(check-expect (posn-adjuster (make-posn 100 100) "left" 5)
              (make-posn 95 100))
(check-expect (posn-adjuster (make-posn 100 100) "right" 10)
              (make-posn 110 100))
(check-expect (posn-adjuster (make-posn 100 530) "up" 10)
              (make-posn 100 520))
(check-expect (posn-adjuster (make-posn 100 30) "down" 15)
              (make-posn 100 80))



;; 5.2.4
;; move-lomo: LOMO -> LOMO
;; to move a given set of moving-objects in the directions given
;; example: see tests
(define (move-lomo alomo)
  (local [(define (move-moving-object mo)
            (cond
              [(vehicle? mo) 
               (make-vehicle (posn-adjuster (vehicle-position mo)
                                            (vehicle-direction mo)
                                            VEHICLE_MOVEMENT)
                             (vehicle-direction mo))]
              [(logg? mo) 
               (make-logg (posn-adjuster (logg-position mo)
                                         (logg-direction mo)
                                         LOGG_MOVEMENT)
                          (logg-direction mo)
                          (logg-size mo))]
              [else 
               (make-turtle (posn-adjuster (turtle-position mo)
                                           (turtle-direction mo)
                                           TURTLE_MOVEMENT)
                            (turtle-direction mo))]))]
    (map move-moving-object alomo)))

;; tests
(check-expect (move-lomo VS1)
              (cons (make-vehicle (make-posn -50 50) "right")
                    (cons (make-vehicle (make-posn 95 80) "left") empty)))

;; 5.2.5
;; move-player: player -> player
;; to move a player in the direction given in the player
;; example: see tests
(define (move-player p)
  (make-player (posn-move (player-position p)
                          (player-direction p)
                          PLAYER_MOVEMENT)
               (player-direction p)))

;; tests
(check-expect (move-player P2) (make-player (make-posn 100 
                                                       (- 200 PLAYER_MOVEMENT))
                                            "up"))


;; 5.2.6
;; move-when-on-turtle-logg: player LOMO -> player
;; to move player when on a turtle or logg
;; example: see tests
(define (move-when-on-turtle-logg p alomo)
  (cond
    [(empty? alomo) p]
    [(and (turtle? (first alomo))
          (turtle-collide? p (first alomo)))
     (make-player (posn-move (player-position p)
                             (turtle-direction (first alomo))
                             TURTLE_MOVEMENT)
                  (player-direction p))]
    [(and (logg? (first alomo))
          (logg-collide? p (first alomo)))
     (make-player (posn-move (player-position p)
                             (logg-direction (first alomo))
                             LOGG_MOVEMENT)
                  (player-direction p))]
    [else (move-when-on-turtle-logg p (rest alomo))]))

;; tests
(check-expect (move-when-on-turtle-logg P1 VS2) P1)
(check-expect (move-when-on-turtle-logg (make-player (make-posn 20 260)
                                                     "right")
                                        VS2)
              (make-player (posn-move (make-posn 20 260)
                                      "right"
                                      LOGG_MOVEMENT)
                           "right"))
(check-expect (move-when-on-turtle-logg (make-player (make-posn 70 220)
                                                     "up")
                                        VS2)
              (make-player (posn-move (make-posn 70 220)
                                      "left"
                                      TURTLE_MOVEMENT)
                           "up"))


;; ---------- / movement functions ----------






;; 5.3 ---------- collision detection ----------

;; 5.3.1
;; posn=?: posn posn -> boolean
;; to determine if two posns are the same
;; example: see tests
(define (posn=? a b)
  (and (= (posn-x a) (posn-x b))
       (= (posn-y a) (posn-y b))))

;; tests
(check-expect (posn=? (make-posn 50 100) (make-posn 50 100)) true)
(check-expect (posn=? (make-posn 72 20) (make-posn 70 50)) false)



;; 5.3.2
;; posn-within?: posn posn number number -> boolean
;; to determine whether two posns are within each other
;; example: see tests 
(define (posn-within? a b width height)
  ;; check the center of the frog
  (in-range? a
             (+ (posn-x b) width)
             (- (posn-x b) width)
             (+ (posn-y b) height)
             (- (posn-y b) height)))

;; tests
(check-expect (posn-within? (make-posn 50 50) 
                            (make-posn 50 50)  
                            VEHICLE_WIDTH
                            VEHICLE_HEIGHT) true)
(check-expect (posn-within? (make-posn 60 5) 
                            (make-posn 40 80)
                            VEHICLE_WIDTH
                            VEHICLE_HEIGHT) false)


;; 5.3.3
;; logg-collide?: player logg -> boolean
;; to determine whether a player is ontop of a logg
;; example: see tests
(define (logg-collide? p l)
  (posn-within? (player-position p)
                (logg-position l)
                (* LOGG_WIDTH (logg-size l))
                LOGG_HEIGHT))

;; tests
(check-expect (logg-collide? (make-player (make-posn 40 20) "up")
                             (make-logg (make-posn 80 20) "left" 4))
              true)
(check-expect (logg-collide? (make-player (make-posn 220 260)
                                          "up")
                             (make-logg (make-posn 140 260)
                                        "left" 4))
              true)

;; 5.3.4
;; turtle-collide? player turtle -> boolean
;; to determine whether a player is ontop of a turtle
;; example: see tests
(define (turtle-collide? p t)
  (posn-within? (player-position p)
                (turtle-position t)
                (* 2 TURTLE_WIDTH)
                TURTLE_HEIGHT))

;; tests
(check-expect (turtle-collide? 
               (make-player (make-posn 80 220) "left")
               WL4-2)
              true)
(check-expect (turtle-collide? 
               P2
               WL4-4)
              false)


;; 5.3.5
;; vehicles-collide?: player LOMO -> boolean
;; to determine whether a player has hit any car in a given set
;; example: see tests
(define (vehicles-collide? p alomo)
  (ormap (lambda (x) (and (vehicle? x) 
                          (posn-within? (player-position p)
                                        (vehicle-position x)
                                        VEHICLE_WIDTH
                                        VEHICLE_HEIGHT))) 
         alomo))

;; tests
(check-expect (vehicles-collide? (make-player (make-posn 50 50)
                                              "left")  
                                 VS1)
              true)
(check-expect (vehicles-collide? (make-player (make-posn 200 500)
                                              "left")
                                 VS2)
              true)


;; 5.3.6
;; logg-turtle-collide?: player LOMO -> boolean
;; to determine whether a player has collided with a logg or turtle
;; example: see tests
(define (logg-turtle-collide? p alomo)
  (local [(define (lt-collide mo)
            (or (and (turtle? mo)
                     (turtle-collide? p mo))
                (and (logg? mo)
                     (logg-collide? p mo))))]
    (ormap lt-collide alomo)))

;; tests
(check-expect (logg-turtle-collide? 
               (make-player (make-posn 20 100) "left") VS2)
              true)

;; 5.3.7
;; player-container: player key -> player
;; to contain the player within the scene box
;; example: see tests
(define (player-container p k)
  (cond
    [(not (in-range? (player-position 
                      (move-player 
                       (make-player (player-position p) k)))
                     400 0 600 0))
     (make-player (player-position p)
                  (player-direction p))]
    [else (move-player (make-player (player-position p)
                                    k))]))

;; tests
(check-expect (player-container (make-player (make-posn 750 200)
                                             "left")
                                "right")
              (make-player (make-posn 750 200)
                           "left"))
(check-expect (player-container (make-player (make-posn 300 150)
                                             "right")
                                "left")
              (make-player (make-posn (- 300 PLAYER_MOVEMENT) 150)
                           "left"))


;; ---------- / collision detection ----------






;; 5.4 ---------- key handling ----------

;; 5.4.1
;; key-handler: world key -> world
;; change the direction of the player
;; example: see tests
(define (key-handler w k)
  (cond
    [(or (key=? "up" k)
         (key=? "down" k)
         (key=? "left" k)
         (key=? "right" k))
     (make-world (player-container (world-player w) k)
                 (world-objects w))] 
    [else w]))

;; tests
(check-expect (key-handler W1 "down") 
              (make-world (make-player (make-posn 13 55) "down")
                          VS1))
(check-expect (key-handler (make-world (make-player (make-posn 100 100)
                                                    "left")
                                       VS1)
                           "right")
              (make-world (make-player (make-posn 140 100)
                                       "right")
                          VS1))

;; ---------- / key handling ----------

;; 5.5 ---------- end game states ----------

;; 5.5.1
;; end-game-message: w -> image
;; to display win message
;; example: see tests
(define (end-game-message w)
  (cond
    [(at-end? (world-player w))
     (overlay (overlay (text "You win" 20 'black)
                       (rectangle 150 20 'solid 'white))
              (draw-world w))]
    [(vehicles-collide? (world-player w) (world-objects w))
     (overlay (overlay (text "You lose" 20 'black)
                       (rectangle 150 20 'solid 'white))
              (place-image runover-frog-img
                           (posn-x (player-position (world-player w)))
                           (posn-y (player-position (world-player w)))
                           (draw-world w)))] 
    [else 
     (overlay (overlay (text "You lose" 20 'black)
                       (rectangle 150 20 'solid 'white))
              (place-image sunk-img
                           (posn-x (player-position (world-player w)))
                           (posn-y (player-position (world-player w)))
                           (draw-world w)))]))

;; tests
(check-expect (end-game-message W1) (overlay (overlay (text "You win" 20 'black)
                                                      (rectangle 150 20 'solid 'white))
                                             (draw-world W1)))
(check-expect (end-game-message W2) (overlay (overlay (text "You lose" 20 'black)
                                                      (rectangle 150 20 'solid 'white))
                                             (place-image sunk-img 
                                                          240 580
                                                          (draw-world W2))))


;; 5.5.2
;; at-end?: player -> boolean
;; to determine whether the player has reached the other side
;; example: see tests
(define (at-end? p)
  (<= (posn-y (player-position p)) 30))

;; tests
(check-expect (at-end? P1) true)
(check-expect (at-end? P2) false)


;; 5.5.3
;; game-over?: world -> boolean
;; determines if the game is over if a collision occurs
;; example: see tests for at-end? and car-collide? 
(define (game-over? w)
  (or (vehicles-collide? (world-player w) (world-objects w))
      (and (and (> 280 (posn-y (player-position (world-player w))))
                (< 80 (posn-y (player-position (world-player w))))) 
           (not (logg-turtle-collide? (world-player w) (world-objects w))))
      (at-end? (world-player w)))) 


;; ---------- / end game states ----------







;; 5.6 ---------- game loop ----------

;; 5.6.1
;; world-tick: world -> world
;; move the player around the scene
(define (world-tick w) 
  (make-world (move-when-on-turtle-logg (world-player w)
                                        (world-objects w))
              (move-lomo (world-objects w))))


;; 5.6.2
(big-bang W2
          (on-tick world-tick)
          (to-draw draw-world)
          (on-key key-handler)
          (stop-when game-over? end-game-message))

;; ---------- / game loop ----------






