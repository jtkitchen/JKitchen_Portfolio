# bouncing_ball.py
# A program that draws a ball bouncing around inside a window.  When the ball
#   reaches the side of the window it bounces back again.
# Graphics and basic code by RGW, 2018
# Code to bounce ball off of the walls by Joshua Kitchen 

from graphics import *
from walls import *
import random
from time import sleep
import tkinter as tk 

    
## Really laggy
def getMousePos( win ):
    
    root = tk.Tk
    return root.winfo_pointerxy(win)

def resetGame( win, RADIUS, WIDTH, HEIGHT ):
    # Create the ball in the upper-left of the window
    ball = Circle(Point(RADIUS,RADIUS),RADIUS)
    ball.setWidth(0)
    ball.setFill('WHITE')
    ball.draw( win )

    return ball

def quitGame( win ):
    win.close()

def addScore( win, score, text ):
    text.setText( score )
    
    
def main( ):
    file = "points.txt"
    
    # Create the window for the bouncing ball
        
    RADIUS = 12
    WIDTH = 1000
    HEIGHT = 600
    win = GraphWin("Bouncing Ball", WIDTH, HEIGHT )
    ball = resetGame(win, RADIUS, WIDTH, HEIGHT)

    # Set scoreboard
    scoreboard = Text(Point(WIDTH/2, 20), "0")
    scoreboard.setSize(20)
    scoreboard.draw(win)
    score = 0
    
    win.setBackground('GREEN')
    x, y = getMousePos( win )
    y = y
    walls = Walls(10, 10, WIDTH/25, HEIGHT/5 )

    walls.draw(win)
    
    
    # Set the initial direction, down and to the right
    dx = 4
    dy = 3
    
    #print( xPoints )
    # move the ball
    while True:
        ball.move(dx,dy)
        center = ball.getCenter()
        paddle = walls.getPaddle()
        key = win.checkKey()
        if key == 'q':
            break
        mPosX, mPosY = getMousePos( win )
        
        walls.movePaddleMouse( win, paddle, mPosY )
        
        
        x = center.getX()
        y = center.getY()
        if win.checkKey() == "q":
            quitGame( win )

        if x > WIDTH - RADIUS:
            dx = -dx
            #print("RIGHT")
            
        elif x < RADIUS:
            dx = -dx
            print("LEFT")
            text = Text( Point(WIDTH/2, HEIGHT/2), "Game Over" )
            text.setSize( 20 )
            text.draw( win )
            infoText = Text( Point(WIDTH/2, HEIGHT/2 + 50), "q to quit, r to reset")
            infoText.draw(win)
            score = 0
            
            option = win.getKey()
            
            if option == "q":
                quitGame( win )

            elif option == "r":
                win.close()
                main()
            

        elif y < RADIUS:
            dy = -dy
            #print("UP")
        
        elif y > HEIGHT - RADIUS:
            dy = -dy
            #print("DOWN")
            
        elif walls.hit( center, RADIUS + 5 ) == "hit":
            dx = -(dx * 1.1 )
            score = score + 1
            addScore(win, score, scoreboard)
            
        elif win.checkMouse():
            win.getMouse()
##            mx, my = getMousePos( win )
##            walls1 = Walls(10, my-40, 40, my+40)
##            walls.undraw( win )
##            walls1.draw( win )
##            walls = walls1
        
        sleep(0.005)
        

    
main()
