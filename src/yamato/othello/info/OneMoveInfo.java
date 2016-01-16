package yamato.othello.info;

import yamato.othello.board.Board;
import yamato.othello.board.Stone;
import yamato.util.Vector;

public class OneMoveInfo {
	private Vector nextMove;
	private Board board;
	private Stone nextColor;
	
	public OneMoveInfo(Vector nextMove,Board board,Stone nextColor){
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
	public Stone getNextColor() {
		return nextColor;
	}
	
}
