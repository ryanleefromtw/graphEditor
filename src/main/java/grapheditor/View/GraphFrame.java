package grapheditor.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import grapheditor.View.GraphPanel;

public class GraphFrame extends JFrame {

	private GraphPanel panel;

	public GraphFrame(GraphPanel panel) {
		this.panel = panel;
		initFrame(panel);

	}

	public void creatMenus() {

		JMenuBar bar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem menuItemSave = new JMenuItem("save");
		menuItemSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.getModel().save("data.txt");
			}
		});
		fileMenu.add(menuItemSave);
		bar.add(fileMenu);
		JMenu editMenu = new JMenu("Edit");
		bar.add(editMenu);
		JMenu windowMenu = new JMenu("Window");
		bar.add(windowMenu);

		this.setJMenuBar(bar);

	}

	public void initFrame(GraphPanel panel) {
		this.setTitle("Graph Editor");
		this.setLayout(new BorderLayout());
		this.add(panel, BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(800, 600));
		this.pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		creatMenus();
		this.setVisible(true);

	}

}
