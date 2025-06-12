package org.iks.kriegsspiel

class Model(val initialState: GameState) {
    val updates: MutableSet<Diff> = mutableSetOf()
    var currentState = initialState

    fun recomputeCurrentState() {
        currentState = updates.sorted().fold(initialState) { a, b -> b.apply(a) }
    }
}
