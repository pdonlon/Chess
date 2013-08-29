
public class KingPiece extends Piece{
	
	

	public KingPiece(boolean white, int xCord, int yCord, Piece[][] pieceBoard, boolean[][]whiteMoves, boolean[][]blackMoves) 
	{
		super(white, xCord, yCord, pieceBoard,whiteMoves,blackMoves);

		this.pieceType = "TKing";
		isKing = true;
	}

	public void setMoves()
	{

		for(int y=-1; y<2; y++){
			for(int x=-1; x<2; x++){

				if(isValid(xCord+x,yCord+y)&&(!sameColor(xCord+x, yCord+y)||isEmpty(xCord+x,yCord+y)))
				{				
					if((white && !blackMoves[xCord+x][yCord+y]) || (!white && !whiteMoves[xCord+x][yCord+y]))	
						canMove[xCord+x][yCord+y]=true;
					
				}
			}
		}
		addBlackAndWhiteMoves();
	}

}
