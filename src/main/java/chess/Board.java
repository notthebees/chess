package chess;

import chess.pieces.Position;

public interface Board {

	Board move(Position from, Position to);

}
