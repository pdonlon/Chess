import java.awt.Color;
import java.awt.Graphics;


public class Board {

	Piece[][] board;
	int[] reflectNumbers = {0,1,2,3,4,5,6,7};
	int tileSize =60;
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

	boolean[][] whiteMoves;
	boolean[][] blackMoves;
	boolean click = false;
	boolean dragging = false;

	public Board()
	{
		whiteMoves = new boolean[8][8];
		blackMoves = new boolean[8][8];
		initializeBlackAndWhiteMoves();

		board = new Piece[8][8];
		initializeBoard();
		printWhiteMoves();
	}

	public void movePiece()
	{
		if(board[x1][y1].isAble(x2,y2)&& !sameSpot())
		{

			if(moveSuccessful())
			{

				if(turnCount%2==0) //White Move
				{
					resetWhiteMoves();
					resetBlackMoves();
					blackInCheck();
				}

				else if(turnCount%2==1) //Black Move
				{
					resetBlackMoves();
					resetWhiteMoves();
					whiteInCheck();
				}

				System.out.println("Black is in check "+blackInCheck());
				System.out.println("White is in check "+whiteInCheck());
				//printWhiteMoves();

				//			if(inCheck()){
				//				System.out.println("CHECK");
				//			}
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

	public void setClickCoordinates(int x, int y)
	{
		clickX = x;
		clickY = y;
	}
	
	public boolean isWhite(int x, int y){
		boolean white = false;

		if(board[x][y].isWhite())
			white = true;

		return white;
	}

	public void showMoves(int x, int y){

		board[x][y].printMoves();
	}

	public boolean moveSuccessful(){

		boolean successful = true;
		Piece tempPiece1 = board[x1][y1];
		Piece tempPiece2 = board[x2][y2];

		board[x2][y2] = board[x1][y1];
		board[x1][y1] = null;

		if(turnCount%2==0)	//white move
		{ 
			resetBlackMoves();

			if(whiteInCheck()) //still in check
			{
				board[x1][y1] = tempPiece1; //undo move
				board[x2][y2] = tempPiece2;
				resetBlackMoves();
				successful = false;
			}
			else //still have to undo to the last black move initialization 
			{
				board[x1][y1] = tempPiece1; 
				board[x2][y2] = tempPiece2;
				resetBlackMoves();

				board[x2][y2] = board[x1][y1];
				board[x1][y1] = null;
				board[x2][y2].setMoved(true);
			}
			
		}

		else if(turnCount%2==1)	//black move
		{ 
			resetWhiteMoves();

			if(blackInCheck()) //still in check
			{
				board[x1][y1] = tempPiece1; //undo move
				board[x2][y2] = tempPiece2;
				resetWhiteMoves();
				successful = false;
			}
			else //still have to undo to the last white move initialization 
			{
				board[x1][y1] = tempPiece1; 
				board[x2][y2] = tempPiece2;
				resetWhiteMoves();

				board[x2][y2] = board[x1][y1];
				board[x1][y1] = null;
				board[x2][y2].setMoved(true);
			}
		}

		if(successful)
			board[x2][y2].setXandYCord(x2, y2);
		return successful;
	}
	
	public void resetBlackMoves()
	{
		clearBlackMoves();
		clearBlackMoveSet();
		initializeBlackMoves();
	}
	
	public void resetWhiteMoves()
	{
		clearWhiteMoves();
		clearWhiteMoveSet();
		initializeWhiteMoves();
	}

	public void initializeBoard()
	{
		for(int y=0; y<9; y+=7)
		{ //rook
			for(int x=0; x<9; x+=7)
			{

				board[x][y] = new RookPiece(false,x,y,board,whiteMoves,blackMoves);

			}
		}

		for(int y=0; y<9; y+=7)
		{ //knight
			for(int x=1; x<8; x+=5)
			{

				board[x][y] = new KnightPiece(false,x,y,board,whiteMoves,blackMoves);
			}
		}

		for(int y=0; y<9; y+=7)
		{ //bishop
			for(int x=2; x<8; x+=3)
			{

				board[x][y] = new BishopPiece(false,x,y,board,whiteMoves,blackMoves);
			}
		}

		for(int y=0; y<9; y+=7)
		{ //queen
			int x = 3;

			board[x][y] = new QueenPiece(false,x,y,board,whiteMoves,blackMoves);

		}

		for(int y=0; y<9; y+=7)
		{ //king
			int x = 4;

			board[x][y] = new KingPiece(false,x,y,board,whiteMoves,blackMoves);
		}

		for(int y=1; y<7; y+=5)
		{ //pawn
			for(int x=0; x<8; x++)
			{

				board[x][y] = new PawnPiece(false,x,y,board,whiteMoves,blackMoves);
			}
		}

		for(int y=6; y<8; y++){
			for(int x=0; x<8; x++){

				board[x][y].setWhite(true);

			}
		}		
		initializeWhiteMoves();
		initializeBlackMoves();
	}

	public void initializeWhiteMoves()
	{

		for(int y=0; y<8; y++)
		{
			for(int x=0; x<8; x++)
			{
				if(!isEmpty(x,y)&&board[x][y].isWhite())
					board[x][y].setMoves();

			}
		}

	}

	public void initializeBlackMoves()
	{
		for(int y=0; y<8; y++)
		{
			for(int x=0; x<8; x++)
			{
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

	public void clearBlackMoveSet()
	{
		for(int y=0; y<8; y++)
		{
			for(int x=0; x<8; x++)
			{

				if(!isEmpty(x,y)&&!board[x][y].isWhite())
					board[x][y].clearMoveSet();

			}
		}

	}

	public void clearWhiteMoveSet()
	{
		for(int y=0; y<8; y++)
		{
			for(int x=0; x<8; x++)
			{

				if(!isEmpty(x,y)&&board[x][y].isWhite())
					board[x][y].clearMoveSet();

			}
		}

	}

	public void clearWhiteMoves()
	{
		for(int y=0; y<8; y++)
		{
			for(int x=0; x<8; x++)
			{
				whiteMoves[x][y] = false;

			}
		}

	}

	public void clearBlackMoves()
	{
		for(int y=0; y<8; y++)
		{
			for(int x=0; x<8; x++)
			{
				blackMoves[x][y] = false;
			}
		}
	}

	public boolean whiteInCheck()
	{		
		boolean wCheck = false;
		for(int y=0; y<8; y++)
		{
			for(int x=0; x<8; x++)
			{
				if(!isEmpty(x,y)&&board[x][y].isKing())
				{

					if(board[x][y].isWhite() && blackMoves[x][y])
						wCheck = true;

				}

			}
		}
		whiteCheck = wCheck;
		return whiteCheck;
	}

	public boolean blackInCheck()
	{
		boolean bCheck = false;
		for(int y=0; y<8; y++)
		{
			for(int x=0; x<8; x++)
			{
				if(!isEmpty(x,y)&&board[x][y].isKing())
				{	
					if(!board[x][y].isWhite() && whiteMoves[x][y])
						bCheck = true;
				}

			}
		}
		blackCheck = bCheck;
		return blackCheck;
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

//	public void setMoved(boolean a)
//	{
//		
//	}
	
	public void paintBoard(Graphics g)
	{

		int tileMarker=0;
		int ySpacing =0;
		if(turnCount%2==1) //Black Move
			ySpacing = (tileSize+1)*7;
		for(int y=0; y<8; y++)
		{
			int xSpacing =0;
			if(turnCount%2==1)
				xSpacing = (tileSize+1)*7;

			for(int x=0; x<8; x++)
			{

				if(tileMarker%2==0)
					g.setColor(Color.LIGHT_GRAY);
				else
					g.setColor(Color.GRAY);

				g.fillRect(xSpacing, ySpacing, tileSize+1, tileSize+1);

				if(!isEmpty(x,y))
				{
					if((board[x][y].isWhite())&&((!dragging)||(x1!=x)||(y1!=y)))

					{
						g.setColor(Color.WHITE);
						g.fillOval(xSpacing+tileSize*1/4-(tileSize*1/8), ySpacing+tileSize*1/4-(tileSize*1/8), tileSize*3/4, tileSize*3/4);
						g.setColor(Color.BLACK);
					}
					else if((!board[x][y].isWhite())&&((!dragging)||(x1!=x)||(y1!=y)))
					{
						g.setColor(Color.BLACK);
						g.fillOval(xSpacing+tileSize*1/4-(tileSize*1/8), ySpacing+tileSize*1/4-(tileSize*1/8), tileSize*3/4, tileSize*3/4);
						g.setColor(Color.WHITE);
					}
					g.drawString(""+board[x][y].getPieceType().charAt(0), xSpacing+tileSize/2, ySpacing+tileSize/2);
				}

				//				else if(isEmpty(x,y))
				//				{
				//					g.setColor(Color.CYAN);
				//					if(whiteMoves[x][y]&&turnCount%2==1)
				//						g.fillOval(xSpacing+tileSize*1/4-(tileSize*1/8), ySpacing+tileSize*1/4-(tileSize*1/8), tileSize*3/4, tileSize*3/4);
				//					else if(blackMoves[x][y]&&turnCount%2==0){
				//						g.setColor(Color.RED);
				//						g.fillOval(xSpacing+tileSize*1/4-(tileSize*1/8), ySpacing+tileSize*1/4-(tileSize*1/8), tileSize*3/4, tileSize*3/4);
				//					}
				//				}
				
				else if(isEmpty(x,y)&&click){
					g.setColor(Color.CYAN);
					if(board[clickX][clickY].isAble(x, y))
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

	}

	public void printWhiteMoves()
	{
		for(int y=0; y<8; y++)
		{
			for(int x=0; x<8; x++)
			{
				if(whiteMoves[x][y])
					System.out.print(whiteMoves[x][y]+"  ");
				else
					System.out.print(whiteMoves[x][y]+" ");

			}
			System.out.println();
		}
		System.out.println();
	}
}

