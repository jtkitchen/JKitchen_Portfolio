from graphics import *
import itertools



class Walls:

    def __init__( self, x1, y1, x2, y2 ):
        
        self.rect = [
            x1, y1, x2, y2
                    ]
        self.rectangles = [Rectangle(Point(x1,y1),Point(x2, y2) )]
        self.speed = 10
        print(self.rect)
        
        ## Collisions not working with new design
    def hit( self, center, r ):
        x = center.getX()
        y = center.getY()
        i = self.rect
        x1 = i[0]
        y1 = i[1]
        x2 = i[2]
        y2 = i[3]
        hit = ""
        if x1 <= (x + r) and (x - r) <= x2 and y1 <= (y + r) and (y - r) <= y2:
            return "hit"
        

                
    def movePaddle(self, win, paddle, speed, key ):        
        if key == 'w':
            paddle.move(0,-speed)
            self.rect[1] -= speed
            self.rect[3] -= speed
            
        elif key == 's':
            left.move(0,speed)
            self.rect[1] += speed
            self.rect[3] += speed
   

    def movePaddleMouse( self, win, paddle, pt ):
        c = paddle.getCenter()
        y = c.getY() + 50
        newY = pt - y
        paddle.move(0, newY )
        self.rect[1] += newY
        self.rect[3] += newY
        

    def getPaddle( self ):
        return self.rectangles[0]

    
    def draw(self, win):
        
        for i in self.rectangles:
            i.setFill("RED")
            i.draw(win)

    def undraw( self, win ):
        self.rectangles[0].undraw()



        

    
    
