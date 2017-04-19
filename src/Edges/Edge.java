package edges;

public class Edge {
	int ymax;
	int xmin;
	int num;	// numerator
	int den;	// and denominator of the (edge's angular coeficient) ^ -1 --> 1/m

	public Edge(int ymax, int xmin, int num, int den){
		this.ymax = ymax;
		this.xmin = xmin;
		this.num = num;
		this.den = den;
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
}
