package jp.ac.tanaka.Othello.IO;

import jp.ac.tanaka.Othello.Board.*;

import static jp.ac.tanaka.Othello.OthelloConstant.SIZE_X;
import static jp.ac.tanaka.Othello.OthelloConstant.SIZE_Y;

public class OutputCUI {
	//実体化させない
	private OutputCUI() {
	};


	//オセロ盤を表示
	public static void board(Board board){
	    int i,j;
	    
		System.out.print("    ");
	    for(i=0;i<SIZE_X;i++)
	        System.out.print(" "+(char)(i+'a')+"  ");
	    System.out.print("\n");
		for(i=0;i<SIZE_Y;i++){
	        line();
	        System.out.print(" " + (char)(i+'1') + " |");
	        for(j=0;j<SIZE_X;j++){
	        		OutputCUI.stone(board.getStones(j, i));
	            System.out.print("|");
	        }
	        System.out.print("\n");
	    }
	    line();
	}

	// 石を表示○●
	public static void stone(Stone stone) {
		//TODO nullなら空白
		System.out.print(stone == null ? "   " :
			stone.getStoneColor() == StoneColor.WHITE ? " ○ " : " ● ");
	}
	
	//オセロ盤の横ラインを表示
	private static void line(){
	    int i;
	    System.out.print("    ");
	    for(i=0;i<SIZE_X;i++)
	        System.out.print("----");
	    System.out.println("");
	}
	   
}
