package org.badgrades.tetris.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import org.badgrades.tetris.InputHandler
import org.badgrades.tetris.world.GameRenderer
import org.badgrades.tetris.TetrisGame
import org.badgrades.tetris.world.TetrisWorld

class GameScreen(val tetrisGame: TetrisGame, val tetrisWorld: TetrisWorld) : Screen {

    val renderer: GameRenderer
    var runTime: Float

    init {
        renderer = GameRenderer(tetrisWorld)
        runTime = 0f
        Gdx.input.inputProcessor = InputHandler(tetrisWorld)
    }

    override fun render(delta: Float) {
        runTime += delta
        tetrisWorld.update(delta)
        renderer.render(delta, runTime)
    }

    override fun show() { }
    override fun pause() { }
    override fun resize(width: Int, height: Int) { }
    override fun hide() { }
    override fun resume() { }
    override fun dispose() { }
}
