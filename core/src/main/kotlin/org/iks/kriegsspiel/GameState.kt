package org.iks.kriegsspiel

import com.badlogic.gdx.graphics.g2d.SpriteBatch

class GameState(val pieces: List<Piece>) {
    fun render(sb: SpriteBatch) {
        for (piece in pieces) {
            piece.render(sb)
        }
    }
}
