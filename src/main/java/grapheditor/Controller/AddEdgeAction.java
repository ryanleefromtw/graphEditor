package grapheditor.Controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;

import grapheditor.Model.GraphModel;
import grapheditor.View.VertexView;

public class AddEdgeAction extends AbstractAction {
//this class is used when users double click on two vertex
	GraphModel model;
	ArrayList<VertexView> vertexViews;

	public AddEdgeAction(GraphModel model, ArrayList<VertexView> vertexViews) {
		this.vertexViews = vertexViews;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!model.checkEdgesExistence()) {//to check it the edges has already existed
			model.addEdgesByButton();//add edges 
		} else {
			//if edges is already exitsedm paint the color back to pink
			for (int i = 0; i < vertexViews.size(); i++) {
				if ((model.getSelectedVertex().get(0).equals(vertexViews.get(i).getGraphVertex()))
						|| (model.getSelectedVertex().get(1).equals(vertexViews.get(i).getGraphVertex()))) {
					vertexViews.get(i).setBackground(Color.PINK);
				}
			}
		
			model.clearSelectedVertex(); 
		}

	}
}
