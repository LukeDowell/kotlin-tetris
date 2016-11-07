package org.badgrades.tetris.model

import com.badlogic.gdx.graphics.Color
import java.awt.Point

/**
 * BlockTypes have a color and implement getPointOffsets. This is used when
 * a new Block is created to initialize the block's list of points. They should not be
 * used post-initialization.
 *
 * Returns a list of 3 points that contain offset values for a given block. Each method has a
 * small comment above it that shows the 'location' of the offsets, with '=' denoting the starting
 * position of the block and with '+' denoting the location of the offsets.
 *
 * An example flow would be that an L block gets spawned with a starting position of (1, 1). The offsets
 * for the L block are (1, 0), (2, 0) and (3, 0). This would cause the cell positions of the L block to be
 * (1, 1), (2, 1), (3, 1) and (4, 1)
 *
 * @see org.badgrades.tetris.world.WorldRenderer
 * @see org.badgrades.tetris.TetrisWorld
 */
enum class BlockType(val color: Color, val offsets: List<Point>) {

    /**
     *  = + + +
     */
    I(Color.CYAN, listOf(Point(1, 0), Point(2, 0), Point(3, 0))),

    /**
     *  =  +
     *  +  +
     */
    O(Color.YELLOW, listOf(Point(1, 0), Point(0, -1), Point(1, -1))),

    /**
     *      =
     *   +  +  +
     */
    T(Color.MAGENTA, listOf(Point(-1, -1), Point(0, -1), Point(1, -1))),

    /**
     *     + =
     *   + +
     */
    S(Color.GREEN, listOf(Point(-1, 0), Point(-1, -1), Point(-2, -1))),

    /**
     *
     *  = +
     *    + +
     *
     */
    Z(Color.RED, listOf(Point(1, 0), Point(1, -1), Point(2, -1))),

    /**
     *     =
     *     +
     *   + +
     */
    J(Color.BLUE, listOf(Point(0, -1), Point(0, -2), Point(-1, -2))),

    /**
     *   =
     *   +
     *   + +
     */
    L(Color.ORANGE, listOf(Point(0, -1), Point(0, -2), Point(1, -2))),
}
