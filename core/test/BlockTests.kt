import org.badgrades.tetris.model.Block
import org.badgrades.tetris.model.BlockType
import org.junit.Test
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.*
import java.awt.Point

class BlockTests {

    @Test fun `I Block cell initialization`() {
        val iBlock = Block(BlockType.I, Point(1, 0))
        assertThat(iBlock.cells,
                `is`(mutableListOf(Point(1,0), Point(2,0), Point(3,0), Point(4,0))))
    }

    @Test fun `I Block cell rotation`() {
        val iBlock = Block(BlockType.I, Point(5, 5))

        iBlock.cells.forEach { cell -> println(cell) }
        iBlock.rotate(clockwise = false)
        println("--- POST ROTATE --- ")
        iBlock.cells.forEach { cell -> println(cell) }
    }
}