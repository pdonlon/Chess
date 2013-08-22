
public class KnightPiece extends Piece{

	public KnightPiece(boolean white, int xCord, int yCord, Piece[][] pieceBoard) 
	{
		super(white, xCord, yCord, pieceBoard);
		
		this.pieceType = "Knight";
	}

	public void setMoves(int x1, int y1)
	{

		for(int y=-2; y<3; y+=4)
		{
			for(int x=-1; x<2; x+=2)
			{

				int moveX = x+x1;
				int moveY = y+y1;

				if(isValid(moveX,moveY))
				{

					if(isEmpty(moveX,moveY) || !sameColor(moveX,moveY))
						canMove[moveX][moveY] = true;
				}
			}
		}

		for(int y=-1; y<2; y+=2)
		{ 
			for(int x=-2; x<3; x+=4)
			{

				int moveX = x+x1;
				int moveY = y+y1;

				if(isValid(moveX,moveY))
				{

					if(isEmpty(moveX,moveY) || !sameColor(moveX,moveY))
						canMove[moveX][moveY] = true;
				}
			}
		}

	}

}
