package org.badgrades.tetris.world

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
 *              (GRID_WIDTH, GRID_HEIGHT)
 *
 * Apparently I need to learn to format documentation
 */
class TetrisWorld {

    /** A collection of active blocks, i.e blocks that are visible on the screen */
    val blocks = mutableListOf<Block>()

    companion object {
        /** The buffer gives us space to place a piece before it comes into view */
        const val GRID_BUFFER = 4
        const val GRID_HEIGHT = 20
        const val GRID_WIDTH = 10
    }

    init {
        blocks.add(Block(BlockType.L, Point(5,5)))
        blocks.add(Block(BlockType.Z, Point(5,15)))
    }
}
