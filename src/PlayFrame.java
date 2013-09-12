import javax.swing.JFrame;

public class PlayFrame extends JFrame 
{

	Display playBoard;

	public static void main(String[]args)
	{

		@SuppressWarnings("unused")
		PlayFrame playChess = new PlayFrame();

	}

	public PlayFrame()
	{
		playBoard = new Display();

		this.pack();
		setTitle("Chess");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //EXIT_ON_CLOSE
		this.add(playBoard);
		this.setSize((playBoard.playBoard.getTileSize()+1)*8+playBoard.playBoard.getBorder()*2,(playBoard.playBoard.getTileSize()+1)*8+22+playBoard.playBoard.getBorder()*2);
		this.setVisible(true);
		this.setResizable(false);

	}

}
