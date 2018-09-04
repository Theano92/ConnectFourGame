package connectFour;

import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class MakeTheGrid extends JFrame implements ActionListener {
	private JFrame window;
	private JPanel panel;
	private JButton cell[][];
	private static final long serialVersionUID = 1L;
	private static final int COLS = 7;  
	private static final int ROWS = 6;
	private static final int FRAMEX =500;
	private static final int FRAMEY =500;
	private String [][] board = new String[ROWS][COLS];
    public boolean hWinner;
    public boolean vWinner;
    public boolean dWinner;
    public boolean fullGrid;
	private int totalTurn = 0;
	ImageIcon whiteCircle;
	ImageIcon yellowCircle;
	ImageIcon redCircle;
	private int x;
	
	
	
	public MakeTheGrid(){                                         //constructor to make a the grid

		window = new JFrame();                                  //create a window
		window.setSize(FRAMEX,FRAMEY);                         //size of the window
		window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); 
		
		window.setTitle("ConnectFour Game");
		window.setResizable(false);                           // the window is appeared on the screen
		
		panel = new JPanel();                                //create a panel inside the frame
		
		whiteCircle = new ImageIcon(getClass().getResource("/blankCircle.png"));   
		
		panel.setLayout(new GridLayout(ROWS,COLS));         //create the layout of the panel
		
		 
		cell = new JButton[ROWS][COLS];                    //create a double array to represent a button in the panel
		
		emptyPanel(cell);                                 // fill the panel with white circles
		
		initializeArray(board);                          //fill the array "board" with zeros
		
		
		window.add(panel);                               //add panel into the window
	    window.setLocationRelativeTo(null);             //the window is in the center of the screen
	    window.setVisible(true);                       // appear the window on the screen
	    
	}     //  end makeTheGrid
	
	
	
	public void actionPerformed(ActionEvent e){
		
		if(checkTurn(totalTurn)){                                          //change the icon to yellow

			yellowCircle = new ImageIcon(getClass().getResource("/yellowCircle.png"));            			
	
			JButton cell = (JButton)e.getSource();                       // which button is pressed
			
			Rectangle r = cell.getBounds();                             //get the location of the button which is pressed
			Point p = cell.getLocation();
			
			int colPosition = p.x /r.width;
			
			
			for(x=ROWS-1; x>=0; x--){								//keep the disc to the bottom every time 
				
				if(board[x][colPosition]=="0"){                               
				this.cell[x][colPosition].setIcon(yellowCircle);          
			    board[x][colPosition]="1";                         //the location is not empty anymore
			   
			    totalTurn++;
		       break;
		      }else {
				continue;                                        //if its not empty go to the next loop until you find an empty cell
			   }
			}
			
			
			/* methods to check who is the winner*/
			
			hWinner = checkHorizontalWinner(board); 
			vWinner = checkVerticalWinner(board);
			dWinner = checkDiagonalWinner(board);
			fullGrid = checkTheGrid(board);
			
			
				
			if(hWinner || vWinner || dWinner){                //if the winner is the yellow player
			
		        String output = "The Yellow player is the Winner :)\n Do you want to play again?";
		        int reply = JOptionPane.showConfirmDialog(null,output,"Game over",JOptionPane.YES_NO_OPTION);
		        if ( reply == JOptionPane.YES_OPTION) {
		        	window.setVisible(false);
    			    new MakeTheGrid();
    	        }else {
    	           JOptionPane.showMessageDialog(null, "GOODBYE!!");
    	           System.exit(0);
    	        }
			}
			if(fullGrid==true){                             //The grid is full
				String  output = "NO ONE won the game :(\n Do you want to play again?";
	    		int reply = JOptionPane.showConfirmDialog(null,output,"Game over",JOptionPane.YES_NO_OPTION);
	    		if ( reply == JOptionPane.YES_OPTION) {
	    			window.setVisible(false);
	    			new MakeTheGrid();
	    	        }else {
	    	           JOptionPane.showMessageDialog(null, "GOODBYE!!");
	    	           System.exit(0);
	    	        }
			}
			
			
	  }else{
		  redCircle = new ImageIcon(getClass().getResource("/redCircle.png"));   //change the icon to red
			
		  JButton cell = (JButton)e.getSource();
		  Rectangle r = cell.getBounds();
		  Point p = cell.getLocation();
			
	      int colPosition = p.x /r.width;
			
		  for(x=ROWS-1; x>=0; x--){
			  if(board[x][colPosition] == "0"){
			  this.cell[x][colPosition].setIcon(redCircle);            // insert the red disc on the panel
			  board[x][colPosition] = "2";
			  totalTurn++;
		      break;
		      }else {
				continue;
			   }
		  }
            /*check who is the winner*/
            hWinner = checkHorizontalWinner(board);
    	    vWinner = checkVerticalWinner(board);
    	    dWinner = checkDiagonalWinner(board);
    	    fullGrid = checkTheGrid(board);
    				
    	    if(hWinner || vWinner || dWinner){                     //if the winner is the red player
    	    	String output = "The winner is the red player :)\n Do you want to play again?";
    		    int reply = JOptionPane.showConfirmDialog(null,output,"Game over",JOptionPane.YES_NO_OPTION);
    		    if ( reply == JOptionPane.YES_OPTION) {
    		    	 window.setVisible(false);
        			new MakeTheGrid();
        	        }else {
        	           JOptionPane.showMessageDialog(null, "GOODBYE!!");
        	           System.exit(0);
        	        }
    			
    			} 
    	    if(fullGrid){                                      //the table is full
    	    	String  output = "the grid is full. Do you want to play again?";
	    		int reply = JOptionPane.showConfirmDialog(null,output,"Game over",JOptionPane.YES_NO_OPTION);
	    		if ( reply == JOptionPane.YES_OPTION) {
	    			 window.setVisible(false);
	    			new MakeTheGrid();
	    	        } else {
	    	           JOptionPane.showMessageDialog(null, "GOODBYE!!");
	    	           System.exit(0);
	            }
	    	}
    		
			
		}
		
   }//end actionPerfomed
		
	
	/*method to know who is the next player*/
	public boolean checkTurn(int totalTurn){
		if(totalTurn % 2 == 0){
			
			return true;         //player 1 plays
		}
		else{
			
			return false;      // player 2 plays
		}
	}
	
	
	
   private boolean checkHorizontalWinner(String [][] board) {  //method to check the discs which are in the same row
	    
	   if(totalTurn % 2 == 0){                               //player yellow plays
		   for (int i = 0; i < ROWS; i++) {                 //check every row to find the "1" that belongs to yellow player
			   int hCount = 0;
		       for (int j = 0; j <COLS; j++) {
		    	   if (board[i][j] == "1") {
		                hCount++;                       //check the yellow disc be next to another yellow disc, if its not do hcount=0  
		            }else 
		            	hCount = 0;
		        
		            if (hCount == 4) {             //if there are 4 discs in the same row yellow player is the winner
		             
		        	  return true;
		            }
		       }
		 }
	 } else{
		 for (int i = 0; i < ROWS; i++) {              //player red plays
			 int hCount = 0;
			 for (int j = 0; j < COLS; j++) {         //check every row to find the "2" that belongs to red player
				 if (board[i][j] == "2") {
		                hCount++;                     //check the red disc to be next to another red disc every time, if its not do hcount=0  
		         }else 
		            hCount = 0;
				 if (hCount == 4) {                 //if there are 4 discs in the same row red player is the winner
		            return true;
		         } 
		     }
		}
	 }
	return false;                      //no one is the winner
} //end horizontalWinner
	
	
   private boolean checkVerticalWinner(String[][] board) { //method to check the discs which are in the same column
       if(totalTurn % 2 == 0){                             //player yellow plays
	    	
	    	for (int j = 0; j < COLS; j++) {               //keep the same column and check the row 
		        int vCount = 0;
		        for (int i = 0; i < ROWS; i++) {
		        	
		            if (board[i][j] == "1") {              //check the yellow disc to add one in the same column, if its not do vcount=0  
		                vCount++;
		                
		             }else 
		               vCount = 0;
		        
		             if (vCount == 4) {                   //if there are 4 discs in the same column, yellow player is the winner
		            
		        	   return true;
		                
		             }
		       }
		  }
	 } else{                                              //player red plays
	    	for (int j=0; j<COLS; j++) {
		        int vCount = 0;
		        for (int i=0; i<ROWS; i++) {
		            if (board[i][j]=="2") {
		                vCount++;
		            }else {
		            	vCount = 0;
		            }
		            if (vCount == 4) {
		              return true;    
		            }
		        }
		    }
	    }
	    return false;
	} //end verticalWinner
	   
   private boolean checkDiagonalWinner(String[][] board){               //method to check the discs which are in the same diagonal
	   
	   if(totalTurn % 2 == 0){                                         /*yellow player plays, check the diagonals below the main diagonal
		                                                                   [0][0] [1][1] [2][2] [3][3]...*/
		   
		   for( int rowStart = 0; rowStart < ROWS - 4; rowStart++){    // top-left to bottom-right
		       int dCount = 0;
		       for( int i = rowStart, j = 0; i < ROWS && j < COLS; i++, j++ ){  // begin from the first row [0][0] and move to [1][1]
		                                                                        
		    	   if(board[i][j] == "1"){                                  
		    		   dCount++;
		            
		           }else{
		        	   dCount = 0;
		           }
		    	   if(dCount == 4)
		        	return true; 
		        }
		    }
	   
	       for(int colStart = 1; colStart <= COLS - 4; colStart++){      //check the diagonals above the main diagonal, begin from column [0][1] and move to [1][1]
	    	   int dCount = 0;
	    	   for( int i = 0, j = colStart; i < ROWS && j < COLS; i++, j++ ){  // top-left to bottom-right 
	    		   if(board[i][j] == "1"){
	    			   dCount++;
	    		   }else {
	                   dCount = 0;
	               }
	               if(dCount == 4)
	            	   return true; 
	           }
	       } 
	
	
	
	      for(int rowStart = 5 ; rowStart > ROWS - 4; rowStart--){
	    	  int dCount = 0;
	    	  for( int i = rowStart, j = 0; i > 0 && j < COLS; i--, j++ ){   // bottom-left to top-right
		        if(board[i][j] == "1"){
		            dCount++;
		            
		        }else{
		        	dCount = 0;
		        }
		        if(dCount == 4)
		            return true; 
		        }
		    }
	
	     for(int colStart = 1; colStart <= COLS - 4; colStart++){        // bottom-left to top-right
	         int dCount = 0;
	         for( int i = 5, j = colStart; i > 0 && j < COLS; i--, j++ ){  
	        	 if(board[i][j] == "1"){
	        		 dCount++;
	        	 }else {
	        		 dCount = 0;
	             }
	        	 if(dCount == 4)
	        		 return true; 
	        }
	   } 
    return false;
  
    }else{                                                               //red player plays
	     for(int rowStart = 0; rowStart < ROWS - 4; rowStart++){
	    	 int dCount = 0;
	    	 for( int i = rowStart, j = 0; i < ROWS && j < COLS; i++, j++ ){
		    	if(board[i][j] == "2"){
		            dCount++;
		        }else{
		        	dCount = 0;
		        } 
		        if(dCount == 4)
		            return true; 
		        }
		    }
	   
	   for(int colStart = 1; colStart <= COLS - 4; colStart++){
		   int dCount = 0;
		   for( int i = 0, j = colStart; i < ROWS && j < COLS; i++, j++ ){
	        if(board[i][j] == "2"){
	            dCount++;    
	        }else {
	            dCount = 0;
	        }
	        if(dCount == 4)
	        	return true; 
	        }
	    } 
	  for(int rowStart = 5 ; rowStart > ROWS - 4; rowStart--){
		  int dCount = 0;
		  for(int i = rowStart, j = 0; i > 0 && j < COLS; i--, j++ ){
		        if(board[i][j] == "2"){
		            dCount++;
		            
		        }else{
		        	dCount = 0;
		        }
		        if(dCount == 4)
		            return true; 
		        }
		    }
	
	  for(int colStart = 1; colStart <= COLS - 4; colStart++){
		  int dCount = 0;
		  for( int i = 5, j = colStart; i > 0 && j < COLS; i--, j++ ){
	        if(board[i][j] == "2"){
	            dCount++;
	            
	        }else {
	            dCount = 0;
	        }
	        if(dCount == 4)
	            return true; 
	        }
	   } 
	return false;
  }
}//end checkDiagonalWinner

	
	
	
  private void emptyPanel(JButton [][]cell){             //insert buttons into the cells
	  for(int i=0; i<ROWS; i++){
		for(int j=0; j<COLS; j++){
			cell[i][j] = new JButton(whiteCircle);     //add blank circles in the panel
			cell[i][j].addActionListener(this);      
			panel.add(cell[i][j]);                   //add its cell(button) inside the panel
		}	
	  }	  
  }// end emptyPanel
  
  

  private void initializeArray(String[][] board){     //fill the board with zeros 
	 for(int i=0; i<ROWS; i++){
		 for(int j=0; j<COLS; j++){
			
			board[i][j]="0";
	   }
	 }	
   } //end intializeArray 

  
  
   /*if the grid is full*/
   private boolean checkTheGrid(String board[][]){
	   
	   int counter = 0;
	   for(int i = 0; i < ROWS; i++){
		   for(int j = 0; j < COLS; j++){
			   if(board[i][j] == "1" || board[i][j] == "2") { //check how many yellow and red discs exist in the grid
				   counter++;   
			   }
			   if(counter == 42)                             //if the grid is full
				   return true;
		   }
		   
		}
	   return false;
   } //end checkTheGrid
   
	

}// end MakeTheGrid
