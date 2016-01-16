package yamato.util;

public class Vector implements Cloneable{
	public int x;
	public int y;

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

	
	@Override
	public Vector clone(){
		return new Vector(this);
	}
	
	@Override
	public String toString(){
		return "["+ this.x + "," + this.y + "]";
	}
	
	@Override
	public int hashCode() {
		int result = 37;
		result += 31 * x;
		result += 31 * y;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null) return false;
		if(!(obj instanceof Vector)) return false;
		Vector v = (Vector) obj;
		return this.x == v.x && this.y == v.y;
	}
}
