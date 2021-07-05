/*
	Code: Kaan Sinar
	Date: 15-06-2020
	Name: tictactoe.java
	Info: A simple tic-tac-toe game written in Java
*/


import java.util.Scanner;
import java.util.Random;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class tictactoe{

	
	static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
	static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();
	
	public static void main(String[] args){
	
		char[][] board = {{' ', '|', ' ', '|', ' '},
				 {'-', '+', '-', '+', '-'},
				 {' ', '|', ' ', '|', ' '},
				 {'-', '+', '-', '+', '-'},
				 {' ', '|', ' ', '|', ' '}};


		while(true){
			String result = checkWinner();

			boolean resultExists = (result.length() > 0);

			if(resultExists) {
				System.out.println(result);
				break;
			}	
			Scanner scan = new Scanner(System.in);

			System.out.println("Enter your placement (1-9):");
			int playerPos = scan.nextInt();

			while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)){
				System.out.println("Position taken, try again!");
				playerPos = scan.nextInt();
			}

			clearScreen();
			
			placePiece(board,playerPos,"player");

			result = checkWinner();
		
			if(resultExists) {
				System.out.println(result);
				printBoard(board);
				break;
			}


			Random rand = new Random();
			
			int cpuPos = rand.nextInt(9) + 1;
		
			while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)){
				cpuPos = rand.nextInt(9) + 1;
			}

			placePiece(board,cpuPos,"cpu");

			if(resultExists){
				System.out.println(result);
				printBoard(board);
				break;
			}

			printBoard(board);

			result = checkWinner();	
			
		}
				
	}

	public static void clearScreen() {  
    	System.out.print("\033[H\033[2J");  
    	System.out.flush();  
	}


	public static void printBoard(char[][] board){
		for(char[] row : board){
			for(char c : row){
				System.out.print(c);
			}
			System.out.println();
		}
	}

	public static void placePiece(char[][] board, int pos, String user){
	
		char symbol = ' ';

		if(user.equals("player")){
			symbol = 'X';
			playerPositions.add(pos);
		} else if(user.equals("cpu")){
			symbol = 'O';
			cpuPositions.add(pos);
		}
	
		board[2*((pos-1)/3)][2*((pos-1)%3)] = symbol;

	}

	public static String checkWinner(){
		
	
		List topRow = Arrays.asList(1,2,3);
		List midRow = Arrays.asList(4,5,6);		
		List botRow = Arrays.asList(7,8,9);

		List leftCol = Arrays.asList(1,4,7);
		List midCol = Arrays.asList(2,5,8);	
		List rightCol = Arrays.asList(3,6,9);
	
		List cross1 = Arrays.asList(1,5,9);
		List cross2 = Arrays.asList(3,5,7);

		List<List> winConditions = new ArrayList<List>();
		

		winConditions.add(topRow);
		winConditions.add(midRow);
		winConditions.add(botRow);
		
		winConditions.add(rightCol);
		winConditions.add(midCol);
		winConditions.add(leftCol);
		
		winConditions.add(cross1);
		winConditions.add(cross2);

		for(List l : winConditions){
			if(playerPositions.containsAll(l)){
				return "Congratulations you won!";
			} else if(cpuPositions.containsAll(l)){
				return "CPU wins! Better luck next time!";
			}
		}
			
		if((playerPositions.size() + cpuPositions.size()) == 9){
			return "It is a draw!";
		}
		
		return "";	
	}
}
