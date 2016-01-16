package yamato.othello.board;

import static yamato.othello.board.StoneColor.WHITE;
import static yamato.othello.board.StoneColor.BLACK;

public class Stone {
	private StoneColor stone_c;
	
	public Stone(StoneColor sc){
		this.stone_c = sc;
	}

	
	public StoneColor getStoneColor(){return this.stone_c;}
	public void setStoneColor(StoneColor sc){this.stone_c = sc;}
	
	public StoneColor reverse(){
		this.stone_c = this.getReverseStoneColor();
		return this.stone_c;
	}
	public StoneColor getReverseStoneColor(){
		return this.stone_c == WHITE ? BLACK : WHITE;
	}
}
