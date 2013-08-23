
public class PawnPiece extends Piece
{

	public PawnPiece(boolean white, int xCord, int yCord, Piece[][] pieceBoard) 
	{
		super(white, xCord, yCord, pieceBoard);

		this.pieceType = "Pawn";
	}

	public void setMoves()
	{

		if(isWhite())
		{
			int y=(yCord-1); //up
			if(isValid(xCord,y))
			{
				if(isEmpty(xCord,y))
					canMove[xCord][y] = true;

				if(isValid(xCord+1,y)&&!isEmpty(xCord+1,y)&&(!pieceBoard[xCord+1][y].isWhite()))
					canMove[xCord+1][y] = true;

				if(isValid(xCord-1,y)&&!isEmpty(xCord-1,y)&&(!pieceBoard[xCord-1][y].isWhite()))
					canMove[xCord-1][y] = true;

			}

		}

		else
		{
			int y=(yCord+1);
			if(isValid(xCord,y))
			{
				if(isEmpty(xCord,y))
					canMove[xCord][y] = true;

				if(isValid(xCord+1,y)&&!isEmpty(xCord+1,y)&&(pieceBoard[xCord+1][y].isWhite()))
					canMove[xCord+1][y] = true;

				if(isValid(xCord-1,y)&&!isEmpty(xCord-1,y)&&(pieceBoard[xCord-1][y].isWhite()))
					canMove[xCord-1][y] = true;
			}
		}
	}


}
