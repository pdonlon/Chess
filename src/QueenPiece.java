
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
		super.setImage();
		
		pieceImage = pieceImage.getSubimage(xSpacing*2, ySpacing, 70, 70);
	}
	
}
