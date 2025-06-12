package org.iks.kriegsspiel

import java.time.LocalDateTime

abstract class Diff(val time: LocalDateTime): Comparable<Diff> {
    abstract fun apply(state: GameState): GameState
    abstract fun rollback(state: GameState): GameState

    override fun compareTo(other: Diff): Int {
        return time.compareTo(other.time)
    }
}
