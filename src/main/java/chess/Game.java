package chess;

import chess.pieces.Position;

public interface Game {

	void play(Position from, Position to);

	Board currentBoard();

}
