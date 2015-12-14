package jp.ac.tanaka.Othello.Player;


import jp.ac.tanaka.Othello.AI.AIBase;
import jp.ac.tanaka.Othello.Board.*;
import jp.ac.tanaka.Util.Vector;

public class Computer extends PlayerBase {
	private AIBase AIAlgorithm;
	
	public Computer(StoneColor sc,AIBase aialgorithm){
		this("COM", sc,aialgorithm);
	}
	public Computer(String name, StoneColor sc,AIBase aialgorithm) {
		super(name, sc);
		this.AIAlgorithm = aialgorithm;
		this.AIAlgorithm.setStoneColor(this.stoneColor);
	}

	public void makeMove(Board board){
		Vector bestMove = calcBestMove(board);
		if(bestMove != null){
			board.PutStone(this.stoneColor,new Vector(bestMove));
			System.out.println((char)('a'+bestMove.x)+""+(bestMove.y+1)+"に置きました！");
		}else
			System.out.println(this.name + "は置ける場所がないためパスです！");
	}
	
	public Vector calcBestMove(Board board){
		AIAlgorithm.setBoard(board);
		return AIAlgorithm.calc().getBestMove();
	}
}
