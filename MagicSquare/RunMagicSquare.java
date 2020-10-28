import java.util.Arrays;
import java.util.Scanner;

//Analysis in the google doc with questions from Ch. 3

public class RunMagicSquare {

   public void printArray( int[] a, int size ) {
      for( int i = 0; i < a.length; i++ ) {
         for( int j = 0; i < size; j++ ) {
            System.out.print( a[i] + "  " );
         }
         System.out.println();
      }
   }
   
   public static void printResult( int[] result, int size ) {
      
      if( result[0] != -1 ) {
            for(int i = 1; i <= size * size ; i++) {
               System.out.print( result[i - 1] + " " );
               if( i%size == 0 ) System.out.println();
            }
         }
         System.out.println();
   }
   
	public static void main(String[] args)
	{
      //find all magic squares of given size. (>3 will be SLOOOOOW)...
      /* at the speed per check calculated at size = 3 (~42 ns), it
         would take over 10 days to check all 16! permutations of a 4x4 matrix...
         
      */
      
      Scanner scan = new Scanner( System.in );
      System.out.println( "Size of the N x N matrix: " );
      int size = scan.nextInt();
		MagicSquare test = new MagicSquare( size );
      int[] result = test.findMagicSquare();
      
      printResult( result, size );
      
      while( result[0] != -1 ) {
         test = new MagicSquare( result );
         result = test.findMagicSquare();
         
         printResult( result, size );
      }      
	}
}