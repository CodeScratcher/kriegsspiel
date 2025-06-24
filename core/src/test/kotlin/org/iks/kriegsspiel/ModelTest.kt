package org.iks.kriegsspiel

import com.badlogic.gdx.graphics.Texture
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class ModelTest {
    private val testPiece = Piece(0f, 0f, 100f, 100f, mockk<Texture>())
    private val testGameState = GameState(listOf(testPiece))
    private val testModel = Model(testGameState)

    @Test
    fun testRecomputeState() {
    }
}
