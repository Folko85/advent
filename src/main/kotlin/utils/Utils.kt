package utils


object Utils {

    //        @JvmStatic
    fun rotateClockwise(mat: Array<CharArray>): Array<CharArray> {
        val rowSize = mat.size
        val colSize = mat[0].size
        val ret = Array(colSize) { CharArray(rowSize) }
        for (r in 0..<rowSize) {
            for (c in 0..<colSize) {
                ret[c][rowSize - 1 - r] = mat[r][c]
            }
        }
        return ret
    }

    fun getCopy(array: Array<Array<String>>): Array<Array<String>> {
        val copy = Array(array.size) { Array(array[0].size) { "" } }
        for (i in array.indices) {
            for (j in array[i].indices) {
                copy[i][j] = array[i][j]
            }
        }
        return copy
    }

}