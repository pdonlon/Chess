
public class KnightPiece extends Piece{

	public KnightPiece(Board board,boolean white, int xCord, int yCord, Piece[][] pieceBoard, boolean[][]whiteMoves, boolean[][]blackMoves) 
	{
		super(board,white, xCord, yCord, pieceBoard,whiteMoves,blackMoves);
		
		this.pieceType = "Knight";
	}

	public void setMoves()
	{
		for(int y=-2; y<3; y+=4)
		{
			for(int x=-1; x<2; x+=2)
			{

				int moveX = x+xCord;
				int moveY = y+yCord;

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

				int moveX = x+xCord;
				int moveY = y+yCord;

				if(isValid(moveX,moveY))
				{

					if(isEmpty(moveX,moveY) || !sameColor(moveX,moveY))
						canMove[moveX][moveY] = true;
				}
			}
		}
		addBlackAndWhiteMoves();
	}
	
	public void setImage()
	{
		super.setImage();
		pieceImage = pieceImage.getSubimage(xSpacing*8, ySpacing, 70, 70);
	}
	
}
