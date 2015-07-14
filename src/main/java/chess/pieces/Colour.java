package chess.pieces;

public enum Colour {
	WHITE (1, 1),
	BLACK (8, -1),
	NULL_COLOUR (0, 0);

	private final int backRow;
	private final int forwardStep;

	Colour(final int backRow, final int forwardStep) {
		this.backRow = backRow;
		this.forwardStep = forwardStep;
	}

	public Colour opposite() {
		if (this == WHITE) {
			return BLACK;
		} else {
			return WHITE;
		}
	}

	public int backRow() {
		return backRow;
	}

	public int frontRow() {
		return backRow + forwardStep;
	}

	public int forwardStep() {
		return forwardStep;
	}

	public Position kingPosition() {
		final int kingColumn = backRow + 4*forwardStep;
		return new Position(kingColumn, backRow);
	}

	public Position queenPosition() {
		final int queenColumn = backRow + 3*forwardStep;
		return new Position(queenColumn, backRow);
	}

	public Position kingSideRookPosition() {
		final int rookColumn = backRow + 7*forwardStep;
		return new Position(rookColumn, backRow);
	}

	public Position queenSideRookPosition() {
		final int rookColumn = backRow;
		return new Position(rookColumn, backRow);
	}

}
