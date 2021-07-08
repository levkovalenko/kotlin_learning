
/*
Проверить, является ли заданное число n палиндромом: 
первая цифра равна последней, вторая -- предпоследней и так далее. 
15751 -- палиндром, 3653 -- нет.
*/

fun check_palindrom(numbers: List<Int>): Boolean {
    if (numbers.size <= 1) {
        return true
    }

    if (numbers.first() == numbers.last()) {
        return check_palindrom(numbers.subList(1, numbers.lastIndex))
    }
    return false
}

fun main(args: Array<String>) {
    println("Input number:")

    var (number,) = readLine()!!.split(' ').map(String::toInt)
    if (number < 0) {
        println("Error: number is  below zero!")
        return
    }

    var numbers = listOf<Int>()

    while (number > 0) {
        numbers += listOf(number % 10)
        number /= 10
    }

    println(check_palindrom(numbers))
}
