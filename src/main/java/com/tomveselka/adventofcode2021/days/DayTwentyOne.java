package com.tomveselka.adventofcode2021.days;

import java.util.ArrayList;
import java.util.List;

import com.tomveselka.adventofcode2021.utils.FileReaderCustom;

public class DayTwentyOne {

	public static void main(String[] args) {
		DayTwentyOne dayTwentyOne = new DayTwentyOne();
		dayTwentyOne.taskOne();
		// dayTwentyOne.taskTwo();

	}

	private int player1Pos = 10;
	private int player2Pos = 2;

	public void taskOne() {
		int p1Score = 0;
		int p2Score = 0;
		int diceRolls = 0;
		int roll = 1;
		while (p1Score < 1000 && p2Score < 1000) {
			int sumOfRolls = 0;
			int[] rolls = new int[3];
			for (int i = 0; i < 3; i++) {
				rolls[i] = roll;
				sumOfRolls = sumOfRolls + roll;
				roll++;
				if (roll > 100) {
					roll = 1;
				}
			}
			if (player1Pos + sumOfRolls > 10 && (sumOfRolls + player1Pos) % 10 != 0) {
				player1Pos = (sumOfRolls + player1Pos) % 10;
			} else if ((sumOfRolls + player1Pos) % 10 == 0) {
				player1Pos = 10;
			} else {
				player1Pos = sumOfRolls + player1Pos;
			}
			p1Score = p1Score + player1Pos;
			diceRolls = diceRolls + 3;

			System.out.println("Player 1 rolls " + rolls[0] + "+" + rolls[1] + "+" + rolls[2] + " and moves to "
					+ player1Pos + " for a total score of " + p1Score);

			if (p1Score < 1000) {
				sumOfRolls = 0;
				for (int i = 0; i < 3; i++) {
					rolls[i] = roll;
					sumOfRolls = sumOfRolls + roll;
					roll++;
					if (roll > 100) {
						roll = 1;
					}
				}
				if (player2Pos + sumOfRolls > 10 && (sumOfRolls + player2Pos) % 10 != 0) {
					player2Pos = (sumOfRolls + player2Pos) % 10;
				} else if ((sumOfRolls + player2Pos) % 10 == 0) {
					player2Pos = 10;
				} else {
					player2Pos = sumOfRolls + player2Pos;
				}
				p2Score = p2Score + player2Pos;
				diceRolls = diceRolls + 3;

				System.out.println("Player 2 rolls " + rolls[0] + "+" + rolls[1] + "+" + rolls[2] + " and moves to "
						+ player2Pos + " for a total score of " + p2Score);
			}
		}
		int losingScore = 0;
		if (p1Score < 1000) {
			losingScore = p1Score;
			System.out.println("Player 2 won. Losing score is " + losingScore);
		} else {
			losingScore = p2Score;
			System.out.println("Player 1 won. Losing score is " + losingScore);
		}
		System.out.println("Dice was rolled " + diceRolls + " times");
		System.out.println("Final score is " + (losingScore * diceRolls));
	}

	public void taskTwo() {
		long numberOfGames = 0;
		List<Long> points = new ArrayList<Long>();// -21 win for P1, 0 draw +21 win for P2 -> list 43 elements long
		List<Long> p1Pos = new ArrayList<Long>(); // number of games at each of positions
		List<Long> p2Pos = new ArrayList<Long>();
		// initialize arrays
		for (int i = 0; i < 10; i++) {
			p1Pos.add((long) 0);
			p2Pos.add((long) 0);
		}
		for (int i = 0; i < 44; i++) {
			points.add((long) 0);
		}
		// start position
		p1Pos.set((player1Pos) % 10, (long) 1);
		p1Pos.set((player2Pos) % 10, (long) 1);

		// loop
		do {
			// P1 rolls
			for (int i = 0; i < 3; i++) {
				for (int j = 1; j < 4; j++) {					
					for (int k = 0; k < p1Pos.size(); k++) {
						if (p1Pos.get(k) > 0) {
							if(k+j<11) {
								p1Pos.set(k+j+1, p1Pos.get(k+j+1)+1);
							}else {
								p1Pos.set((k+j+1)%10, p1Pos.get((k+j+1)+1)%10);
							}
							//pridat points
						}
					}
				}
			}
		} while (numberOfGames > 0);

	}

}
