import java.util.LinkedList;
import java.util.Random;
import java.lang.StringBuffer;
import java.util.Arrays;

/**
 A class to perform common tasks on arrys of integers.  It allows the 
 creation of arrays with various characteristics, gives tools for
 displaying arrays, and other useful tools.
 @author Robert G. Willhoft
 @version 2007.01.26
 */ 
public class IArrays
{

	public static void sort( int[] array )
	{
		Arrays.sort( array );
	}
  /**
	  Creates an exact copy of the array given.  The two arrays are equal.
		@param array the array to be cloned
		@return the new array created
	 */
  public static int[] clone( int[] array )
	{
	  int[] result = new int[ array.length ];
	  for( int i=0; i<array.length; i++ )
		{
		  result[i] = array[i];
		}
		return result;
	}
	
	/**
	  Creates a new array with selected elements from the given array.
	  @param array the array from which values are going to be copied
    @param fromIndex the index of the first element (inclusive) to be copied
		@param toIndex the index of the last element (exclusive) to be copied 
		@return the new array with the selected elements
		@throws IllegalArgumentException if fromIndex > toIndex 
    @throws ArrayIndexOutOfBoundsException if fromIndex < 0 or toIndex > a.length
	 */
	public static int[] subarray( int[] array, int fromIndex, int toIndex )
	{
	  int[] result = new int[ toIndex - fromIndex ];
		for( int i=fromIndex, j=0; i<toIndex; i++, j++ )
		{
		  result[j] = array[i];
		}
		return result;
	}
	
	/**
   	Assigns the specified value to each element of the specified array.
	 	@param array the array to be filled
	 	@param value the value to be stored in all elements of the array
	 */
  public static void fill( int[] array, int value )
	{
	  for( int i=0; i<array.length; i++ )
		{
		  array[i] = value;
		}
	}
	
	/**
		Assigns the specified int value to each element of the specified range 
		of the specified array of ints. The range to be filled extends from index
		fromIndex, inclusive, to index toIndex, exclusive.  (If fromIndex==toIndex, 
		the range to be filled is empty.) 
    @param array the array to be filled
    @param fromIndex the index of the first element (inclusive) to be filled 
		with the specified value
		@param toIndex the index of the last element (exclusive) to be filled with 
		the specified value
		@param value the value to to be stored in all the specified elements
		@throws IllegalArgumentException if fromIndex > toIndex 
    @throws ArrayIndexOutOfBoundsException if fromIndex < 0 or toIndex > a.length
	 */
  public static void fill( int[] array, int fromIndex, int toIndex, int value )
	{
	  for( int i=fromIndex; i<toIndex; i++ )
		{
		  array[i] = value;
		}
	}
	
	/**
	  Creates a new array that contains a sequence (0, 1, 2, 3, etc.) of values.  The
		values will be 0 to n-1 given that the length of the array is n.
		@param n the length of the array to be created
		@return the array with the sequence of values
	 */
	public static int[] sequence( int n )
	{
		int[] result = new int[n];
		for( int i=0; i<n; i++ )
		{
		  result[i] = i;
		}
		return result;
	}
	
	/**
	  Shuffles the values in an array, i.e. takes the values and puts them in a new
		random order.  Warning: the array is shuffed in place.
		@param array the array to be shuffled
	 */
  public static void shuffle( int[] array )
	{
		Random random = new Random();
		// we will fill the array from the right end, selecting a random value from
		// all the remain values each time
		for( int i=array.length-1; i>0; i-- )
		{
			int j = random.nextInt(i);  // select a random position
			if( i != j )
			{
				// swap the ith value and this random position
				int hold = array[i];
				array[i] = array[j];
				array[j] = hold;
			}
		}
	}
	
	/**
	  Creates and fills the array with random values between minValue (inclusive) and
		max value (exclusive).  Random values can be repeated.
		@param n the size of the new array, i.e. the number of random values
		@param minValue the smallest possible value
		@param maxValue 1 greater than the largest possible value
		@return the array with the random values
	 */
  public static int[] roll( int n, int minValue, int maxValue )
	{
		Random random = new Random();
		int result[] = new int[n];
		for( int i=0; i<n; i++ )
		{
			result[i] = minValue + random.nextInt(maxValue-minValue);
		}
		return result;
	}

	/**
		Returns true if the two specified arrays are equal to one another. Two 
		arrays are considered equal if both arrays contain the same number 
		of elements, and all corresponding pairs of elements in the two arrays 
		are equal. In other words, two arrays are equal if they contain the same 
		elements in the same order. Also, two array references are considered 
		equal if both are null.
		@param array1 one array to be tested for equality
		@param array2 the other array to be tested for equality
	 */
	public static boolean equals( int[] array1, int[] array2 )
	{
		if( array1 == null && array2 == null )
		{
			return true;
		}
		if( array1 == null || array2 == null )
		{
			return false;
		}
		if( array1.length != array2.length )
		{
			return false;
		}
		for( int i=0; i<array1.length; i++ )
		{
			if( array1[i] != array2[i] )
			{
				return false;
			}
		}
		return true;
	}
	
	/**
	  Returns a linked list with the same elements, except as Integers, as the 
		specified array.   
    @param array the array to be converted (unchanged by the operation)
		@return the list of the same values
	 */
	public static LinkedList<Integer> toLinkedList( int[] array )
	{
		LinkedList<Integer> result = new LinkedList<Integer>();
		for( int i=0; i<array.length; i++ )
		{
			result.add( array[i] );
		}
		return result;
	}
	
	/**
	  Returns a string representation of the values in the array.  The representation
		begins and ends with [ and ].  Within the brackets, the values are listed with 
		comma separators.
    @param array the array to represent as a string
		@return the string created
	 */		
  public static String toString( int[] array )
	{
		if( 0 == array.length )
		{
			return "[]";
		}
		else
		{
			StringBuffer result = new StringBuffer( "[" + array[0] );
			for( int i=1; i<array.length; i++ )
			{
				result.append( "," + array[i] );
			}
			result.append("]");
			return result.toString();
		}
	}
	
	/**
		Prints a string representation of the values in the array.  The representation
		begins and ends with [ and ].  Within the brackets, the values are listed with 
		comma separators.
    @param array the array to print
	 */
  public static void print( int[] array )
	{
		System.out.println( toString( array ) );
	}
	
	/**
		Prints the array with a label and a fixed width.  The representation
		begins and ends with [ and ].  Within the brackets, the values are listed with 
		comma separators.
    @param array the array to print
		@param label a label to be printed on the first line
		@param width the array will be printed in lines no longer than this value
	 */
  public static void print( int[] array, String label, int width )
	{
		if( 0 == array.length )
		{
			System.out.println( label + "[]" );
		}
		else
		{
			String value = "" + array[0];
			System.out.print( label + "[" + value );
			int count = label.length() + 1 + value.length();
			for( int i=1; i<array.length; i++ )
			{
				System.out.print(",");
				count++;
				value = "" + array[i];
				if( ( count + value.length() + 1) > width )
				{
					System.out.println();
					System.out.print("     ");
					count = 5;
				}
				System.out.print(value);
				count += value.length();
			}
			System.out.println("]");
		}
	}
	
	// ***** BUILT-IN TEST *****
	public static void main(String[] args)
	{
		int[] array1 = IArrays.sequence( 100 );
		System.out.println( IArrays.toString( array1 ) );
		System.out.println();
		
		IArrays.print( array1 );
		System.out.println();
		
		IArrays.print( array1, "array1 = ", 50 );
		System.out.println();
		
		int[] array2 = IArrays.subarray( array1, 20, 40 );
		IArrays.print( array2 );
		System.out.println();
		
		IArrays.fill( array2, 1 );
		IArrays.print( array2 );
		System.out.println();
		
		IArrays.fill( array2, 7, 16, 2 );
		IArrays.print( array2 );
		System.out.println();
		
		int[] array3 = IArrays.roll( 25, 3, 8 );
		IArrays.print( array3 );
		System.out.println();
		
		int[] array4 = IArrays.sequence( 25 );
		IArrays.shuffle( array4 );
		IArrays.print( array4 );
		System.out.println();
		
		int[] array5 = clone( array4 );
		IArrays.print( array5 );
		System.out.println( IArrays.equals( array4, array5 ) );
		array5[24] = 100;
		System.out.println( IArrays.equals( array4, array5 ) );		
		System.out.println();
		
		System.out.println( toLinkedList( array4 ) );
	}
}
