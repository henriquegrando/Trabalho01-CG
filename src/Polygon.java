//package my_package;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.*;
import Edges.*;

public class Polygon {
	ArrayList<Point> vertices = new ArrayList<Point>();
	ArrayList<Point> pixels = new ArrayList<Point>();
	ArrayList<Edge> edgeTable = new ArrayList<Edge>();
	ArrayList<Edge> activeEdgeTable = new ArrayList<Edge>();
	Color color;
	
	public Polygon() {
		color = Color.GRAY;
	}
	
	public Polygon(Color color) {
		this.color = color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void addVertice(Point v) {
		vertices.add(v);
	}
	
	public int sizeOfVertices() {
		return vertices.size();
	}
	
	public void addPixel(Point p) {
		pixels.add(p);
	}
	
	public void paintPolygon(Graphics g, int gridSize, int left, int top) {
		g.setColor(color);
		for (Point v : vertices) {
			g.fillRect(v.x + left, v.y + top, gridSize , gridSize);
		}
		
		for (Point p : pixels) {
			g.fillRect(p.x + left, p.y + top, gridSize , gridSize);
		}
	}
	
	public void fillPolygon(int gridSize) {
		Edge transfer, head, aux;
		// TODO: algoritmo que a moca pediu vai aqui
		
		//Obtem a menor coordenada y armazenada na ET; ou seja, o valor de y do primeiro “cesto” nao vazio. 
		buildEdgeTable();
		int index = -1; //o grid nao tem posicoes negativas, certo?
		boolean flag = false; 
		int i = 0;
		while (!flag){
			if (edgeTable.get(i) == null) { i++;}
			else {
				flag = true;
			}
		}
		index = i;
		
		//Transfere do cesto y na ET para a AET as arestas cujo ymin = y (lados que estao comecando a serem varridos), mantendo a AET ordenada em x
		transfer = edgeTable.get(index);
		if(activeEdgeTable.get(index) == null){
			activeEdgeTable.add(index, transfer);
		}
		else{
			aux = activeEdgeTable.get(index);
			//head = aux;
			while (aux.nextEdge != null){
				aux = aux.nextEdge();
			}
			aux.addNextEdge(transfer);
			// DUVIDA: como atualizar um elemento de uma lista ligada que nao é a cabeca (que esta no arraylist)
			activeEdgeTable.add(index, aux);
		}

		//-------------------------
		// DEBUG
		for (int i = vertices.get(0).x + gridSize; i <= vertices.get(1).x; i++) {
			addPixel(new Point(i, vertices.get(0).y));
		}
	}

	// inutil, a principio
	/*
	public void leftEdgeScan(int xmin, int xmax, int ymin, int ymax, int valor){
		int x, y;
		Point p;

		x = xmin;
		y = ymin;
		numerador = xmax - xmin;
		denominador = ymax - ymin;
		incremento = denominador;

		for(y = ymin; y <= ymax; y++){
			//PintaPixel(x, y, valor)
			p = (x, y);
			addPixel(p);
			incremento += numerador;

			if (incremento > denominador){
				x++;
				incremento -= denominador;
			}
		}
	}
	*/
	public void buildEdgeTable(){
		Point v1, v2;
		Edge edge;
		int xmin, xmax, ymin, ymax, num, den, scanline, index;

		for(Iterator<Point> i = vertices.iterator(); i.hasNext(); ) {
    		//seleciona dois pontos consecutivos
    		v1 = i.next();
    		index = vertices.indexOf(v1);
    		if (index == vertices.size())
    			v2 = vertices.get(0);
    		else
    			v2 = vertices.get(index+1);

    		//calculo das informacoes que fazem parte da tabela
    		xmin = Math.min(v1.x, v2.x);
    		ymax = Math.max(v1.y, v2.y);

    		scanline = Math.min(v1.y, v2.y);

    		// 1/m
    		num = (v2.x - v1.x);
    		den = (v2.y - v1.y);

    		edgeTable.add(scanline, new Edge(ymax, xmin, num, den, scanline));

		}
	}
}
