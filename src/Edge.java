

public class Edge {
	int ymax;
	int xmin
	/* 1/m é salvo como divisão de dois inteiros */
	int num;
	int den;
	int scanline;

	Edge nextEdge;

	public Edge(int ymax, int xmin, int num, int den, int scanline){
		this.ymax = ymax;
		this.xmin = xmin;
		this.num = num;
		this.den = den;
		this.scanline = scanline;
		this.nextEdge = null;
	}

	public addNextEdge(Edge next){
		this.nextEdge = next;
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