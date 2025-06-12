package org.iks.kriegsspiel

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.Input.Keys
import java.time.LocalDateTime
import kotlin.random.Random

class Controller {
    fun createDiffs(currentState: GameState): MutableList<Diff> {
        val diffs: MutableList<Diff> = mutableListOf();

        if (Gdx.input.isKeyJustPressed(Keys.Q)) {
            val currentPiece = currentState.pieces[0];
            diffs.add(PieceUpdate(currentState.pieces, 0, Piece(Gdx.input.x.toFloat(),
                480 - Gdx.input.y.toFloat(),
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

        model.updates.addAll(createDiffs(model.currentState))
        model.recomputeCurrentState()
    }
}
