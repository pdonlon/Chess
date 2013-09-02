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
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Play extends JFrame implements ActionListener, MouseMotionListener, MouseListener, KeyListener
{
	Board playBoard;
	Display playDisplay;

	public static void main(String[]args)
	{

		Play playChess = new Play();
		
	}

	public Play()
	{
		playBoard = new Board(this);

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

		if(playBoard.justMoved)
			return;
		
		int x = (e.getX()+1)/(playBoard.tileSize+1);
		int y = (e.getY()-22)/(playBoard.tileSize+1);

		if(playBoard.getTurnCount()%2==1)
		{
			x = playBoard.reflectNumber(x);
			y = playBoard.reflectNumber(y);
		}

		if(!playBoard.isEmpty(x,y)){

			if(playBoard.click && (playBoard.getClickX() == x) && (playBoard.getClickY() == y))
			playBoard.setClick(false);
			else
				playBoard.setClick(true);
			//playBoard.newbool = true;
			
			if((playBoard.isWhite(x, y)&&playBoard.getTurnCount()%2==1)
					|| (!playBoard.isWhite(x, y)&&playBoard.getTurnCount()%2==0))
			{
				playBoard.setClick(false);
			}
			else
				playBoard.setClickCoordinates(x,y);

		}
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		playBoard.setJustMoved(false);

		boolean whiteTurn = (playBoard.turnCount%2 == 0);

		int x = (e.getX()+1)/(playBoard.tileSize+1);
		int y = (e.getY()-22)/(playBoard.tileSize+1);
		
		if(!whiteTurn){
			x = playBoard.reflectNumber(x);
			y = playBoard.reflectNumber(y);
		}
			
		
		if( (playBoard.isWhite(x, y)&&!whiteTurn) || (!playBoard.isWhite(x, y)&&whiteTurn))
			return;
		
			if(!playBoard.isEmpty(x, y))
			{
				playBoard.setX1(x);
				playBoard.setY1(y);	
			}

//		}
		//System.out.println(playBoard.getX1() + ", " + playBoard.getY1());
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		playBoard.setDragging(false);
		
		int x = (e.getX()+1)/(playBoard.tileSize+1);
		int y = (e.getY()-22)/(playBoard.tileSize+1);

		if(playBoard.getTurnCount()%2==1){
			x = playBoard.reflectNumber(x);
			y = playBoard.reflectNumber(y);
		}

		playBoard.setX2(x);
		playBoard.setY2(y);

		playBoard.movePiece();
		
		repaint();
		
		if(playBoard.gameIsOver())
		{
			JOptionPane.showMessageDialog(this, "Checkmate! "+ playBoard.getWinner() + " wins!");
			playBoard.setStopPainting(true);
			repaint();
			//TODO prompt a new game
		}
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
		playBoard.setDragging(true);
		playBoard.setDraggingCoordinates(e.getX(),e.getY());
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) 
	{
		//System.out.println(playBoard.newbool);

	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub

	}



}
