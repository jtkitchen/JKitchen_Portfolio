from graphics import *
import itertools



class Walls:

    
    def __init__( self, inFile ):
        
        inFile = open( inFile )
        self.rect = []
        
        for line in inFile:
            data = line[0:-1]
            things = data.split(" ")
            self.rect.append(things)
            
        print(self.rect)
        
    def hit( self, center, r ):
        x = center.getX()
        y = center.getY()
        for i in self.rect:
            hit = ""
            
            if (int(i[0]) <= (x + r) and (x - r) <= int(i[2]) and
                int(i[1]) <= (y + r) and (y - r) <= int(i[3])    ):
                
                if (x+r) == int(i[0]) or (x-r) == int(i[2]):
                    
                    return "dx"
                else:
                    return "dy"
                
                    
    def draw(self, win):
        for i in self.rect:
            rectangle = Rectangle(Point( i[0], i[1]), Point( i[2], i[3] ) )
            rectangle.setFill("RED")
            print(rectangle)
            rectangle.draw(win)

        
##file = "points.txt"
##getLayout(file)


        

    
    
