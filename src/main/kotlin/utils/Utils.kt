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

}