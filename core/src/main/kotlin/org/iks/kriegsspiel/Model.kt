package org.iks.kriegsspiel

class Model(val initialState: GameState) {
    val updates: MutableList<Diff> = mutableListOf()
    var currentState = initialState

    fun recomputeCurrentState() {
        currentState = updates.fold(initialState) { a, b -> b.apply(a) }
    }
}
