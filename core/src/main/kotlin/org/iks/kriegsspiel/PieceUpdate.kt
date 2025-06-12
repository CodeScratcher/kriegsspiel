package org.iks.kriegsspiel

import java.time.LocalDateTime

class PieceUpdate(val initial: List<Piece>, val idx: Int, val newPiece: Piece, val localTime: LocalDateTime) : Diff(localTime) {
    override fun apply(state: GameState): GameState {
        return GameState(state.pieces.toMutableList().apply { this[idx] = newPiece })
    }

    override fun rollback(state: GameState): GameState {
        return GameState(initial)
    }
}
