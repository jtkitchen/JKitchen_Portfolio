import java.util.Scanner;
import java.util.Arrays;

public class GaussianSolve
{
   public static void main(String[] args)
   {
    
    int rows;
    int columns;
    Scanner in = new Scanner(System.in);
    while(true)
    {
       System.out.println("How many rows and columns?");
       System.out.print("Rows: ");
       rows = Integer.parseInt(in.nextLine());
       System.out.print("Columns: ");
       columns = Integer.parseInt(in.nextLine());
       
       if( columns == (rows + 1) ) {break;}
       else { System.out.println("Must a a square matrix (x by x+1)"); }
    }
    
    double[][] matrix = new double[rows][columns];
    double[][] matrixClone = new double[rows][columns];
    
    for( int i=0; i<rows; i++)
    {
      for(int j=0; j<columns; j++)
      {
         
         double input = 0;
         while (true) 
         {
             System.out.print("Enter row "+(i+1)+", column "+(j+1) + ": ");
             try 
             {
                input = Double.parseDouble(in.nextLine());
                break; 
             } 
             catch (NumberFormatException e)
             {
                System.out.println("Invalid input, type a number please.");
             }
         }
         matrix[i][j] = input;
         matrixClone[i][j] = input;
      }
    }
    
    MatrixOperations calcs = new MatrixOperations( matrix, matrixClone, rows, columns );
    calcs.printArrays();
    
    
    while( true )
    {
      System.out.println("What operation do you want to perform?\n");
      System.out.println("Solve the matrix (C), Print array (P), Simplify matrix (E), or any other button to quit.");
      
      String inst = in.nextLine();
      
      if( inst.equals("S") || inst.equals("s") )
      {
         System.out.print("Enter first row to swap: ");
         int row1 = Integer.parseInt(in.nextLine() );
         System.out.print("Enter second row to swap: ");
         int row2 = Integer.parseInt(in.nextLine() );
         calcs.swapRows( row1, row2 );
         calcs.printArrays();
      }
      
      else if( inst.equals("A") || inst.equals("a") )
      {
         System.out.print("Enter first row (it will be the one added to): ");
         int row1 = Integer.parseInt(in.nextLine() );
         System.out.print("Enter second row: ");
         int row2 = Integer.parseInt(in.nextLine() );
         
         calcs.addRows( row1, row2 );
         calcs.printArrays();
      }
      
      else if( inst.equals("M") || inst.equals("m") )
      {
         System.out.print("Enter row to multiply: ");
         int row1 = Integer.parseInt(in.nextLine() );
         System.out.print("Enter number to be multiplied by: ");
         double number = Double.parseDouble(in.nextLine() );
         calcs.multiplyRow( row1, number );
         calcs.printArrays();
      }
      
      else if ( inst.equals("C") || inst.equals("c") )
      {
         calcs.solve();
         calcs.printSolution();
      } 
      else if ( inst.equals("R") || inst.equals("r") )
      {
         calcs.resetMatrix();
      }
      else if ( inst.equals("P") || inst.equals("p") ) {calcs.printArrays();}
      else if ( inst.equals("E") || inst.equals("e") ) 
      {
         calcs.simplify();
         calcs.printArrays();
      }
      else {break;}

    }            
  }
}

// test Example of a 3x4: https://www.math.purdue.edu/files/academic/courses/2010spring/MA26200/2_5.pdf
// (3, -2, 2, 9, 1, -2, 1, 5, 2, -1, -2, -1) --> answer is a = 1, b = -1, c = 2.