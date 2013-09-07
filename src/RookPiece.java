
public class RookPiece extends Piece
{

	public RookPiece(Board board,boolean white, int xCord, int yCord, Piece[][] pieceBoard, boolean[][]whiteMoves, boolean[][]blackMoves) 
	{
		super(board,white, xCord, yCord, pieceBoard,whiteMoves,blackMoves);
		
		this.pieceType = "Rook";
	}

	public void setMoves()
	{
		setHorizontalAndVertical();
	}
	
	public void setImage()
	{
		super.setImage();
		pieceImage = pieceImage.getSubimage(xSpacing*4-6, ySpacing, 70, 70);
	}
	
}
