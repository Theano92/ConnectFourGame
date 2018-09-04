package connectFour;
import javax.swing.JOptionPane;

public class PlayerDetails {
	String playerName;
	String colorDisc;
	
	
	public PlayerDetails(){                      //insert players' names
		for(int i=1; i<=2; i++){
		playerName=JOptionPane.showInputDialog("player "+i+ " : Enter your name:");
		}
	}
	
	public String getName(){                  //return the name of the player
		return this.playerName;
	}
	
	public void setName(String playerName){
		this.playerName = playerName;
	}
	
	public void chooseColor(){             //the player choose a colour
		
	}
	

}
