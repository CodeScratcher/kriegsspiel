package org.iks.kriegsspiel

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class Piece(val x: Float, val y: Float, val w: Float, val h: Float, val texture: Texture) {
    fun render(sb: SpriteBatch) {
        sb.draw(texture, x, y, w, h)
    }
}
