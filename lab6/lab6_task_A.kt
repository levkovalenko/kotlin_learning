/* 
 Разработать класс рациональное число и реализовать метод инициализации числа. 
 В классе реализовать конструктор по умолчанию. 
 При реализации методов предполагается, что знаменатель не может принимать нулевое значение. 
 */

class Rational(numerator: Int, denominator: Int) {
    private var num: Int = 0
    private var denom: Int = 0
    companion object {
        private const val BASE = 1000000000
    }
    constructor(double: Double) : this((double * BASE).toInt(), BASE)

    init {
        if (denominator == 0) {
            throw Exception("Error: Denominator equals zero!")
        }
        val nor = gcf(numerator, denominator)
        num = numerator / nor
        denom = denominator / nor
    }

    private fun gcf(a: Int, b: Int): Int {
        return if (b == 0) a else gcf(b, a % b)
    }

    override fun toString(): String { return "$num / $denom" }

    operator fun plus(r: Rational): Rational = Rational(num * r.denom + r.num * denom, denom * r.denom)
    operator fun minus(r: Rational): Rational = Rational(num * r.denom - r.num * denom, denom * r.denom)

    operator fun plus(r: Double): Rational = this + Rational(r)
    operator fun minus(r: Double): Rational = this - Rational(r)

    operator fun times(r: Rational): Rational = Rational(num * r.num, denom * r.denom)
    operator fun times(r: Double): Rational = this * Rational(r)

    operator fun div(r: Rational): Rational = Rational(num * r.denom, denom * r.num)
    operator fun div(r: Double): Rational = this / Rational(r)
}

fun main() {
    for ((a, b) in listOf<Pair<Int, Int>>(1 to 2, 10 to 100, 0 to 20, 5 to 0, 16 to 6)) {
        try {
            var num = Rational(a, b)
            println(num)
        } catch (e: Exception) {
            println(e.toString())
        }
    }

    for (a in listOf<Double>(0.023, 0.98, 0.12008)) {
        try {
            var num = Rational(a)
            println(num)
        } catch (e: Exception) {
            println(e.toString())
        }
    }

    println(Rational(10, 53) * 0.2)

    println(Rational(10, 53) + 0.2)

    println(Rational(10, 53) - 0.2)

    println(Rational(10, 53) / 0.2)
}
