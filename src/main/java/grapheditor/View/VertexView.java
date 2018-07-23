package grapheditor.View;

import java.awt.Color;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

import grapheditor.Controller.DragLabelAction;
import grapheditor.Model.GraphModel;
import grapheditor.Model.GraphVertex;

public class VertexView extends JLabel {

	Point pressPoint;
	Point releasePoint;
	DragLabelAction dragLabelAction;
	private GraphVertex graphVertex;

	public VertexView(GraphModel graphModel, GraphVertex graphVertex) {
		super(graphVertex.getName());
		this.graphVertex = graphVertex;
		initView();
		dragLabelAction = new DragLabelAction(graphModel, graphVertex, this);
		addMouseListener(dragLabelAction);
		addMouseMotionListener(dragLabelAction);
	}

	public GraphVertex getGraphVertex() {
		return graphVertex;
	}

	private void initView() {
		Border border = BorderFactory.createLineBorder(Color.BLUE, 2);
		setBorder(border);
		setBackground(Color.PINK);
		setOpaque(true);
		setBounds((int) graphVertex.getX(), (int) graphVertex.getY(), (int) graphVertex.getWidth(),
				(int) graphVertex.getHeight());
		setVerticalAlignment(JLabel.CENTER);
		setHorizontalAlignment(JLabel.CENTER);

	}

}
