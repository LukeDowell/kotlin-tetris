package org.badgrades.tetris

import org.badgrades.tetris.model.Block
import org.badgrades.tetris.model.BlockType
import org.badgrades.tetris.world.TetrisWorld
import java.awt.Point
import java.util.*

/**
 * Holds all of our game logic
 */
class GameHandler(val tetrisWorld: TetrisWorld) {

    /** gravitational period, ie the amount of time in seconds it takes for a block to drop one unit */
    var gravityPeriod = 1f
    var timeToDrop = 0f // wub wub wub

    val startingBlockPosition: Point

    init {
        startingBlockPosition = Point(
                Math.round(TetrisWorld.GRID_WIDTH.toDouble() / 2).toInt(), // Kind of gross to have all this conversion
                TetrisWorld.GRID_HEIGHT - 1
        )

        spawnBlock()
    }

    fun update(delta: Float) {
        // Drop block
        timeToDrop += delta
        if(timeToDrop >= gravityPeriod) {
            // Hm how can we tell the difference between hitting a wall and hitting another block?
            // Should we separate attemptToMove into moveSideways and dropBlock?
            if(canDrop())
                getPlayerBlock().move(0, -1)
            else
                spawnBlock() // TODO turn into a queue so we can see the 'next' block

            timeToDrop = 0f
        }

        // Check for out of bounds / game over

        // Check for tetris
    }

    fun spawnBlock() = tetrisWorld.blocks.add(getRandomBlock())

    fun getRandomBlock() : Block {
        val randomBlockType = BlockType.values()[Random().nextInt(BlockType.values().size)]
        return Block(randomBlockType, startingBlockPosition.clone() as Point)
    }

    fun canDrop(block: Block = getPlayerBlock()) : Boolean {
        val blockClone = block.clone()
        blockClone.move(0, -1)

        return isBlockPositionValid(blockClone)
    }

    fun attemptToMove(dx: Int, dy: Int, block: Block = getPlayerBlock()) {
        val blockClone = block.clone()
        blockClone.move(dx, dy)

        if(isBlockPositionValid(blockClone))
            block.move(dx, dy)
    }

    /**
     * Wall kicks yo
     */
    fun attemptToRotate(block : Block = getPlayerBlock(), clockwise : Boolean = true) {
        val blockClone = block.clone()
        blockClone.rotate(clockwise)

        if(isBlockPositionValid(blockClone))
            block.rotate(clockwise)
        else
            println("wall kick")// How do we determine that it's a wall kick and not a 'block' kick?
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
                    || it.y < 1)
                return false
        }

        // Intersect
        tetrisWorld.blocks
                .dropLast(1) // Remove the player block, it will always intersect with itself
                .forEach {
                    if(block.intersectsWith(it))
                        return false
                }
        return true
    }


    fun getPlayerBlock() : Block = tetrisWorld.blocks.last()
}
