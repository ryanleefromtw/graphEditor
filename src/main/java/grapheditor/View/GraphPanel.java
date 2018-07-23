package grapheditor.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import grapheditor.Controller.AddEdgeAction;
import grapheditor.Controller.AddVertexAction;
import grapheditor.Controller.DeleteNodeAction;
import grapheditor.Controller.RedoAction;
import grapheditor.Controller.UndoAction;
import grapheditor.Model.GraphEdge;
import grapheditor.Model.GraphModel;
import grapheditor.Model.GraphVertex;

public class GraphPanel extends JPanel implements Observer {

	private GraphModel model;
	ArrayList<VertexView> vertexViewBox = new ArrayList<>();

	// model.addObserver(this);

	private JTextField textField;

	public GraphPanel(GraphModel model) {

		setLayout(null);
		setPreferredSize(new Dimension(800, 600));
		this.model = model;
		model.addObserver(this);

		JToolBar toolBar = new JToolBar();
		setButtonOnToolBar(toolBar);

		toolBar.setBounds(0, 0, 800, 30);
		add(toolBar, BorderLayout.PAGE_START);
		List<GraphVertex> vertexs = model.getVertices();

		for (int i = 0; i < vertexs.size(); i++) {
			System.out.println("paintComponent" + vertexs.get(i).getHeight());
			VertexView vv = new VertexView(model, vertexs.get(i));
			vertexViewBox.add(vv);
			add(vv);
		}

		setTextFiled(textField);

	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (int i = 0; i < vertexViewBox.size(); i++) {
			vertexViewBox.get(i).setLocation((int) vertexViewBox.get(i).getGraphVertex().getX(),
					(int) vertexViewBox.get(i).getGraphVertex().getY());
			System.out.println("-------" + vertexViewBox.get(i).getGraphVertex().getName().toString());
		}

		// draw Edges
		for (int i = 0; i < model.getEdges().size(); i++) {
			GraphEdge graphEdge = model.getEdges().get(i);
			g.drawLine(graphEdge.getStartVertexPointX(), graphEdge.getStartVertexPointY(),
					graphEdge.getEndVertexPointX(), graphEdge.getEndVertexPointY());
		}
		System.out
				.println("newest state v , e :  " + model.getVertices().toString() + " " + model.getEdges().toString());
	}

	public void setToolBar() {

		JToolBar toolBar = new JToolBar();

		setButtonOnToolBar(toolBar);

		toolBar.setBounds(0, 0, 800, 30);

		add(toolBar, BorderLayout.PAGE_START);

	}

	private void setTextFiled(JTextField textFiled) {
		textField = new JTextField();
		textField.setBounds(0, 500, 800, 50);
		textField.setVisible(false);

		add(textField, BorderLayout.SOUTH);
		textField.addActionListener(new AddVertexAction(textField, model));
	}

	private void setButtonOnToolBar(JToolBar toolBar) {

		JButton newNodeButton = new JButton();
		newNodeButton.setText("new node");
		newNodeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textField.setVisible(true);
				textField.requestFocus();
			}
		});

		JButton deleteNodeButton = new JButton();
		deleteNodeButton.setAction(new DeleteNodeAction(model, vertexViewBox));
		deleteNodeButton.setText("delete node");

		JButton addEdgeButton = new JButton();
		addEdgeButton.setAction(new AddEdgeAction(model, vertexViewBox));
		addEdgeButton.setText("add edge");

		JButton undoButton = new JButton();
		undoButton.setAction(new UndoAction(model));
		undoButton.setText("undo");

		JButton redoButton = new JButton("redo");
		redoButton.setAction(new RedoAction(model));
		redoButton.setText("redo");

		toolBar.add(newNodeButton);
		toolBar.add(deleteNodeButton);
		toolBar.add(addEdgeButton);
		toolBar.add(undoButton);
		toolBar.add(redoButton);
	}

	public GraphModel getModel() {
		return model;
	}

	@Override
	public void update(Observable o, Object arg) {

		if (arg != null) {
			// this is used when users use the program wrongly
			// for example click two vertex to delete
			// or add edges that already exist
			// under this situation, the program has to paint the color back
			if (arg.getClass().equals(Integer.class)) {
				for (int i = 0; i < vertexViewBox.size(); i++) {
					vertexViewBox.get(i).setBackground(Color.PINK);
				}
			} else {
				// this is for adding new vertex
				GraphVertex graphVertex = (GraphVertex) arg;
				String specifiedName = graphVertex.getName();

				if (String.valueOf(specifiedName.charAt(0)).equals("@")) {
					graphVertex.setName(graphVertex.getName().substring(1));
					VertexView vv2add = new VertexView(model, graphVertex);
					vertexViewBox.add(vv2add);
					add(vv2add);
					repaint();
				} else {

					// this is for deleting vertex
					for (int i = 0; i < vertexViewBox.size(); i++) {
						if (graphVertex.equals(vertexViewBox.get(i).getGraphVertex())) {
							remove(vertexViewBox.get(i));
						}
					}

					model.getVertices().remove(graphVertex);
				}
			}
		}

		repaint();
	}
}
