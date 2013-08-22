
public class KnightPiece extends Piece{

	public KnightPiece(boolean white, int xCord, int yCord, Piece[][] pieceBoard) 
	{
		super(white, xCord, yCord, pieceBoard);
		
		this.pieceType = "Knight";
	}

	public void setMoves()
	{
		knightMoves();
	}

}
