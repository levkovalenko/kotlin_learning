/*
Для заданного списка неотрицательных чисел и числа определить,  
есть ли в списке пара чисел таких, что их сумма равна заданному числу.  
Если да, верните их индексы в виде Pair<Int, Int>;  если нет, верните пару Pair(-1, -1).  
Индексы в результате должны следовать в порядке (меньший, больший).  

Постарайтесь сделать ваше решение как можно более эффективным,  
используя то, что вы узнали в данном уроке.  Например:    
findSumOfTwo(listOf(1, 2, 3), 4) -> Pair(0, 2)    
findSumOfTwo(listOf(1, 2, 3), 6) -> Pair(-1, -1)
 */

fun Pair<Int, Int>.add(a: Pair<Int, Int>): Pair<Int, Int> {
    if (this.first >= 0 && this.second >= 0) return Pair(this.first + a.first, this.second + a.second)
    return this
}

tailrec fun List<Int>.findSumOfTwo(pair_sum: Int): Pair<Int, Int> {
    if (this.size <= 1) return Pair(-1, -1)
    val sum_fisrt_last = this.first() + this.last()
    return when {
        sum_fisrt_last > pair_sum -> this.subList(0, this.lastIndex).findSumOfTwo(pair_sum)
        sum_fisrt_last < pair_sum -> this.subList(1, this.size).findSumOfTwo(pair_sum).add(Pair(1, 1))
        sum_fisrt_last == pair_sum -> Pair(0, this.lastIndex)
        else -> Pair(-1, -1)
    }
}

fun main(args: Array<String>) {
    println("Enter list of numbers")
    val input_string = readLine()
    if (input_string == null || input_string!!.trim().isEmpty()) {
        println("Error: emptu string!")
        return
    }
    println("Enter pair sum")
    val pair_sum = readLine()!!.toInt()

    var numbers = input_string!!.trim().split(" ").map(String::toInt)

    println(numbers.sorted().findSumOfTwo(pair_sum))
}
