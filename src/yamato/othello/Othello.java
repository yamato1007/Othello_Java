package yamato.othello;

import yamato.othello.board.*;
import yamato.othello.info.GameInfo;
import yamato.othello.info.PlayerInfo;
import yamato.util.*;

import static yamato.othello.board.StoneColor.WHITE;
import static yamato.othello.board.StoneColor.BLACK;



public class Othello {
	private GameInfo gameInfo;
	
	private Board board;
	private StoneColor nowColor;
	
	public Othello(PlayerInfo playerInfo){
		this.gameInfo = new GameInfo(playerInfo);
		this.board = new Board();
		nowColor = WHITE;
	}
	
	public void play(){
		System.out.println(board.toString());
		
		//ゲームが終了するまで指し続ける
		while(this.oneMove());
		
		this.gameInfo.addMove(null, board, null);
		System.out.println("WHITE:" + board.countStoneColor(WHITE));
		System.out.println("BLACK:" + board.countStoneColor(BLACK));
		System.out.println("EMPTY:" + board.countEmpty());
		System.out.println(board.PutStone(WHITE,new Vector(5,3)));
		System.out.println(board.toString());
	}
	
	/**
	 * 一手指す
	 * @return ゲームがまだ継続可能ならTrue,終了ならFalseを返す
	 */
	public boolean oneMove(){
		Board beforeBoard = this.board.clone();
		//１手指し、現在のボードの状態を変化させ、指した手を取得している
		Vector move = gameInfo.getPlayer(this.nowColor).makeMove(this.board);
		System.out.println(board.toString());
		
		//ゲーム（棋譜）情報を追加
		this.gameInfo.addMove(move, beforeBoard, nowColor);
		
		//次の手番へ
		this.nowColor = nowColor == WHITE ? BLACK : WHITE;
		
		return !board.isEnd();
	}

	public PlayerInfo getGameInfo(){
		return this.gameInfo;
	}
}
