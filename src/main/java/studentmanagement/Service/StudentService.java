package studentmanagement.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import studentmanagement.Entity.Student;

public class StudentService {
	private static Scanner sc = new Scanner(System.in);
	private static String url = "jdbc:postgresql://localhost:5432/sch";
	private static String user = "postgres";
	private static String pswd = "123";
	private static Connection connection;
	
	//Establish Connection to Database
	static {
		try {
			Class.forName("org.postgresql.Driver");
			
			connection = DriverManager.getConnection(url, user, pswd);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Saving Data in Database
	public int save() {
		
		int res = 0;
		System.out.println("Enter the Student Id : ");
		int id = sc.nextInt();
		System.out.println("Enter the Student Name : ");
		String name = sc.next();
		System.out.println("Enter the Student Age : ");
		int age = sc.nextInt();
		
		String sql = "INSERT into student values(?, ? , ?)";
		
		try {
			
			PreparedStatement pstm = connection.prepareStatement(sql);
			
			pstm.setInt(1, id);
			pstm.setString(2, name);
			pstm.setInt(3, age);
			
			res = pstm.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}
	
	//Updating Data in Database
	public int update() {
		int res = 0;
		System.out.println("Enter the ID of Student whose Age you want to update : ");
		int id = sc.nextInt();
		System.out.println("Enter the Updated Age : ");
		int age = sc.nextInt();
		
		String sql = "UPDATE student set age = ? where id = ?";
		
		try {
			
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setInt(1, age);
			pstm.setInt(2, id);
			
			res = pstm.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}
	
	//Fetching data from Database
	public List<Student> fetchAll() {
		List<Student> list = new ArrayList<Student>();
		String sql = "SELECT * from student";
		
		try {
			Statement stm = connection.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				int age = rs.getInt(3);
				
				Student student = new Student(id, name, age);
				list.add(student);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	//Deleting Record from Table
	public int delete() {
		int res = 0;
		String sql = "DELETE from student where id = ?";
		System.out.println("Enter the Id of Record to be Deleted : ");
		int id = sc.nextInt();
		
		try {
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setInt(1, id);
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}
	
	public boolean exit() {
		boolean flag = false;
		try {
			connection.close();
			flag = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
}
