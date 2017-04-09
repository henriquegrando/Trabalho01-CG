package Edges;

public class Edge {
	int ymax;
	int xmin;
	/* 1/m é salvo como divisão de dois inteiros */
	int num;
	int den;
	int scanline;
	boolean isHeader;

	Edge nextEdge;

	public Edge (){
		this.nextEdge = null;
		isHeader = true;
	}

	public Edge(int ymax, int xmin, int num, int den, int scanline){
		this.ymax = ymax;
		this.xmin = xmin;
		this.num = num;
		this.den = den;
		this.scanline = scanline;
		this.nextEdge = null;
		isHeader = false;
	}

	public void addNextEdge(Edge next){
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

	//NOTE: nao foi testado
	public void bubbleSort(Edge head) {
        if (head.isHeader() == true) {
            boolean wasChanged;

            do {
                Edge current = head;
                Edge previous = null;
                Edge next = head.nextEdge;
                wasChanged = false;

                while ( next != null ) {
                    if (current.xmin > next.xmin) {
                        wasChanged = true;

                        if ( previous != null ) {
                            Edge sig = next.nextNode;

                            previous.nextNode = next;
                            next.nextNode = current;
                            current.nextNode = sig;
                        } else {
                            Edge sig = next.nextNode;

                            head = next;
                            next.nextNode = current;
                            current.nextNode = sig;
                        }

                        previous = next;
                        next = current.nextNode;
                    } else { 
                        previous = current;
                        current = next;
                        next = next.nextNode;
                    }
                } 
            } while( wasChanged );
        }
    }



}