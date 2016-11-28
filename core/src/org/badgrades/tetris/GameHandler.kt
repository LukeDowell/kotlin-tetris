package org.badgrades.tetris

import org.badgrades.tetris.model.Block
import org.badgrades.tetris.world.TetrisWorld
import java.util.*

/**a
 * Holds all of our game logic
 */
class GameHandler(val tetrisWorld: TetrisWorld) {

    /** gravitational period, ie the amount of time in seconds it takes for a block to drop one unit */
    var gravityPeriod = 0.7f
    var timeToDrop = 0f // wub wub wub
    var blockQueue: Queue<Block> = LinkedList<Block>()
    var isRunning = false

    companion object {
        const val TETRIS_SCORE = 100
        const val QUEUE_SIZE = 40
    }

    init {


        (0..QUEUE_SIZE)
                .forEach { blockQueue.add(Block.getRandom(TetrisWorld.STARTING_POSITION)) }
    }

    fun start() {
        tetrisWorld.playerBlock = blockQueue.remove()
        isRunning = true
    }

    fun update(delta: Float) {
        // Drop block
        timeToDrop += delta
        if(timeToDrop >= gravityPeriod && isRunning) {

            if(canDrop(tetrisWorld.playerBlock)) {
                tetrisWorld.playerBlock.move(0, -1)
            }
            else {
                tetrisWorld.placedBlocks.add(tetrisWorld.playerBlock)
                tetrisWorld.playerBlock = Block.getRandom(TetrisWorld.STARTING_POSITION)

                var yValuesWithTetris = getYValuesWithTetris()
                while (yValuesWithTetris.isNotEmpty()) {
                    yValuesWithTetris.forEach { processTetrisAtY(it) } // TODO there is a bug here
                    yValuesWithTetris = getYValuesWithTetris()
                }

                if(!canDrop(tetrisWorld.playerBlock)) {
                    // Game over
                    System.exit(0)
                }
            }

            timeToDrop = 0f
        } else {
            //TODO display paused message
        }
    }

    fun processTetrisAtY(y: Int) {
        println("Processing tetris at ${y}")
        // Gather
        val cellsOnY = tetrisWorld.placedBlocks
            .map { it.cells }
            .flatten()
            .filter {
                it.y == y
            }

        // Destroy
        tetrisWorld.placedBlocks
            .forEach { it.cells.removeAll(cellsOnY) }

        // Drop TODO
        tetrisWorld.placedBlocks
            .map { it.cells }
            .flatten()
            .filter { it.y >= y }
            .forEach { it.move(it.x, (it.y-1)) }

        // Score
        TetrisGame.score += TETRIS_SCORE
    }

    fun getYValuesWithTetris() = (0..TetrisWorld.GRID_HEIGHT-1).filter { doesTetrisExistAtY(it) }

    fun doesTetrisExistAtY(y: Int) : Boolean {
        val matrix = tetrisWorld.generateMatrix()
        return (0..TetrisWorld.GRID_WIDTH-1).all { x -> matrix[x, y] == 1 }
    }

    fun canDrop(block: Block) : Boolean {
        val blockClone = block.clone()
        blockClone.move(0, -1)
        return isBlockPositionValid(blockClone)
    }

    fun attemptToMove(dx: Int, dy: Int, block: Block = tetrisWorld.playerBlock) : Boolean {
        val blockClone = block.clone()
        blockClone.move(dx, dy)

        if(isBlockPositionValid(blockClone)) {
            block.move(dx, dy)
            return true
        }

        return false
    }

    /**
     * Wall kicks yo
     */
    fun attemptToRotate(block: Block = tetrisWorld.playerBlock, clockwise: Boolean = true) : Boolean {
        val blockClone = block.clone()
        blockClone.rotate(clockwise)

        if(isBlockPositionValid(blockClone)) {
            block.rotate(clockwise)
            return true
        }

        return false
    }

    /**
     * Checks to see if a block is within the bounds of the game and
     * if it is not intersecting with any other blocks
     */
    fun isBlockPositionValid(block: Block) : Boolean {
        // Bounds
        block.cells.forEach {
            if(it.x > (TetrisWorld.GRID_WIDTH - 1)
                    || it.x < 0
                    || it.y < 0)
                return false
        }

        // Intersect
        tetrisWorld.placedBlocks
                .forEach {
                    if(block.intersectsWith(it))
                        return false
                }
        return true
    }
}
