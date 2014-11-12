import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;

import javax.swing.JComboBox;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ComboBoxes {
	private JFrame frame;
	private JComboBox<String> comboBoxDay;
	private Calendar dateDisplayed;
	private JComboBox<String> comboBoxYear;
	private JComboBox<String> comboBoxMonth;
	private int curYear;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ComboBoxes window = new ComboBoxes();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ComboBoxes() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);

		dateDisplayed =new GregorianCalendar();
		java.util.Date date = new java.util.Date();
		dateDisplayed.setTime(date);
		curYear = dateDisplayed.get(Calendar.YEAR);
		
		JLabel lblMonth = new JLabel("Month:");
		GridBagConstraints gbc_lblMonth = new GridBagConstraints();
		gbc_lblMonth.insets = new Insets(0, 0, 5, 5);
		gbc_lblMonth.anchor = GridBagConstraints.EAST;
		gbc_lblMonth.gridx = 0;
		gbc_lblMonth.gridy = 0;
		frame.getContentPane().add(lblMonth, gbc_lblMonth);
		comboBoxMonth = new JComboBox<String>();
		comboBoxMonth.insertItemAt("January",0);
		comboBoxMonth.insertItemAt("February",1);
		comboBoxMonth.insertItemAt("March",2);
		comboBoxMonth.insertItemAt("April",3);
		comboBoxMonth.insertItemAt("May",4);
		comboBoxMonth.insertItemAt("June",5);
		comboBoxMonth.insertItemAt("July",6);
		comboBoxMonth.insertItemAt("August",7);
		comboBoxMonth.insertItemAt("September",8);
		comboBoxMonth.insertItemAt("October",9);
		comboBoxMonth.insertItemAt("November",10);
		comboBoxMonth.insertItemAt("December",11);
		comboBoxMonth.setSelectedIndex(dateDisplayed.get(Calendar.MONTH));
		comboBoxMonth.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						int index = comboBoxMonth.getSelectedIndex();
						dateDisplayed.set(Calendar.MONTH, index);
						setComboBoxDay();
					}
		});
		GridBagConstraints gbc_comboBoxMonth = new GridBagConstraints();
		gbc_comboBoxMonth.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxMonth.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxMonth.gridx = 1;
		gbc_comboBoxMonth.gridy = 0;
		frame.getContentPane().add(comboBoxMonth, gbc_comboBoxMonth);

		JLabel lblDay = new JLabel("Day:");
		GridBagConstraints gbc_lblDay = new GridBagConstraints();
		gbc_lblDay.anchor = GridBagConstraints.EAST;
		gbc_lblDay.insets = new Insets(0, 0, 5, 5);
		gbc_lblDay.gridx = 0;
		gbc_lblDay.gridy = 1;
		frame.getContentPane().add(lblDay, gbc_lblDay);

		comboBoxDay = new JComboBox<String>();
		setComboBoxDay();
		comboBoxDay.setSelectedIndex(dateDisplayed.get(Calendar.DAY_OF_MONTH)-1);
		GridBagConstraints gbc_comboBoxDay = new GridBagConstraints();
		gbc_comboBoxDay.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxDay.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxDay.gridx = 1;
		gbc_comboBoxDay.gridy = 1;
		frame.getContentPane().add(comboBoxDay, gbc_comboBoxDay);

		JLabel lblYear = new JLabel("Year:");
		GridBagConstraints gbc_lblYear = new GridBagConstraints();
		gbc_lblYear.anchor = GridBagConstraints.EAST;
		gbc_lblYear.insets = new Insets(0, 0, 0, 5);
		gbc_lblYear.gridx = 0;
		gbc_lblYear.gridy = 2;
		frame.getContentPane().add(lblYear, gbc_lblYear);

		comboBoxYear = new JComboBox<String>();
		setComboBoxYear();
		comboBoxYear.setSelectedIndex(5);
		comboBoxYear.addActionListener(
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						int year = comboBoxYear.getSelectedIndex()+curYear-5;
						dateDisplayed.set(Calendar.YEAR, year);
						setComboBoxDay();
					}
		});
		GridBagConstraints gbc_comboBoxYear = new GridBagConstraints();
		gbc_comboBoxYear.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxYear.gridx = 1;
		gbc_comboBoxYear.gridy = 2;
		frame.getContentPane().add(comboBoxYear, gbc_comboBoxYear);
	}


	private void setComboBoxYear() {
		int year = dateDisplayed.get(Calendar.YEAR);
		int j=0;
		for(int i=year-5 ; i<year+5 ; i++){
			comboBoxYear.insertItemAt(String.valueOf(i),j);
			j+=1;
		}
		
	}

	private void setComboBoxDay(){
		int noDaysInMonthSelected = dateDisplayed.getActualMaximum(Calendar.DAY_OF_MONTH);
		if( comboBoxDay.getItemCount() <  noDaysInMonthSelected) {
			for(int i=comboBoxDay.getItemCount() ; i<noDaysInMonthSelected ; i++){
				comboBoxDay.insertItemAt(String.valueOf(i+1), i);
			}
		} else if( comboBoxDay.getItemCount() > noDaysInMonthSelected ) {
			for(int i=comboBoxDay.getItemCount(); i>noDaysInMonthSelected ; i--){
				comboBoxDay.removeItemAt(i-1);
			}
		}
	}

}
