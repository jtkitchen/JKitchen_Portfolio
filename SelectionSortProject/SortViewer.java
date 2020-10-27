import java.awt.Canvas;
import java.awt.Graphics;
import javax.swing.JFrame;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Color;
import java.lang.Thread;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics2D;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;

public class SortViewer extends Canvas {

   public static int width = 800;
   public static int height = 500;
   public static int[][] array;
   public static int size = 40;
   public static int speed = 20;
   public static JFrame frame;
   public static int scale;
   
   public static void main(String[] args) {
        frame = new JFrame("My Drawing");
        JButton button = new JButton("Regular");
        JButton button2 = new JButton("Min & Max");
        JButton button3 = new JButton("I Tried...");
        JButton upButton = new JButton("+Size");
        JButton downButton = new JButton("-Size");
        JButton speedUpButton = new JButton("+Speed");
        JButton speedDownButton = new JButton("-Speed");
        
		  button.setBounds(20, height - 30, 100, 25);
        button2.setBounds(20, height - 60, 100, 25);
        button3.setBounds(20, height - 90, 100, 25);
        upButton.setBounds(20, height - 120, 100, 25);
        downButton.setBounds(20, height - 150, 100, 25);
        speedUpButton.setBounds(20, height - 180, 100, 25);
        speedDownButton.setBounds(20, height - 210, 100, 25);
		  frame.add(button);
        frame.add(button2);
        frame.add(button3);
        frame.add(upButton);
        frame.add(downButton);
        frame.add(speedUpButton);
        frame.add(speedDownButton);
        
        Canvas canvas = new SortViewer();
        canvas.setBackground(Color.BLACK);
        canvas.setSize(width, height);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        class SelectionSortDraw implements ActionListener
   		{
   			public void actionPerformed(ActionEvent event)
   			{
               array = createArray(size);
               scale = (width / array.length);
               drawSelectionSort( canvas.getGraphics() );
               drawInfo( canvas.getGraphics(), "List Size: " + size, "Speed: " + speed );
   				//frame.repaint();
   			}
   		}
         
         class MinMaxSelectionSortDraw implements ActionListener
   		{
   			public void actionPerformed(ActionEvent event)
   			{
               array = createArray(size);
               scale = (width / array.length);
               drawMinMaxSelectionSort( canvas.getGraphics() );
               drawInfo( canvas.getGraphics(), "List Size: " + size, "Speed: " + speed );
   				//frame.repaint();
   			}
   		}
         
         class FourMinMaxSelectionSortDraw implements ActionListener
   		{
   			public void actionPerformed(ActionEvent event)
   			{
               array = createArray(size);
               scale = (width / array.length);
               drawFourMinMaxSelectionSort( canvas.getGraphics() );
               drawInfo( canvas.getGraphics(), "List Size: " + size, "Speed: " + speed );
   				//frame.repaint();
   			}
   		}
         
         class UpButtonPress implements ActionListener
   		{
   			public void actionPerformed(ActionEvent event)
   			{
               size += 20;
               drawInfo( canvas.getGraphics(), "List Size: " + size, "Speed: " + speed );
               //frame.repaint();
   			}
   		}
         
         class DownButtonPress implements ActionListener
   		{
   			public void actionPerformed(ActionEvent event)
   			{
               size -= 20;
               drawInfo( canvas.getGraphics(), "List Size: " + size, "Speed: " + speed );
               //frame.repaint();
   			}
   		}
         
         
         class speedDownButtonPress implements ActionListener
   		{
   			public void actionPerformed(ActionEvent event)
   			{
               if(speed != 0 )  {
                  speed -= 5;
                  drawInfo( canvas.getGraphics(), "List Size: " + size, "Speed: " + speed );
               }
               //frame.repaint();
   			}
   		}
         
         class speedUpButtonPress implements ActionListener
   		{
   			public void actionPerformed(ActionEvent event)
   			{
          
               speed += 5;
               drawInfo( canvas.getGraphics(), "List Size: " + size, "Speed: " + speed );
               //frame.repaint();
   			}
   		}
         
   		ActionListener listener = new SelectionSortDraw();
   		button.addActionListener(listener);
   		ActionListener listener2 = new MinMaxSelectionSortDraw();
   		button2.addActionListener(listener2);
         ActionListener listener3 = new FourMinMaxSelectionSortDraw();
   		button3.addActionListener(listener3);
         
         ActionListener listener4 = new UpButtonPress();
   		upButton.addActionListener(listener4);
   		ActionListener listener5 = new DownButtonPress();
   		downButton.addActionListener(listener5);
         ActionListener listener6 = new speedUpButtonPress();
   		speedUpButton.addActionListener(listener6);
   		ActionListener listener7 = new speedDownButtonPress();
   		speedDownButton.addActionListener(listener7);
         
    }
   
    public void paint(Graphics g) {
      
    }
    
    public static void drawInfo( Graphics g, String sizeInfo, String speedInfo  ){
        String info = sizeInfo + speedInfo;
        g.setColor(Color.black);
        g.fillRect(20, height-300, 100, 100 );
        g.setColor(Color.white);
        g.drawString( sizeInfo, 20, height - 250 );
        g.drawString( speedInfo, 20, height - 230 );
    }
    
    public static int[][] createArray( int size ) {
       int[][] array = new int[size][2];
       Random rand = new Random();
       
       for( int i = 0; i < size; i++ ) {
          array[i][0] = rand.nextInt(size);
          array[i][1] = 0;
       }
       
       return array;
    }
    
    public static void cleanSlate(Graphics g) {
         int margin = 0;
         for( int a = 0; a < array.length; a++ ) {
            g.setColor(Color.white);
            g.fillRect( (scale*a) + 20, margin, scale, array[a][0] * (height/array.length)); 
         }
    }
    
    public static void drawMinMaxSelectionSort(Graphics g) {

      int min;
      int max;
      int n = array.length;
      int margin = 0;
      boolean end = false;
      
      
      for( int i=0; i < n-1-i; i++ ) {
         min = i;
         max = i;
         for( int j=i+1; j < n - i; j++ ) {
            if( array[j][0] < array[min][0] ) {
               min = j;
            }
            if( array[j][0] > array[max][0] ) {
               max = j;
            }
         }
         
         if( min == n-1-i && max == i ) { //max and min are in the other's position, just need to swap the ends
            array = swap( array, i, n-1-i );
                       
         } else if( min == n-1-i ) {
            array = swap( array, i, min );
            array = swap( array, n-1-i, max );
            
         } else { //the maximum is at the beginning of the section, or it doesn't matter
            array = swap( array, n-1-i, max );
            array = swap( array, i, min );
         }
         array[i][1] = 1;
         array[n-1-i][1] = 1;
         try {
            Thread.sleep(speed);
            g.clearRect(0, 0, width, height);
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
         for( int a = 0; a < array.length; a++ ) {
            if( array[a][1] == 0 ) g.setColor( Color.white );
            else g.setColor( Color.red );
            g.fillRect( (scale*a) + 20, margin, scale, array[a][0] * (height/array.length)); 
         }
         array[i][1] = 0;
         array[n-1-i][1] = 0;   
      }
      cleanSlate(g);
    }
    
    public static void drawSelectionSort(Graphics g) {

      int margin = 0;
      boolean end = false;
      
         for( int i=0; i < array.length - 1; i++ ) {
   			int min = i;
   			for( int j = i+1; j < array.length; j++ )
   			{
   				if( array[j][0] < array[min][0] )
   				{
   					min = j;
   				}
   			}
   			int hold = array[i][0];
   			array[i][0] = array[min][0];
   			array[min][0] = hold;
            
   		   array[i][1] = 1;
            array[min][1] = 1;
            
            try {
               Thread.sleep(speed);
               g.clearRect(0, 0, width, height);
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
            for( int a = 0; a < array.length; a++ ) {
               if( array[a][1] == 0 ) g.setColor( Color.white );
               else g.setColor( Color.red );
               g.fillRect( (scale*a) + 20, margin, scale, array[a][0] * (height/array.length)); 
            }
            array[i][1] = 0;
            array[min][1] = 0;
         }
       cleanSlate(g);
    }
   
   public static void drawFourMinMaxSelectionSort( Graphics g ) {

      int margin = 0;
            
      int min;
      int max;
      int prevMin = -1; //essentially null
      int prevMax = -1; //essentially null
      int swapped = 0;
      int n = array.length;
      
      for( int i=0; i < n-1-i; i=i+1+swapped ) {
         swapped = 0;
         min = i;
         max = i;
         for( int j=i+1; j < n - i; j++ ) {
            if( array[j][0] <= array[min][0] ) {
               prevMin = min;
               min = j;
            } else if( prevMin != -1 && array[j][0] <= array[prevMin][0] ) prevMin = j;
            if( array[j][0] >= array[max][0]) {
               prevMax = max;
               max = j;
            } else if( prevMax != -1 && array[j][0] >= array[prevMax][0] ) prevMax = j;
         }
         
         if( min == n-1-i && max == i ) { //max and min are in the other's position, just need to swap the ends
            array = swap( array, i, n-1-i );
            
         } else if( min == n-1-i ) {
            array = swap( array, i, min );
            array = swap( array, n-1-i, max );
            
         } else { //the maximum is at the beginning of the section, or it doesn't matter
            array = swap( array, n-1-i, max );
            array = swap( array, i, min );
         }
         array[i][1] = 1;
         array[n-1-i][1] = 1;
         
         if( prevMin == n-2-i && prevMax == i+1 ) { //max and min are in the other's position, just need to swap the ends
            array = swap( array, i+1, n-2-i );
            swapped = 1;
            
         } else if( prevMin == n-2-i ) {
            array = swap( array, i+1, prevMin );
            swapped = 1;
            if( prevMax != -1 ) {
               array = swap( array, n-2-i, prevMax );
            }
            
         } else { //the maximum is at the beginning of the section, or it doesn't matter
            if( prevMax != -1 && prevMax != max) {
               array = swap( array, n-2-i, prevMax );
            } else i--;
            if( prevMin != -1 && prevMin != min ) {
               array = swap( array, i+1, prevMin );
               swapped = 1;
            }
         }
         array[i+1][1] = 1;
         array[n-2-i][1] = 1;
         if( i == (n/2) - 2 ) break;
         
         try {
            Thread.sleep(speed);
            g.clearRect(0, 0, width, height);
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
         for( int a = 0; a < array.length; a++ ) {
            if( array[a][1] == 0 ) g.setColor( Color.white );
            else g.setColor( Color.red );
            g.fillRect( (scale*a) + 20, margin, scale, (array[a][0] * (height/array.length))); 
         } 
         array[i][1] = 0;
         array[n-1-i][1] = 0;
         array[i+1][1] = 0;
         array[n-2-i][1] = 0;     
      }
      cleanSlate(g);   
   }

   
   public static int[][] swap( int[][] array, int oldPos, int newPos ) {
      int temp = array[oldPos][0];
		array[oldPos][0] = array[newPos][0];
		array[newPos][0] = temp;
      
      return array;
   }
}