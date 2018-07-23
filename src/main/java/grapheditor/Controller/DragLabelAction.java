package grapheditor.Controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JWindow;
import javax.swing.SwingUtilities;

import grapheditor.Model.GraphModel;
import grapheditor.Model.GraphVertex;
import grapheditor.View.VertexView;

public class DragLabelAction extends MouseAdapter implements MouseListener, MouseMotionListener {

	private GraphModel graphModel;
	private GraphVertex graphVertex;
	private VertexView vertexView;
	Point pressPoint;
	Point releasePoint;

	public DragLabelAction(GraphModel model, GraphVertex graphVertex, VertexView vertexView) {
		this.graphModel = model;
		this.graphVertex = graphVertex;
		this.vertexView = vertexView;
	}

	Window dragWindow = new JWindow() {
		public void paint(Graphics g) {
			super.paint(g);
			vertexView.paint(g);
		}
	};

	
	//when mouse drag this function will make a copy of our vertexView and show up on the screen
	public void mouseDragged(MouseEvent e) {
		Point dragPoint = e.getPoint();
		int xDiff = pressPoint.x - dragPoint.x;
		int yDiff = pressPoint.y - dragPoint.y;

		Rectangle b = e.getComponent().getBounds();
		Point p = b.getLocation();
		SwingUtilities.convertPointToScreen(p, e.getComponent().getParent());
		p.x -= xDiff;
		p.y -= yDiff;

		dragWindow.setLocation(p);
	}

	public void mouseMoved(MouseEvent e) {
	}

	//start dragging
	public void mousePressed(MouseEvent e) {
		pressPoint = e.getPoint();
		Rectangle b = e.getComponent().getBounds();
		Point p = b.getLocation();
		SwingUtilities.convertPointToScreen(p, e.getComponent().getParent());
		dragWindow.setBounds(b);
		dragWindow.setLocation(p);
		dragWindow.setVisible(true);
	}
	
	//when release, send the data to model 
	public void mouseReleased(MouseEvent e) {
		releasePoint = e.getPoint();
		dragWindow.setVisible(false);

		int xDiff = pressPoint.x - releasePoint.x;
		int yDiff = pressPoint.y - releasePoint.y;

		Rectangle b = e.getComponent().getBounds();
		Point p = b.getLocation();
		SwingUtilities.convertPointToScreen(p, e.getComponent().getParent());
		p.x -= xDiff;
		p.y -= yDiff;

		SwingUtilities.convertPointFromScreen(p, vertexView.getParent());
		if (p.x <= 30) {
			p.x = 30;
		}
		if (p.x > vertexView.getParent().getWidth() - b.width) {
			p.x = vertexView.getParent().getWidth() - b.width;
		}
		if (p.y <= 30) {
			p.y = 30;
		}
		if (p.y > vertexView.getParent().getHeight() - b.height) {
			p.y = vertexView.getParent().getHeight() - b.height;
		}

		// vertexView.setLocation(p);
		// vertexView.getParent().repaint();
		// model.changeVertexPosition();
		graphModel.changeVertexPosition(graphVertex, (double) p.x, (double) p.y);
		// graphVertex.changeVertexPosition;
		// graphVertex.changePosition(p.getX(), p.getY());
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		super.mouseClicked(arg0);

		if (arg0.getClickCount() == 2) {
			vertexView.setBackground(Color.yellow);
			graphModel.setSelectedVertex(graphVertex);
		}
	}
}
