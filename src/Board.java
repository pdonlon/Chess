import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;


public class Board 
{	
	static BufferedImage boardImage;
	Piece[][] board;
	Play boardPlay;
	int[] reflectNumbers = {0,1,2,3,4,5,6,7};
	int tileSize =60;
	int boarderSize = 40;
	Piece boardPiece;
	int turnCount = 0;
	int x1;
	int y1;
	int x2;
	int y2;

	int clickX;
	int clickY;
	int dragCordX;
	int dragCordY;

	boolean blackCheck = false;
	boolean whiteCheck = false;
	boolean checkmate = false;
	boolean justMoved = false;
	boolean gameOver = false;
	
	boolean[][] whiteMoves;
	boolean[][] blackMoves;
	boolean click = false;
	boolean dragging = false;
	boolean stopPainting = false;
	
	String winner = "";

	public Board(Play game, BufferedImage img)
	{
		boardPlay = game;
		
		boardImage = img;
		whiteMoves = new boolean[8][8];
		blackMoves = new boolean[8][8];
		initializeBlackAndWhiteMoves();

		board = new Piece[8][8];
		initializeBoard();
		//printWhiteMoves();
	}

	public void movePiece()
	{
		if(board[x1][y1].isAble(x2,y2)&& !sameSpot())
		{
			if(board[x1][y1].validMove(x2, y2))
			{
				boolean white = turnCount%2==0;
				
				if(castle(x1,y1,x2,y2)!=0)
				{
					board[castle(x1,y1,x2,y2)][getColorValueKing(white)] = 
							new RookPiece(this,white,castle(x1,y1,x2,y2),getColorValueKing(white),board,whiteMoves,blackMoves);
					
					if(x2<5)
						board[0][getColorValueKing(white)] = null;
					else
						board[7][getColorValueKing(white)] = null;
				}
				
				board[x2][y2] = board[x1][y1];
				board[x1][y1] = null;
				board[x2][y2].setXandYCord(x2, y2);
	
				

				if(pawnAtEnd()){
					
					Object[] options = {"Knight","Bishop","Rook","Queen"};
					
					int n = JOptionPane.showOptionDialog(boardPlay,"Select a piece","Chess",JOptionPane.YES_NO_CANCEL_OPTION,
					    JOptionPane.QUESTION_MESSAGE,null,options,options[3]);
					makePiece(x2,y2,n,white);
				}
				
				board[x2][y2].setMoved(true);
				
				resetMoves(white); //no restrictions
				resetMoves(!white); //restrictions
				inCheck(!white);

				justMoved = true;
				if(!checkmate)
					turnCount++;
			}
		}
	}

	public boolean sameSpot()
	{
		boolean same = false;

		if(x1 == x2 && y1 == y2)
			same = true;

		return same;
	}

	public Piece[][] getBoard()
	{ 

		return board;

	}

	public int getBoarder()
	{
		return boarderSize;
	}
	
	public int getTileSize()
	{

		return tileSize;
	}

	public int getTurnCount(){

		return turnCount;
	}

	public void setX1(int a){

		x1 = a;
	}

	public int getX1(){

		return x1;
	}

	public void setX2(int a){

		x2 = a;
	}

	public void setY1(int a){

		y1 = a;
	}

	public int getY1(){

		return y1;
	}
	
	public String getWinner()
	{
		return winner;
	}
	
	public boolean gameIsOver()
	{
		return gameOver;
	}

	public void setY2(int a){

		y2 = a;
	}

	public void setDragging(boolean a){

		dragging = a;
	}

	public void setDraggingCoordinates(int x, int y)
	{
		dragCordX = x;
		dragCordY = y;
	}

	public int getClickX(){

		return clickX;
	}

	public int getClickY(){

		return clickY;
	}

	public void setClick(boolean a){

		click = a;
	}

	public void setJustMoved(boolean a)
	{
		justMoved=a;
	}
	
	public void setClickCoordinates(int x, int y)
	{
		clickX = x;
		clickY = y;
	}

	public boolean isWhite(int x, int y){
		boolean white = false;

		if(!isEmpty(x,y)&&board[x][y].isWhite())
			white = true;

		return white;
	}

	private void makePiece(int x, int y, int num, boolean white)
	{
		Piece[] pieces = new Piece[4];
		pieces[0] = new KnightPiece(this,white,x,y,board,whiteMoves,blackMoves);
		pieces[1] = new BishopPiece(this,white,x,y,board,whiteMoves,blackMoves);
		pieces[2] = new RookPiece(this,white,x,y,board,whiteMoves,blackMoves);
		pieces[3] = new QueenPiece(this,white,x,y,board,whiteMoves,blackMoves);
		
		board[x][y] = pieces[num];
		
	}
	
	public void showMoves(int x, int y){

		board[x][y].printMoves();
	}

	public boolean moveSuccessful(){

		boolean white = (turnCount%2==0);

		boolean successful = true;
		Piece tempPiece1 = board[x1][y1];
		Piece tempPiece2 = board[x2][y2];

		board[x2][y2] = board[x1][y1];
		board[x1][y1] = null;

		if(white)	//white move
		{ 
			resetMoves(!white);

			if(inCheck(white)) //still in check
			{
				board[x1][y1] = tempPiece1; //undo move
				board[x2][y2] = tempPiece2;
				resetMoves(!white);
				successful = false;
			}
			else //still have to undo to the last black move initialization 
			{
				board[x1][y1] = tempPiece1; 
				board[x2][y2] = tempPiece2;
				resetMoves(!white);

				board[x2][y2] = board[x1][y1];
				board[x1][y1] = null;
				board[x2][y2].setMoved(true);
			}

		}
		if(successful)
			board[x2][y2].setXandYCord(x2, y2);
		return successful;
	}

	public void setStopPainting(boolean a)
	{
		stopPainting = a;
	}
	
	public void resetMoves(boolean color)
	{
		clearMoves(color);
		clearMoveSets(color);
		initializeMoves(color);
	}

	public void initializeBoard()
	{
		for(int y=0; y<9; y+=7)
		{ //rook
			for(int x=0; x<9; x+=7)
			{
				
				board[x][y] = new RookPiece(this,false,x,y,board,whiteMoves,blackMoves);
				
				
			}
		}

		for(int y=0; y<9; y+=7)
		{ //knight
			for(int x=1; x<8; x+=5)
			{

				board[x][y] = new KnightPiece(this,false,x,y,board,whiteMoves,blackMoves);
			}
		}

		for(int y=0; y<9; y+=7)
		{ //bishop
			for(int x=2; x<8; x+=3)
			{

				board[x][y] = new BishopPiece(this,false,x,y,board,whiteMoves,blackMoves);
			}
		}

		for(int y=0; y<9; y+=7)
		{ //queen
			int x = 3;

			board[x][y] = new QueenPiece(this,false,x,y,board,whiteMoves,blackMoves);

		}

		for(int y=0; y<9; y+=7)
		{ //king
			int x = 4;

			board[x][y] = new KingPiece(this,false,x,y,board,whiteMoves,blackMoves);
		}

		for(int y=1; y<7; y+=5)
		{ //pawn
			for(int x=0; x<8; x++)
			{

				board[x][y] = new PawnPiece(this,false,x,y,board,whiteMoves,blackMoves);
			}
		}

		for(int y=6; y<8; y++){
			for(int x=0; x<8; x++){

				if(!isEmpty(x,y))
				board[x][y].setWhite(true);

			}
		}		
		initializeMoves(true);
		initializeMoves(false);
		initializeImages();
	}
	
	public void initializeImages()
	{
		for(int y=0; y<8; y++)
		{
			for(int x=0; x<8; x++)
			{
				if(!isEmpty(x,y))
						board[x][y].setImage();
			}
		}
	}

	public void initializeMoves(boolean white)
	{

		for(int y=0; y<8; y++)
		{
			for(int x=0; x<8; x++)
			{
				if(white){
					if(!isEmpty(x,y) && board[x][y].isWhite())
						board[x][y].setMoves();
				}
				else
					if(!isEmpty(x,y)&&!board[x][y].isWhite())
						board[x][y].setMoves();

			}
		}

	}

	public void initializeBlackAndWhiteMoves()
	{	
		for(int y=0; y<8; y++)
		{
			for(int x=0; x<8; x++)
			{

				whiteMoves[x][y] = false;
				blackMoves[x][y] = false;

			}
		}

	}
	
	public int getColorValueKing(boolean white)
	{
		int cValue;

		if(white)
			cValue = 7;
		else
			cValue = 0;

		return cValue;
	}
	
	public int castle(int x1, int y1, int x2, int y2){
		
		int castle = 0;
		
		if(board[x1][y1].isKing)
		{
			if(x2 == 2)
				castle = 3;
			else if(x2 == 6)
				castle = 5;
		}
		
		return castle;
	}

	public void clearMoveSets(boolean colorWhite)
	{
		for(int y=0; y<8; y++)
		{
			for(int x=0; x<8; x++)
			{
				if(colorWhite){
					if(!isEmpty(x,y)&&board[x][y].isWhite())
						board[x][y].clearMoveSet();
				}
				else
					if(!isEmpty(x,y)&&!board[x][y].isWhite())
						board[x][y].clearMoveSet();


			}
		}

	}

	public void clearMoves(boolean colorWhite)
	{
		for(int y=0; y<8; y++)
		{
			for(int x=0; x<8; x++)
			{
				if(colorWhite)
					whiteMoves[x][y] = false;
				else
					blackMoves[x][y] = false;

			}
		}

	}

	public boolean inCheck(boolean colorWhite)
	{		
		boolean check = false;
		for(int y=0; y<8; y++)
		{
			for(int x=0; x<8; x++)
			{

				if(colorWhite&&!isEmpty(x,y)&&board[x][y].isKing())
				{
					if(board[x][y].isWhite() && blackMoves[x][y])
						check = true;
				}
				else if(!colorWhite&&!isEmpty(x,y)&&board[x][y].isKing())
				{
					if(!board[x][y].isWhite() && whiteMoves[x][y])
						check = true;
				}
			}
		}
		return check;
	}


	public boolean isEmpty(int x, int y)
	{
		boolean empty = false;

		try
		{
			if(board[x][y] == null)
				empty = true;
		}
		catch(Exception e){

		}
		return empty;
	}

	public boolean isValid(int x, int y)
	{

		boolean valid = true;

		if((x>=0 && x<8) && (y>=0 && y<8))
			valid = false;

		return valid;
	}

	public int reflectNumber(int num){

		int searchCount = 0;
		for(int i=0; i<reflectNumbers.length; i++){
			if(reflectNumbers[i]==num)
				break;
			else
				searchCount++;
		}

		int reflectedNum = reflectNumbers[7-searchCount];
		return reflectedNum;
	}

	public boolean validMove(int x, int y)
	{
		boolean valid = board[x1][y1].validMove(x, y);
		
		return valid;
	}
	
	public boolean pawnAtEnd()
	{
		boolean end = false;
		
			for(int x=0; x<8; x++)
			{
				if((!isEmpty(x,0) && board[x][0].isPawn()) || (!isEmpty(x,7) && board[x][7].isPawn()))
					end = true;
			}
			
			return end;
	}

	public void paintBoard(Graphics g)
	{

		int tileMarker=0;
		int ySpacing =boarderSize;
		if(turnCount%2==1) //Black Move
			ySpacing = (tileSize+1)*7+boarderSize;
		for(int y=0; y<8; y++)
		{
			int xSpacing =boarderSize;
			if(turnCount%2==1)
				xSpacing = (tileSize+1)*7+boarderSize;

			for(int x=0; x<8; x++)
			{

				if(tileMarker%2==0)
					g.setColor(Color.LIGHT_GRAY);
				else
					g.setColor(Color.GRAY);

				g.fillRect(xSpacing, ySpacing, tileSize+1, tileSize+1);

				if(!isEmpty(x,y))
				{
				
					 ((Graphics2D) g).drawImage(board[x][y].getImage(), xSpacing, ySpacing, boardPlay);
//					g.drawString(""+board[x][y].getPieceType().charAt(0), xSpacing+tileSize/2, ySpacing+tileSize/2);
				}

				//				else if(isEmpty(x,y))
				//				{
				//					g.setColor(Color.CYAN);
				//					if(whiteMoves[x][y]&&turnCount%2==0)
				//						g.fillOval(xSpacing+tileSize*1/4-(tileSize*1/8), ySpacing+tileSize*1/4-(tileSize*1/8), tileSize*3/4, tileSize*3/4);
				//					else if(blackMoves[x][y]&&turnCount%2==1){
				//						g.setColor(Color.RED);
				//						g.fillOval(xSpacing+tileSize*1/4-(tileSize*1/8), ySpacing+tileSize*1/4-(tileSize*1/8), tileSize*3/4, tileSize*3/4);
				//					}
				//				}

				else if(isEmpty(x,y)&&click){
					g.setColor(Color.CYAN);
					if(board[clickX][clickY].validMove(x, y))
					{
						g.fillOval(xSpacing+tileSize*1/4-(tileSize*1/8), ySpacing+tileSize*1/4-(tileSize*1/8), tileSize*3/4, tileSize*3/4);
					}


				}

				if(turnCount%2==1)
					xSpacing-=(tileSize+1);
				else
					xSpacing+=(tileSize+1);	
				tileMarker++;	

			}
			if(turnCount%2==1)
				ySpacing-=(tileSize+1);
			else
				ySpacing+=(tileSize+1);
			tileMarker++;
		}

		if(click)
			click = false;
		if(dragging)
		{
			if(board[x1][y1].isWhite())
				g.setColor(Color.WHITE);
			else
				g.setColor(Color.BLACK);
			g.fillOval(dragCordX-(tileSize*2/7), dragCordY-(tileSize*5/7), tileSize*3/4, tileSize*3/4);

		}
	
		if(!stopPainting)
		{
		
		if(checkmate())
		{
			g.setColor(new Color(255, 0, 0,125));
			g.fillRect(0, 0, (getTileSize()+1)*8 + getBoarder()*2,(getTileSize()+1)*8+22+getBoarder()*2);
		}
		
		else if (inCheck(false) || inCheck(true))
		{
			g.setColor(new Color(255, 0, 0,50));
			g.fillRect(0, 0, (getTileSize()+1)*8 + getBoarder()*2,(getTileSize()+1)*8+22+getBoarder()*2);
		}
		}
		
	}

	public boolean checkmate()
	{
		int whiteMoves = 0;
		int blackMoves = 0;
		
		for(int y=0; y<8; y++)
		{
			for(int x=0; x<8; x++)
			{
				for(int i=0; i<8; i++)
				{
					for(int j=0; j<8; j++)
					{
						if(!isEmpty(j,i))
						{
						
						if(isWhite(j,i)&&board[j][i].validMove(x,y))
							whiteMoves++;
						else if(!isWhite(j,i)&&board[j][i].validMove(x,y))
							blackMoves++;
						}
					}
				}
			}
		}
		
		if(whiteMoves == 0 || blackMoves == 0)
		{
			checkmate = true;
			if(whiteMoves == 0)
				winner = "Black";
			else
				winner = "White";
			
		}
		
		return checkmate;
	}
}

