
public class KingPiece extends Piece{

	public KingPiece(boolean white, int xCord, int yCord, Piece[][] pieceBoard) 
	{
		super(white, xCord, yCord, pieceBoard);
		
		this.pieceType = "King";
	}
	
	public void setMoves(){
		
		for(int y=-1; y<2; y++){
			for(int x=-1; x<2; x++){
			
				if(isValid(xCord+x,yCord+y)&&(!sameColor(xCord+x, yCord+y)||isEmpty(xCord+x,yCord+y)))
					canMove[xCord+x][yCord+y]=true;
				
			}
		}
		
	}


}
