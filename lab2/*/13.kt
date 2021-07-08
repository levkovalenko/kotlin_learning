/*
ревести натуральное число n > 0 в римскую систему. 
Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L, 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M. 
Например: 23 = XXIII, 44 = XLIV, 100 = C
 */

val arabic_simbols = listOf(1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000).reversed()
val roman_simbols = listOf("I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M").reversed()

fun arab2roam(number: Int): String {
    var inumber = number
    var result: String = ""
    for ((a, r) in arabic_simbols zip roman_simbols) {
        val iter_num = inumber / a
        inumber = inumber % a
        result += r.repeat(iter_num)
    }
    return result
}

fun main(args: Array<String>) {
    println("Input number:")
    var (number,) = readLine()!!.split(' ').map(String::toInt)

    println(arab2roam(number))
}
