package jp.ac.tanaka.Othello;

import jp.ac.tanaka.Othello.Board.*;
import jp.ac.tanaka.Othello.IO.OutputCUI;
import jp.ac.tanaka.Othello.Player.*;
import jp.ac.tanaka.Util.*;

import static jp.ac.tanaka.Othello.Board.StoneColor.WHITE;

import jp.ac.tanaka.Othello.AI.EasiestAI;
import jp.ac.tanaka.Othello.AI.MinMaxAI;

import static jp.ac.tanaka.Othello.Board.StoneColor.BLACK;

public class Othello {
	public static final boolean DEBUGMODE = true;
	
	public static void main(String args[]){
		
		//初期化
		Board board = new Board();
		PlayerBase[] players = new PlayerBase[2]; 
		if(DEBUGMODE){
			players[0] = new Computer("いち！", WHITE, new MinMaxAI(board, WHITE, 5));
			players[1] = new Computer("に！", BLACK, new MinMaxAI(board, BLACK, 5));
 		}else{
			players[0] = setPlayerTalk(WHITE);
			players[1] = setPlayerTalk(BLACK);
		}
		//ゲーム開始
		OutputCUI.board(board);
		int i=0;
		while(!board.isEnd()){
			players[i].makeMove(board);
			OutputCUI.board(board);
			i = i==0 ? 1 : 0;
		}
		System.out.println("WHITE:" + board.countStoneColor(WHITE));
		System.out.println("BLACK:" + board.countStoneColor(BLACK));
		System.out.println("EMPTY:" + board.countEmpty());
		System.out.println(board.PutStone(WHITE,new Vector(5,3)));
		OutputCUI.board(board);
	}
	
	private static PlayerBase setPlayerTalk(StoneColor sc){
		PlayerBase player;
		System.out.print("先手（白）のプレイヤを選択[1:人間 2:COM 3:MinMax]：");
		switch(MyInput.getIntRange(1, 3)){
			case 1:	player = new Human(sc); break;
			case 2:	player = new Computer(sc, new EasiestAI(new Board(), sc)); break;
			case 3:
				System.out.print("コンピュータのレベルを入力[0-9]：");
				int level = MyInput.getIntRange(0, 9);
				player = new Computer(sc, new MinMaxAI(new Board(), sc,level));
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