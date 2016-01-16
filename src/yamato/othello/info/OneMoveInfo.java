package yamato.othello.info;

import yamato.othello.board.Board;
import yamato.othello.board.StoneColor;
import yamato.util.Vector;

public class OneMoveInfo {
	private Vector nextMove;
	private Board board;
	private StoneColor nextColor;
	
	public OneMoveInfo(Vector nextMove,Board board,StoneColor nextColor){
		this.nextMove = nextMove;
		this.board = board;
		this.nextColor = nextColor;
	}
	
	public Vector getnextMove() {
		return nextMove;
	}
	public Board getBoard() {
		return board;
	}
	public StoneColor getNextColor() {
		return nextColor;
	}
	
}
