package chess;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import chess.pieces.Colour;
import chess.pieces.King;
import chess.pieces.Pawn;
import chess.pieces.Piece;
import chess.pieces.Position;
import chess.pieces.move.Move;

public class StandardBoard implements Board {

	private final Set<Piece> pieces = new HashSet<>();

	public StandardBoard(final Set<Piece> pieces) {
		this.pieces.addAll(pieces);
	}

	public StandardBoard(final List<Piece> pieces) {
		this.pieces.addAll(pieces);
	}

	public StandardBoard(final Piece...pieces) {
		for (final Piece piece : pieces) {
			this.pieces.add(piece);
		}
	}

	@Override
	public boolean isInCheck(final Colour colour) {
		if (hasNoKing(colour)) {
			return false;
		}
		final Piece relevantKing = kingOfColour(colour);
		if (isAttackedBy(colour.opposite(), relevantKing.position())) {
			return true;
		}
		return false;
	}

	private boolean hasNoKing(final Colour colour) {
		for (final Piece piece : pieces) {
			if (piece.getClass().equals(King.class) & piece.colour().equals(colour)) {
				return false;
			}
		}
		return true;
	}

	private Piece kingOfColour(final Colour colour) {
		for (final Piece piece : pieces) {
			if (piece.getClass().equals(King.class) & piece.colour().equals(colour)) {
				return piece;
			}
		}
		return null;
	}

	@Override
	public boolean isAttackedBy(final Colour colour, final Position position) {
		for (final Piece piece : pieces) {
			if (piece.colour().equals(colour) & positionsAttackedBy(piece).contains(position)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Set<Position> positionsAttackedBy(final Piece piece) {
		final Set<Position> allPositions = allPositions();
		final Set<Position> attackedPositions = new HashSet<>();
		for (final Position position : allPositions) {
			if (! piece.moveIsIllegal(position, boardWithOppositeColouredPieces(piece))) {
				attackedPositions.add(position);
			}
		}
		return attackedPositions;
	}

	private Board boardWithOppositeColouredPieces(final Piece attackingPiece) {
		final Colour oppositeColour = attackingPiece.colour().opposite();
		final Set<Piece> oppositeColouredPieces = new HashSet<>();
		for (final Piece piece : pieces) {
			oppositeColouredPieces.add(new Pawn(oppositeColour, piece.position()));
		}
		return new StandardBoard(oppositeColouredPieces);
	}

	private Set<Position> allPositions() {
		final Set<Position> positions = new HashSet<>();
		for (int column=1; column<=8; column++) {
			for (int row=1; row<=8; row++) {
				positions.add(new Position(column, row));
			}
		}
		return positions;
	}

	@Override
	public Set<Piece> pieces() {
		return new HashSet<>(pieces);
	}

	@Override
	public StandardBoard play(final Move move) {
		if (move.isIllegal(this)) {
			return this;
		}
		return new StandardBoard(move.updatePieces(this));
	}

	@Override
	public boolean isOccupiedBy(final Colour colour, final Position position) {
		if (! isOccupiedAt(position)) {
			return false;
		}
		return pieceAt(position).colour().equals(colour);
	}

	@Override
	public boolean isOccupiedAt(final Position position) {
		for (final Piece piece : pieces) {
			if (piece.position().equals(position)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Piece pieceAt(final Position position) {
		for (final Piece piece : pieces) {
			if (piece.position().equals(position)) {
				return piece;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "Standard Board with " + pieces.toString();
	}

	@Override
	public boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

}
