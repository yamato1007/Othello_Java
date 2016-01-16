package yamato.util;
import java.io.*;

public class MyInput {
    public static String getStr(){
        String buf = "";
        try {buf = (new BufferedReader(new InputStreamReader(System.in))).readLine();}
        catch (IOException e) {
            System.out.println("!IOException!\n!よくわからないけど入力に失敗しました!");
            System.out.println(e.toString());
        }
        return buf;
    }
    public static int getInt(){
        try {return  Integer.parseInt(getStr());}
        catch(java.lang.NumberFormatException e){
            System.out.println("!!整数を入力せよ!!");
            return getInt();
        }   
    }
    public static int getIntRange(int start , int end){
        int n = getInt();
        if(start > end){ 
            int tmp = start;
            start = end;
            end = tmp;
        }
        if( n < start || end < n){
            System.out.println("!!" + start + "から" + end + "までの整数を入力せよ!!");
            return getIntRange(start,end);
        }
        return n;
    }
    public static int getUnsignedInt(){
        int n = getInt();
        if(n < 0){
            System.out.println("!!正の整数を入力せよ!!");
            return getUnsignedInt();
        }
        return n;
    }
}

