package me.quentin.morpion

import me.quentin.morpion.gamelogic.Game
import me.quentin.morpion.gamelogic.Grid
import org.junit.Assert.*
import org.junit.Test

class GameLogicTests {
    @Test
    fun testHorizontalLinesCheckWork() {
        val player = Game.Player.Player1

        var grid = Grid.testGrid(
            arrayOf(
                arrayOf(player, player, player),
                arrayOf(null, null, null),
                arrayOf(null, null, null)
            )
        )
        assert(grid.checkWin() != null) {
            throw AssertionError("Failed winner check on horizontal line with x = 0")
        }

        grid = Grid.testGrid(
            arrayOf(
                arrayOf(null, null, null),
                arrayOf(player, player, player),
                arrayOf(null, null, null)
            )
        )
        assert(grid.checkWin() != null) {
            throw AssertionError("Failed winner check on horizontal line with x = 1")
        }

        grid = Grid.testGrid(
            arrayOf(
                arrayOf(null, null, null),
                arrayOf(null, null, null),
                arrayOf(player, player, player)
            )
        )
        assert(grid.checkWin() != null) {
            throw AssertionError("Failed winner check on horizontal line with x = 2")
        }
    }

    @Test
    fun testVerticalLinesCheckWork() {
        val player = Game.Player.Player1

        var grid = Grid.testGrid(
            arrayOf(
                arrayOf(player, null, null),
                arrayOf(player, null, null),
                arrayOf(player, null, null)
            )
        )
        assert(grid.checkWin() != null) {
            throw AssertionError("Failed winner check on vertical line with y = 0")
        }

        grid = Grid.testGrid(
            arrayOf(
                arrayOf(null, player, null),
                arrayOf(null, player, null),
                arrayOf(null, player, null)
            )
        )
        assert(grid.checkWin() != null) {
            throw AssertionError("Failed winner check on vertical line with y = 1")
        }

        grid = Grid.testGrid(
            arrayOf(
                arrayOf(null, player, null),
                arrayOf(null, player, null),
                arrayOf(null, player, null)
            )
        )
        assert(grid.checkWin() != null) {
            throw AssertionError("Failed winner check on vertical line with y = 1")
        }

        grid = Grid.testGrid(
            arrayOf(
                arrayOf(null, null, player),
                arrayOf(null, null, player),
                arrayOf(null, null, player)
            )
        )
        assert(grid.checkWin() != null) {
            throw AssertionError("Failed winner check on vertical line with y = 2")
        }
    }

    @Test
    fun testDiagonalsCheckWork() {
        val player = Game.Player.Player1

        var grid = Grid.testGrid(
            arrayOf(
                arrayOf(player, null, null),
                arrayOf(null, player, null),
                arrayOf(null, null, player)
            )
        )
        assert(grid.checkWin() != null) {
            throw AssertionError("Failed winner check on left-to-right diagonal")
        }

        grid = Grid.testGrid(
            arrayOf(
                arrayOf(null, null, player),
                arrayOf(null, player, null),
                arrayOf(player, null, null)
            )
        )
        assert(grid.checkWin() != null) {
            throw AssertionError("Failed winner check on right-to-left diagonal")
        }
    }

    @Test
    fun testPlacePlayerAtWorks() {
        var grid = Grid()
        grid.placePlayerAt(Game.Player.Player1, 2, 0)

        val expected = Grid.testGrid(
            arrayOf(
                arrayOf(null, null, Game.Player.Player1),
                arrayOf(null, null, null),
                arrayOf(null, null, null)
            )
        )
        assertEquals(expected, grid)
    }

    @Test
    fun testCheckDrawWorks() {
        val player1 = Game.Player.Player1
        val player2 = Game.Player.Player2

        var grid = Grid.testGrid(
            arrayOf(
                arrayOf(null, null, null),
                arrayOf(null, null, null),
                arrayOf(null, null, null)
            )
        )
        assertEquals(false, grid.isDraw())

        grid = Grid.testGrid(
            arrayOf(
                arrayOf(player1, player1, player2),
                arrayOf(player2, player2, player1),
                arrayOf(player1, player2, player2)
            )
        )
        assertEquals(true, grid.isDraw())

        grid = Grid.testGrid(
            arrayOf(
                arrayOf(player1, player2, player2),
                arrayOf(player1, player1, player2),
                arrayOf(player1, player2, player1)
            )
        )
        assertEquals(false, grid.isDraw())

        grid = Grid.testGrid(
            arrayOf(
                arrayOf(player1, player1, player2),
                arrayOf(player2, player2, player1),
                arrayOf(player1, player2, null)
            )
        )
        assertEquals(false, grid.isDraw())
    }
}