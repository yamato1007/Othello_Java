package yamato.othello.ai;

import static yamato.othello.board.Stone.BLACK;
import static yamato.othello.board.Stone.WHITE;
import static yamato.othello.OthelloConstant.*;

import java.util.List;

import yamato.othello.board.Board;
import yamato.othello.board.Stone;
import yamato.util.Vector;

public class MinMaxAI extends AIBase{
	private static final int DEFAULT_LEVEL = 4;
	
	private int level;
	private int[][] ratingBoard;

	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
	
	public MinMaxAI(int level) {
		super();
		this.level = level;
		this.ratingBoard = createRatingBoard();
	}
	public MinMaxAI() {
		this(DEFAULT_LEVEL);
	}

	@Override
	public AIBase calc(Board board, Stone stoneColor) {
		Board boardClone = board.clone();
		if(level-1 >= board.countEmpty())
			this.ratingBoard = this.createRatingBoardEquarity();
		this.bestMove = calcBestMoveLoop(boardClone,stoneColor, this.level - 1);
		return this;
	}
	
	//最善手を再帰的に探索
	private Vector calcBestMoveLoop(Board board,Stone sc,int readTime){
		Vector bestMove = null;
		Board bestBoard = board.clone();
		Stone next_sc = (sc == BLACK ? WHITE : BLACK);
		List<Vector> putPoints = board.PutStoneCanPoint(sc);
		//パスするしかない時
		if(putPoints.size() == 0){
        	if(readTime > 0)
        		this.calcBestMoveLoop(bestBoard, next_sc, readTime-1);
		}
		//打てる手がある時
		else{
			int rating = -100000000;
			//最善手探し
		    for(Vector p : putPoints){
	        	Board next_board = board.clone();
	        	next_board.PutStone(sc, p);
	        	if(readTime > 0)
	        		this.calcBestMoveLoop(next_board, next_sc, readTime-1);
	        	int tmp_rate = calcRating(next_board, sc);
	        	if(tmp_rate > rating){
	        		rating = tmp_rate;
	        		bestMove = p;
	        		bestBoard = next_board.clone();
	        	}
	        	
	        	//手の評価値
	        	if(level-1 == readTime){
	        		System.out.println((char)('a'+p.x)+""+(p.y+1)+":"+tmp_rate);
	        		//OutputCUI.board(next_board);
	        	}
	        }
		}
	    board.copyBy(bestBoard);
		return bestMove;
	}
	
	//評価値を計算
	private int calcRating(Board borad,Stone sc){
		int rate = 0;
		for(int i=0;i<SIZE_Y;i++){
	        for(int j=0;j<SIZE_X;j++){
        		if(borad.getStone(i, j) == sc)
					rate += this.ratingBoard[i][j];
        		else if(borad.getStone(i, j) == Stone.reverse(sc))
        			rate -= this.ratingBoard[i][j];
	        }
		}
		return rate;
	}
	//石の場所によって重み付け
	private int[][] createRatingBoard(){
		int[][] rating = new int[SIZE_X][SIZE_Y];
		for(int i=0;i<SIZE_Y;i++){
	        for(int j=0;j<SIZE_X;j++){
	            if(2 <= i && i < SIZE_Y - 2 &&
	               2 <= j && j < SIZE_X - 2)//内部
	            	rating[i][j] = 2;
	            if(i == 1 || i == SIZE_Y - 2 ||
	               j == 1 || j == SIZE_X - 2)//端のひとつ内側
	            	rating[i][j] = -5;
	            if(i == 0 || i == SIZE_Y - 1 ||
	               j == 0 || j == SIZE_X - 1 )//端
	            	rating[i][j] = 50;
	            if((i == 1 || i == SIZE_Y - 2) &&
	               (j == 1 || j == SIZE_X - 2))//角のひとつ内側
	            	rating[i][j] = -250;
	            if((i == 0 || i == SIZE_Y - 1) &&
	               (j == 0 || j == SIZE_X - 1))//角
	            	rating[i][j] = 600;
	        }
	    }
		return rating;
	}
	//単純に石の数のみで評価
	private int[][] createRatingBoardEquarity(){
		int[][] rating = new int[SIZE_X][SIZE_Y];
		for(int i=0;i<SIZE_Y;i++){
	        for(int j=0;j<SIZE_X;j++){
	            rating[i][j] = 1;
	        }
	    }
		return rating;
	}
}
