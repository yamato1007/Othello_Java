package yamato.othello.board;

import yamato.util.Vector;

import java.util.List;

import java.util.ArrayList;

import static yamato.othello.board.Stone.*;
import static yamato.othello.OthelloConstant.SIZE_X;
import static yamato.othello.OthelloConstant.SIZE_Y;

public class Board implements Cloneable{
	//ボード
	private Stone[][] stones = new Stone[SIZE_X][SIZE_Y];
	
	public Board(){
		for(int i=0;i<SIZE_X;i++){
			for(int j=0;j<SIZE_Y;j++){
				this.stones[i][j] = Stone.EMPTY;
			}
		}
		this.stones[SIZE_X/2-1][SIZE_Y/2-1] = Stone.WHITE;
		this.stones[SIZE_X/2][SIZE_Y/2] = Stone.WHITE;
		this.stones[SIZE_X/2][SIZE_Y/2-1] = Stone.BLACK;
		this.stones[SIZE_X/2-1][SIZE_Y/2] = Stone.BLACK;
	}
	public Board(Stone[][] sotnes){
		for(int i=0;i < SIZE_X;i++){
			for(int j=0;j<SIZE_Y;j++){
				this.stones[i][j] = sotnes[i][j];
			}
		}
	}
	
	
	//石を置く、ひっくり返す
	public int PutStone(Stone sc,Vector p){
		int flag=0;
		if(this.getStone(p.x, p.y) != Stone.EMPTY)
			return 0;
		for(int x =-1;x <= 1;x++){
			for(int y=-1;y <= 1; y++){
				flag += this.PutStoneLine(sc, p, new Vector(x,y));
			}
		}
		if (flag >= 1)
			this.setStone(p.x, p.y, sc);
		return flag;
	}
	//１方向ひっくり返す
	private int PutStoneLine(Stone sc,Vector p,Vector dir){
		int flag = 0;
		Vector i = new Vector(p);
		for(i = Vector.sum(i,dir);
				0 <= i.x && i.x < SIZE_X &&
				0 <= i.y && i.y < SIZE_Y;
				i = Vector.sum(i,dir)){
			if(this.stones[i.x][i.y] == Stone.EMPTY)
				break;
			else if(this.stones[i.x][i.y] == sc){
				//ひっくり返す
				i = new Vector(p);
				for(i = Vector.sum(i,dir);
						!(this.stones[i.x][i.y] == sc);
						i = Vector.sum(i,dir)){
					this.stones[i.x][i.y] = Stone.reverse(this.stones[i.x][i.y]);
				}
				return flag;
			}
			else if(!(this.stones[i.x][i.y] == sc))
				flag++;
		}
		return 0;
	}
	
	//ひっくり返せるかチェック。ひっくり返せる数を返す
	public int PutStoneCheck(Stone sc,Vector p){
		int flag=0;//ひっくり返せる数
		//置く場所にすでに石があるか
		if(this.getStone(p.x, p.y) != Stone.EMPTY)
			return 0;
		//各方向に対して石をひっくり返せるかチェック
		for(int x =-1;x <= 1;x++){
			for(int y=-1;y <= 1; y++){
				flag += this.PutStoneCheckLine(sc, p, new Vector(x,y));
			}
		}
		return flag;
	}
	//各方向ごとにひっくり返せるかチェック
	private int PutStoneCheckLine(Stone sc,Vector p,Vector dir){
		int flag = 0;
		Vector i = new Vector(p);
		for(i = Vector.sum(i,dir);
				0 <= i.x && i.x < SIZE_X &&
				0 <= i.y && i.y < SIZE_Y;
				i = Vector.sum(i,dir)){
			if(this.stones[i.x][i.y] == Stone.EMPTY)
				break;
			if(this.stones[i.x][i.y] == sc)
				return flag;
			if(!(this.stones[i.x][i.y] == sc))
				flag++;
		}
		return 0;
	}
	//石を置ける場所をリストで返す
	public List<Vector> PutStoneCanPoint(Stone sc){
		List<Vector> points = new ArrayList<Vector>();
		for(int x=0;x<SIZE_X;x++){
			for(int y=0;y<SIZE_Y;y++){
				if(this.PutStoneCheck(sc, new Vector(x,y)) != 0){
					points.add(new Vector(x, y));
				}
			}
		}
		return points;
	}
	//石を置ける場所の数を返す
	public int PutStoneAmountCount(Stone sc){
		int cnt = 0;
		for(int i=0;i < SIZE_X;i++){
			for(int j=0;j<SIZE_Y;j++){
				if(PutStoneCheck(sc, new Vector(i, j)) >= 1)
					cnt++;
			}
		}
		return cnt;
	}
	
	public boolean isPass(Stone sc){
		return PutStoneAmountCount(sc) == 0;
	}
	
	//ゲームが終わりか確認
	public boolean isEnd(){
		return isPass(WHITE) && isPass(BLACK);
	}
	
	//石の数カウント
	public int countStoneAll(){
		int cnt = 0;
		for(int i=0;i<SIZE_X;i++){
			for(int j=0;j<SIZE_Y;j++){
				if(this.stones[i][j]!=Stone.EMPTY)
					cnt++;
			}
		}
		return cnt;
	}
	//色ごとに石の数カウント
	public int countStoneColor(Stone sc){
		int cnt = 0;
		for(int i=0;i<SIZE_X;i++){
			for(int j=0;j<SIZE_Y;j++){
				if(this.stones[i][j] == sc)
					cnt++;
			}
		}
		return cnt;
	}
	//空白スペースのカウント
	public int countEmpty(){
		return this.countStoneColor(EMPTY);
	}

	public Stone getStone(int x,int y) {return stones[x][y];}
	public Stone getStone(Vector v) {return this.getStone(v.x, v.y);}

	public void setStone(int x, int y, Stone stone) {this.stones[x][y] = stone;}
	
	//深いコピーを作る
	@Override
	public Board clone(){
		Board clone = new Board();
		clone.copyBy(this);
		return clone;
	}
	
	//石の配置を引数のボードと同じにする
	public void copyBy(Board b){
		for(int i=0;i<SIZE_X;i++){
			for(int j=0;j<SIZE_Y;j++){
				this.setStone(i, j, b.getStone(i, j));
			}
		}
	}

	@Override	//オセロ盤を表示
	public String toString(){
		StringBuilder str = new StringBuilder();
	    
		str.append("    ");
	    for(int i=0;i<SIZE_X;i++)
	        str.append(" "+(char)(i+'a')+"  ");
	    str.append("\n");
		for(int i=0;i<SIZE_Y;i++){
	        str.append(line());
	        str.append(" " + (char)(i+'1') + " |");
	        for(int j=0;j<SIZE_X;j++){
	        	str.append(this.getStone(j, i).toString());
	            str.append("|");
	        }
	        str.append("\n");
	    }
	    str.append(line());
	    
	    return str.toString();
	}
	
	//オセロ盤の横ラインを表示
	private String line(){
		StringBuilder str = new StringBuilder();
	    str.append("    ");
	    for(int i=0;i<SIZE_X;i++)
	        str.append("----");
	    str.append("\n");
		return str.toString();
	}
}
