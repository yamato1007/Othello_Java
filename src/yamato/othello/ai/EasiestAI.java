package yamato.othello.ai;

import java.util.List;

import yamato.othello.board.Board;
import yamato.othello.board.Stone;
import yamato.util.Vector;

public class EasiestAI extends AIBase{

	public EasiestAI() {
		super();
	}

	@Override
	public AIBase calc(Board board,Stone stoneColor) {
		List<Vector> points = board.PutStoneCanPoint(stoneColor);
		if(points.isEmpty())
			this.bestMove = null;
		else
			this.bestMove = points.get(0);
		return this;
	}

}
