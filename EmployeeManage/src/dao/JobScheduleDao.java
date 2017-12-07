package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.JobSchedule;
import util.DBUtil;

public class JobScheduleDao {
	public List<JobSchedule> getJobs(int empId){
		List<JobSchedule> jobs=new ArrayList<>();
		Connection connection=DBUtil.getConnection();
		PreparedStatement statement=null;
		try {
			statement=connection.prepareStatement("select * from schedule where empId=?");
			statement.setInt(1, empId);
			ResultSet rs=statement.executeQuery();
			while(rs.next()){
				JobSchedule jobSchedule=new JobSchedule();
				jobSchedule.setId(rs.getInt(1));
				jobSchedule.setEmployeeId(rs.getInt(2));
				jobSchedule.setEvent(rs.getString(3));
				jobSchedule.setDate(rs.getDate(4));
				jobs.add(jobSchedule);
			}
			statement.close();
			return jobs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public void addJob(JobSchedule jobSchedule){
		Connection connection=DBUtil.getConnection();
		PreparedStatement statement=null;
		try {
			statement=connection.prepareStatement("insert into schedule(empId,event,date) values(?,?,?)");
			statement.setInt(1, jobSchedule.getEmployeeId());
			statement.setString(2, jobSchedule.getEvent());
			statement.setDate(3, new Date(jobSchedule.getDate().getTime()));
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public JobSchedule findJob(int empId,java.util.Date date){
		Connection connection=DBUtil.getConnection();
		PreparedStatement statement=null;
		try {
			statement=connection.prepareStatement("select * from schedule where empId=? and date=?");
			statement.setInt(1, empId);
			statement.setDate(2, new Date(date.getTime()));
			ResultSet rs=statement.executeQuery();
			while(rs.next()){
				JobSchedule jobSchedule=new JobSchedule();
				jobSchedule.setId(rs.getInt(1));
				jobSchedule.setEmployeeId(rs.getInt(2));
				jobSchedule.setEvent(rs.getString(3));
				jobSchedule.setDate(rs.getDate(4));
				statement.close();
				return jobSchedule;
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
