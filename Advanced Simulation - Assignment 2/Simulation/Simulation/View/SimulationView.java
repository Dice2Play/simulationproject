package Simulation.View;

import java.awt.EventQueue;

import javax.swing.JFrame;

import Simulation.Core.Node;



public class SimulationView {

	private JFrame frame;

	/**
	 * Create the application.
	 */
	public SimulationView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 703, 422);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public void setVisible(boolean setVisible)
	{
		frame.setVisible(setVisible);
	}
	
	public void addNode(Node node)
	{
		// Get coordinates, draw
	}

}
