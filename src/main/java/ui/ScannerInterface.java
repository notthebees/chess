package ui;

import static java.lang.Character.getNumericValue;

import java.util.Scanner;

import chess.Board;
import chess.Game;
import chess.pieces.Position;
import chess.pieces.move.SimpleMove;

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
		if (input.equals("quit")) {
			System.exit(0);
		}
		if (input.equals("show")) {
			final Board currentBoard = game.currentBoard();
			print(currentBoard.print());
		}
		if (input.equals("move")) {
			move();
		}
	}

	private void move() {
		print("Enter move:");
		final String input = scanner.next();
		if (input.contains("-")) {
			final String[] positions = input.split("-");
			final String from = positions[0];
			final String to = positions[1];
			game.play(new SimpleMove(position(from), position(to)));
		}
	}

	private Position position(final String string) {
		final int column = string.charAt(0) - 'a' + 1;
		final int row = getNumericValue(string.charAt(1));
		return new Position(column, row);
	}

	private void print(final String message) {
		out.println(message);
	}

}
