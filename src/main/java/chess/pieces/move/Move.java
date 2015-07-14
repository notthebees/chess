package chess.pieces.move;

import java.util.Set;

import chess.Board;
import chess.pieces.Piece;

public interface Move {

	Set<Piece> updatePieces(Set<Piece> pieces, Board board);

	boolean isIllegal(Board board);

}