package org.neggly.perspective

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.viewport.FitViewport

const val WIDTH = 960f
const val HEIGHT = 540f

class PerspectiveTest : ApplicationAdapter()
{
    private lateinit var stage: Stage

    private val group = PerspectiveGroup2()

    private lateinit var image: Image

    override fun create()
    {
        stage = Stage(FitViewport(WIDTH, HEIGHT))

        stage.camera.near = -1000f
        stage.camera.far = 1000f

        Gdx.input.inputProcessor = stage
        //stage.batch.projectionMatrix = stage.camera.combined

        image = Image(Texture("badlogic.jpg"))

        group.addActor(image)

        stage.addActor(group)
    }

    override fun render()
    {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        group.xAxisRotation++

        stage.act()
        stage.draw()
    }

    override fun resize(width: Int, height: Int)
    {
        stage.viewport.update(width, height)
    }

    override fun dispose()
    {
        stage.dispose()
    }
}
