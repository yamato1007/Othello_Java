package jp.ac.tanaka.Othello.AI;

import jp.ac.tanaka.Othello.Board.Board;
import jp.ac.tanaka.Othello.Board.StoneColor;
import jp.ac.tanaka.Util.Vector;

public abstract class AIBase {
	protected Vector bestMove = null;
	protected Board board;
	protected StoneColor stoneColor;
	
	public AIBase(Board board,StoneColor stoneColor){
		this.board = board;
		this.stoneColor = stoneColor;
	}
	
	public abstract AIBase calc();
	
	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public StoneColor getStoneColor() {
		return stoneColor;
	}

	public void setStoneColor(StoneColor stoneColor) {
		this.stoneColor = stoneColor;
	}

	public Vector getBestMove(){
		return bestMove;
	}
}
