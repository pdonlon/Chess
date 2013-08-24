
public class RookPiece extends Piece
{

	public RookPiece(boolean white, int xCord, int yCord, Piece[][] pieceBoard, boolean[][]whiteMoves, boolean[][]blackMoves) 
	{
		super(white, xCord, yCord, pieceBoard,whiteMoves,blackMoves);
		
		this.pieceType = "Rook";
	}

	public void setMoves()
	{
		setHorizontalAndVertical();
	}
	
}
