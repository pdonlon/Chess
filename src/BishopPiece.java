

public class BishopPiece extends Piece{

	
	public BishopPiece(boolean white, String pieceType) {
		super(white, pieceType);
		// TODO Auto-generated constructor stub
	}

	public boolean canMove(int startX, int startY, int moveX, int moveY){

		boolean canMove = false;

		return canMove;

	}

	public boolean canJump(int startX, int startY, int moveX, int moveY){
		
		boolean canJump = false;
		
		if(canMove(startX,startY,moveX,moveY))
			canJump = true;
		
		return canJump;
	}
	
}
