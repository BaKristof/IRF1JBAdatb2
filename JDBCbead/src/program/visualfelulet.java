package program;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;

public class visualfelulet {

	private JFrame frame;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {	
				try {
					visualfelulet window = new visualfelulet();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public visualfelulet() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		SQLalap sql = new SQLalap();
		sql.CreateTable();
		sql.TabelUpload();
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		
		textField = new JTextField();
		textField.setBounds(370, 192, 86, 20);
		textField.setVisible(false);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setVisible(false);
		textField_1.setBounds(469, 192, 86, 20);
		frame.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setVisible(false);
		textField_2.setBounds(568, 192, 86, 20);
		frame.getContentPane().add(textField_2);
		
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(370, 261, 86, 20);
		textField_3.setVisible(false);
		frame.getContentPane().add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setVisible(false);
		textField_4.setBounds(469, 261, 86, 20);
		frame.getContentPane().add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setVisible(false);
		textField_5.setBounds(568, 261, 86, 20);
		frame.getContentPane().add(textField_5);
		
		
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(370, 167, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(469, 167, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("New label");
		lblNewLabel_1_1.setBounds(568, 167, 46, 14);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("New label");
		lblNewLabel_1_2.setBounds(370, 236, 46, 14);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("New label");
		lblNewLabel_1_3.setBounds(469, 236, 46, 14);
		frame.getContentPane().add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("New label");
		lblNewLabel_1_4.setBounds(568, 236, 46, 14);
		frame.getContentPane().add(lblNewLabel_1_4);
		
		
		JComboBox comboBox = new JComboBox( SQLalap.GetTableName() );
		comboBox.setVisible(false);
		comboBox.setBounds(370, 115, 86, 22);
		frame.getContentPane().add(comboBox);
		
		
		
		JButton btnNewButton = new JButton("Megjelenites");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.isVisible()){
					try {
						ResultSet rs = sql.LookTable(comboBox.getSelectedItem().toString());
						String[] collum =sql.TableCollum(comboBox.getSelectedItem().toString());
						
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
					comboBox.setVisible(false);
				}
				else {
				comboBox.setVisible(true);	
					
				}
				
				
				
				
			}
		});
		btnNewButton.setBounds(370, 11, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Data To Diak");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField_3.isVisible())
				{
					try {
						sql.DataUploadToDiak(textField.getText(), textField_1.getText(), textField_2.getText(), Integer.parseInt( textField_3.getText()));
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					textField.setVisible(false);
					textField_1.setVisible(false);
					textField_2.setVisible(false);
					textField_3.setVisible(false);
				}
				else {
					textField.setVisible(true);
					textField_1.setVisible(true);
					textField_2.setVisible(true);
					textField_3.setVisible(true);
				}
				
				
				
			}
		});
		btnNewButton_1.setBounds(469, 11, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Databydaz");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.isVisible())
				{
					try {
						sql.DataChangeInDiakByDazon(Integer.parseInt( textField.getText()), textField_1.getText());
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					textField.setVisible(false);
					textField_1.setVisible(false);
				}
				else {
					textField.setVisible(true);
					textField_1.setVisible(true);
				}
				
				
			}
		});
		btnNewButton_2.setBounds(568, 11, 89, 23);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Diak delete");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.isVisible())
				{
					try {
						sql.DeleteDataFromDiakByDazon(Integer.parseInt( textField.getText()));
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					textField.setVisible(false);
				}
				else {
					textField.setVisible(true);
				}
				
				
			}
		});
		btnNewButton_3.setBounds(370, 45, 89, 23);
		frame.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("New button");
		btnNewButton_4.setBounds(469, 45, 89, 23);
		frame.getContentPane().add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("New button");
		btnNewButton_5.setBounds(568, 45, 89, 23);
		frame.getContentPane().add(btnNewButton_5);
		
		TableColumnModel table = null;
		TableColumn tb = new TableColumn();
		tb.setHeaderValue("Bakos");
		table.addColumn(tb);
		table_1 = new JTable();
		table_1.setBounds(36, 33, 304, 292);
		table_1 .setColumnModel(table);
		frame.getContentPane().add(table_1);
		
	}
	public void megjelenit (ResultSet rs )
	{
		
		
		
	}
}
