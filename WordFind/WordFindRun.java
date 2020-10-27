public class WordFindRun {

   public static void main(String[] args) throws Exception {
      System.out.println("Coordinates start with (X,Y) = (0,0) in the top left \n" +
                         "with X going left to right, and Y going from top to bottom.\n" +
                         "**If there are duplicates, only one will be found**" +
                         "\n------------------------------------------------------------");
      String fileName;
      
      fileName = "largeWordFind.txt";
      WordFindArrays puzzle3 = new WordFindArrays( fileName );
      System.out.println("\nTesting bigger word find puzzle (50 x 75)");
      puzzle3.findString("GESTICULATION");
      puzzle3.findString("HELLO");
      puzzle3.findString("THERE");
      puzzle3.findString("JOSHUA");
      puzzle3.findString("KITCHEN");
      
      // fileName = "bigBigBoy.txt";
//       WordFindArrays puzzle4 = new WordFindArrays( fileName );
//       System.out.println("\nTesting an even bigger word find puzzle (100 x 150)");
//       puzzle4.findString("GESTICULATION");
//       puzzle4.findString("HELLO");
//       puzzle4.findString("THERE");
//       puzzle4.findString("JOSHUA");
//       puzzle4.findString("KITCHEN");
//       
//       
//       fileName = "wordFind.txt";
//       WordFindArrays puzzle = new WordFindArrays( fileName );
//       
//       System.out.println("\nTesting with row size > column size");
//       puzzle.findString("hi"); //row
//       puzzle.findString("sl"); //column
//       puzzle.findString("ji"); //row
//       puzzle.findString("jc"); //column
//       puzzle.findString("sm"); //diag
//       puzzle.findString("as"); //diag
//       puzzle.findString("ci"); //diag
//       puzzle.findString("ai"); //diag
//       
//       
//       fileName = "wordFindColLarge.txt";
//       WordFindArrays puzzle2 = new WordFindArrays( fileName );
//       System.out.println("\nTesting with column size > row size");
//       puzzle2.findString("wo"); //row
//       puzzle2.findString("pq"); //column
//       puzzle2.findString("sd"); //row
//       puzzle2.findString("ln"); //column
//       puzzle2.findString("ki"); //diag
//       puzzle2.findString("qq"); //diag
//       puzzle2.findString("cl"); //diag
//       puzzle2.findString("pd"); //diag

   }
}