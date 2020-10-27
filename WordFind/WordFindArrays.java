import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.Math;
import java.lang.Character;

public class WordFindArrays {
   public String fileName;
   public ArrayList<Character> tempPuzzle = new ArrayList<Character>();
   public ArrayList<Character> searchWordList;
   public String[] location;
   public char[][] puzzle;
   public int colSize = 0;
   public int rowSize = 1;
   public int tempI, tempJ;
   
   public WordFindArrays( String file ) throws Exception {
      fileName = file;
      readFile();
   }
   public void readFile( ) throws Exception {
  
       FileReader fr = new FileReader(fileName);
       
       int i;
       int currentSize = 0;
       boolean firstPass = true;
       
       while ((i=fr.read()) != -1) 
         if(i != ' ' ) {
            if( i == '\r' || i == '\n' ) {
               if( firstPass ) {
                  colSize = tempPuzzle.size();
                  firstPass = false;
               }
               if( i == '\n' ) rowSize += 1;
            } else {
               tempPuzzle.add(Character.toUpperCase( (char) i ));
            }
         }
      int count = 0;
      puzzle = new char[rowSize][colSize];
      
      for(int k = 0; k < rowSize; k++ ) {
         for( int j = 0; j < colSize; j++ ) {
            puzzle[k][j] = tempPuzzle.get(count);
            //System.out.print( puzzle[k][j] );
            count++;
         }
         //System.out.println();
      }
   }
   
   
   public void findString( String searchWord ) {
    searchWordList = new ArrayList<Character>();
      for (char c : searchWord.toCharArray()) {
            searchWordList.add(Character.toUpperCase( c ));
      }
      location = new String[searchWordList.size()];
      if( searchRows() ) {
         System.out.println("[" + searchWord + "]" + " found in a row...");
         System.out.println("Location: " + Arrays.toString(location) + "\n" );
      }
      
      if( searchColumns() ) {
         System.out.println("[" + searchWord + "]" + " found in a column...");
         System.out.println("Location: " + Arrays.toString(location) + "\n" );
      }
      
      if( searchDiagonals() ) {
         System.out.println("[" + searchWord + "]" + " found in a diagonal...");
         System.out.println("Location: " + Arrays.toString(location) + "\n" );
      }
   }
  
   
   private boolean searchRows() {
      boolean match = false;
      int searchIndex = 0;
      boolean firstPass = true;
      
      //left to right
      for( int i = 0; i < rowSize; i++ ) {
         for( int j = 0; j < colSize; j++ ) {
            if(  puzzle[i][j] == searchWordList.get(searchIndex) ) {
               if( firstPass ) {
                  firstPass = false;
                  tempI = i; tempJ = j;
               }
               location[searchIndex] = "(" + i + " " + j + ")";
               if( searchIndex + 1 == searchWordList.size() ) {
                  match = true;
                  return match;
               }
               searchIndex += 1;
            } else {
               if( !firstPass ) {
                  i = tempI; j = tempJ;
                  firstPass = true;
               }
               searchIndex = 0;
            }
         }
      }
      
      //right to left
      for( int i = rowSize-1; i >= 0; i-- ) {
         for( int j = colSize-1; j >= 0; j-- ) {
            if(  puzzle[i][j] == searchWordList.get(searchIndex) ) {
               if( firstPass ) {
                  firstPass = false;
                  tempI = i; tempJ = j;
               }
               location[searchIndex] = "(" + i + " " + j + ")";
               if( searchIndex + 1 == searchWordList.size() ) {
                  match = true;
                  return match;
               }
               searchIndex += 1;
            } else {
               if( !firstPass ) {
                  i = tempI; j = tempJ;
                  firstPass = true;
               }
               searchIndex = 0;
            }
         }
      }
      return match;
   }


   private boolean searchColumns() {
      boolean match = false;
      int searchIndex = 0;
      boolean firstPass = true;
      
      //top to bottom
      for( int i = 0; i < colSize; i++ ) {
         for( int j = 0; j < rowSize; j++ ) {
            if( puzzle[j][i] == searchWordList.get(searchIndex) ) {
               if( firstPass ) {
                  firstPass = false;
                  tempI = i; tempJ = j;
               }
               location[searchIndex] = "(" + j + " " + i + ")";
               if( searchIndex + 1 == searchWordList.size() ) {
                  match = true;
                  return match;
               }
               searchIndex += 1;
            } else {
               if( !firstPass ) {
                  i = tempI; j = tempJ;
                  firstPass = true;
               }
               searchIndex = 0;
            }
         }
      }
      
      //bottom to top
      for( int i = colSize-1; i >= 0; i-- ) {
         for( int j = rowSize-1; j >= 0; j-- ) {
            if( puzzle[j][i] == searchWordList.get(searchIndex) ) {
               if( firstPass ) {
                  firstPass = false;
                  tempI = i; tempJ = j;
               }
               location[searchIndex] = "(" + j + " " + i + ")";
               if( searchIndex + 1 == searchWordList.size() ) {
                  match = true;
                  return match;
               }
               searchIndex += 1;
            } else {
               if( !firstPass ) {
                  i = tempI; j = tempJ;
                  firstPass = true;
               }
               searchIndex = 0;
            }
         }
      }
      return match;
   }

   
   private boolean searchDiagonals() {
      int searchIndex = 0;
      boolean match = false;
      int count;
      int tempCount = 0;
      boolean firstPass = true;
      
      //bottom-left to top-right
      //Iterate through left column, 0-n
      count = 0;
      for(int i = 0; i <= rowSize-1; i++ ) {
         for( int j = 0; j <= count; j++ ) {
            if(  puzzle[(i-j)][j] == searchWordList.get(searchIndex) ) {
               if( firstPass ) {
                  firstPass = false;
                  tempI = i; tempJ = j; tempCount = count;
               }
               location[searchIndex] = "(" + (i-j) + " " + j + ")";
               if( searchIndex + 1 == searchWordList.size() ) {
                  match = true;
                  return match;
               }
               searchIndex += 1;
            } else {
               if( !firstPass ) {
                  i = tempI; j = tempJ; count = tempCount;
                  firstPass = true;
               }
               searchIndex = 0;
            }
         }
         if( count < colSize-1 ) count++;
      }
      
      //Iterate through bottom row, 0-n
      //count = 1 problem
      count = rowSize-1;
      firstPass = true;
      for(int i = colSize-1; i > 0 ; i-- ) {
         for( int j = rowSize-1; j >= count; j-- ) {
            if(  puzzle[j][(i-j) + rowSize-1] == searchWordList.get(searchIndex) ) {
               if( firstPass ) {
                  firstPass = false;
                  tempI = i; tempJ = j; tempCount = count;
               }
               location[searchIndex] = "(" + j + " " + (i-j) + ")";
               if( searchIndex + 1 == searchWordList.size() ) {
                  match = true;
                  return match;
               }
               searchIndex += 1;
            } else {
               if( !firstPass ) {
                  i = tempI; j = tempJ; count = tempCount;
                  firstPass = true;
               }
               searchIndex = 0;
            }
         }
         if( count > 0 ) count--;
      }
      
      
       //top-Right to bottom-left
      count = 0;
      firstPass = true;
      for(int i = 0; i < colSize; i++ ) {
         for( int j = 0; j <= count; j++ ) {
            if(  puzzle[j][i-j] == searchWordList.get(searchIndex) ) {
               if( firstPass ) {
                  firstPass = false;
                  tempI = i; tempJ = j; tempCount = count;
               }
               location[searchIndex] = "(" + j + " " + (i-j) + ")";
               if( searchIndex + 1 == searchWordList.size() ) {
                  match = true;
                  return match;
               }
               searchIndex += 1;
            } else {
               if( !firstPass ) {
                  i = tempI; j = tempJ; count = tempCount;
                  firstPass = true;
               }
               searchIndex = 0;
            }
         }
         if( count < rowSize-1 ) count++;
      }
      
      count = 0;
      int helperCount = 0;
      int tempHelperCount = 0;
      firstPass = true;
      
      for(int i = rowSize - 1; i > 0 ; i-- ) {
         for( int j = 0; j <= count; j++ ) {
            if(  puzzle[(rowSize-1 - helperCount + j)][colSize-1 - j] == searchWordList.get(searchIndex) ) {
               if( firstPass ) {
                  firstPass = false;
                  tempI = i; tempJ = j; tempCount = count; tempHelperCount = helperCount;
               }
               location[searchIndex] = "(" + (rowSize-1 - count + j) + " " + (colSize-1 - j) + ")";
               if( searchIndex + 1 == searchWordList.size() ) {
                  match = true;
                  return match;
               }
               searchIndex += 1;
            } else {
               if( !firstPass ) {
                  i = tempI; j = tempJ; count = tempCount; helperCount = tempHelperCount;
                  firstPass = true;
               }
               searchIndex = 0;
            }
         }
         if( count < colSize-1 ) {
            count++;
         }
         helperCount++;
      }
      
      
      //top-left to bottom-right
      count = 0;
      firstPass = true;
      for(int i = rowSize-1; i >= 0; i-- ) {
         for( int j = 0; j <= count; j++ ) {
            if(  puzzle[(i+j)][j] == searchWordList.get(searchIndex) ) {
               if( firstPass ) {
                  firstPass = false;
                  tempI = i; tempJ = j; tempCount = count;
               }
               location[searchIndex] = "(" + (i+j) + " " + j + ")";
               if( searchIndex + 1 == searchWordList.size() ) {
                  match = true;
                  return match;
               }
               searchIndex += 1;
            } else {
               if( !firstPass ) {
                  i = tempI; j = tempJ; count = tempCount;
                  firstPass = true;
               }
               searchIndex = 0;
            }

         }
         if( count < colSize-1 ) count++;
      }
      
      
      count = 0;
      firstPass = true;
      for(int i = colSize-1; i > 0; i-- ) {
         for( int j = 0; j <= count; j++ ) {
            if(  puzzle[j][i+j] == searchWordList.get(searchIndex) ) {
               if( firstPass ) {
                  firstPass = false;
                  tempI = i; tempJ = j; tempCount = count;
               }
               location[searchIndex] = "(" + j + " " + (i+j) + ")";
               if( searchIndex + 1 == searchWordList.size() ) {
                  match = true;
                  return match;
               }
               searchIndex += 1;
            } else {
               if( !firstPass ) {
                  i = tempI; j = tempJ; count = tempCount;
                  firstPass = true;
               }
               searchIndex = 0;
            }
         }
         if( count < rowSize-1 ) count++;
      }
      
      
      //bottom-right to top-left  
      count = 0;   
      firstPass = true;
      for(int i = 0; i < rowSize ; i++ ) {
         for( int j = 0; j <= count; j++ ) {
            if(  puzzle[(i-j)][(colSize - 1 -j)] == searchWordList.get(searchIndex) ) {
               if( firstPass ) {
                  firstPass = false;
                  tempI = i; tempJ = j; tempCount = count;
               }
               location[searchIndex] = "(" + (i-j) + " " + (colSize - 1 -j) + ")";
               if( searchIndex + 1 == searchWordList.size() ) {
                  match = true;
                  return match;
               }
               searchIndex += 1;
            } else {
               if( !firstPass ) {
                  i = tempI; j = tempJ; count = tempCount;
                  firstPass = true;
               }
               searchIndex = 0;
            }
         }
         if( count < colSize-1 ) count++;
      }
      
      
      count = rowSize-1;
      firstPass = true;
      for(int i = colSize-1; i >0; i--) {
         for( int j = rowSize-1; j >= count; j-- ) {
            if(  puzzle[j][colSize-1- i - (rowSize-1-j)] == searchWordList.get(searchIndex) ) {
               if( firstPass ) {
                  firstPass = false;
                  tempI = i; tempJ = j; tempCount = count;
               }
               location[searchIndex] = "(" + j + " " + (colSize-1- i - (rowSize-1-j)) + ")";
               if( searchIndex + 1 == searchWordList.size() ) {
                  match = true;
                  return match;
               }
               searchIndex += 1;
            } else {
               if( !firstPass ) {
                  i = tempI; j = tempJ; count = tempCount;
                  firstPass = true;
               }
               searchIndex = 0;
            }
         }
         if( count > 0  ) count--;
      }
      return match; 
   }     
}

