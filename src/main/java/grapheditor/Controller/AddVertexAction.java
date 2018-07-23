package grapheditor.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import grapheditor.Model.GraphModel;
import grapheditor.Model.GraphVertex;

public class AddVertexAction implements ActionListener {

	private JTextField textField;
	private GraphModel model;

	public AddVertexAction(JTextField textField, GraphModel model) {
		this.textField = textField;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//add vertex 
		String name = "@" + textField.getText();
		model.addVertex(new GraphVertex(20, 20, 80, 40, name));
		textField.setVisible(false);
		textField.setText("");
	}
}
