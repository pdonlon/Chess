
public class RookPiece extends Piece
{

	public RookPiece(boolean white, int xCord, int yCord, Piece[][] pieceBoard) 
	{
		super(white, xCord, yCord, pieceBoard);
		
		this.pieceType = "Rook";
	}

	public void setMoves()
	{
		setHorizontalandVertical();
	}
	
}
