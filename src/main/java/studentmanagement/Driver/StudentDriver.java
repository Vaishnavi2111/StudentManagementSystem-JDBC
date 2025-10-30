package studentmanagement.Driver;

import java.util.List;
import java.util.Scanner;

import studentmanagement.Entity.Student;
import studentmanagement.Service.StudentService;

public class StudentDriver {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		StudentService service = new StudentService();
		
		System.out.println("! ---- Welcome to Student Management System ---- !");
		
		boolean flag = true;
		
		while(flag) {
			
			System.out.println("You can perform following operations : ");
			System.out.println("Enter 1 to Save the Student data.");
			System.out.println("Enter 2 to Update the Student data.");
			System.out.println("Enter 3 to Fetch the Student data.");
			System.out.println("Enter 4 to Delete the Student data.");
			System.out.println("Enter 5 to Close the Application.");
			
			System.out.println("Enter the your choice : ");
			int choice = sc.nextInt();
			
			switch (choice) {
			case 1:
				//Save
				int save = service.save();
				if (save != 0) {
					System.out.println("Data Is Saved!");
				}
				break;
				
			case 2:
				//Update
				int update = service.update();
				if (update != 0) {
					System.out.println("Data Is Updated!");
				}
				break;
				
			case 3:
				//Fetch
				List<Student> list = service.fetchAll();
				if(list != null) {
					for (Student student : list) {
						System.out.println(student);
					}
				}
				break;
				
			case 4:
				//Delete
				int delete = service.delete();
				if (delete != 0) {
					System.out.println("Data Is Deleted!");
				}
				break;
				
			case 5:
				//Close
				service.exit();
				if(flag) {
					System.out.println("!!! Application Is Close !!!");
					flag = false;
				}
				break;

			default:
				System.out.println("Invalid Input Please Try Again!!");
				break;
			}
			
		}
	}
	
}
