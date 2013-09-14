import javax.swing.JApplet;

public class PlayApplet extends JApplet{

	Display playBoard;

	public PlayApplet()
	{
		playBoard = new Display();

		this.add(playBoard);
		this.setSize((playBoard.playBoard.getTileSize()+1)*8+playBoard.playBoard.getBorder()*2,(playBoard.playBoard.getTileSize()+1)*8+22+playBoard.playBoard.getBorder()*2);
		this.setVisible(true);
		this.setFocusable(true);
	}
}
