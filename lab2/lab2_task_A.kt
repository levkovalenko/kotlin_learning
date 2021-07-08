import kotlin.math.pow
import kotlin.math.sqrt

data class Circle(val x: Double, val y: Double, val r: Double)

/*
Проверить, лежит ли окружность с центром в (x1, y1) и радиусом r1
целиком внутри окружности с центром в (x2, y2) и радиусом r2. 
Вернуть true, если утверждение верно
 */

fun input_circle(circle_name: String): Circle {
    println("Please, enter $circle_name coordinates and radius")
    println("Example: x y r")
    val (x, y, r) = readLine()!!.split(' ').map(String::toDouble)
    if (r < 0) {
        throw Exception("Invalid radius of $circle_name")
    }
    println()

    return Circle(x, y, r)
}

fun circle_intersection(c1: Circle, c2: Circle): Boolean {
    val center_distance = sqrt((c1.x - c2.x).pow(2) + (c1.y - c2.y).pow(2))

    return center_distance + c1.r <= c2.r
}

fun main(args: Array<String>) {
    try {
        // Read coordinates of the circle 1.
        val circle1 = input_circle("circle 1")
        // Read coordinates of the circle 2.
        val circle2 = input_circle("circle 2")

        println(circle_intersection(circle1, circle2))
    } catch (e: Exception) {
        println(e)
    }
}
