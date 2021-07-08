/*
Найти скалярное произведение двух векторов равной размерности, представленные в виде списков a и b. 
Скалярное произведение считать по формуле: C = a1b1 + a2b2 + ... + aNbN. 
Произведение пустых векторов считать равным 0.0.
 */

fun input_vector(vector_name: String): List<Double> {
    println("Please, enter $vector_name coordinates and radius")
    println("Example: x1 x2 x3 x4...")
    val input_string = readLine()
    if (input_string!!.isEmpty()) {
        return listOf<Double>()
    }
    val coordinates = input_string!!.trim().split(' ').map(String::toDouble)

    return coordinates
}

fun main(args: Array<String>) {
    try {
        val vector1 = input_vector("vector 1")

        val vector2 = input_vector("vector 2")

        if (vector1.size != vector2.size) {
            println("Error: Mismatch length between lists!")
            return
        }

        var result: Double = 0.0

        for ((a, b) in vector1 zip vector2) {
            result += a * b
        }

        println(result)
    } catch (e: Exception) {
        println(e)
    }
}
