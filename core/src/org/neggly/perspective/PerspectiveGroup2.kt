package org.neggly.perspective

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.math.Matrix4
import com.badlogic.gdx.scenes.scene2d.Group

class PerspectiveGroup2 : Group()
{
    private val projectionMatrix = Matrix4()

    var xAxisRotation = 0f
    var yAxisRotation = 0f
    private var perspective = 1f / 1500f

    override fun draw(batch: Batch?, parentAlpha: Float)
    {
        if (batch != null)
        {
            projectionMatrix.set(batch.projectionMatrix)

            batch.projectionMatrix.`val`[Matrix4.M32] = perspective
            //batch.projectionMatrix.translate(0f, 100f, 0f)
            batch.projectionMatrix.rotate(1f, 0f, 0f, xAxisRotation)
            batch.projectionMatrix.rotate(0f, 1f, 0f, yAxisRotation)

            super.draw(batch, parentAlpha)

            batch.projectionMatrix = projectionMatrix
        }
    }
}
