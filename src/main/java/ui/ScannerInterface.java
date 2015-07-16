package ui;

import java.util.Scanner;

import chess.Board;
import chess.Game;

public class ScannerInterface implements UserInterface {

	private final Scanner scanner;
	private final Output out;
	private final Game game;
	private final MoveParser parser;
	private boolean gameOver;

	public ScannerInterface(final Game game, final Output out) {
		scanner = new Scanner(System.in);
		this.out = out;
		this.game = game;
		parser = new MoveParser();
		gameOver = false;
	}

	@Override
	public void getCommand() {
		final Board currentBoard = game.currentBoard();
		print(currentBoard.print());
		print("Enter move:");
		final String input = scanner.next();
		if (input.equals("quit")) {
			gameOver = true;
		}
		if (parser.isValid(input)) {
			game.play(parser.parse(input));
		} else {
			print("Invalid command.");
		}
	}

	@Override
	public boolean gameOver() {
		return gameOver;
	}

	private void print(final String message) {
		out.println(message);
	}

}
