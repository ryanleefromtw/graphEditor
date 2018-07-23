package grapheditor.Model;

import grapheditor.Model.GraphVertex;

import java.awt.*;
import java.awt.geom.Point2D;

public class GraphEdge {
	private GraphVertex startVertex;
	private GraphVertex endVertex;
	Point startPoint;
	Point endPoing;

	public GraphEdge() {

	}

	public GraphEdge(GraphVertex startVertex, GraphVertex endVertex) {
		this.startVertex = startVertex;
		this.endVertex = endVertex;
	}

	public GraphVertex getStartVertex() {
		return startVertex;
	}

	public GraphVertex getEndVertex() {
		return endVertex;
	}

	public String toString() {
		return startVertex.getName() + "," + endVertex.getName();
	}


	//we make the staring point of the edges from the middle of a vertex
	//so we multiple 0.5
	
	public int getStartVertexPointX() {
		return (int) (startVertex.getX() + 0.5 * startVertex.getWidth());

	}

	public int getStartVertexPointY() {
		return (int) (startVertex.getY() + 0.5 * startVertex.getHeight());
	}

	public int getEndVertexPointX() {
		return (int) (endVertex.getX() + 0.5 * endVertex.getWidth());

	}

	public int getEndVertexPointY() {
		return (int) (endVertex.getY() + 0.5 * endVertex.getHeight());
	}

}
