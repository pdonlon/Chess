
public class Piece 
{

	Piece[][] pieceBoard;
	boolean white;
	String pieceType;
	int xCord;
	int yCord;
	boolean[][] canMove = new boolean[8][8];

	public Piece(boolean white, int xCord, int yCord, Piece[][] pieceBoard)
	{

		this.white = white;
		this.xCord = xCord;
		this.yCord = yCord;
		this.pieceBoard = pieceBoard;
	}

	public boolean isWhite()
	{

		return white;
	}

	public void setWhite(boolean a)
	{

		white = a;
	}

	public String getPieceType()
	{

		return pieceType;
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

		boolean valid = true;

		if((x>0 && x<8) && (y>0 && y<8))
			valid = false;

		return valid;
	}

	public boolean sameColor(int x2, int y2)
	{

		boolean same = false;

		if(isEmpty(x2,y2))
			same =true;

		else{
				if(pieceBoard[x2][y2].isWhite()&&pieceBoard[x2][y2].isWhite()
						||(!pieceBoard[xCord][yCord].isWhite()&&!pieceBoard[x2][y2].isWhite()))
					same =true;

		}
		return same;
	}

	public void setHorizontalandVertical(){

//		System.out.println(""+xCord+","+yCord);
//		System.out.println(""+isWhite());
		
		for(int x=(xCord-1); x>=0; x--)
		{
			if(isValid(x,yCord)){
				if(isEmpty(x,yCord) || !sameColor(x, yCord))
					canMove[x][yCord] = true;
				else
					break;
			}
		}

		for(int x=(xCord+1); x<8; x++)
		{
			if(isValid(x,yCord)){
				if(isEmpty(x,yCord) || !sameColor(x, yCord))
					canMove[x][yCord] = true;
				else
					break;
			}
		}

		for(int y=(yCord-1); y>=0; y--)
		{
			if(isValid(xCord,y)){
				if(isEmpty(xCord,y) || !sameColor(xCord, y))
					canMove[xCord][y] = true;
				else
					break;
			}
		}

		for(int y=(yCord+1); y<8; y++)
		{
			if(isValid(xCord,y)){
				if(isEmpty(xCord,y) || !sameColor(xCord, y))
					canMove[xCord][y] = true;
				else
					break;
			}
		}

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
		System.out.println("1");
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

	}
	public boolean isAble(int x, int y){

		boolean able = false;

		if(canMove[x][y]==true)
			able = true;

		return able;
	}

}
