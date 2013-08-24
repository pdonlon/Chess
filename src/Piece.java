
public class Piece 
{

	Piece[][] pieceBoard;
	boolean[][] whiteMoves;
	boolean[][] blackMoves;
	boolean white;
	String pieceType;
	int xCord;
	int yCord;
	int turnCount;
	boolean[][] canMove = new boolean[8][8];
	boolean isKing = false;

	public Piece(boolean white, int xCord, int yCord, Piece[][] pieceBoard, boolean[][] whiteMoves, boolean[][] blackMoves)
	{

		this.white = white;
		this.xCord = xCord;
		this.yCord = yCord;
		this.pieceBoard = pieceBoard;
		this.whiteMoves = whiteMoves;
		this.blackMoves = blackMoves;
	}

	public boolean isWhite()
	{

		return white;
	}

	public void setWhite(boolean a)
	{

		white = a;
	}

	public void setXandYCord(int x, int y){

		xCord = x;
		yCord = y;
	}

	public String getPieceType()
	{

		return pieceType;
	}

	public void setMoves(){

	}

	public void clearMoveSet()
	{

		for(int y=0; y<8; y++)
		{
			for(int x=0; x<8; x++)
			{

				canMove[x][y] = false;

			}
		}

	}
	
	public boolean isKing(){
		
		return isKing;
	}

	public boolean isEmpty(int x, int y)
	{
		boolean empty = false;

		try
		{
			if(pieceBoard[x][y] == null)
				empty = true;
		}
		catch(Exception e)
		{

		}
		return empty;
	}

	public boolean isValid(int x, int y)
	{

		boolean valid = false;

		if((x>=0 && x<8) && (y>=0 && y<8))
			valid = true;

		return valid;
	}

	public boolean sameColor(int x2, int y2)
	{

		boolean same = false;

		if(isEmpty(x2,y2))
			same =true;

		else{
			if(pieceBoard[xCord][yCord].isWhite()&&pieceBoard[x2][y2].isWhite()
					||(!pieceBoard[xCord][yCord].isWhite()&&!pieceBoard[x2][y2].isWhite()))
				same =true;

		}
		return same;
	}
	
	public void addBlackAndWhiteMoves(){

		for(int y=0; y<8; y++){
			for(int x=0; x<8; x++){

				if(white && canMove(x,y) && !whiteMoves[x][y])
					whiteMoves[x][y] = true;

				else if(!white && canMove(x,y) && !whiteMoves[x][y])
					blackMoves[x][y] = true;

			}
		}

	}
	
	public boolean canMove(int x, int y){
		
		boolean can = false;
		try{
			if(canMove[x][y])
				can = true;
		}
		catch(Exception e){
			
		}
		return can;
	}

	public void setHorizontalAndVertical()
	{
		for(int x=(xCord-1); x>=0; x--) //left
		{
			if(isValid(x,yCord))
			{
				if(isEmpty(x,yCord) || !sameColor(x, yCord))
					canMove[x][yCord] = true;
				else
					break;
			}
		}

		for(int x=(xCord+1); x<8; x++) //right
		{
			if(isValid(x,yCord))
			{
				if(isEmpty(x,yCord) || !sameColor(x, yCord))
					canMove[x][yCord] = true;
				else
					break;
			}
		}

		for(int y=(yCord-1); y>=0; y--) //up
		{
			if(isValid(xCord,y))
			{
				if(isEmpty(xCord,y) || !sameColor(xCord, y))
					canMove[xCord][y] = true;
				else
					break;
			}
		}

		for(int y=(yCord+1); y<8; y++) //down
		{
			if(isValid(xCord,y))
			{
				if(isEmpty(xCord,y) || !sameColor(xCord, y))
					canMove[xCord][y] = true;
				else
					break;
			}
		}
		addBlackAndWhiteMoves();
	}

	public void setDiagonal(){

		int xL = (xCord-1); //left
		int yD = (yCord-1); //down
		while(xL >=0 && yD >=0)
		{
			if(isValid(xL,yD))
			{
				if(isEmpty(xL,yD) || !sameColor( xL, yD))
					canMove[xL][yD] = true;
				else
					break;
			}
			xL--;
			yD--;
		}
		xL = (xCord-1); //left
		int yU = (yCord+1); //up
		while(xL >=0 && yU<8)
		{
			if(isValid(xL,yU))
			{
				if(isEmpty(xL,yU) || !sameColor(xL, yU))
					canMove[xL][yU] = true;
				else
					break;
				xL--;
				yU++;
			}
			else
				break;
		}

		int xR = (xCord+1); //right
		yD = (yCord-1); //down
		while(xR<8 && yD >=0)
		{
			if(isValid(xR,yD))
			{
				if(isEmpty(xR,yD) || !sameColor(xR, yD))
					canMove[xR][yD] = true;
				else
					break;
				xR++;
				yD--;
			}
			else
				break;
		}

		xR = (xCord+1); //right
		yU = (yCord+1); //up
		while(xR<8 && yD<8)
		{
			if(isValid(xR,yU))
			{
				if(isEmpty(xR,yU) || !sameColor(xR, yU))
					canMove[xR][yU] = true;
				else
					break;
				xR++;
				yU++;
			}
			else
				break;
		}
		addBlackAndWhiteMoves();
	}
	public boolean isAble(int x, int y){

		boolean able = false;

		if(canMove[x][y]==true)
			able = true;

		return able;
	}

	public void printMoves(){

		for(int y=0; y<8; y++){
			for(int x=0; x<8; x++){

				System.out.print(canMove[x][y]+" ");

			}
			System.out.println();
		}

	}

}
