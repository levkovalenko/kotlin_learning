
import kotlin.math.abs
import kotlin.math.sign
/*
Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37. 
Результат перевода вернуть в виде строки, 
цифры более 9 представлять латинскими строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее. 
Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c 
Использовать функции стандартной библиотеки, напрямую и полностьюрешающие данную задачу 
(например, n.toString(base) и подобные), запрещается.
 */
fun num2char(x: Int): Char {
    when (x) {
        in 0..9 -> return "$x"[0]
        else -> return ('a'.toInt() + x - 10).toChar()
    }
}

fun main(args: Array<String>) {
    println("Input number:")
    var (number,) = readLine()!!.split(' ').map(String::toInt)

    println("Input base:")
    val (base,) = readLine()!!.split(' ').map(String::toInt)
    if (base !in 2..36) {
        println("Error: invalid base!")
        return
    }
    val number_sgin = sign(number.toDouble())
    number = abs(number)

    var new_number: List<Char> = listOf<Char>()
    while (number != 0) {
        new_number += listOf(num2char(number % base))
        number /= base
    }
    if (number_sgin == -1.0) {
        new_number += listOf('-')
    }
    new_number = new_number.reversed()

    println(new_number.joinToString(separator = ""))
}
