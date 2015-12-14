package jp.ac.tanaka.Util;

public class Vector {
	public int x;
	public int y;
	
	public Vector(){
		this(0,0);
	}
	public Vector(Vector v){
		this(v.x,v.y);
	}
	public Vector(int x,int y){
		this.x = x;
		this.y = y;
	}
	
	public static Vector sum(Vector v1,Vector v2){
		return new Vector(v1.x + v2.x, v1.y+v2.y);
	}
	
	public void sum(Vector v){
		this.x += v.x;
		this.y += v.y;
	}
	
	@Override
	public String toString(){
		return "["+ this.x + "," + this.y + "]";
	}
}
