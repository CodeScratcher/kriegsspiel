package org.iks.kriegsspiel

class Model(val initialState: GameState) {
    val updates: MutableList<Diff> = mutableListOf()
    val temporaryUpdates: MutableList<Diff> = mutableListOf()
    var currentState = initialState
    var currentTempState = currentState

    fun addDiff(diff: Diff) {
        temporaryUpdates.clear()
        if (updates.isEmpty() || diff >= updates.last()) {
            currentState = diff.apply(currentState)
            updates.add(diff)
        }
        else {
            for ((idx, item) in updates.withIndex()) {
                if (item >= diff) {
                    updates.add(idx - 1, diff)
                }
            }
            recomputeCurrentState()
        }

        currentTempState = currentState
    }

    fun addTemporaryDiff(diff: Diff) {
        if (temporaryUpdates.isEmpty() || diff >= temporaryUpdates.last()) {
            currentTempState = diff.apply(currentState)
            temporaryUpdates.add(diff)
        }
        else {
            for ((idx, item) in temporaryUpdates.withIndex()) {
                if (item >= diff) {
                    temporaryUpdates.add(idx - 1, diff)
                }
            }
            recomputeCurrentState()
        }
    }

    fun revertChange() {
        temporaryUpdates.clear()
        currentState = updates.last().rollback(currentState)
        currentTempState = currentState
        updates.removeLast()
    }

    fun recomputeCurrentState() {
        currentState = updates.fold(initialState) { a, b -> b.apply(a) }
        currentTempState = temporaryUpdates.fold(currentState) { a, b -> b.apply(a) }
    }
}
