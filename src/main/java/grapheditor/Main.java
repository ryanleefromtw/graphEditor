package grapheditor;


import java.awt.*;
import java.io.IOException;

import grapheditor.Model.GraphModel;
import grapheditor.Model.GraphVertex;
import grapheditor.View.GraphFrame;
import grapheditor.View.GraphPanel;
import grapheditor.View.VertexView;

import javax.swing.border.EtchedBorder;

public class Main {
		//this class is the starting point of the program
	public static void main(String[] args) throws IOException {
		
		GraphModel model = new GraphModel();

		model.load(args[0]);

		GraphPanel panel = new GraphPanel(model);
		GraphFrame frame = new GraphFrame(panel);

	}
}