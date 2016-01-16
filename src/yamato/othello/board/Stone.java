package yamato.othello.board;


public enum Stone {
	WHITE,
	BLACK,
	EMPTY;
	
	@Override
	public String toString(){
		if(this == Stone.WHITE) return " ● ";
		else if(this == Stone.BLACK) return " ○ ";
		else return "   ";
	}
	
	public static Stone reverse(Stone sc){
		if(sc == Stone.WHITE) return Stone.BLACK;
		else if(sc == Stone.BLACK) return Stone.WHITE;
		else return Stone.EMPTY;
	}
}
