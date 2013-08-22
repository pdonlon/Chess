
public class QueenPiece extends Piece{

	public QueenPiece(boolean white, int xCord, int yCord, Piece[][] pieceBoard) 
	{
		super(white, xCord, yCord, pieceBoard);
		
		this.pieceType = "Queen";
	}

	public void setMoves(int x1, int y1)
	{
		setDiagonal();
		setHorizontalandVertical();
	}

}
