package entity;

import java.util.Date;

public class Employee {
	
	
	public static final String DEP_FINACIAL="Finance Department";
	
	public static final String DEP_PEOPLE="Employment Department";
	
	public static final String DEP_INVENTORY="Production Department";
	
	public static final String DEP_BACK="Logistics Department";
	
	public static final String DEP_POLICE="Administration Department";
	
	private int id;
	private String departmentName;
	private String name;
	private String gender;
	private String workNumber;
	private String email;
	private String idCard;
	private Date getInTime;
	private String position;
	private String nativePlace;
	private String phoneNumber;
	private String degree;
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getName();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDepartmentId() {
		return departmentName;
	}
	public void setDepartmentId(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getWorkNumber() {
		return workNumber;
	}
	public void setWorkNumber(String workNumber) {
		this.workNumber = workNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public Date getGetInTime() {
		return getInTime;
	}
	public void setGetInTime(Date getInTime) {
		this.getInTime = getInTime;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getNativePlace() {
		return nativePlace;
	}
	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}

}
