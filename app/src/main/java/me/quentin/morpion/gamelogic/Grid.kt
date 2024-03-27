package me.quentin.morpion.gamelogic

import org.jetbrains.annotations.TestOnly

class Grid {

    var inner: Array<Array<Game.Player?>> = arrayOf(
        arrayOf(null, null, null),
        arrayOf(null, null, null),
        arrayOf(null, null, null)
    )

    fun placePlayerAt(player: Game.Player?, x: Int, y: Int): Boolean {
        if (inner[y][x] == null) {
            inner[y][x] = player
            return true
        }
        return false
    }

    fun checkWin(): Game.Player? {
        // Check horizontal lines
        for (y in 0..2) {
            val playerAt = inner[y][0]
            var count = 0

            // Check if this position has been played, if not then it's useless to check for win
            if (playerAt != null) {
                for (x in 0..2) {
                    if (inner[y][x] == playerAt) count += 1
                }
                if (count == 3) return playerAt
            }
        }

        // Check vertical lines
        for (x in 0..2) {
            val playerAt = inner[0][x]
            var count = 0

            if (playerAt != null) {
                for (y in 0..2) {
                    if (inner[y][x] == playerAt) count += 1
                }
                if (count == 3) return playerAt
            }
        }

        // Check the left-to-right diagonal
        var playerAt = inner[0][0]
        if (playerAt != null) {
            var count = 0
            for (x in 0..2) {
                if (inner[x][x] == playerAt) count += 1
            }

            if (count == 3) return playerAt
        }

        // Check the right-to-left diagonal
        var x = 2
        var y = 0
        playerAt = inner[y][x]
        var count = 0

        while (x >= 0) {
            if (inner[y][x] == playerAt) count += 1
            x -= 1
            y += 1
        }
        if (count == 3) return playerAt

        return null
    }

    fun isDraw(): Boolean {
        // If any box has not been played then it's not a draw
        for (row in inner) {
            for (box in row) {
                if (box == null) return false
            }
        }

        // Else if all the boxes has been played and no played has won yet, it's a draw
        return checkWin() == null
    }

    fun playerAt(x: Int, y: Int): Game.Player? {
        return inner[y][x]
    }

    override fun toString(): String {
        var buf = ""
        for (row in inner) {
            buf += "(${row[0]}, ${row[1]}, ${row[2]})\n"
        }

        return buf
    }

    override fun equals(other: Any?): Boolean {
        return toString() == other.toString()
    }

    companion object {
        @TestOnly
        fun testGrid(gridVal: Array<Array<Game.Player?>>): Grid {
            var grid = Grid()
            grid.inner = gridVal

            return grid
        }
    }
}