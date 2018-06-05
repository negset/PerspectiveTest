package org.neggly.perspective

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.math.Affine2
import com.badlogic.gdx.math.Matrix4
import com.badlogic.gdx.scenes.scene2d.Group

class PerspectiveGroup : Group()
{
    private val transformMatrix = Matrix4()
    private val projectionMatrix = Matrix4()

    private val worldTransform = Affine2()

    var xAxisRotation = 60f
    var yAxisRotation = 0f
    private var perspective = 1f / 1500f

    override fun draw(batch: Batch?, parentAlpha: Float)
    {
        if (batch != null)
        {
            transformMatrix.set(batch.transformMatrix)
            projectionMatrix.set(batch.projectionMatrix)
            batch.end()

            val scaledOriginX = originX * scaleX
            val scaledOriginY = originY * scaleY

            worldTransform.setToTrnRotScl(scaledOriginX, scaledOriginY, rotation, scaleX, scaleY)
            if (originX != 0f || originY != 0f) worldTransform.translate(-originX, -originY)

            batch.transformMatrix.set(worldTransform)

            batch.projectionMatrix.translate(x + (originX - scaledOriginX), y + (originY - scaledOriginY), 0f)
            batch.projectionMatrix.`val`[Matrix4.M32] = perspective
            batch.projectionMatrix.rotate(1f, 0f, 0f, xAxisRotation)
            batch.projectionMatrix.rotate(0f, 1f, 0f, yAxisRotation)

            batch.begin()

            drawChildren(batch, parentAlpha)

            batch.transformMatrix = transformMatrix
            batch.projectionMatrix = projectionMatrix
        }
    }
}
