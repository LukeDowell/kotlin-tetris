package org.badgrades.tetris

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer

class GameRenderer(tetrisGame: TetrisGame) {

    val camera: OrthographicCamera
    val shapeRenderer: ShapeRenderer
    val batch: SpriteBatch

    init {
        camera = OrthographicCamera()
        shapeRenderer = ShapeRenderer()
        batch = SpriteBatch()
    }

    fun render(delta: Float, runTime: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    }
}