/*
Записать заданное натуральное число 1..999999 прописью по-русски. 
Например, 375 = "триста семьдесят пять", 
23964 = "двадцать три тысячи девятьсот шестьдесят четыре" 
*/

val dig1 = listOf(
    listOf("один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять"),
    listOf("одна", "две")
)
val dig10 = listOf(
    "десять", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать",
    "пятнадцать", "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать"
)
val dig20 = listOf(
    "двадцать", "тридцать", "сорок", "пятьдесят",
    "шестьдесят", "семьдесят", "восемьдесят", "девяносто"
)
val dig100 = listOf(
    "сто", "двести", "триста", "четыреста", "пятьсот",
    "шестьсот", "семьсот", "восемьсот", "девятьсот"
)
val leword = listOf(listOf("", "", "", "0"), listOf("тысяча", "тысячи", "тысяч", "1"))

fun num2words(num: Int, level: Int): String {
    var words: String = ""
    if (num == 0) {
        words += "ноль"
    }
    var sex = leword[level][3].indexOf("1") + 1
    var h = num % 1000
    var d = h / 100
    if (d> 0) {
        words += dig100[d - 1] + " "
    }
    var n = h % 100
    d = n / 10
    n = n % 10
    when (d) {
        0 -> words += ""
        1 -> words += dig10[n] + " "
        else -> words += dig20[d - 2] + " "
    }
    if (d == 1) {
        n = 0
    }
    when (n) {
        0 -> words += ""
        1, 2 -> words += dig1[sex][n - 1] + " "
        else -> words += dig1[0][n - 1] + " "
    }
    when (n) {
        1 -> words += leword[level][0]
        in 2..4 -> words += leword[level][1]
        else -> words += leword[level][2]
    }
    var nextnum = num / 1000
    if (nextnum> 0) {
        return (num2words(nextnum, level + 1) + " " + words).trim()
    } else {
        return words.trim()
    }
}

fun main(args: Array<String>) {
    println("Input number:")
    var (number,) = readLine()!!.split(' ').map(String::toInt)
    if (number !in 1..999999) {
        println("Error: invalid number.")
        return
    }

    println(num2words(number, 0))
}
