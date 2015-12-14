package jp.ac.tanaka.Othello.Board;

import jp.ac.tanaka.Util.Vector;
import static jp.ac.tanaka.Othello.OthelloConstant.SIZE_X;
import static jp.ac.tanaka.Othello.OthelloConstant.SIZE_Y;

import java.util.List;
import java.util.ArrayList;

import static jp.ac.tanaka.Othello.Board.StoneColor.WHITE;
import static jp.ac.tanaka.Othello.Board.StoneColor.BLACK;

public class Board {
	private Stone[][] stones = new Stone[SIZE_X][SIZE_Y];
	public Board(){
		this.stones[SIZE_X/2-1][SIZE_Y/2-1] = new Stone(WHITE);
		this.stones[SIZE_X/2][SIZE_Y/2] = new Stone(WHITE);
		this.stones[SIZE_X/2][SIZE_Y/2-1] = new Stone(BLACK);
		this.stones[SIZE_X/2-1][SIZE_Y/2] = new Stone(BLACK);
	}
	public Board(Stone[][] sotnes){
		for(int i=0;i < SIZE_X;i++){
			for(int j=0;j<SIZE_Y;j++){
				this.stones[i][j] = sotnes[i][j];
			}
		}
	}
	
	
	//石を置く、ひっくり返す
	public int PutStone(StoneColor sc,Vector p){
		int flag=0;
		if(this.getStones(p.x, p.y) != null)
			return 0;
		for(int x =-1;x <= 1;x++){
			for(int y=-1;y <= 1; y++){
				flag += this.PutStoneLine(sc, p, new Vector(x,y));
			}
		}
		if (flag >= 1)
			this.setStones(p.x, p.y, new Stone(sc));
		return flag;
	}
	//１方向ひっくり返す
	private int PutStoneLine(StoneColor sc,Vector p,Vector dir){
		int flag = 0;
		Vector i = new Vector(p);
		for(i.sum(dir);
				0 <= i.x && i.x < SIZE_X &&
				0 <= i.y && i.y < SIZE_Y;
				i.sum(dir)){
			if(this.stones[i.x][i.y] == null)
				break;
			else if(this.stones[i.x][i.y].getStoneColor() == sc){
				//ひっくり返す
				i = new Vector(p);
				for(i.sum(dir);
						!(this.stones[i.x][i.y].getStoneColor() == sc);
						i.sum(dir)){
					this.stones[i.x][i.y].reverse();
				}
				return flag;
			}
			else if(!(this.stones[i.x][i.y].getStoneColor() == sc))
				flag++;
		}
		return 0;
	}
	
	//ひっくり返せるかチェック。ひっくり返せる数を返す
	public int PutStoneCheck(StoneColor sc,Vector p){
		int flag=0;//ひっくり返せる数
		//置く場所にすでに石があるか
		if(this.getStones(p.x, p.y) != null)
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
	private int PutStoneCheckLine(StoneColor sc,Vector p,Vector dir){
		int flag = 0;
		Vector i = new Vector(p);
		for(i.sum(dir);
				0 <= i.x && i.x < SIZE_X &&
				0 <= i.y && i.y < SIZE_Y;
				i.sum(dir)){
			if(this.stones[i.x][i.y] == null)
				break;
			if(this.stones[i.x][i.y].getStoneColor() == sc)
				return flag;
			if(!(this.stones[i.x][i.y].getStoneColor() == sc))
				flag++;
		}
		return 0;
	}
	//石を置ける場所をリストで返す
	public List<Vector> PutStoneCanPoint(StoneColor sc){
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
	public int PutStoneAmountCount(StoneColor sc){
		int cnt = 0;
		for(int i=0;i < SIZE_X;i++){
			for(int j=0;j<SIZE_Y;j++){
				if(PutStoneCheck(sc, new Vector(i, j)) >= 1)
					cnt++;
			}
		}
		return cnt;
	}
	
	public boolean isPass(StoneColor sc){
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
				if(this.stones[i][j]!=null)
					cnt++;
			}
		}
		return cnt;
	}
	//色ごとに石の数カウント
	public int countStoneColor(StoneColor sc){
		int cnt = 0;
		for(int i=0;i<SIZE_X;i++){
			for(int j=0;j<SIZE_Y;j++){
				if(this.stones[i][j]!=null &&
						this.stones[i][j].getStoneColor() == sc)
					cnt++;
			}
		}
		return cnt;
	}
	//空白スペースのカウント
	public int countEmpty(){
		return SIZE_X * SIZE_Y - this.countStoneAll();
	}

	public Stone getStones(int x,int y) {return stones[x][y];}
	public Stone getStones(Vector v) {return this.getStones(v.x, v.y);}

	public void setStones(int x, int y, Stone stone) {this.stones[x][y] = stone;}
	
	//深いコピーを作る
	public Board myClone(){
		Board clone = new Board();
		clone.copyBy(this);
		return clone;
	}
	
	//石の配置を引数のボードと同じにする
	public void copyBy(Board b){
		for(int i=0;i<SIZE_X;i++){
			for(int j=0;j<SIZE_Y;j++){
				if (b.getStones(i, j) instanceof Stone)
					this.setStones(i, j, new Stone(b.getStones(i, j).getStoneColor()));
				else
					this.setStones(i, j, null);
			}
		}
	}
}
