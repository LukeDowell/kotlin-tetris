package org.badgrades.tetris.world

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer

/**
 * How are we going to draw a tetris block in between cells? We don't want
 * our game to be jerky, we want a smooth animation. It will also make the
 * force-drop look a lot better. Maybe LibGDX tweening is the answer
 */
class GameRenderer(val tetrisWorld: TetrisWorld) {

    val camera: OrthographicCamera
    val shapeRenderer: ShapeRenderer
    val batch: SpriteBatch

    companion object {
        /** The value we use to convert from game units to visual units */
        const val VISUAL_UNITS = 32f
    }

    init {
        camera = OrthographicCamera(
                Gdx.graphics.width.toFloat(),
                Gdx.graphics.height.toFloat()
        )

        shapeRenderer = ShapeRenderer()
        shapeRenderer.projectionMatrix = camera.combined

        batch = SpriteBatch()
        batch.projectionMatrix = camera.combined
    }

    fun render(delta: Float, runTime: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)

        // For each block
        tetrisWorld.blocks.forEach {
            val color = it.blockType.color
            // Draw each cell
            it.cells.forEach {
                shapeRenderer.color = color
                shapeRenderer.rect(
                    it.x * VISUAL_UNITS,
                    it.y * VISUAL_UNITS,
                    VISUAL_UNITS,
                    VISUAL_UNITS
                )
            }
        }
        shapeRenderer.end()
    }
}