package grapheditor.Controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;

import grapheditor.Model.GraphModel;
import grapheditor.View.VertexView;

public class DeleteNodeAction extends AbstractAction {

	GraphModel model;
	ArrayList<VertexView> vertexViews;

	public DeleteNodeAction(GraphModel model, ArrayList<VertexView> vertexViews) {
		this.model = model;
		this.vertexViews = vertexViews;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (model.getSelectedVertex().size() != 1) {//if user click more than one vertex for delection
			for (int i = 0; i < vertexViews.size(); i++) {
				vertexViews.get(i).setBackground(Color.PINK);
				model.clearSelectedVertex();
			}
		} else {
			model.removeSelectedVertex();
		}
	}
}
