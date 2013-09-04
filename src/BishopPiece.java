

public class BishopPiece extends Piece
{

	public BishopPiece(Board board,boolean white, int xCord, int yCord, Piece[][] pieceBoard, boolean[][]whiteMoves, boolean[][]blackMoves) 
	{
		super(board,white, xCord, yCord, pieceBoard,whiteMoves,blackMoves);
		
		this.pieceType = "Bishop";
	}

	public void setMoves()
	{
		setDiagonal();
	}
}
