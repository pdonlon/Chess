
public class QueenPiece extends Piece{

	public QueenPiece(Board board,boolean white, int xCord, int yCord, Piece[][] pieceBoard, boolean[][]whiteMoves, boolean[][]blackMoves) 
	{
		super(board,white, xCord, yCord, pieceBoard,whiteMoves,blackMoves);
		
		this.pieceType = "Queen";
	}

	public void setMoves()
	{
		setDiagonal();
		setHorizontalAndVertical();
	}
	
	public void setImage()
	{
		pieceImage = pieceImage.getSubimage(0, 0, 70, 70);
	}
	
}
