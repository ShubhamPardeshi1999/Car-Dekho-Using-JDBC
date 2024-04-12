package com.jspiders.cardekhousingjdbc.operations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.jspiders.cardekhousingjdbc.App;

public class Operations {

	private static Connection connection;
	private static PreparedStatement preparedStatement;
	private static String query;
	private static ResultSet resultSet;

	public static void insertCar() {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Car ID");
		int id = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter Car Name");
		String name = scanner.nextLine();

		System.out.println("Enter Car Colour");
		String colour = scanner.nextLine();

		String fuleType = "";
		boolean flag = true;

		System.out.println("Enter Car FuleType \n1. Petrol \n2. Diesel");
		int num = scanner.nextInt();

		while (flag) {

			switch (num) {
			case 1:
				fuleType = "Petrol";
				flag = false;
				break;

			case 2:
				fuleType = "Diesel";
				flag = false;
				break;

			default:
				System.out.println("Invalid Number \n Enter Car FuleType \\n1. Petrol \\n2. Diesel");
				break;
			}
		}
		System.out.println("Enter Car Price");
		Double price = scanner.nextDouble();

		try {
			openConnection();
			query = "INSERT INTO car_dekho VALUES(?, ?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, name);
			preparedStatement.setString(3, colour);
			preparedStatement.setString(4, fuleType);
			preparedStatement.setDouble(5, price);
			int res = preparedStatement.executeUpdate();

			System.out.println(res + "row updated");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void viewAllCar() {

		try {
			openConnection();
			query = "SELECT * FROM car_dekho";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				System.out.println(resultSet.getInt(1));
				System.out.println(resultSet.getString(2));
				System.out.println(resultSet.getString(3));
				System.out.println(resultSet.getString(4));
				System.out.println(resultSet.getDouble(5));
			}
			resultSet.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void searchCar() {

		Scanner scanner = new Scanner(System.in);
		boolean flag = true;
		while (flag) {
			System.out.println(" 1. Search by ID \n 2. Search by Name \n 3. Search by FuleType \n 4. Go Back");
			int num = scanner.nextInt();
			switch (num) {
			case 1:
				SearchCarAllOperation.searchById();
				break;

			case 2:
				SearchCarAllOperation.searchByName();
				break;

			case 3:
				SearchCarAllOperation.searchByFuleType();
				break;

			case 4:
				App.main(null);
				break;

			default:
				System.out.println("invalid number");
				break;
			}
		}
	}

	public static void editCar() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println(" Select which u want to change \n 1. Name \n 2. Colour \n 3. FuleType \n 4. Price");
		int num = scanner.nextInt();
		
		try {
			openConnection();
			
			boolean flag = true;
			while (flag) {
				switch (num) {
				case 1:
					System.out.println("Enter Car id");
					int id = scanner.nextInt();
					scanner.nextLine();
					System.out.println("Enter Car Name");
					String name = scanner.nextLine();
					query = "UPDATE car_dekho SET name = ? WHERE id = ?";
					preparedStatement = connection.prepareStatement(query);
					preparedStatement.setInt(2, id);
					preparedStatement.setString(1, name);
					preparedStatement.executeUpdate();
					System.out.println("Name updated");
					flag = false;
					break;

				case 2:
					
					System.out.println("Enter Car id");
					id = scanner.nextInt();
					scanner.nextLine();
					System.out.println("Enter Car New Colour");
					String colour = scanner.nextLine();
					query = "UPDATE car_dekho SET colour = ? WHERE id = ?";
					preparedStatement = connection.prepareStatement(query);
					preparedStatement.setInt(2, id);
					preparedStatement.setString(1, colour);
					preparedStatement.executeUpdate();
					System.out.println("Colour updated");
					flag = false;
					break;
					
				case 3:
					
					System.out.println("Enter Car id");
					id = scanner.nextInt();
					scanner.nextLine();
					String fuleType = "";
					System.out.println("Enter Car FuleType \n1. Petrol \n2. Diesel");
					int option = scanner.nextInt();
					
					switch (option) {
					case 1:
						fuleType = "Petrol";
						flag = false;
						break;

					case 2:
						fuleType = "Diesel";
						flag = false;
						break;

					default:
						System.out.println("Invalid Number \n Enter Car FuleType \\n1. Petrol \\n2. Diesel");
						break;
					}
					query = "UPDATE car_dekho SET fuletype = ? WHERE id = ?";
					preparedStatement = connection.prepareStatement(query);
					preparedStatement.setInt(2, id);
					preparedStatement.setString(1, fuleType);
					preparedStatement.executeUpdate();
					System.out.println("FuleType updated");
					flag = false;
					break;
					
				case 4:
					
					System.out.println("Enter Car id");
					id = scanner.nextInt();
					scanner.nextLine();
					System.out.println("Enter Car Price");
					double price = scanner.nextDouble();
					query = "UPDATE car_dekho SET price = ? WHERE id = ?";
					preparedStatement = connection.prepareStatement(query);
					preparedStatement.setInt(2, id);
					preparedStatement.setDouble(1, price);
					preparedStatement.executeUpdate();
					System.out.println("Price updated");
					flag = false;
					break;
					
				default:
					System.out.println("invalid number");
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void removeCar() {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Car id");
		int id = scanner.nextInt();
		try {
			openConnection();
			query ="DELETE FROM car_dekho WHERE id = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			int res = preparedStatement.executeUpdate();
			System.out.println(res + "row deleted");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private static void openConnection() throws SQLException {
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/weja4?user=root&&password=root");
	}

	private static void closeConnection() throws SQLException {
		if (preparedStatement != null) {
			preparedStatement.close();
		}
		if (connection != null) {
			connection.close();
		}
	}
}
