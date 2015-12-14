package jp.ac.tanaka.Othello.Player;

import jp.ac.tanaka.Othello.Board.Board;
import jp.ac.tanaka.Othello.Board.StoneColor;
import jp.ac.tanaka.Util.MyInput;
import jp.ac.tanaka.Util.Vector;

import static jp.ac.tanaka.Othello.OthelloConstant.SIZE_X;
import static jp.ac.tanaka.Othello.OthelloConstant.SIZE_Y;

import jp.ac.tanaka.Othello.AI.MinMaxAI;

/*
 * ゲームをプレイする人間
 * 人間に入力してもらうことが前提なのでどうしても
 * IO処理を外部委託することは出来ない気がする
 */
public class Human extends PlayerBase {
	
	public Human(StoneColor sc){
		this("Hum",sc);
	}
	public Human(String name, StoneColor sc) {
		super(name, sc);
	}
	
	//対話的に名前を入力
	public void setNameTalk(){
		System.out.print("名前を入力：");
		this.name = MyInput.getStr();		
	}

	//１手指す。入力やそれを促す表示もここで行ってしまう
	@Override
	public void makeMove(Board board) {
		if(!board.isPass(this.stoneColor)){
			Vector p = new Vector();
			do{
				System.out.print("手を選択([a-h][1-8])：");
				String str = MyInput.getStr();
				if(str.equals("hint")){
					Vector hint = new MinMaxAI(board, stoneColor,5).calc().getBestMove();
					System.out.println((char)('a'+hint.x)+""+(hint.y+1)+ "がおすすめですよ！");
				}
				if(str.length() < 2)
					continue;
				p.x = str.charAt(0) - 'a';
				p.y = str.charAt(1) - '1';
			}while( 0 > p.x || p.x >= SIZE_X ||
					0 > p.y || p.y >= SIZE_Y ||
					board.PutStoneCheck(this.stoneColor, p) == 0);
			board.PutStone(this.stoneColor, p);
		}else {
			System.out.println(this.name + "は置ける場所がないためパスです！");
		}
	}
}
