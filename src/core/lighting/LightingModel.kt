package core.lighting

import core.models.math.Vec4
import core.models.math.matrix.Mat4
import java.awt.Color
import kotlin.math.max
import kotlin.math.pow

class LightingModel(
    var lightDirection: Vec4? = null,
    var lightColor: Vec4? = null,
) {
    private val ambientColor = 0.2f
    private val diffuseColor = 0.8f
    private val specularColor = 0.4f
    private val shininess = 30.0
    private val cameraPos = Vec4(0f, 0f, -1f, 1f)

    fun getCameraPos(): Vec4 = cameraPos
    fun getColor(): Vec4 = lightColor!!

    fun calculateBlinnPhongColor(v1: Vec4, v2: Vec4, v3: Vec4, viewMatrix: Mat4): Color {
        val ambient = Vec4(
            ambientColor * lightColor!!.x,
            ambientColor * lightColor!!.y,
            ambientColor * lightColor!!.z,
            1f
        )

        val centroid = calculateCentroid(v1, v2, v3)

//        val lightPositionInCameraSpace = viewMatrix.multiply(lightDirection!!)
//        val lightDir = lightPositionInCameraSpace.subtract(centroid).normalize()

        val lightDir = lightDirection!!.subtract(centroid).normalize()

        val viewDir = cameraPos.subtract(centroid).normalize()

        val halfwayDir = lightDir.add(viewDir).normalize()

        val normal = viewMatrix.multiply(calculateNormal(v1, v2, v3)).normalize()

        val diffuseIntensity = max(normal.dot(lightDir), 0f)

        val diffuse = Vec4(
            diffuseColor * lightColor!!.x * diffuseIntensity,
            diffuseColor * lightColor!!.y * diffuseIntensity,
            diffuseColor * lightColor!!.z * diffuseIntensity,
            1f
        )

        val specularIntensity = max(normal.dot(halfwayDir).toDouble(), 0.0).pow(shininess).toFloat()
        val specular = Vec4(
            specularColor * lightColor!!.x * specularIntensity,
            specularColor * lightColor!!.y * specularIntensity,
            specularColor * lightColor!!.z * specularIntensity,
            1f
        )

        val r = (ambient.x + diffuse.x + specular.x).toColorInt()
        val g = (ambient.y + diffuse.y + specular.y).toColorInt()
        val b = (ambient.z + diffuse.z + specular.z).toColorInt()

        return Color(r, g, b)
    }

    private fun calculateNormal(v1: Vec4, v2: Vec4, v3: Vec4): Vec4 {
        val a = v2.subtract(v1)
        val b = v3.subtract(v1)
        return a.cross(b).normalize()
    }

    private fun calculateCentroid(v1: Vec4, v2: Vec4, v3: Vec4): Vec4 {
        val x = (v1.x + v2.x + v3.x) / 3f
        val y = (v1.y + v2.y + v3.y) / 3f
        val z = (v1.z + v2.z + v3.z) / 3f

        return Vec4(x, y, z, 1f)
    }

    private fun Float.toColorInt(): Int = (this * 255).toInt().coerceIn(0, 255)
}