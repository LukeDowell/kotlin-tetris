package org.badgrades.tetris.model

import java.awt.Point
import java.awt.geom.AffineTransform

/**
 * There are several ways that blocks can fit into our game world. Every block could fit inside of a 2x4 matrix, with
 * a 0 indicating empty space and a non-zero value indicating a filled cell.
 *
 * I would rather a block be a collection of 'filled' points. It would be pretty neat if I could spawn a block with a
 * certain position, and then have those points be calculated based on some offsets the enum stores. For example,
 * an I block type should know that it is 4 cells in a line, so that when we create a new block the list of points can
 * be automatically populated / calculated based on some offsets stored in the blocktype enum.
 */
class Block(val blockType: BlockType, startingPosition: Point) : Cloneable {

    val cells: MutableList<Point>

    init {
        cells = mutableListOf(startingPosition)
        for(offset in blockType.offsets) {
            val startingClone = startingPosition.clone() as Point
            startingClone.translate(offset.x, offset.y)
            cells.add(startingClone)
        }
    }

    fun move(dx: Int, dy: Int) = cells.forEach { it.translate(dx, dy) }

    fun intersectsWith(block : Block) : Boolean {
        // Create a list of cells whose points are the same as any in our list of cells
        return block.cells.filter { cells.contains(it) }.size > 0
    }

    /**
     *
     * Future consideration: How do we handle wall kicks?
     * http://tetris.wikia.com/wiki/Wall_kick
     *
     * Rotation source:
     * http://stackoverflow.com/questions/9985473/java-rotate-point-around-another-by-specified-degree-value
     */
    fun rotate(clockwise : Boolean) {
        val center = cells[1]
        val radians: Double

        if(clockwise) // I think this might be backwards
            radians = Math.toRadians(90.0)
        else
            radians = Math.toRadians(-90.0)

        val rotationInstance = AffineTransform.getRotateInstance(
                radians,
                center.x.toDouble(),
                center.y.toDouble()
        )

        for(cell in cells)
            rotationInstance.transform(cell, cell)
    }

    // Is this is kind of weird, that you can modify the visibility of an interface method?
    public override fun clone(): Block {
        val clone = Block(blockType, Point(0,0))
        clone.cells.clear()
        this.cells.forEach { clone.cells.add(it.clone() as Point) }
        return clone
    }
}
