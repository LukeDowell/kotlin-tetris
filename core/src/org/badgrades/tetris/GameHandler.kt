package org.badgrades.tetris

import org.badgrades.tetris.model.Block
import org.badgrades.tetris.world.TetrisWorld

/**
 * Holds all of our game logic
 */
class GameHandler(val tetrisWorld: TetrisWorld) {

    fun update(delta: Float) {
        // Drop block
        // Check for out of bounds / game over
        // Check for tetris
    }

    fun attemptToMove(dx: Int, dy: Int, block: Block = getPlayerBlock()) {
        if(canBlockMove(dx, dy, block))
            block.move(dx, dy)
    }

    fun canBlockMove(dx: Int, dy: Int, block: Block) : Boolean {
        val blockClone = block.clone()
        blockClone.move(dx, dy)

        // Bounds
        blockClone.cells.forEach {
            if(it.x > (TetrisWorld.GRID_WIDTH - 1)
                    || it.x < 0
                    || it.y < 1)
                return false
        }

        // Intersect
        tetrisWorld.blocks
                .dropLast(1) // Remove the player block, it will always intersect with itself
                .forEach {
                    if(blockClone.intersectsWith(it))
                        return false
                }
        return true
    }

    /**
     * Wall kicks yo
     */
    fun attemptToRotate(block : Block = getPlayerBlock(), clockwise : Boolean = true) {
        block.rotate(clockwise)
    }

    fun getPlayerBlock() : Block = tetrisWorld.blocks.last()
}
