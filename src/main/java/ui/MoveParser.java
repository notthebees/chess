package ui;

import static chess.pieces.Colour.BLACK;
import static chess.pieces.Colour.WHITE;
import static java.lang.Character.getNumericValue;
import static java.util.regex.Pattern.matches;
import chess.pieces.Bishop;
import chess.pieces.Colour;
import chess.pieces.Knight;
import chess.pieces.Piece;
import chess.pieces.Position;
import chess.pieces.Queen;
import chess.pieces.Rook;
import chess.pieces.move.KingSideCastle;
import chess.pieces.move.Move;
import chess.pieces.move.QueenSideCastle;
import chess.pieces.move.ReplacePawn;
import chess.pieces.move.SimpleMove;

public class MoveParser {

	private final String simpleMovePattern = "[a-h][1-8]-[a-h][1-8]";
	private final String kingsideCastlePattern = "[1|8]-0";
	private final String queensideCastlePattern = "[1|8]-00";
	private final String pawnReplacementPattern = "[a-h][1|8]-[Q|r|b|k]";

	public boolean isValid(final String input) {
		if (matches(simpleMovePattern, input)) {
			return true;
		}
		if (matches(kingsideCastlePattern, input)) {
			return true;
		}
		if (matches(queensideCastlePattern, input)) {
			return true;
		}
		if (matches(pawnReplacementPattern, input)) {
			return true;
		}
		return false;
	}

	public Move parse(final String input) {
		if (matches(kingsideCastlePattern, input)) {
			return new KingSideCastle(castleColour(input));
		} else if (matches(queensideCastlePattern, input)) {
			return new QueenSideCastle(castleColour(input));
		} else if (matches(pawnReplacementPattern, input)) {
			return replacePawn(input);
		} else {
			return simpleMove(input);
		}
	}

	private Move replacePawn(final String input) {
		final String[] substrings = input.split("-");
		final Position position = position(substrings[0]);
		return new ReplacePawn(position, replacementPiece(substrings[1], position, replacementColour(position)));
	}

	private Piece replacementPiece(final String type, final Position position, final Colour colour) {
		if (type.equals("Q")) {
			return new Queen(colour, position);
		}
		if (type.equals("r")) {
			return new Rook(colour, position);
		}
		if (type.equals("b")) {
			return new Bishop(colour, position);
		}
		if (type.equals("k")) {
			return new Knight(colour, position);
		}
		return null;
	}

	private Colour replacementColour(final Position position) {
		if (position.row == 8) {
			return WHITE;
		} else {
			return BLACK;
		}
	}

	private Move simpleMove(final String input) {
		final String[] substrings = input.split("-");
		final String from = substrings[0];
		final String to = substrings[1];
		return new SimpleMove(position(from), position(to));
	}

	private Position position(final String string) {
		final int column = string.charAt(0) - 'a' + 1;
		final int row = getNumericValue(string.charAt(1));
		return new Position(column, row);
	}

	private Colour castleColour(final String input) {
		if (input.charAt(0) == '1') {
			return WHITE;
		} else {
			return BLACK;
		}
	}

}
