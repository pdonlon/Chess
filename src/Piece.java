
public class Piece {

	Board pieceBoard;
	boolean white;
	String pieceType;
	
	public Piece(boolean white, String pieceType){

		this.white = white;
		this.pieceType = pieceType;
	}
	
	public boolean isWhite(){

		return white;
	}

	public void setWhite(boolean a){

		white = a;
	}

	public String getPieceType(){
		
		return pieceType;
	}
	
	public boolean isValid(int x, int y){

		boolean valid = true;

		if((x<0 || x>8) || (y<0 || y>8))
			valid = false;

		return valid;
	}

}
