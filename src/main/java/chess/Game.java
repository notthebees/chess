package chess;

import chess.pieces.move.Move;


public interface Game {

	Board currentBoard();

	void play(Move move);

}
