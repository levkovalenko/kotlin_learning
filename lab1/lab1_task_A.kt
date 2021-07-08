import kotlin.math.pow
import kotlin.math.sqrt

/*
Найти длину отрезка, соединяющего точки на плоскости с координатами (x1, y1) и (x2, y2).Например, расстояние между (3, 0) и (0, 4) равно 5
*/

fun calculate_distance(x1: Double, y1: Double, x2: Double, y2: Double): Double {
    val (x_dist, y_dist) = listOf(x1 - x2, y1 - y2)
    return sqrt(x_dist.pow(2) + y_dist.pow(2))
}

fun main(args: Array<String>) {
    println("Please, enter x1 y1 x2 y2:")

    // Read coordinates from line. User enter coordinates in variable x1, y1, x2, y2.
    val (x1, y1, x2, y2) = readLine()!!.split(' ').map(String::toDouble)

    println(calculate_distance(x1, y1, x2, y2))
}
