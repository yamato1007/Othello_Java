package yamato;

import static yamato.othello.board.StoneColor.BLACK;
import static yamato.othello.board.StoneColor.WHITE;

import yamato.othello.Othello;
import yamato.othello.ai.EasiestAI;
import yamato.othello.ai.MinMaxAI;
import yamato.othello.board.StoneColor;
import yamato.othello.info.PlayerInfo;
import yamato.othello.player.Computer;
import yamato.othello.player.Human;
import yamato.othello.player.PlayerBase;
import yamato.util.MyInput;

public class Main {
	public static final boolean DEBUGMODE = true;
	
	public static void main(String[] args){
		//プレイヤ情報の登録
		PlayerBase first; 
		PlayerBase second;
		if(DEBUGMODE){
			first = new Computer("いち！", WHITE, new MinMaxAI(5));
			second = new Computer("に！", BLACK, new MinMaxAI(5));
 		}else{
			first = setPlayerTalk(WHITE);
			second = setPlayerTalk(BLACK);
		}
		
		System.out.println(new PlayerInfo(first, second).toString());
		
		//ゲーム開始
		Othello othello = new Othello(new PlayerInfo(first, second));
		othello.play();
	}
	
	private static PlayerBase setPlayerTalk(StoneColor sc){
	PlayerBase player;
		System.out.print("先手（白）のプレイヤを選択[1:人間 2:COM 3:MinMax]：");
		switch(MyInput.getIntRange(1, 3)){
			case 1:	player = new Human(sc); break;
			case 2:	player = new Computer(sc, new EasiestAI()); break;
			case 3:
				System.out.print("コンピュータのレベルを入力[0-9]：");
				int level = MyInput.getIntRange(0, 9);
				player = new Computer(sc, new MinMaxAI(level));
				break;
			default: player = new Human(sc); break;
		}
		System.out.print("名前を入力：");
		String name_tmp = MyInput.getStr();
		if(!name_tmp.equals(""))
			player.setName(name_tmp);
		return player;
	}

}
