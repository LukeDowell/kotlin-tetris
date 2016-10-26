package org.badgrades.tetris.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import org.badgrades.tetris.GameHandler
import org.badgrades.tetris.InputHandler
import org.badgrades.tetris.world.WorldRenderer
import org.badgrades.tetris.TetrisGame
import org.badgrades.tetris.model.Block
import org.badgrades.tetris.world.TetrisWorld

class GameScreen(val tetrisGame: TetrisGame, val tetrisWorld: TetrisWorld) : Screen {

    val renderer: WorldRenderer
    val gameHandler: GameHandler

    init {
        gameHandler = GameHandler(tetrisWorld)
        renderer = WorldRenderer(tetrisWorld)
        Gdx.input.inputProcessor = InputHandler(gameHandler)
    }

    override fun render(delta: Float) {
        gameHandler.update(delta)
        renderer.render(delta)
    }

    override fun show() { }
    override fun pause() { }
    override fun resize(width: Int, height: Int) { }
    override fun hide() { }
    override fun resume() { }
    override fun dispose() { }
}
