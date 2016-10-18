package org.badgrades.tetris

import com.badlogic.gdx.Game
import org.badgrades.tetris.screen.GameScreen
import org.badgrades.tetris.world.TetrisWorld

class TetrisGame : Game() {

    companion object {
        var score = 0
    }

    override fun create() {
        setScreen(GameScreen(this, TetrisWorld()))
    }

    override fun dispose() {
        super.dispose()
    }
}
