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

    /** The amount of time in milliseconds it takes for a block to drop */
    var GRAVITY_PERIOD = 1000f

    /** wub wub wub */
    var timeToDrop = 0f

    /** A collection of active blocks, i.e blocks that are visible on the screen */
    val blocks = mutableListOf<Block>()

    /**
     * A collection of blocks that are 'on deck'. The first element in this collection
     * is the block that is shown as the 'next' block.
     */
    val queuedBlocks = mutableListOf<Block>()

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

    fun update(delta: Float) {
        timeToDrop += delta

        // Drop the player-controlled block
        if(timeToDrop >= GRAVITY_PERIOD) {

        }

        // Check for tetris
        // lol how

        // Check for OOB block / game over
    }

    /**
     *
     */
    fun attemptToMove(dx: Int, dy: Int, block : Block = getPlayerBlock()) {
        if(canBlockMove(dx, dy, block))
            block.move(dx, dy)
    }

    /**
     *
     */
    fun canBlockMove(dx: Int, dy: Int, block : Block) : Boolean {
        val blockClone = block.clone()
        blockClone.move(dx, dy)

        // Bounds
        blockClone.cells.forEach {
            if(it.x > (GRID_WIDTH - 1)
                    || it.x < 0
                    || it.y < 1)
                return false
        }

        // Intersect
        blocks
            .dropLast(1) // Remove the player block, it will always intersect with itself
            .forEach {
                if(blockClone.intersectsWith(it))
                    return false
            }
        return true
    }

    fun attemptToRotate() {
        val block = getPlayerBlock()

    }

    fun getPlayerBlock() : Block = blocks.last()
}
