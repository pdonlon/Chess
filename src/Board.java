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

	public Board()
	{
		board = new Piece[8][8];
		initializeBoard();

	}

	public void movePiece()
	{

		if(board[x1][y1].isAble(x2,y2)&& !sameSpot())
		{
			board[x2][y2] = board[x1][y1];
			board[x1][y1] = null;
			System.out.print("works"+ x2+ ", "+y2);
			board[x2][y2].setXandYCord(x2, y2);
			clearAllMoves();
			initializeMoves();
			turnCount++;
		}
		else
			System.out.println("oops");

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

	public boolean isWhite(int x, int y){
		boolean white = false;
		
		if(board[x][y].isWhite())
			white = true;
		
		return white;
	}
	
	public boolean simulateMove()
	{ //checks if move will put you in check
		//TODO
		boolean check = false;

		return check;
	}

	public void showMoves(int x, int y){

		board[x][y].printMoves();
	}

	public void initializeBoard()
	{

		for(int y=0; y<9; y+=7)
		{ //rook
			for(int x=0; x<9; x+=7)
			{

				board[x][y] = new RookPiece(false,x,y,board);
				//TODO change this to be global setMoves
			}
		}

		for(int y=0; y<9; y+=7)
		{ //knight
			for(int x=1; x<8; x+=5)
			{

				board[x][y] = new KnightPiece(false,x,y,board);
			}
		}

		for(int y=0; y<9; y+=7)
		{ //bishop
			for(int x=2; x<8; x+=3)
			{

				board[x][y] = new BishopPiece(false,x,y,board);
			}
		}

		for(int y=0; y<9; y+=7)
		{ //queen
			int x = 3;

			board[x][y] = new QueenPiece(false,x,y,board);

		}

		for(int y=0; y<9; y+=7)
		{ //king
			int x = 4;

			board[x][y] = new KingPiece(false,x,y,board);
		}

		for(int y=1; y<7; y+=5)
		{ //pawn
			for(int x=0; x<8; x++)
			{

				board[x][y] = new PawnPiece(false,x,y,board);
			}
		}

		for(int y=6; y<8; y++){
			for(int x=0; x<8; x++){

				board[x][y].setWhite(true);

			}
		}
		initializeMoves();
	}

	public void initializeMoves()
	{

		for(int y=0; y<8; y++)
		{
			for(int x=0; x<8; x++)
			{

				if(!isEmpty(x,y))
					board[x][y].setMoves();

			}
		}

	}

	public void clearAllMoves(){

		for(int y=0; y<8; y++)
		{
			for(int x=0; x<8; x++)
			{

				if(!isEmpty(x,y))
					board[x][y].clearMoveSet();

			}
		}

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
	
	public void paintBoard(Graphics g)
	{

		int tileMarker=0;
		int ySpacing =0;
		if(turnCount%2==1)
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
					if(board[x][y].isWhite())
					{
						g.setColor(Color.WHITE);
						g.fillOval(xSpacing+tileSize*1/4-(tileSize*1/8), ySpacing+tileSize*1/4-(tileSize*1/8), tileSize*3/4, tileSize*3/4);
						g.setColor(Color.BLACK);
					}
					else
					{
						g.setColor(Color.BLACK);
						g.fillOval(xSpacing+tileSize*1/4-(tileSize*1/8), ySpacing+tileSize*1/4-(tileSize*1/8), tileSize*3/4, tileSize*3/4);
						g.setColor(Color.WHITE);
					}
					g.drawString(""+board[x][y].getPieceType().charAt(0), xSpacing+tileSize/2, ySpacing+tileSize/2);
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

	}

	//check if the piece can go there (rules)
	//will another piece block it? (excludes knight)
	//check if the move will put you in check

}
