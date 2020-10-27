/* Code by: Joshua Kitchen
   September 6, 2020
   Finds Magic squares of size n by exhaustive search
   **Code for finding all permutations of array from:
      https://www.techiedelight.com/find-permutations-string-cpp-java-iterative/
*/
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.Math;
public class MagicSquare{

   private int[] square;
   private int expectedSum;
   private int n;
   private int size;
   private boolean repeat;
   private ArrayList<Long> times = new ArrayList<Long>();
   
   //Searches all possible matrices of size "size"
   public MagicSquare( int size ) {
      repeat = false;
      this.size = size;
      n = size * size;
      expectedSum = (size * ((size * size) + 1))/2;
      square = new int[n];
      initializeGrid();
      
   }
   
   //Searches all permutations following inputted permutation
   public MagicSquare( int[] square ) {
      repeat = true;
      this.square = square;
      n = square.length;
      size = (int) Math.sqrt(n);
      expectedSum = (size * ((size * size) + 1))/2;
   }
   
   //initializes a matrix of size "size," from 1 to size
   private void initializeGrid() {
       for(int i = 1; i <= n; i++) {
          square[i-1] = i;
       }
         
         //used for testing on a 4x4 matrix, as it is unfeasible to get to this point normally
         //square = new int[]{1, 15, 14, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 16}; 
   }
   
   //checks all sums of the matrix rows, columns, and diagonals
   private boolean checkSums() {
      if(checkDiagonals() == true &&
         checkRows() == true &&
         checkColumns() == true ) {
            return true;
         } else {
            return false;
         }
   }
   
   //checks that all row sums equal the expected sum
   private boolean checkRows() {
      boolean isEqual = true;
      int[] sum = new int[size];
      for(int i = 0; i < size; i++) {
         for( int j = 0; j < size; j++) {
            sum[i] += square[(size * i) + j ];
         }
      }
      
      for( int k = 0; k < size; k++ ){
         if( sum[k] != expectedSum ){
            isEqual = false;
         }
      }
      
      return isEqual;
   }
   
   //checks that all columns sums are equal to the expected sum
   private boolean checkColumns() {
      boolean isEqual = true;
      int[] sum = new int[size];
      
      for(int i = 0; i < size; i++) {
         for(int j = 0; j < size; j++ ) {
            sum[i] += square[i + (size * j)];
         }
      }
      
      for( int k = 0; k < size; k++ ){
         if( sum[k] != expectedSum ){
            isEqual = false;
         }
      }
      return isEqual;
   }
   
   //checks that all diagonals equal the expected sum
   private boolean checkDiagonals() {
      boolean isEqual = true;
      int sum1 = 0;
      int sum2 = 0;
      
      for( int i = 0; i < size; i++ ) {
         sum1 += square[((size+1) * i)];
      }
      for( int i = 1; i <= size; i++ ) {
         sum2 += square[((size-1) * i)];
      }
      
      if(sum1 != expectedSum || sum2 != expectedSum) {
         isEqual = false;
      }
      return isEqual;
   }
   
     
   //**Code used for going through all permutations of an array
   // Utility function to swap two characters in a character array
	private static void swap(int[] arr, int i, int j) {
		int c = arr[i];
		arr[i] = arr[j];
		arr[j] = c;
	}

	// Utility function to reverse a char array between specified indexes
	private static void reverse(int[] arr, int i, int j)
	{
		// do till two end-points intersect
		while (i < j) {
			swap(arr, i++, j--);
		}
	}

	// Iterative function to find permutations of a String in Java
	public int[] findMagicSquare()
	{
		// sort the string in natural order
      
		int[] s = square;
      long start = System.currentTimeMillis();

		while (true)
		{
         
			// Print current permutation
			//System.out.println(Arrays.toString(s));
         if( checkSums() == true && repeat == false ) {
            long stop = System.currentTimeMillis();
            System.out.println( "Seconds since start: " + (stop - start)/1000 );
            return s;
         }
         
         
         repeat = false;
			/* Below code will rearrange the string to next lexicographically
			 ordered permutation (if any) or return if we are already at
			 highest possible permutation */

			// Find largest index i such that s[i-1] is less than s[i]
			int i = n - 1;
			while (s[i-1] >= s[i])
			{
				// if i is first index of the string, that means we are
				// already at last possible permutation
				// (string is sorted in reverse order)
				if (--i == 0) {
					s = new int[] { -1 };
               return s;
            }
			}

			// find highest index j to the right of index i such that
			// s[j] > s[i–1] (s[i..n-1] is sorted in reverse order)

			int j = n - 1;
			while (j > i && s[j] <= s[i-1])
				j--;

			// Swap characters at index i-1 with index j
			swap(s, i-1, j);

			// reverse the substring s[i..n-1] and return true
			reverse (s, i, n-1);
		}
	}
      
}