package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import dao.EmployeeDao;
import dao.JobScheduleDao;
import entity.Employee;
import entity.JobSchedule;
import util.MailUtils;

import javax.swing.JTree;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private EmployeeDao employeeDao=new EmployeeDao();
	
	private JobScheduleDao scheduleDao=new JobScheduleDao();
	
	DefaultMutableTreeNode root=new DefaultMutableTreeNode("All Staff",true);
	DefaultMutableTreeNode finicial=new DefaultMutableTreeNode("Finance Department",true);
	DefaultMutableTreeNode people=new DefaultMutableTreeNode("Employment Department",true);
	DefaultMutableTreeNode invotery=new DefaultMutableTreeNode("Administration Department",true);
	DefaultMutableTreeNode politic=new DefaultMutableTreeNode("Production Department",true);
	DefaultMutableTreeNode back=new DefaultMutableTreeNode("Logistics Department",true);
	
	private static final String MAIL_SERVER_ADDR="smtp.qq.com";
	
	private static final String SERDER_EMAIL="1544966004@qq.com";
	
	private static final String SENDER_PWD="hjzcoijvbefyibbg";
	
	private JTree tree;
	private JTextField textField;
	
	protected Employee selectEmp=null;
	private JTextField name;
	private JTextField workNumber;
	private JTextField email;
	private JTextField idCard;
	private JTextField inTime;
	private JTextField position;
	private JTextField nativePlace;
	private JTextField phoneNumber;
	private JTextField degree;
	
	JComboBox<String> empComboBox;
	JRadioButton maleRadio;
	JRadioButton femaleRadio;
	
	JCalendar scheduelPanel;

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		scheduelPanel = new JCalendar();
		scheduelPanel.setScheduleDao(scheduleDao);
		scheduelPanel.setParent(this);
		tabbedPane.addTab("Job Schedule", null, scheduelPanel, null);
		
		JPanel empPanel = new JPanel();
		tabbedPane.addTab("Staff Management", null, empPanel, null);
		empPanel.setLayout(null);
		
		JLabel label1 = new JLabel("Department");
		label1.setBounds(23, 34, 84, 16);
		empPanel.add(label1);
		
		String[] items=new String[]{
				"Finance Department", "Employment Department",
				"Administration Department", "Production Department", "Logistics Department" 
		};
		empComboBox = new JComboBox<>(items);
		
		empComboBox.setBounds(96, 30, 130, 27);
		empPanel.add(empComboBox);
		
		name = new JTextField();
		name.setBounds(394, 29, 130, 26);
		empPanel.add(name);
		name.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Name");
		lblNewLabel_2.setBounds(305, 34, 61, 16);
		empPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Gander");
		lblNewLabel_3.setBounds(23, 87, 61, 16);
		empPanel.add(lblNewLabel_3);
		
		maleRadio = new JRadioButton("Male");
		maleRadio.setBounds(96, 83, 73, 23);
		empPanel.add(maleRadio);
		
		femaleRadio = new JRadioButton("Female");
		femaleRadio.setBounds(150, 83, 84, 23);
		empPanel.add(femaleRadio);
		
		ButtonGroup buttonGroup=new ButtonGroup();
		buttonGroup.add(maleRadio);
		buttonGroup.add(femaleRadio);
		
		JLabel lblNewLabel_4 = new JLabel("Work number");
		lblNewLabel_4.setBounds(305, 87, 84, 16);
		empPanel.add(lblNewLabel_4);
		
		workNumber = new JTextField();
		workNumber.setBounds(394, 82, 130, 26);
		empPanel.add(workNumber);
		workNumber.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Email");
		lblNewLabel_5.setBounds(23, 142, 61, 16);
		empPanel.add(lblNewLabel_5);
		
		email = new JTextField();
		email.setBounds(96, 137, 130, 26);
		empPanel.add(email);
		email.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("ID card");
		lblNewLabel_6.setBounds(305, 142, 97, 16);
		empPanel.add(lblNewLabel_6);
		
		idCard = new JTextField();
		idCard.setBounds(394, 137, 130, 26);
		empPanel.add(idCard);
		idCard.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Entry time");
		lblNewLabel_7.setBounds(23, 202, 84, 16);
		empPanel.add(lblNewLabel_7);
		
		inTime = new JTextField();
		inTime.setBounds(96, 197, 130, 26);
		empPanel.add(inTime);
		inTime.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Position");
		lblNewLabel_8.setBounds(305, 202, 61, 16);
		empPanel.add(lblNewLabel_8);
		
		position = new JTextField();
		position.setBounds(394, 197, 130, 26);
		empPanel.add(position);
		position.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("State");
		lblNewLabel_9.setBounds(23, 261, 61, 16);
		empPanel.add(lblNewLabel_9);
		
		nativePlace = new JTextField();
		nativePlace.setBounds(96, 256, 130, 26);
		empPanel.add(nativePlace);
		nativePlace.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("Phone number");
		lblNewLabel_10.setBounds(305, 261, 97, 16);
		empPanel.add(lblNewLabel_10);
		
		phoneNumber = new JTextField();
		phoneNumber.setBounds(394, 256, 130, 26);
		empPanel.add(phoneNumber);
		phoneNumber.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("Degree");
		lblNewLabel_11.setBounds(23, 320, 61, 16);
		empPanel.add(lblNewLabel_11);
		
		degree = new JTextField();
		degree.setBounds(96, 315, 130, 26);
		empPanel.add(degree);
		degree.setColumns(10);
		
		JButton saveBtn = new JButton("Save");
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectEmp==null) {
					JOptionPane.showMessageDialog(MainFrame.this, "You should choose a staff!");
					return;
				}

				Employee employee=new Employee();
				employee.setId(selectEmp.getId());
				employee.setDepartmentId(empComboBox.getSelectedItem().toString());
				employee.setGender(femaleRadio.isSelected()?"Female":"Male");
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
				
				employeeDao.updateEmployee(employee);
				refreshTree();
				JOptionPane.showMessageDialog(MainFrame.this, "Successfully saved!");
			}
		});
		saveBtn.setBounds(407, 461, 117, 29);
		empPanel.add(saveBtn);
		
		JPanel workPanel = new JPanel();
		tabbedPane.addTab("Job Assignments", null, workPanel, null);
		workPanel.setLayout(null);
		
		JLabel label = new JLabel("Content of job：");
		label.setBounds(20, 18, 104, 16);
		workPanel.add(label);
		
		JTextArea jobText = new JTextArea();
		jobText.setColumns(15);
		jobText.setRows(10);
		jobText.setBounds(122, 18, 287, 317);
		workPanel.add(jobText);
		
		JButton sendMailBtn = new JButton("Send Email");
		sendMailBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectEmp==null) {
					JOptionPane.showMessageDialog(MainFrame.this, "Please choose a staff!");
					return;
				}
				int option=JOptionPane.showConfirmDialog(MainFrame.this, "Confirm assign the job to "+selectEmp.getName()+"?");
				if (option==JOptionPane.OK_OPTION) {
					MailUtils mailUtils=new MailUtils();
					mailUtils.setAddress(SERDER_EMAIL, selectEmp.getEmail(), "Job Assignments");
					mailUtils.send(MAIL_SERVER_ADDR, SERDER_EMAIL, SENDER_PWD, jobText.getText());
					JOptionPane.showMessageDialog(MainFrame.this, "Send the message success!");
				}
			}
		});
		sendMailBtn.setBounds(403, 432, 117, 29);
		workPanel.add(sendMailBtn);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(143, 188, 143));
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Staff Management System");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		initTree();
		panel_1.add(tree, BorderLayout.CENTER);
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.NORTH);
		
		textField = new JTextField();
		panel_2.add(textField);
		textField.setColumns(10);
		
		JButton searchBtn = new JButton("Search");
		searchBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (textField.getText().equals("")) {
					if (root.getDepth()!=2) {
						root.removeAllChildren();
						root.add(finicial);
						root.add(people);
						root.add(invotery);
						root.add(politic);
						root.add(back);
						refreshTree();
					}
				}else{
					Employee employee=employeeDao.getByName(textField.getText());
					if (employee==null) {
						JOptionPane.showMessageDialog(MainFrame.this, "The staff doesn't exists!");
					}else {
						root.removeAllChildren();
						root.add(new DefaultMutableTreeNode(employee,false));
						tree.updateUI();
					}
				}
			}
		});
		panel_2.add(searchBtn);
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_1.add(panel_3, BorderLayout.SOUTH);
		
		JButton addBtn = new JButton("Add");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddDialog dialog=new AddDialog(MainFrame.this);
				dialog.setModal(true);
				dialog.setVisible(true);
			}
		});
		panel_3.add(addBtn);
		
		JButton delBtn = new JButton("Delete");
		delBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TreePath selectionPath=tree.getSelectionPath();
				if (selectionPath==null) {
					JOptionPane.showMessageDialog(MainFrame.this, "Please select the staff to be deleted!");
					return;
				}
				DefaultMutableTreeNode treeNode=(DefaultMutableTreeNode)selectionPath.getLastPathComponent();
				if (treeNode.getUserObject() instanceof String) {
					return;
				}else{
					int option=JOptionPane.showConfirmDialog(MainFrame.this, "Confirm to delete?");
					if (option==JOptionPane.OK_OPTION) {
						Employee employee=(Employee)treeNode.getUserObject();
						employeeDao.deleteEmp(employee.getId());
						refreshTree();
					}
				}
			}
		});
		panel_3.add(delBtn);
		
	}
	
	private void initTree(){
		
		root.add(finicial);
		root.add(people);
		root.add(invotery);
		root.add(politic);
		root.add(back);
		
		Map<String, List<Employee>> empMap=employeeDao.getAllEmployees();
		for (Employee employee : empMap.get(Employee.DEP_FINACIAL)) {
			finicial.add(new DefaultMutableTreeNode(employee,false));
		}
		for (Employee employee : empMap.get(Employee.DEP_BACK)) {
			back.add(new DefaultMutableTreeNode(employee,false));
		}
		for (Employee employee : empMap.get(Employee.DEP_INVENTORY)) {
			invotery.add(new DefaultMutableTreeNode(employee,false));
		}
		for (Employee employee : empMap.get(Employee.DEP_PEOPLE)) {
			people.add(new DefaultMutableTreeNode(employee,false));
		}
		for (Employee employee : empMap.get(Employee.DEP_POLICE)) {
			politic.add(new DefaultMutableTreeNode(employee,false));
		}
		
		DefaultTreeModel treeModel=new DefaultTreeModel(root,true);
		
		DefaultTreeCellRenderer renderer=new DefaultTreeCellRenderer();
		renderer.setLeafIcon(new ImageIcon("images/people.png"));
		renderer.setOpenIcon(new ImageIcon("images/dep.png"));
		renderer.setClosedIcon(new ImageIcon("images/dep.png"));
		tree = new JTree(treeModel);
		tree.setCellRenderer(renderer);
		
		tree.setShowsRootHandles(true);
		tree.addMouseListener(new MouseAdapter() {
			 public void mouseClicked(MouseEvent e){
				 if(e.getClickCount()==1){ // 表示鼠标单击
					 TreePath selectionPath=tree.getSelectionPath();
					 if (selectionPath==null) {
						return;
					 }
					 DefaultMutableTreeNode treeNode=(DefaultMutableTreeNode)selectionPath.getLastPathComponent();
					 if (treeNode.getUserObject() instanceof String) {
							return;
					 }else{
						 selectEmp=(Employee)treeNode.getUserObject();
						 empComboBox.setSelectedItem(selectEmp.getDepartmentId());
						 name.setText(selectEmp.getName());
						 if (selectEmp.getGender().equals("Male")) {
							 maleRadio.setSelected(true);
						 }else {
							femaleRadio.setSelected(true);
						}
						 workNumber.setText(selectEmp.getWorkNumber());
						 email.setText(selectEmp.getEmail());
						 idCard.setText(selectEmp.getIdCard());
						 inTime.setText(new SimpleDateFormat("yyyy-MM-dd").format(selectEmp.getGetInTime()));
						 position.setText(selectEmp.getPosition());
						 nativePlace.setText(selectEmp.getNativePlace());
						 phoneNumber.setText(selectEmp.getPhoneNumber());
						 degree.setText(selectEmp.getDegree());
					 }
					 
					 scheduelPanel.setEmp(selectEmp.getId());
				 }
			 }
		});
		expandTree(tree, new TreePath(root));
	}
	
	protected void addEmp(Employee newEmp){
		employeeDao.addEmployee(newEmp);
		refreshTree();
		//expandTree(tree, new TreePath(root));
	}
	
	protected void addJob(String event,Date date){
		JobSchedule schedule=new JobSchedule();
		schedule.setEmployeeId(selectEmp.getId());
		schedule.setEvent(event);
		schedule.setDate(date);
		scheduleDao.addJob(schedule);
	}
	
	private void refreshTree(){
		finicial.removeAllChildren();
		back.removeAllChildren();
		invotery.removeAllChildren();
		people.removeAllChildren();
		politic.removeAllChildren();
		Map<String, List<Employee>> empMap=employeeDao.getAllEmployees();
		for (Employee employee : empMap.get(Employee.DEP_FINACIAL)) {
			finicial.add(new DefaultMutableTreeNode(employee,false));
		}
		for (Employee employee : empMap.get(Employee.DEP_BACK)) {
			back.add(new DefaultMutableTreeNode(employee,false));
		}
		for (Employee employee : empMap.get(Employee.DEP_INVENTORY)) {
			invotery.add(new DefaultMutableTreeNode(employee,false));
		}
		for (Employee employee : empMap.get(Employee.DEP_PEOPLE)) {
			people.add(new DefaultMutableTreeNode(employee,false));
		}
		for (Employee employee : empMap.get(Employee.DEP_POLICE)) {
			politic.add(new DefaultMutableTreeNode(employee,false));
		}
		tree.updateUI();
	}
	
	private void expandTree(JTree tree, TreePath parent) {
	    TreeNode node = (TreeNode) parent.getLastPathComponent();
	    if (node.getChildCount() >= 0) {
	       for (Enumeration<?> e = node.children(); e.hasMoreElements();) {
	           TreeNode n = (TreeNode) e.nextElement();
	           TreePath path = parent.pathByAddingChild(n);
	           expandTree(tree, path);
	       }
	    }
	    tree.expandPath(parent);
	}
}
