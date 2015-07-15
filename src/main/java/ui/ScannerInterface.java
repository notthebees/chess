package ui;

import java.util.Scanner;

import chess.Board;
import chess.Game;

public class ScannerInterface implements UserInterface {

	private final Scanner scanner;
	private final Output out;
	private final Game game;

	public ScannerInterface(final Game game, final Output out) {
		scanner = new Scanner(System.in);
		this.out = out;
		this.game = game;
	}

	@Override
	public void getCommand() {
		print("Enter command:");
		final String input = scanner.next();
		if (input.equals("show")) {
			final Board currentBoard = game.currentBoard();
			print(currentBoard.print());
		}
	}

	private void print(final String message) {
		out.println(message);
	}

}
