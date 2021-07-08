/*
Перевести число, представленное цифровой строкой str, из системы счисления с основанием base в десятичную. 
Цифры более 9 представляются латинскими строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее. 
Например: str = "13c", base = 14 -> 250 
Использовать функции стандартной библиотеки, напрямую и полностьюрешающие данную задачу (например, str.toInt(base)), запрещается.
 */

fun char2num(x: Char): Int {
    when (x) {
        in '0'..'9' -> return x.toInt() - '0'.toInt()
        else -> return 10 + x.toInt() - 'a'.toInt()
    }
}

fun main(args: Array<String>) {
    println("Input number:")
    var number = readLine().toString()

    println("Input base:")
    val (base,) = readLine()!!.split(' ').map(String::toInt)
    if (base !in 2..36) {
        println("Error: invalid base!")
        return
    }
    val number_sgin = number[0]
    if (number_sgin == '-') {
        number = number.substring(1)
    }

    var new_number: Int = char2num(number[0])
    number = number.substring(1)

    for (num in number.toCharArray()) {
        new_number = new_number * base + char2num(num)
    }
    if (number_sgin == '-') {
        new_number = -new_number
    }

    println(new_number)
}
