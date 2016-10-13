import org.badgrades.tetris.model.Block
import org.badgrades.tetris.model.BlockType
import org.junit.Test
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.*
import java.awt.Point

class BlockTests {

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
}