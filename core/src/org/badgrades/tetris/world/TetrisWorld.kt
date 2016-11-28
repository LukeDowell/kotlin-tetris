package org.badgrades.tetris.world

import org.badgrades.tetris.model.Array2D
import org.badgrades.tetris.model.Block
import org.badgrades.tetris.model.BlockType
import java.awt.Point

/**
 * The tetris world is really just a two dimensional grid that stores a blocks. The coordinate system has (0,0) at
 * the top left.
 *
 *     (0,0)
 *        +------------+
 *        |            |
 *        |            |
 *        |            |
 *        |            |
 *        |            |
 *        |            |
 *        |            |
 *        +------------+
 *              (GRID_WIDTH-1, GRID_HEIGHT-1)
 */
class TetrisWorld {

    /** The current player controlled block */
    var playerBlock: Block = Block.getRandom(STARTING_POSITION)

    /** A collection of active blocks, i.e blocks that are visible on the screen */
    val placedBlocks = mutableListOf<Block>()

    companion object {
        /** The buffer gives us space to place a piece before it comes into view */
        const val GRID_BUFFER = 4
        const val GRID_HEIGHT = 20
        const val GRID_WIDTH = 10
        val STARTING_POSITION = Point(
                Math.round(GRID_WIDTH.toDouble() / 2).toInt(), // Kind of gross to have all this conversion
                GRID_HEIGHT - 1
        )
    }

    /**
     * Generates a 2d matrix representing the game field. a 0 is an empty cell while a
     * 1 is a cell occupied by a block
     */
    fun generateMatrix() : Array2D<Int> {
        val matrix = Array2D<Int>(GRID_WIDTH, GRID_HEIGHT) { x, y -> 0 }

        placedBlocks.forEach { block ->
            block.cells.forEach { cell ->
                matrix[cell.x, cell.y] = 1
            }
        }

        playerBlock.cells.forEach { matrix[it.x, it.y] = 1 }

        return matrix
    }
}
