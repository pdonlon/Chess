
public class PawnPiece extends Piece
{

	public PawnPiece(Board board,boolean white, int xCord, int yCord, Piece[][] pieceBoard, boolean[][]whiteMoves, boolean[][]blackMoves) 
	{
		super(board,white, xCord, yCord, pieceBoard,whiteMoves,blackMoves);

		this.pieceType = "Pawn";
		isPawn = true;
	}

	public void setMoves()
	{
		getColorValuePawn();
		int cVal = getColorValuePawn();
			int y=(yCord+cVal); //for black and white
			if(isValid(xCord,y))
			{
				if(isEmpty(xCord,y))
					canMove[xCord][y] = true;

				if(isValid(xCord+1,y)&& !isEmpty(xCord+1,y) && !sameColor(xCord+1, y))
				{
					canMove[xCord+1][y] = true;
					addMove(xCord+1,y);
				}
					

				if(isValid(xCord-1,y) && !isEmpty(xCord-1,y) && !sameColor(xCord-1, y))
				{
					canMove[xCord-1][y] = true;
					addMove(xCord-1,y);
				}
				
				if(!moved&&isEmpty(xCord,y)&&isEmpty(xCord,y+cVal))
						canMove[xCord][y+cVal] = true;
				
				if(isValid(xCord-1,yCord)&&!sameColor(xCord-1,yCord)&&pieceBoard[xCord-1][yCord].justMovedPawn())
					canMove[xCord-1][yCord+cVal] = true;
				
				if(isValid(xCord+1,yCord)&&!sameColor(xCord+1,yCord)&&pieceBoard[xCord+1][yCord].justMovedPawn())
					canMove[xCord+1][yCord+cVal] = true;
			}

	}
	
	public void setImage()
	{
		super.setImage();
		pieceImage = pieceImage.getSubimage(xSpacing*10-9, ySpacing, 70, 70);
	}
	
}
