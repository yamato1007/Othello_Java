package jp.ac.tanaka.Othello.AI;

import java.util.List;

import jp.ac.tanaka.Othello.Board.Board;
import jp.ac.tanaka.Othello.Board.StoneColor;
import jp.ac.tanaka.Util.Vector;

public class EasiestAI extends AIBase{

	public EasiestAI(Board board, StoneColor stoneColor) {
		super(board, stoneColor);
	}

	@Override
	public AIBase calc() {
		List<Vector> points = this.board.PutStoneCanPoint(this.stoneColor);
		if(points.isEmpty())
			this.bestMove = null;
		else
			this.bestMove = points.get(0);
		return this;
	}

}
