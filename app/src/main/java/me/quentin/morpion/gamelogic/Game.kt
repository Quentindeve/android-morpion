package me.quentin.morpion.gamelogic

class Game {
    enum class Player {
        Player1,
        Player2;

        fun nextPlayer(): Player {
            if (this == Player1) return Player2
            else return Player1
        }

        override fun toString(): String {
            return when (this) {
                Player1 -> "joueur 1"
                Player2 -> "joueur 2"
            }
        }
    }

    enum class GameState {
        Player1Won,
        Player2Won,
        Draw;
    }

    var grid: Grid = Grid()
    var player = Player.Player1
    var lock: Boolean = false

    fun play(x: Int, y: Int): String? {
        if (lock)
            return null

        val result = grid.placePlayerAt(player, x, y)

        if (result) {
            val returned = when (player) {
                Player.Player1 -> "X"
                Player.Player2 -> "O"
            }

            player = player.nextPlayer()

            return returned
        } else return null
    }

    fun gameState(): GameState? {
        if (grid.checkWin() != null) {
            when (grid.checkWin()!!) {
                Player.Player1 -> return GameState.Player1Won
                Player.Player2 -> return GameState.Player2Won
            }
        }

        if (grid.isDraw()) return GameState.Draw

        return null
    }

    fun lock() {
        lock = true
    }
}