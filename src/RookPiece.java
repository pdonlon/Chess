
public class RookPiece extends Piece
{

	public RookPiece(boolean white, int xCord, int yCord) 
	{
		super(white, xCord, yCord);
		
		this.pieceType = "Rook";
	}

	public void setMoves(int x1, int y1)
	{
		setHorizontalandVertical();
	}
	
}
