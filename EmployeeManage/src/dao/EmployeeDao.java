package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Employee;
import util.DBUtil;

public class EmployeeDao {
	
	public Employee getById(int id){
		Connection connection=DBUtil.getConnection();
		PreparedStatement statement=null;
		try {
			statement=connection.prepareStatement("select * from employee where id=?");
			statement.setInt(1, id);
			ResultSet rs=statement.executeQuery();
			while(rs.next()){
				Employee employee=extractEmployee(rs);
				statement.close();
				return employee;
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public Employee getByName(String name){
		Connection connection=DBUtil.getConnection();
		PreparedStatement statement=null;
		try {
			statement=connection.prepareStatement("select * from employee where name=?");
			statement.setString(1, name);
			ResultSet rs=statement.executeQuery();
			while(rs.next()){
				Employee employee=extractEmployee(rs);
				statement.close();
				return employee;
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public Map<String, List<Employee>> getAllEmployees(){
		Map<String, List<Employee>> employees=new HashMap<>();
		employees.put(Employee.DEP_BACK, new ArrayList<>());
		employees.put(Employee.DEP_PEOPLE, new ArrayList<>());
		employees.put(Employee.DEP_POLICE, new ArrayList<>());
		employees.put(Employee.DEP_INVENTORY, new ArrayList<>());
		employees.put(Employee.DEP_FINACIAL, new ArrayList<>());
		Connection connection=DBUtil.getConnection();
		PreparedStatement statement=null;
		try {
			statement=connection.prepareStatement("select * from employee");
			ResultSet rs=statement.executeQuery();
			while(rs.next()){
				Employee employee=extractEmployee(rs);
				employees.get(employee.getDepartmentId()).add(employee);
			}
			statement.close();
			return employees;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	private Employee extractEmployee(ResultSet rs) throws SQLException{
		int nid=rs.getInt(1);
		String departmentName=rs.getString(2);
		String name=rs.getString(3);
		String gender=rs.getString(4);
		String workNumber=rs.getString(5);
		String email=rs.getString(6);
		String idCard=rs.getString(7);
		Date getInTime=rs.getDate(8);
		String position=rs.getString(9);
		String nativePlace=rs.getString(10);
		String phoneNumber=rs.getString(11);
		String degree=rs.getString(12);
		
		Employee employee=new Employee();
		employee.setId(nid);
		employee.setDepartmentId(departmentName);
		employee.setGender(gender);
		employee.setEmail(email);
		employee.setGetInTime(getInTime);
		employee.setName(name);
		employee.setNativePlace(nativePlace);
		employee.setIdCard(idCard);
		employee.setPhoneNumber(phoneNumber);
		employee.setPosition(position);
		employee.setWorkNumber(workNumber);
		employee.setDegree(degree);
		
		return employee;
	}
	
	public void addEmployee(Employee employee){
		Connection connection=DBUtil.getConnection();
		PreparedStatement statement=null;
		try {
			statement=connection.prepareStatement("insert into employee(departmentName,name,gender,workNumber,"
					+ "email,idCard,getInTime,position,nativePlace,phoneNumber,degree) values(?,?,?,?,?,?,?,?,?,?,?)");
			statement.setString(1, employee.getDepartmentId());
			statement.setString(2, employee.getName());
			statement.setString(3, employee.getGender());
			statement.setString(4, employee.getWorkNumber());
			statement.setString(5, employee.getEmail());
			statement.setString(6, employee.getIdCard());
			statement.setDate(7, new java.sql.Date(employee.getGetInTime().getTime()));
			statement.setString(8, employee.getPosition());
			statement.setString(9, employee.getNativePlace());
			statement.setString(10, employee.getPhoneNumber());
			statement.setString(11, employee.getDegree());
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateEmployee(Employee employee){
		Connection connection=DBUtil.getConnection();
		PreparedStatement statement=null;
		try {
			statement=connection.prepareStatement("update employee set departmentName=?,name=?,gender=?,workNumber=?,"
					+ "email=?,idCard=?,getInTime=?,position=?,nativePlace=?,phoneNumber=?,degree=? where id=?");
			statement.setString(1, employee.getDepartmentId());
			statement.setString(2, employee.getName());
			statement.setString(3, employee.getGender());
			statement.setString(4, employee.getWorkNumber());
			statement.setString(5, employee.getEmail());
			statement.setString(6, employee.getIdCard());
			statement.setDate(7, new java.sql.Date(employee.getGetInTime().getTime()));
			statement.setString(8, employee.getPosition());
			statement.setString(9, employee.getNativePlace());
			statement.setString(10, employee.getPhoneNumber());
			statement.setString(11, employee.getDegree());
			statement.setInt(12, employee.getId());
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteEmp(int id){
		Connection connection=DBUtil.getConnection();
		PreparedStatement statement=null;
		try {
			statement=connection.prepareStatement("delete from employee where id=?");
			statement.setInt(1, id);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		EmployeeDao employeeDao=new EmployeeDao();
		Employee employee=new Employee();
		employee.setId(1);
		employee.setGetInTime(new Date());
		employee.setName("Jianan Ni");
		employeeDao.updateEmployee(employee);
	}

}
