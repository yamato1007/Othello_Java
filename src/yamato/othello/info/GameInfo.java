package yamato.othello.info;

import java.util.ArrayList;
import java.util.List;

import yamato.othello.board.Board;
import yamato.othello.board.StoneColor;
import yamato.othello.player.PlayerBase;
import yamato.util.Vector;

/**
 * ゲームの情報が格納されたクラス
 * @author k13082kk
 *
 */
public class GameInfo extends PlayerInfo{
	private List<OneMoveInfo> moves;
	
	public GameInfo(PlayerBase firstPlayer, PlayerBase secondPlayer){
		super(firstPlayer, secondPlayer);
		this.init();
	}
	public GameInfo(PlayerInfo playerInfo){
		this(playerInfo.getFirstPlayer(), playerInfo.getSecondPlayer());
	}

	private void init(){
		this.moves = new ArrayList<>();
	}


	/**
	 * 一手分情報を追加
	 * @param move
	 */
	public void addMove(Vector move,Board board,StoneColor stoneColor) {
		OneMoveInfo moveInfo = new OneMoveInfo(move, board.clone(), stoneColor);
		this.moves.add(moveInfo);
	}
	
	public OneMoveInfo getOneMoveInfo(int n){
		if(n < 0 || moves.size() <= n){
			return null;
		}
		return moves.get(n);
	}
}
