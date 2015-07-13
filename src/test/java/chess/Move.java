package chess;

import chess.pieces.Position;

public class Move {

	public final Position from;
	public final Position to;

	public Move(final Position from, final Position to) {
		this.from = from;
		this.to = to;
	}

}
