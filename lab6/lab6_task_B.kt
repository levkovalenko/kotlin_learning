/*
Разработайте класс вектор из 10 элементов и реализуйте метод скалярного произведения векторов. 
В классе реализовать инициализирующий конструктор. 
При реализации методов предполагается, что методы нельзя вызвать, 
если значение текущего вектора не инициализировались. 
*/

class Vector10(values: List<Int>) {
    var vector: List<Int>
    constructor(values: Array<Int>) : this(values.toList())
    constructor(values: ArrayList<Int>) : this(values.toList())
    constructor(values: Sequence<Int>) : this(values.toList())
    constructor(vararg values: Int) : this(values.asList())

    init {
        if (values.size != 10) {
            throw Exception("Error: Invalid size of vector: $values.size.")
        }
        vector = values
    }

    operator fun times(a: Vector10): Int {
        if (vector.isEmpty()) {
            throw Exception("Error: Vector wasn't inited.")
        }
        return (vector zip a.vector).map { (x, y) -> x * y }.fold(0) { x, y -> x + y }
    }

    override fun toString(): String {
        if (vector.isEmpty()) {
            throw Exception("Error: Vector wasn't inited.")
        }
        return vector.joinToString(", ")
    }
}

fun main() {
    var a = Vector10(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
    var b = Vector10(sequenceOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
    var c = Vector10(arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
    var d = Vector10(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    println(c * a)
    println(d)
}
