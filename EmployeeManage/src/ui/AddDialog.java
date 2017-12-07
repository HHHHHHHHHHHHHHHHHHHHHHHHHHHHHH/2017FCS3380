package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entity.Employee;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

public class AddDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField name;
	private JTextField workNumber;
	private JTextField email;
	private JTextField idCard;
	private JTextField inTime;
	private JTextField position;
	private JTextField nativePlace;
	private JTextField phoneNumber;
	private JTextField degree;

	private MainFrame parent;

	/**
	 * Create the dialog.
	 */
	public AddDialog(MainFrame frame) {
		this.parent = frame;
		setBounds(100, 100, 580, 440);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Department:");
			lblNewLabel.setBounds(75, 23, 85, 16);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel label1 = new JLabel("Name:");
			label1.setBounds(328, 23, 61, 16);
			contentPanel.add(label1);
		}
		{
			name = new JTextField();
			name.setBounds(421, 18, 130, 26);
			contentPanel.add(name);
			name.setColumns(10);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Gender:");
			lblNewLabel_2.setBounds(75, 71, 61, 16);
			contentPanel.add(lblNewLabel_2);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("Work Number:");
			lblNewLabel_3.setBounds(328, 71, 90, 16);
			contentPanel.add(lblNewLabel_3);
		}
		{
			workNumber = new JTextField();
			workNumber.setBounds(421, 66, 130, 26);
			contentPanel.add(workNumber);
			workNumber.setColumns(10);
		}
		{
			JLabel newLabel4 = new JLabel("Email:");
			newLabel4.setBounds(75, 115, 61, 16);
			contentPanel.add(newLabel4);
		}
		{
			email = new JTextField();
			email.setBounds(153, 110, 130, 26);
			contentPanel.add(email);
			email.setColumns(10);
		}
		{
			JLabel newlabel5 = new JLabel("ID card:");
			newlabel5.setBounds(328, 115, 81, 16);
			contentPanel.add(newlabel5);
		}
		{
			idCard = new JTextField();
			idCard.setBounds(421, 110, 130, 26);
			contentPanel.add(idCard);
			idCard.setColumns(10);
		}
		{
			JLabel newLabel6 = new JLabel("Entry Time:");
			newLabel6.setBounds(75, 165, 85, 16);
			contentPanel.add(newLabel6);
		}
		{
			inTime = new JTextField();
			inTime.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			inTime.setBounds(153, 160, 130, 26);
			contentPanel.add(inTime);
			inTime.setColumns(10);
		}

		JLabel lblNewLabel_7 = new JLabel("Position:");
		lblNewLabel_7.setBounds(328, 165, 61, 16);
		contentPanel.add(lblNewLabel_7);

		position = new JTextField();
		position.setBounds(421, 160, 130, 26);
		contentPanel.add(position);
		position.setColumns(10);

		JLabel lblNewLabel_8 = new JLabel("State:");
		lblNewLabel_8.setBounds(75, 216, 61, 16);
		contentPanel.add(lblNewLabel_8);

		JComboBox<String> depComboBox = new JComboBox<>(new String[] { "Finance Department", "Employment Department",
				"Administration Department", "Production Department", "Logistics Department" });
		depComboBox.setBounds(153, 19, 130, 27);
		contentPanel.add(depComboBox);

		nativePlace = new JTextField();
		nativePlace.setBounds(153, 211, 130, 26);
		contentPanel.add(nativePlace);
		nativePlace.setColumns(10);

		JLabel lblNewLabel_9 = new JLabel("Phone Number:");
		lblNewLabel_9.setBounds(328, 216, 102, 16);
		contentPanel.add(lblNewLabel_9);

		phoneNumber = new JTextField();
		phoneNumber.setBounds(421, 211, 130, 26);
		contentPanel.add(phoneNumber);
		phoneNumber.setColumns(10);

		JLabel lblNewLabel_10 = new JLabel("Degree:");
		lblNewLabel_10.setBounds(75, 261, 61, 16);
		contentPanel.add(lblNewLabel_10);

		degree = new JTextField();
		degree.setBounds(153, 256, 130, 26);
		contentPanel.add(degree);
		degree.setColumns(10);

		JRadioButton maleRadio = new JRadioButton("Male");
		maleRadio.setBounds(148, 67, 63, 23);
		contentPanel.add(maleRadio);

		JRadioButton femaleRadio = new JRadioButton("Female");
		femaleRadio.setBounds(212, 67, 85, 23);
		contentPanel.add(femaleRadio);

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(maleRadio);
		buttonGroup.add(femaleRadio);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						Employee employee = new Employee();
						employee.setDepartmentId(depComboBox.getSelectedItem().toString());
						employee.setGender(femaleRadio.isSelected() ? "Female" : "Male");
						employee.setEmail(email.getText());
						try {
							employee.setGetInTime(new SimpleDateFormat("yyyy-MM-dd").parse(inTime.getText()));
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						employee.setName(name.getText());
						employee.setNativePlace(nativePlace.getText());
						employee.setIdCard(idCard.getText());
						employee.setPhoneNumber(phoneNumber.getText());
						employee.setPosition(position.getText());
						employee.setWorkNumber(workNumber.getText());
						employee.setDegree(degree.getText());
						parent.addEmp(employee);
						dispose();
					}
				});
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}
}
