
int edgeFunction( int px, int py, int v1_x, int v1_y, int v2_x, int v2_y ) {
  int sign = 0;
  sign = (px - v1_x) * (v2_y - v1_y) - (py - v1_y) * (v2_x - v1_x);

  return sign;
}

boolean isTopLeft( int x0, int y0, int x1, int y1, int x2, int y2 ) {
  boolean isTopLeft = false;
  float dx = x1 - x0;
  float dy = y1 - y0;
  float slope;
  if( dx != 0 ) {
    slope = dy/dx;
  } else {
    slope = 0;
  }
  
  if( slope != 0 ) {
    //println(edgeFunction(x2, y2, x0, y0, x1, y1));
    if( y1 < y0 ) {
       isTopLeft = true; 
    }
  } else if( slope == 0 && x0 == x1 ) {
    //println(edgeFunction(x2, y2, x0, y0, x1, y1));
    if( y1 < y0 ) {
        isTopLeft = true;
    }
  } else if( slope == 0 && y0 == y1 ) {
    //println(edgeFunction(x2, y2, x0, y0, x1, y1));
    if( x1 > x0 ) {
        isTopLeft = true;
    }
  } else {
    isTopLeft = false;
    println("You shouldn't be here...");
  }
  return isTopLeft;
}


void myColorTriangle (int x0, int y0, int r0, int g0, int b0, 
                      int x1, int y1, int r1, int g1, int b1,
                      int x2, int y2, int r2, int g2, int b2)
{
  // insert your code here to draw a triangle with vertices (x0, y0), (x1, y1) and (x2, y2) 
  // with colors (r0, g0, b0), (r1, g1, b1) and (r2, g2, b2) attached to each vertex respectively.
  //
  // Your implementation should interpolate the colors accross the triangle.
  //
  // Only use calls to the function drawColorPoint() which is below the do not edit line
  // This function has the following signature
  
  // your code should be an extension of the myTrangle function from Assignment 2.
  
    //arranges lines so that they are "clockwise" in reference
  if( edgeFunction(x2, y2, x0, y0, x1, y1) < 0 ) {
      int tempx = x1;
      int tempy = y1;
      x1 = x2;
      y1 = y2;
      x2 = tempx;
      y2 = tempy;
  }
  
  int bias1 = isTopLeft(x0, y0, x1, y1, x2, y2) ? 0 : -1; 
  int bias2 = isTopLeft(x1, y1, x2, y2, x0, y0) ? 0 : -1;
  int bias3 = isTopLeft(x2, y2, x0, y0, x1, y1) ? 0 : -1;

  int v1;
  int v2;
  int v3;
  float bary0, bary1, bary2;
  int br, bg, bb; //barycentric colors
  
  for( int i = min(x0, x1, x2); i <= max(x0, x1, x2); i++ ) {
    for( int j = min(y0, y1, y2); j <= max(y0, y1, y2); j++) {
      v1 = edgeFunction( i, j, x0, y0, x1, y1 ) + bias1;
      v2 = edgeFunction( i, j, x1, y1, x2, y2 ) + bias2;
      v3 = edgeFunction( i, j, x2, y2, x0, y0 ) + bias3;
      if(  v1 >= 0  &&
           v2 >= 0  &&
           v3 >= 0 ) {
        float subArea01 = .5 * v1;
        float subArea12 = .5 * v2;
        float subArea02 = .5 * v3;
        float totalArea = subArea01 + subArea12 + subArea02;
        bary0 = subArea12 / totalArea;
        bary1 = subArea02 / totalArea;
        bary2 = subArea01 / totalArea;
        br = (int)(bary0 * r0 + bary1 * r1 + bary2 * r2);
        bg = (int)(bary0 * g0 + bary1 * g1 + bary2 * g2);
        bb = (int)(bary0 * b0 + bary1 * b1 + bary2 * b2);
        drawColorPoint(i, j, br, bg, bb);
        //point( i, j );
      }
    }
  }
}


PMatrix2D transformTheHouse( )
{
  // return a matrix that has all of the transformations of the highest level you reached in the 
  // transformation game of last week's online assignment
  //
  //***I did not do level 13 as that was the highest that I got stumped on, not the highest I completed...***
  
  //Level 12: Rotate 90, Transform X: +80, Transform Y: +60 (combining the X and Y into one matrix call).
  //          Rotated -90 to deal with the different rotation defaults, and negated Y to deal with the different Y-axis
  
  int deg = -90;
  float rad = (deg * PI )/180;
  // start with the identity matrix
  PMatrix2D retval = new PMatrix2D();
  
  PMatrix2D translationTransform = new PMatrix2D( 1, 0, 80, 0, 1, -60 );
  PMatrix2D rotationTransform = new PMatrix2D( cos( rad ), -sin(rad), 0, sin(rad), cos(rad), 0);
  
  PMatrix2D combinedTranslationMatrix = new PMatrix2D();
  combinedTranslationMatrix.preApply(rotationTransform);
  combinedTranslationMatrix.preApply(translationTransform);
  
  //Apply the translation matrix to the identity
  retval.preApply( combinedTranslationMatrix );
  // Add your transformations here....remember you must preMultiply
  // Also recall, in Processing +y is down (in transformation game +y is up)
  // in processing: +rotation is clockwise (and in radians)....in transformation game +rotation is counter-clockwise (and in degrees).
  
  // return the result
  return retval;
}

// --------------------------------------------------------------------------------------------
//
//  Do not edit below this lne
//
// --------------------------------------------------------------------------------------------

boolean doMine = true;
int scene = 1;
color backgroundColor = color (150, 150, 150);

void setup () 
{
  size (500, 500);
  background (backgroundColor);
}

void draw ()
{
  if (scene == 1) doHouse();
  if (scene == 2) doTriangle();
}

//
// fills in the pixel (x, y) with the color (r,g,b)
//
void drawColorPoint (int x, int y, int r, int g, int b)
{
  stroke (r, g, b);
  point (x,y);
}

void doHouse()
{
  
  stroke (0,0,0);
  line (0, 250, 500, 250);
  line (250, 0, 250, 500);
  
  PMatrix2D trn = new PMatrix2D(); //<>//
  trn.translate (250, 250);
  trn.apply (transformTheHouse( ));

  applyMatrix (trn);
  
  fill (255, 0, 0);
  stroke (255,0,0);
  triangle (-25, 25, 25, -25, -25, -25);
  triangle (25, 25, 25, -25, -25, 25);
  
  fill (0, 255, 0);
  stroke (0,255,0);
  triangle (-25,-25, 25, -25, 0, -50);
  
  stroke (0,0,255);
  fill (0,0,255);
 triangle (10, 0, 10, 25, 20, 25);
 triangle (10, 0, 20, 25, 20, 0);
}

void doTriangle ()
{
  myColorTriangle (300, 400, 0, 0, 255,
                   400, 100, 0, 255, 0,
                   50, 50, 255, 0, 0);
}

void keyPressed()
{
  if (key == '1') 
  {
    background (backgroundColor);
    scene = 1;
  }
  
  if (key == '2') 
  {
    background (backgroundColor);
    scene = 2;
  }

  
  if (key == 'q') exit();
}
