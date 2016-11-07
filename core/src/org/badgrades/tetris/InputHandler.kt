package org.badgrades.tetris

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.InputAdapter

class InputHandler(val gameHandler: GameHandler) : InputAdapter() {

    override fun keyDown(keycode: Int) : Boolean = when(keycode) {
            Input.Keys.UP -> {
                gameHandler.attemptToMove(0, 1)
                true
            }
            Input.Keys.RIGHT -> {
                gameHandler.attemptToMove(1, 0)
                true
            }
            Input.Keys.DOWN -> {
                gameHandler.gravityPeriod = 0.05f
                true
            }
            Input.Keys.LEFT -> {
                gameHandler.attemptToMove(-1, 0)
                true
            }
            Input.Keys.SPACE -> {
                if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT))
                    gameHandler.attemptToRotate()
                else
                    gameHandler.attemptToRotate(clockwise = false)
                true
            }
            else -> false
        }

    override fun keyUp(keycode: Int): Boolean = when(keycode) {
        Input.Keys.DOWN -> {
            gameHandler.gravityPeriod = 0.7f
            true
        }
        else -> false
    }
}
