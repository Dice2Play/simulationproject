package Simulation.View;

import java.awt.EventQueue;

import javax.swing.JFrame;

import Simulation.Core.Node;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JPanel;



public class SimulationView {

	private JFrame frame;
	private JButton btnShowDistanceDistribution;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblDegree;
	private JTextField textField_2;
	private JPanel panel;

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
		frame.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("max(7dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("135px:grow"),
				ColumnSpec.decode("159px:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(59dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.LINE_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("23px"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(19dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(18dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Erdos");
		frame.getContentPane().add(rdbtnNewRadioButton, "3, 2");
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Configuration");
		frame.getContentPane().add(rdbtnNewRadioButton_1, "4, 2");
		
		btnShowDistanceDistribution = new JButton("Show Distance Distribution");
		frame.getContentPane().add(btnShowDistanceDistribution, "12, 2, left, top");
		
		JLabel lblNewLabel = new JLabel("Amount of nodes");
		frame.getContentPane().add(lblNewLabel, "3, 4, right, default");
		
		textField = new JTextField();
		frame.getContentPane().add(textField, "4, 4, fill, default");
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Probability");
		frame.getContentPane().add(lblNewLabel_1, "3, 6, right, default");
		
		textField_1 = new JTextField();
		frame.getContentPane().add(textField_1, "4, 6, fill, default");
		textField_1.setColumns(10);
		
		lblDegree = new JLabel("Degree");
		frame.getContentPane().add(lblDegree, "3, 8, right, default");
		
		textField_2 = new JTextField();
		frame.getContentPane().add(textField_2, "4, 8, fill, default");
		textField_2.setColumns(10);
		
		panel = new JPanel();
		frame.getContentPane().add(panel, "3, 10, 14, 1, fill, fill");
		
	}
	
	public void setVisible(boolean setVisible)
	{
		frame.setVisible(setVisible);
	}
	
	public void addNode(Node node)
	{
		// Get coordinates, draw
	}
	
	public void addShowDistanceDistribution_ActionListener(ActionListener listenForCalcButton)
	{
		btnShowDistanceDistribution.addActionListener(listenForCalcButton);
	}

}
