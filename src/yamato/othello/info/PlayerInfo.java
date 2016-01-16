package yamato.othello.info;


import yamato.othello.board.Stone;
import yamato.othello.player.PlayerBase;


/**
 * プレイヤの情報が格納されたクラス
 * @author k13082kk
 *
 */
public class PlayerInfo {
	private PlayerBase firstPlayer;
	private PlayerBase secondPlayer;

	
	public PlayerInfo(PlayerBase firstPlayer, PlayerBase secondPlayer){
		this.firstPlayer = firstPlayer;
		this.secondPlayer = secondPlayer;
	}
	
	public PlayerBase getFirstPlayer() {
		return firstPlayer;
	}
	public PlayerBase getSecondPlayer() {
		return secondPlayer;
	}
	public PlayerBase getPlayer(Stone sc) {
		return sc == Stone.WHITE ? firstPlayer : secondPlayer;
	}
	
	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
		str.append("FirstPlayer : ").append(firstPlayer.getClass().getSimpleName()).append(" : ").append(firstPlayer.getName()).append("\n");
		str.append("SecondPlayer : ").append(secondPlayer.getClass().getSimpleName()).append(" : ").append(secondPlayer.getName());
		return str.toString();
	}
}
