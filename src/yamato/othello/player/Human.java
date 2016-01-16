package yamato.othello.player;

import yamato.othello.board.Board;
import yamato.othello.board.StoneColor;
import yamato.util.MyInput;
import yamato.util.Vector;

import static yamato.othello.OthelloConstant.SIZE_X;
import static yamato.othello.OthelloConstant.SIZE_Y;

import yamato.othello.ai.MinMaxAI;

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
	public Vector makeMove(Board board) {
		Vector p = null;
		if(!board.isPass(this.stoneColor)){
			do{
				System.out.print("手を選択([a-h][1-8])：");
				String str = MyInput.getStr();
				if(str.equals("hint")){
					Vector hint = new MinMaxAI(5).calc(board,this.stoneColor).getBestMove();
					System.out.println((char)('a'+hint.x)+""+(hint.y+1)+ "がおすすめですよ！");
				}
				if(str.length() < 2) continue;
				p = new Vector(str.charAt(0) - 'a', str.charAt(1) - '1');
			}while( 0 > p.x || p.x >= SIZE_X ||
					0 > p.y || p.y >= SIZE_Y ||
					board.PutStoneCheck(this.stoneColor, p) == 0);
			board.PutStone(this.stoneColor, p);
		}else {
			System.out.println(this.name + "は置ける場所がないためパスです！");
		}
		return p;
	}
}
