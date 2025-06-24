package org.iks.kriegsspiel

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.Input.Keys
import java.time.LocalDateTime
import kotlin.random.Random

class Controller {
    var pieceSelected = 0
    var offsetX = 0f
    var offsetY = 0f
    var dragging = false

    fun createTemporaryDiffs(currentState: GameState): MutableList<Diff> {
        val diffs: MutableList<Diff> = mutableListOf()

        if (dragging) {
            val cornerX = Gdx.input.x + offsetX
            val cornerY = Gdx.graphics.height - 1 - Gdx.input.y + offsetY

            val currentPiece = currentState.pieces[pieceSelected];
            diffs.add(PieceUpdate(currentState.pieces, pieceSelected, Piece(cornerX,
                cornerY,
                currentPiece.w,
                currentPiece.h,
                currentPiece.texture),
                LocalDateTime.now()))
        }

        return diffs
    }

    fun createDiffs(currentState: GameState): MutableList<Diff> {
        val diffs: MutableList<Diff> = mutableListOf()

        if (dragging && !Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            val cornerX = Gdx.input.x + offsetX
            val cornerY = Gdx.graphics.height - 1 - Gdx.input.y + offsetY

            val currentPiece = currentState.pieces[pieceSelected];
            diffs.add(PieceUpdate(currentState.pieces, pieceSelected, Piece(cornerX,
                cornerY,
                currentPiece.w,
                currentPiece.h,
                currentPiece.texture),
                LocalDateTime.now()))
        }

        return diffs;
    }

    fun updateModel(model: Model) {
        val ctrlZPressed = (Gdx.input.isKeyPressed(Keys.CONTROL_LEFT) && Gdx.input.isKeyJustPressed(Keys.Z)) ||
            (Gdx.input.isKeyJustPressed(Keys.CONTROL_LEFT) && Gdx.input.isKeyPressed(Keys.Z))

        if (ctrlZPressed && model.updates.isNotEmpty()) {
            model.updates.removeLast()
        }

        model.temporaryUpdates.addAll(createTemporaryDiffs(model.currentState))
        model.updates.addAll(createDiffs(model.currentState))

        dragging = dragging && Gdx.input.isButtonPressed(Input.Buttons.LEFT)
        if (!dragging) model.temporaryUpdates.clear()
        model.recomputeCurrentState()

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            model.currentState.pieces.forEachIndexed { idx, piece ->
                val x = Gdx.input.x
                val y = Gdx.graphics.height - 1 - Gdx.input.y

                if (piece.x < x && x < piece.x + piece.w && piece.y < y && y < piece.y + piece.h) {
                    pieceSelected = idx
                    offsetX = piece.x - x
                    offsetY = piece.y - y
                    dragging = true
                }
            }
        }

        dragging = dragging && Gdx.input.isButtonPressed(Input.Buttons.LEFT)
    }
}
