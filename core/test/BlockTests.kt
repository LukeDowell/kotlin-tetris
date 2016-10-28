import org.badgrades.tetris.GameHandler
import org.badgrades.tetris.model.Block
import org.badgrades.tetris.model.BlockType
import org.badgrades.tetris.world.TetrisWorld
import org.junit.Test
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.*
import org.junit.Before
import java.awt.Point

class BlockTests {

    lateinit var tetrisWorld: TetrisWorld
    lateinit var gameHandler: GameHandler

    @Before fun setUp() {
        tetrisWorld = TetrisWorld()
        gameHandler = GameHandler(tetrisWorld)
    }

    @Test fun `I Block cell initialization`() {
        val iBlock = Block(BlockType.I, Point(1, 0))
        val iBlockMutableList = mutableListOf(Point(1,0), Point(2,0), Point(3,0), Point(4,0))
        assertThat(
                iBlock.cells,
                `is`(iBlockMutableList)
        )
    }

    @Test fun `I Block cell rotation`() {
        // TODO
    }

    @Test fun `block should intersect`() {
        val iBlock = Block(BlockType.I, Point(4, 4))
        val zBlock = Block(BlockType.Z, Point(4, 4))

        assertThat(
                iBlock.intersectsWith(zBlock),
                `is`(true)
        )
    }

    @Test fun `block should not intersect`() {
        val iBlock = Block(BlockType.I, Point(0, 0))
        val zBlock = Block(BlockType.Z, Point(6, 6))

        assertThat(
                iBlock.intersectsWith(zBlock),
                `is`(false)
        )
    }

    @Test fun `block should rotate`() {

    }

    @Test fun `block should not rotate`() {

    }

    @Test fun `tetris should exist`() {
        tetrisWorld.blocks.addAll(listOf(
                Block(BlockType.O, Point(0, 1)),
                Block(BlockType.O, Point(2, 1)),
                Block(BlockType.O, Point(4, 1)),
                Block(BlockType.O, Point(6, 1)),
                Block(BlockType.O, Point(8, 1))
        ))

        assertThat(
            gameHandler.doesTetrisExistAtY(0),
                `is`(true)
        )
        assertThat(
                gameHandler.doesTetrisExistAtY(1),
                `is`(true)
        )
    }

    @Test fun `tetris should not exist`() {
        tetrisWorld.blocks.addAll(listOf(
                Block(BlockType.O, Point(2, 1)),
                Block(BlockType.O, Point(4, 1)),
                Block(BlockType.O, Point(6, 1)),
                Block(BlockType.O, Point(8, 1))
        ))

        assertThat(
                gameHandler.doesTetrisExistAtY(0),
                `is`(false)
        )
        assertThat(
                gameHandler.doesTetrisExistAtY(1),
                `is`(false)
        )
    }
}