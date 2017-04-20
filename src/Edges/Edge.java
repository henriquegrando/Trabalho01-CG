package Edges;

<<<<<<< HEAD
// Note: this class has a natural ordering that is inconsistent with equals.
public class Edge implements Comparable<Edge>{
	int ymax;
	int xmin;
	int num;	// numerator
	int den;	// and denominator of the (edge's angular coeficient) ^ -1 --> 1/m
	int inc;

	public Edge(int ymax, int xmin, int num, int den) {
		this.ymax = ymax;
		this.xmin = xmin;
		this.num = num;
		this.den = den;
		this.inc = den;
	}

	public int getYmax() {
		return ymax;
	}

	public int getXmin() {
		return xmin;
	}

	public int getNum() {
		return num;
	}

	public int getDen() {
		return den;
	}

	// Updates Edge's xmin considering an increment of 1 in y cordinate
	public void scan(){

		// Left and top edge scan
		if (num > 0 && den > 0){
			inc += num;

			// Loop run more than once for top edge
			while (inc > den){
				inc -= den;
				xmin++;
			}
		}

		// Right and bottom edge scan
		else {
			inc -= num;

			// Loop run more than once for bottom edge
			while (Math.abs(inc) > Math.abs(den)){
				inc -= den;
				xmin--;
			}
		}
	}

	// Compare edges by its xmin parameters
	@Override
	public int compareTo(Edge other) {
		return this.xmin - other.xmin;
	}
}
