package grapheditor.Controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import grapheditor.Model.GraphModel;

public class UndoAction extends AbstractAction {

	//sorry , we didn't finish this function
	
	GraphModel model;

	public UndoAction(GraphModel model) {
		this.model = model;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("UndoButton clicked");
	}

}
