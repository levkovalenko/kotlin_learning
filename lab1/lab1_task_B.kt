data class ChessField(val vert: Int, val hor: String)

/*
Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали). 
Определить, не находится ли король под боем, а если есть угроза, то от кого именно. 
Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
*/

fun chess_field_input(figure_name: String): ChessField {
    println("Please, enter $figure_name field.")
    println("Horizontal coordinate(a,b,c,d,e,f,g,h):")
    // Horizontal coordinate of figure.
    val horizontal = readLine().toString()
    if (horizontal < "a" || horizontal > "h") {
        throw Exception("Incorrect horisontal coordinate of $figure_name")
    }

    println("Vertical coordinate(1,2,3,4,5,6,7,8):")
    // Vertical coordinate of figure.
    val (vertical,) = readLine()!!.split(' ').map(String::toInt)
    if (vertical < 1 || vertical > 8) {
        throw Exception("Incorrect vertical coordinate of $figure_name")
    }
    println()

    return ChessField(vertical, horizontal)
}

fun main(args: Array<String>) {
    try {
        // Read coordinates of the King.
        val king_position = chess_field_input("King")

        // Read coordinates of Rook 1.
        val rook1_position = chess_field_input("Rook1")

        // Read coordinates of Rook 2.
        val rook2_position = chess_field_input("Rook2")

        var answer = 0

        // Does the first rook beat the King.
        if (king_position.hor == rook1_position.hor || king_position.vert == rook1_position.vert) {
            answer += 1
        }
        // Does the second rook beat the King.
        if (king_position.hor == rook2_position.hor || king_position.vert == rook2_position.vert) {
            answer += 2
        }

        println(answer)
    } catch (e: Exception) {
        println(e)
    }
}
