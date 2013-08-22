import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Play extends JFrame implements ActionListener, MouseMotionListener, MouseListener, KeyListener
{
	
	Board playBoard;
	Display playDisplay;
	
	public static void main(String[]args)
	{
		
		//System.out.print("Hello Ricky");
		
		Play playChess = new Play();
		playChess.playBoard.movePiece(0, 7, 0, 1);

		
		playChess.repaint();
//		System.out.print(playChess.playBoard.boardPiece.);
	}
	
	public Play()
	{
		playBoard = new Board();

		
		playDisplay = new Display();
		
		this.pack();
		setTitle("Chess");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //EXIT_ON_CLOSE
		this.add(playDisplay);
		this.setSize((playBoard.getTileSize()+1)*8,(playBoard.getTileSize()+1)*8+22);
		this.setVisible(true);
		this.setResizable(false);

		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addKeyListener(this);

		
	}
	
	
	
	public class Display extends JPanel
	{ 
		
		public void paintComponent(Graphics g)
		{
			
			super.paintComponent(g);
			this.setBackground(Color.GRAY);
			playBoard.paintBoard(g);
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		
	}
	
}
