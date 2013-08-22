
public class QueenPiece extends Piece{

	public QueenPiece(boolean white, int xCord, int yCord) 
	{
		super(white, xCord, yCord);
		
		this.pieceType = "Queen";
	}

	public void setMoves(int x1, int y1)
	{
		setDiagonal();
		setHorizontalandVertical();
	}

}
