package jp.ac.tanaka.Othello.Board;

public enum StoneColor {
	WHITE,
	BLACK;
	
	@Override
	public String toString(){
		return this == StoneColor.WHITE ? "WHITE" : "BLACK";				
	}
}
