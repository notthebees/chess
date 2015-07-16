package ui;

import static java.lang.Character.getNumericValue;
import chess.pieces.Position;
import chess.pieces.move.Move;
import chess.pieces.move.SimpleMove;

public class MoveParser {

	public boolean isValid(final String input) {
		if (input.contains("-")) {
			return true;
		}
		return false;
	}

	public Move parse(final String input) {
		if (input.contains("-")) {
			final String[] positions = input.split("-");
			final String from = positions[0];
			final String to = positions[1];
			return new SimpleMove(position(from), position(to));
		}
		return null;
	}

	private Position position(final String string) {
		final int column = string.charAt(0) - 'a' + 1;
		final int row = getNumericValue(string.charAt(1));
		return new Position(column, row);
	}

}
