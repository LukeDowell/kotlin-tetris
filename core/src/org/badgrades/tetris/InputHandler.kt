package org.badgrades.tetris

import com.badlogic.gdx.Input
import com.badlogic.gdx.InputAdapter
import org.badgrades.tetris.world.TetrisWorld

class InputHandler(val tetrisWorld: TetrisWorld) : InputAdapter() {

    override fun keyDown(keycode: Int) : Boolean = when(keycode) {
            Input.Keys.UP -> {
                tetrisWorld.attemptToMove(0, 1)
                true
            }
            Input.Keys.RIGHT -> {
                tetrisWorld.attemptToMove(1, 0)
                true
            }
            Input.Keys.DOWN -> {
                tetrisWorld.attemptToMove(0, -1)
                true
            }
            Input.Keys.LEFT -> {
                tetrisWorld.attemptToMove(-1, 0)
                true
            }
            Input.Keys.SPACE -> {
                tetrisWorld.getPlayerBlock().rotate()
                true
            }
            else -> false
        }
}
