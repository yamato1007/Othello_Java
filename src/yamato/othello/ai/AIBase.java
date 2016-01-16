package yamato.othello.ai;

import yamato.othello.board.Board;
import yamato.othello.board.Stone;
import yamato.util.Vector;

public abstract class AIBase {
	protected Vector bestMove = null;
	
	public AIBase(){
		this.bestMove = null;
	}
	
	public abstract AIBase calc(Board board, Stone stoneColor);

	public Vector getBestMove(){
		return bestMove;
	}
}
