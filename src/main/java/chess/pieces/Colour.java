package chess.pieces;

public enum Colour {
	WHITE (1, 1, 1),
	BLACK (2, 8, -1);

	private final int backRow;
	private final int forwardStep;
	private final int playerNumber;

	Colour(final int playerNumber, final int backRow, final int forwardStep) {
		this.playerNumber = playerNumber;
		this.backRow = backRow;
		this.forwardStep = forwardStep;
	}

	public int playerNumber() {
		return playerNumber;
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

	public int endRow() {
		return backRow + 7 * forwardStep;
	}

	public int forwardStep() {
		return forwardStep;
	}

	public Position kingPosition() {
		return new Position(5, backRow);
	}

	public Position queenPosition() {
		return new Position(4, backRow);
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
