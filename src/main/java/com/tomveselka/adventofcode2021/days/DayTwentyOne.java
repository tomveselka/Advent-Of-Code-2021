package com.tomveselka.adventofcode2021.days;

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

}
