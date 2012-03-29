import java.awt.*;
import javax.swing.*;

public class Checkboard extends JFrame{

	public Checkboard(){
	
		setTitle("Scacchiera");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		int size = 8;
		
		this.getContentPane().setLayout(new GridLayout(size,size));
		
		for(int i = 0;i < size*size; i++){
		
			this.getContentPane().add(
				new Tile(
					( (i + (int)(i/size) %2 ) %2 == 0 ) ? Color.WHITE : Color.BLACK
				)
			);
		
		}
		
		pack();
	
	}

	public static void main(String[] a){
	
		new Checkboard().setVisible(true);
	
	}
	
	class Tile extends JPanel{
	
		public Tile(Color c){
			super();
			setBackground(c);
			setPreferredSize(new Dimension(50,50));
		}
	
	}

}