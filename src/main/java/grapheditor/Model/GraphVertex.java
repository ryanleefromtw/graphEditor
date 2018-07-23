package grapheditor.Model;

import javafx.scene.input.MouseDragEvent;

import javax.swing.event.MenuDragMouseEvent;
import javax.swing.event.MenuDragMouseListener;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;
import java.util.Observable;

public class GraphVertex {

	private String name;
	private double x;
	private double y;
	private double width;
	private double height;
	private Dimension lastPosition;

	public GraphVertex(int x, int y, int width, int height, String name) {
		// the constructor should also add in name
		this.x = x;
		// if y < 30 which means it will be under toolbar
		if (y < 30) {
			this.y = 30;
		} else {
			this.y = y;
		}
		this.width = width;
		this.height = height;
		this.name = name;

	}

	public Dimension getLastPosition() {
		return lastPosition;
	}

	public void setLastPosition(Dimension lastPosition) {
		this.lastPosition = lastPosition;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public void changePosition(double x, double y) {
		this.x = x;
		this.y = y;
	}

}