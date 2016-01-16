package yamato.othello.player;


import yamato.othello.ai.AIBase;
import yamato.othello.board.*;
import yamato.util.Vector;

public class Computer extends PlayerBase {
	private AIBase AIAlgorithm;
	
	public Computer(StoneColor sc,AIBase aialgorithm){
		this("COM", sc,aialgorithm);
	}
	public Computer(String name, StoneColor sc,AIBase aialgorithm) {
		super(name, sc);
		this.AIAlgorithm = aialgorithm;
	}

	public Vector makeMove(Board board){
		Vector bestMove = calcBestMove(board);
		if(bestMove != null){
			board.PutStone(this.stoneColor,new Vector(bestMove));
			System.out.println((char)('a'+bestMove.x)+""+(bestMove.y+1)+"に置きました！");
		}else{
			System.out.println(this.name + "は置ける場所がないためパスです！");
		}
		return bestMove;
	}
	
	public Vector calcBestMove(Board board){
		return AIAlgorithm.calc(board,this.stoneColor).getBestMove();
	}
}
