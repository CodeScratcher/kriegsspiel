package org.iks.kriegsspiel

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.ScreenUtils

/** [com.badlogic.gdx.ApplicationListener] implementation shared by all platforms. */
class Main() : ApplicationAdapter() {
    val controller = Controller()
    var model: Model
    var pieceTexture: Texture? = null
    var sb: SpriteBatch? = null

    init {
        val gameState = GameState(listOf())

        model = Model(gameState)
    }

    override fun create() {
        pieceTexture = Texture("IKS_Infantry_L_Template.png")
        val piece = Piece(0f, 0f, 125f, 75f, pieceTexture!!)
        val piece2 = Piece(200f, 100f, 125f, 75f, pieceTexture!!)

        val gameState = GameState(listOf(piece, piece2))
        sb = SpriteBatch()

        model = Model(gameState)
    }

    override fun render() {
        controller.updateModel(model)

        ScreenUtils.clear(Color.BLACK)

        sb!!.begin()
        model.currentTempState.render(sb!!)
        sb!!.end()
    }
}
