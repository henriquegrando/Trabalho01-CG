package edges;
import java.lang.Math;

//Note: this class has a natural ordering that is inconsistent with equals.
public class Edge implements Comparable<Edge>{
	int ymax;
	int xmin;
	int num;	// numerator
	int den;	// and denominator of the (edge's angular coeficient) ^ -1 --> 1/m
	int increment; // increment different than zero for edges with angular coeficient between (0,1)

	public Edge(int ymax, int xmin, int num, int den){
		this.ymax = ymax;
		this.xmin = xmin;
		this.num = num;
		this.den = den;
		this.increment = num/den;
	}

	public int getYmax(){
		return ymax;
	}

	public int getXmin(){
		return xmin;
	}

	public int getNum(){
		return num;
	}

	public int getDen(){
		return den;
	}

	// Updates Edge's xmin considering an increment of 1 in y cordinate
	public void scan(){
		// num += num;
		//
		// if (Math.abs(num) > den){
		// 	num = den
		// }
	}

	// Compare edges by its xmin parameters
	@Override
	public int compareTo(Edge other){
		return this.xmin - other.xmin;
	}
}
