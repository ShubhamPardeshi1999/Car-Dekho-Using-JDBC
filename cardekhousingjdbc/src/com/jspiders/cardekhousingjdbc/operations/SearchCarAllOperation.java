package com.jspiders.cardekhousingjdbc.operations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SearchCarAllOperation {

	private static Connection connection;
	private static PreparedStatement preparedStatement;
	private static String query;
	private static ResultSet resultSet;

	static Scanner scanner = new Scanner(System.in);

	public static void searchById() {

		System.out.println("Enter id for Search");
		int id = scanner.nextInt();
		try {
			openConnection();
			query = "SELECT * FROM car_dekho WHERE id = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				System.out.println(resultSet.getInt(1));
				System.out.println(resultSet.getString(2));
				System.out.println(resultSet.getString(3));
				System.out.println(resultSet.getString(4));
				System.out.println(resultSet.getDouble(5));
			}
			resultSet.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public static void searchByName() {

		System.out.println("Enter name for search");
		String name = scanner.nextLine();

		try {
			openConnection();
			query = "SELECT * FROM car_dekho WHERE name = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, name);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				System.out.println(resultSet.getInt(1));
				System.out.println(resultSet.getString(2));
				System.out.println(resultSet.getString(3));
				System.out.println(resultSet.getString(4));
				System.out.println(resultSet.getDouble(5));
			}

			resultSet.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public static void searchByFuleType() {

		String fuleType = "";
		
		boolean flag = true;
		
		while (flag) {
			System.out.println(" Select FuleType for Search \n 1. Peterol \n 2. Diesel \n 3. Exit");
			int num = scanner.nextInt();

			switch (num) {
			case 1:
				fuleType = "Petrol";
				flag = false;
				break;

			case 2:
				fuleType = "Diesel";
				flag = false;
				break;
			case 3:
				flag = false;
				System.out.println("Exited");
				break;

			default:
				System.out.println("Invalid Number");
				break;
			}
		}

		try {
			openConnection();
			query = "SELECT * FROM car_dekho WHERE fuletype = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, fuleType);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				System.out.println(resultSet.getInt(1));
				System.out.println(resultSet.getString(2));
				System.out.println(resultSet.getString(3));
				System.out.println(resultSet.getString(4));
				System.out.println(resultSet.getDouble(5));
			}

			resultSet.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
