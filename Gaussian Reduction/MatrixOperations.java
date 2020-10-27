import java.util.ArrayList;


public class MatrixOperations

{ 
   double[][] matrix;
   int rows;
   int columns;
   double[][] savedMatrix;
   double[][] temp;
   int steps;
   
   public MatrixOperations( double[][] _matrix, double[][] _savedMatrix, int _rows, int _columns )
   {
      rows = _rows;
      columns = _columns;
      matrix = _matrix;
      savedMatrix = _savedMatrix;
   }
   
   //resets the matrix to the original matrix (only work once for some reason...)
   public void resetMatrix()
   {
      matrix = savedMatrix;
   }
   // swaps two rows of the matrix
   public void swapRows( int row1, int row2 )
   {
      for( int i=0; i<columns; i++)
      {
         double[][] temp = new double[rows][columns];
         temp[0][i] = matrix[row1-1][i];
         matrix[row1-1][i] = matrix[row2-1][i];
         matrix[row2-1][i] = temp[0][i];
      }
   }
   
   // adds row2 to row1, with the sum remaining in row1.
   public void addRows( int row1, int row2 )
   {
      for( int i=0; i<columns; i++)
      {
         matrix[row1-1][i] += matrix[row2-1][i];
      }      
   }
   
   // subtracts row2 from row1 and replaces row1 with the answer.
   public void subtractRows( int row1, int row2 )
   {
      for( int i=0; i<columns; i++)
      {       
         matrix[row1-1][i] -= matrix[row2-1][i];
      }
   }
   
   //multiplies a row by a specified number.
   public void multiplyRow( int row, double number )
   {
      for( int i=0; i<columns; i++)
      {       
         matrix[row-1][i] *= number;
      } 
   }
   
   //returns the number at a certain point of the matrix
   public double getNumber( int row, int column )
   {
      return matrix[row-1][column-1];
   }
   
   //Simplifies 
   public int simplifyRow( int row )
   {
      boolean noRemainder = false;
      int count = 2;
      
      while( count <= 10 )
      {
         for( int i=0; i < columns; i++ )
         {
            if( matrix[row-1][i] % count == 0 ) { noRemainder = true; }
            else{ noRemainder = false; break; }
         }
         if( noRemainder == true )
         {
            for( int i=0; i < columns; i++ )
            {
               matrix[row-1][i] /= count; 
            }
            count--;
         }
         count++;
      }
      return 0;
   }
   
   public void simplify()
   {
      for(int i = 1; i <= rows; i++ ) { simplifyRow(i); }
   }
   
   //attempts to solve for each variable, though with no preference for "nice" numbers, merely in sequence.
   
   public void solve()
   {  
      //simplifies each of the rows for nicer final numbers
      simplify();
      printArrays();
      double toZero;
      double compared;
      int count = 2;
      int escape = 0;   
      steps = 0;
      //loop that clears the "bottom half" of the echelon triangle.
      for( int i = 1; i < columns; i++)
      {   
         for( int j = count; j < columns; j++ )
         {  
            steps += 1;
            //number meant to be simplify to 0
            toZero = getNumber( j, i);
            //reference, "final triangle" number to simplify it with
            compared = getNumber( i, i);
            //loop that simplifies and returns the original row back to normal
            if( toZero != 0 && compared != 0 )
            {
               multiplyRow( i, -toZero/compared );
               addRows( j, i );
               multiplyRow( i, -1/(toZero/compared));
            }
            else if( compared == 0 )
            {
               swapRows( i, j );
               j--;
               escape++;
               if( escape > 0 ){ break; }
            }
         } 
         count++;
            
      }
        
      //solves the "top half" of the triangle using the same method as the first loop, just starting on the other end.
      
      //retrieves the bottom right, second from the bottom and iterates backwards to eliminate the upper triangle to 0's
      for( int i = columns-1; i > 1; i--)
      {
         count = columns-2; 
         for( int j = count; j > 0; j-- )
         {  
            steps+= 1;
            if(j!=i)
            {
               
               toZero = getNumber( j, i);
               compared = getNumber( i, i);
               if(compared != 0 && toZero != 0)
               {
                  multiplyRow( i, -toZero/compared );
                  addRows( j, i );
                  multiplyRow( i, -1/(toZero/compared));
               }
//                else if( compared == 0 )
//                {
//                   addRows( i, j );
//                   j++;
//                }
               
            }
            count++;
         }  
      }       
   }
  
  //prints the matrix in such a way as it resembles a system of equations.
  //printed as integers to avoid clutter, MISLEADING RESULTS!!
   public void printArrays()
   {
       for( int i=0; i<rows; i++)
       {
         for(int j=0; j<columns; j++)
         {
            if ( j == columns - 1 ) {System.out.print( "= " ); }
            
            System.out.printf( "%.0f", matrix[i][j]);
            
            if ( j != columns - 1 )
            {
               System.out.print((char)(j+97));
               System.out.print(" ");
            }            
         }
       System.out.println("\n");
       }
       System.out.println( "This took " + steps + " steps." );
   }
   
   
   public void printSolution()
   {
       for( int i=0; i<rows; i++)
       {
         for(int j=0; j<columns; j++)
         {
            if ( j == columns - 1 ) {System.out.print( "= " ); }
            
            System.out.printf( "%.5f", matrix[i][j]);
            
            if ( j != columns - 1 )
            {
               System.out.print((char)(j+97));
               System.out.print(" ");
            }            
         }
       System.out.println("\n");
       }
       
       for( int i=0; i<rows; i++)
       {
         for(int j=0; j<columns; j++)
         {
            if( i == j)
            {
               double a =  matrix[i][columns-1];
               double b = matrix[i][j];
               double answer;
               if( b != 0){ answer =  a / b; }
               else {answer = 0; }
               System.out.print((char)(j+97));
               System.out.print(" = ");
               System.out.printf( "%.2f", answer);
               System.out.println();
            }
         }
       }
      
   }
}