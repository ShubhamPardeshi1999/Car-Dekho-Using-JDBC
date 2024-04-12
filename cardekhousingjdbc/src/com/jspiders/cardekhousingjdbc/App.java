package com.jspiders.cardekhousingjdbc;

import java.util.Scanner;

import com.jspiders.cardekhousingjdbc.operations.Operations;

public class App {

	public static void main(String[] args) {

		
//		srif number choose karo agar alaphabet or other aaisa 
//		kuch choose kiya to error aayenga isliye try catch main() me 
//		create kiya hai or mainMenu() create kiya hai
		
		
		System.out.println("* * * WELCOME TO CAR DEKHO APPLICATION * * * ");
		System.out.println("============================================\n");
		System.out.println("CHOOSE ANY NUMBER");

		try {
			
			mainMenu();
			
		} catch (Exception e) {
			System.out.println("\n----------------------\nInvalid Number! \nU Choose Wrong Option \nTry Again");
			mainMenu();
			
		}
	}

	private static void mainMenu() {
		boolean flag = true;
		
		Scanner scanner = new Scanner(System.in);
		
		while (flag) {
			System.out.println(" \n1. Add Car \n2. View All Car \n3. Search Car \n4. Edit Car \n5. Remove Car \n6. Exit");
			int option = scanner.nextInt();	
			switch (option) {
				case 1:
					Operations.insertCar();
					break;

				case 2:
					Operations.viewAllCar();
					break;

				case 3:
					Operations.searchCar();
					break;

				case 4:
					Operations.editCar();
					break;

				case 5:
					Operations.removeCar();
					break;
					
				case 6:
					flag = false;
					System.out.println("Thank you \nExited");
					break;
				
				default:
					System.out.println("\n----------------------\nInvalid Number! \nU Choose Wrong Option \nTry Again");
					break;
			
			}
		}
		
	}
}
