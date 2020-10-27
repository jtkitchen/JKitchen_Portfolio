import java.io.*;
import java.util.ArrayList;
import java.lang.Math;

public class WordFind {

   public ArrayList<Character> puzzle = new ArrayList<Character>();
   public ArrayList<Character> searchWordList = new ArrayList<Character>();
   public String fileName;
   public int rowSize = 1;
   public int colSize = 0;
   
   public WordFind( String file ) throws Exception {
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
                  colSize = puzzle.size();
                  firstPass = false;
               }
               if( i == '\n' ) rowSize += 1;
            } else {
               puzzle.add((char) i);
            }
         }
      System.out.println( puzzle.toString() );
   }
   
   public void findString( String searchWord ) {
      for (char c : searchWord.toCharArray()) {
            searchWordList.add(c);
      }
      searchRows();
      searchColumns();
      searchDiagonals();
   }
   
   private boolean searchRows() {
      boolean match = false;
      int searchIndex = 0;
      for( int i = 0; i < rowSize; i++ ) {
         for( int j = 0; j < colSize; j++ ) {
            //System.out.println(j + (colSize*i) );
            if( puzzle.get(j + (colSize*i)) == searchWordList.get(searchIndex) ) {
               if( searchIndex + 1 == searchWordList.size() ) {
                  match = true;
                  break;
               }
               searchIndex += 1;
            } else {
               searchIndex = 0;
            }
         }
      }
      System.out.println( match );
      return match;
   }
   
   private boolean searchColumns() {
      boolean match = false;
      int searchIndex = 0;
      
      for( int i = 0; i < colSize; i++ ) {
         for( int j = 0; j < rowSize; j++ ) {
            //System.out.println(i + (colSize*j));
            if( puzzle.get(i + (colSize*j)) == searchWordList.get(searchIndex) ) {
               if( searchIndex + 1 == searchWordList.size() ) {
                  match = true;
                  System.out.println( match );
                  return match;
               }
               searchIndex += 1;
            } else {
               searchIndex = 0;
            }
         }
      }
      return match;
   }
   
   
   private boolean searchDiagonals() {
      int searchIndex = 0;
      boolean match = false;
      int diagCount = 0;
      int iter = 0;
      
      //bottom-left to top-right
      for(int i = 0; i <= (colSize * (rowSize - 1)); i += colSize ) {
         for( int j = i; j >= 0; j -= (colSize - 1) ) {
            System.out.println(j);
            
            if( puzzle.get(j) == searchWordList.get(searchIndex) ) {
               if( searchIndex + 1 == searchWordList.size() ) {
                  match = true;
                  System.out.println( match );
                  return match;
               }
               searchIndex += 1;
            } else {
               searchIndex = 0;
            }
         }
         searchIndex = 0;
      }
      
      for( int i = puzzle.size()-1; i > puzzle.size() - colSize - 1 ; i-- ) {
         if( diagCount != rowSize ) diagCount++;
         iter = 0;
         
         for( int j = i; j >= 0; j -= (colSize - 1) ) {
            if( iter == diagCount ) break;
            iter++;
            System.out.println(j);
            if( puzzle.get(j) == searchWordList.get(searchIndex) ) {
               if( searchIndex + 1 == searchWordList.size() ) {
                  match = true;
                  System.out.println( match );
                  return match;
               }
               searchIndex += 1;
            } else {
               searchIndex = 0;
            }
         }
         searchIndex = 0;  
      }      
      
      
      //top-right to bottom-left
      
    //  for(int i = puzzle.size(); i >= colSize - 1; i -= colSize ) {
//          for( int j = i; j < 0; j += (colSize - 1) ) {
//             System.out.println(j);
//             
//             if( puzzle.get(j) == searchWordList.get(searchIndex) ) {
//                if( searchIndex + 1 == searchWordList.size() ) {
//                   match = true;
//                   System.out.println( match );
//                   return match;
//                }
//                searchIndex += 1;
//             } else {
//                searchIndex = 0;
//             }
//          }
//          searchIndex = 0;
//       }
//       
//       for( int i = 0; i < colSize - 1; i++ ) {
//          if( diagCount != rowSize ) diagCount++;
//          iter = 0;
//          
//          for( int j = i; j >= 0; j += (colSize - 1) ) {
//             if( iter == diagCount ) break;
//             iter++;
//             System.out.println(j);
//             if( puzzle.get(j) == searchWordList.get(searchIndex) ) {
//                if( searchIndex + 1 == searchWordList.size() ) {
//                   match = true;
//                   System.out.println( match );
//                   return match;
//                }
//                searchIndex += 1;
//             } else {
//                searchIndex = 0;
//             }
//          }
//          searchIndex = 0;  
//       } 
//       
//       //top-left to bottom-right
//       
//       //bottom-right to top-left
       return match;
    }

}