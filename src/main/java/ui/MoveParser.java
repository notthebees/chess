package ui;

import static chess.pieces.Colour.BLACK;
import static chess.pieces.Colour.WHITE;
import static java.lang.Character.getNumericValue;
import chess.pieces.Position;
import chess.pieces.move.KingSideCastle;
import chess.pieces.move.Move;
import chess.pieces.move.QueenSideCastle;
import chess.pieces.move.SimpleMove;

public class MoveParser {

	public boolean isValid(final String input) {
		if (input.contains("-")) {
			return true;
		}
		return false;
	}

	public Move parse(final String input) {
		final String[] substrings = input.split("-");
		final String from = substrings[0];
		final String to = substrings[1];
		if (to.equals("0")) {
			return parseKingsideCastle(from);
		}
		if (to.equals("00")) {
			return parseQueenSideCastle(from);
		}
		return new SimpleMove(position(from), position(to));
	}

	private Move parseQueenSideCastle(final String from) {
		if (from.equals("e1")) {
			return new QueenSideCastle(WHITE);
		} else if (from.equals("e8")) {
			return new QueenSideCastle(BLACK);
		} else {
			return null;
		}
	}

	private Move parseKingsideCastle(final String from) {
		if (from.equals("e1")) {
			return new KingSideCastle(WHITE);
		} else if (from.equals("e8")) {
			return new KingSideCastle(BLACK);
		} else {
			return null;
		}
	}

	private Position position(final String string) {
		final int column = string.charAt(0) - 'a' + 1;
		final int row = getNumericValue(string.charAt(1));
		return new Position(column, row);
	}

}
