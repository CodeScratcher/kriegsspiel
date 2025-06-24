package org.iks.kriegsspiel

import com.badlogic.gdx.graphics.Texture
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import java.time.LocalDateTime
import kotlin.test.Test

class ModelTest {


    @Test
    fun testRecomputeState() {
        val testPiece = Piece(0f, 0f, 100f, 100f, mockk<Texture>())
        val testGameState = GameState(listOf(testPiece))
        val testModel = Model(testGameState)
        val testPiece2 = Piece(10f, 10f, 100f, 100f, mockk<Texture>())
        val testChange = PieceUpdate(testGameState.pieces, 0, testPiece2, LocalDateTime.now())

        testModel.updates += testChange
        testModel.recomputeCurrentState()

        assertEquals(testModel.currentState.pieces[0], testPiece2)
    }
}
