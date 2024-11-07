package core.models

import core.utils.ObjLoader

data class ObjModel(
    var path: String = "./assets/icosphere.obj",
    var indexedFace: IndexedFace? = null,
) {
    companion object {
        private val objModel = ObjModel()
        fun getInstance(): ObjModel = objModel
        fun regenerateIndexedFace() { objModel.indexedFace = ObjLoader.parseObj(objModel.path) }
    }

    init {
        indexedFace = ObjLoader.parseObj(path)
    }
}
