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
    var gravityPeriod = 0.7f
    var timeToDrop = 0f // wub wub wub

    val startingBlockPosition: Point

    companion object {
        // TODO https://en.wikipedia.org/wiki/Tetris#Scoring
        const val TETRIS_SCORE = 100
    }

    init {
        startingBlockPosition = Point(
                Math.round(TetrisWorld.GRID_WIDTH.toDouble() / 2).toInt(), // Kind of gross to have all this conversion
                TetrisWorld.GRID_HEIGHT - 1
        )
    }

    fun update(delta: Float) {
        // Drop block
        timeToDrop += delta
        if(timeToDrop >= gravityPeriod) {

            if(canDrop()) {
                getPlayerBlock().move(0, -1)
            }
            else {

                if(doesTetrisExist()) {
                    processTetris()
                }

                spawnBlock() // TODO turn into a queue so we can see the 'next' block

                if(!canDrop()) {
                    // Game over
                    System.exit(0)
                }
            }

            timeToDrop = 0f

            println(tetrisWorld.generateMatrix().toString())
        }
    }

    fun doesTetrisExist() : Boolean {
        return getYValuesWithTetris().isNotEmpty()
    }

    fun processTetris() {
        val yValuesWithTetris = getYValuesWithTetris()
        TetrisGame.score += yValuesWithTetris.size * GameHandler.TETRIS_SCORE

        // Gather
        val allCells = mutableListOf<Point>()
        tetrisWorld.blocks.forEach { allCells.addAll(it.cells) }

        // Destroy
        val cellsOnYAxis = allCells.filter { yValuesWithTetris.contains(it.y) }
        tetrisWorld.blocks.forEach { it.cells.removeAll(cellsOnYAxis) }

        // Drop TODO
        var fallingBlocks = getFallingBlocks()
        while(fallingBlocks.isNotEmpty()) {
            fallingBlocks.forEach { it.move(0, -1) }
            fallingBlocks = getFallingBlocks()
        }
    }

    fun getYValuesWithTetris() = (0..TetrisWorld.GRID_HEIGHT-1).filter { doesTetrisExistAtY(it) }

    fun doesTetrisExistAtY(y: Int) : Boolean {
        val matrix = tetrisWorld.generateMatrix()
        return (0..TetrisWorld.GRID_WIDTH-1).none { x -> matrix[x, y] != 1 }
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
    fun attemptToRotate(block: Block = getPlayerBlock(), clockwise: Boolean = true) {
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
                    || it.y < 0)
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

    /**
     * Gets a list of blocks that doesn't include the player block and that can also drop it like it's hotttt
     */
    fun getFallingBlocks() = tetrisWorld.blocks
                .filter { it != getPlayerBlock() }
                .filter { canDrop(it) }

}
