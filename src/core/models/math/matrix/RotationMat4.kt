package core.models.math.matrix

class RotationMat4(
    x: AxisXRotationMat4,
    y: AxisYRotationMat4,
    z: AxisZRotationMat4,
) : Mat4() {
    init {
        this.setValues(z.multiply(x).multiply(y).getValues())
    }
}