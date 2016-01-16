package yamato.othello.player;

import yamato.othello.board.*;
import yamato.util.Vector;

public abstract class PlayerBase {
	protected String name;
	protected StoneColor stoneColor;
	
	public PlayerBase(StoneColor sc){
		this("Non Name", sc);
	}
	public PlayerBase(String name,StoneColor sc){
		this.name = name;
		this.stoneColor = sc;	
	}
	
	//次の手を思考し、指す一連の処理
	public abstract Vector makeMove(Board board);

	public void setName(String name) {this.name = name;}
	public String getName() {return name;}
}
