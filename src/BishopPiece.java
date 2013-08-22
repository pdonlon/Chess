

public class BishopPiece extends Piece
{


	public BishopPiece(boolean white, int xCord, int yCord) 
	{
		super(white, xCord, yCord);
		
		this.pieceType = "Bishop";
	}

	public void setMoves(int x1, int y1)
	{
		setDiagonal();
	}
}
