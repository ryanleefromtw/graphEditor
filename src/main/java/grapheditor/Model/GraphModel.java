package grapheditor.Model;

//import java.awt.Rectangle;

import grapheditor.Model.GraphVertex;
import grapheditor.Model.GraphEdge;

import java.io.*;
import java.nio.file.Path;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.List;
import java.util.Observable;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

public class GraphModel extends Observable {

	private ArrayList<GraphEdge> edges;
	private ArrayList<GraphVertex> vertices;
	private ArrayList<GraphVertex> selectedVertex = new ArrayList<>();

	public GraphModel() {
		edges = new ArrayList<GraphEdge>();
		vertices = new ArrayList<GraphVertex>();
	}

	
	//when users double click the vertex, it will be added to here
	public void setSelectedVertex(GraphVertex graphvertex) {
		selectedVertex.add(graphvertex);
	}

	public ArrayList<GraphVertex> getSelectedVertex() {
		return selectedVertex;
	}


	//after manipulation of vertex, clear them all.
	public void clearSelectedVertex() {
		selectedVertex.clear();
	}

	public ArrayList<GraphEdge> getEdges() {
		return this.edges;
	}

	public ArrayList<GraphVertex> getVertices() {
		return this.vertices;
	}

	public void addVertex(GraphVertex vertex) {
		vertices.add(vertex);
		setChanged();
		notifyObservers(vertex);
	}

	public void removeSelectedVertex() {

		// if users choose more than one vertex this will show up.
		if (selectedVertex.size() != 1) {
			System.out.println("You have to click one and only one vertex for deletion!");
			clearSelectedVertex();
			return;
		}
		//get selected vertex that user wants to remove
		GraphVertex removeVertex = selectedVertex.get(0);
		Iterator<GraphEdge> iterator = getEdges().iterator();

		// reomve edges
		
		while (iterator.hasNext()) {
			GraphEdge edge = iterator.next();
			if ((edge.getStartVertex().equals(removeVertex)) || (edge.getEndVertex().equals(removeVertex))) {
				iterator.remove();
			}
		}

		setChanged();
		notifyObservers(removeVertex);
		clearSelectedVertex();
	}

	public void addEdgesByButton() {
		edges.add(new GraphEdge(selectedVertex.get(0), selectedVertex.get(1)));
		setChanged();
		notifyObservers(1);
		clearSelectedVertex();
	}

	public boolean checkEdgesExistence() {
		for (int i = 0; i < edges.size(); i++) {
			if ((edges.get(i).getStartVertex().equals(selectedVertex.get(0))
					&& edges.get(i).getEndVertex().equals(selectedVertex.get(1)))
					|| (edges.get(i).getStartVertex().equals(selectedVertex.get(1))
							&& edges.get(i).getEndVertex().equals(selectedVertex.get(0)))) {
				System.out.println("The edge is already exist!");
				return true;
			}
		}
		return false;
	}

	public void addEdges(GraphEdge edge) {
		edges.add(edge);
		setChanged();
		notifyObservers();
	}

	public void removeEdges(GraphEdge edge) {
		edges.remove(edge);
	}

	public void save(String fileName) {
		List<String> lines = new ArrayList<String>();
		String vertex_edgesNum = this.vertices.size() + " " + this.edges.size();

		lines.add(vertex_edgesNum);
		for (int i = 0; i < vertices.size(); i++) {
			String vertexPosition = (int) this.vertices.get(i).getX() + " " + (int) this.vertices.get(i).getY() + " "
					+ (int) this.vertices.get(i).getWidth() + " " + (int) this.vertices.get(i).getHeight() + " "
					+ this.vertices.get(i).getName().toString();
			lines.add(vertexPosition);
		}
		for (int j = 0; j < edges.size(); j++) {
			String edgeInformation = this.edges.get(j).getStartVertex().getName() + " "
					+ this.edges.get(j).getEndVertex().getName();
			lines.add(edgeInformation);
		}
		
		Path file = Paths.get(fileName);
		
		try {
			Files.write(file, lines, Charset.forName("UTF-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//load file from data.txt
	public void load(String fileName) throws IOException {

		int vertexNum = 0, edgesNum = 0;

		List<String> content = readSmallTextFile(fileName);
		File file = new File(fileName);// this could be null since it might
		// required url
		Scanner scanner = new Scanner(file);
		System.out.println("content size = " + content.size());
		// read vertex , edges Num;
		vertexNum = scanner.nextInt();
		edgesNum = scanner.nextInt();
		System.out.println("v : " + vertexNum + " e:" + edgesNum);

		// read vertex;
		for (int j = 0; j < vertexNum; j++) {
			int x = scanner.nextInt();
			int y = scanner.nextInt();
			int width = scanner.nextInt();
			int height = scanner.nextInt();
			String name = scanner.next();
			addVertex(new GraphVertex(x, y, width, height, name));
			System.out.println(x + " " + y + " " + width + " " + height + " " + name);
		}

		// read edgesNum;
		for (int k = 0; k < edgesNum; k++) {
			String startName = scanner.next();
			String endName = scanner.next();
			GraphVertex startNode = findGraphVertexByName(startName);
			GraphVertex endNode = findGraphVertexByName(endName);

			addEdges(new GraphEdge(startNode, endNode));
			System.out.println("s : " + startNode.toString() + " e : " + endNode.toString());
		}

	}

	private List<String> readSmallTextFile(String fileName) throws IOException {
		Path path = Paths.get(fileName);
		return Files.readAllLines(path, Charset.forName("UTF-8"));
	}

	private GraphVertex findGraphVertexByName(String name) {
		GraphVertex gv = null;
		for (int i = 0; i < vertices.size(); i++) {
			GraphVertex temp = vertices.get(i);
			if (name.equals(temp.getName())) {
				return temp;
			}
		}
		return null;
	}

	public void changeVertexPosition(GraphVertex vertex, double x, double y) {
		vertex.changePosition(x, y);
		setChanged();
		notifyObservers();
	}

}