/*
Для заданного списка пар "акция"-"стоимость" вернуть ассоциативный массив, 
содержащий для каждой акции ее усредненную стоимость. 
Например:  averageStockPrice(listOf("MSFT" to 100.0, "MSFT" to 200.0, "NFLX" to 40.0))      
-> mapOf("MSFT" to 150.0, "NFLX" to 40.0)
 */

tailrec fun treasure_input(): Map<String, Pair<Int, Int>> {
    println("Please enter treasure:")
    println("Treasure name:")
    val input_string = readLine()
    if (input_string!!.isEmpty() || input_string == null) {
        throw Exception("Error: empty treasure name!")
    }
    val treasure_name = input_string!!.toString()
    println("Treasure cost:")
    val treasure_cost = readLine()!!.toInt()
    if (treasure_cost < 0) {
        throw Exception("Error: treasure cost is bellow zero!")
    }
    println("Treasure weight:")
    val treasure_weight = readLine()!!.toInt()
    if (treasure_weight < 0) {
        throw Exception("Error: treasure weight is bellow zero!")
    }
    tailrec fun is_input_continue(): Map<String, Pair<Int, Int>> {
        println("Do you want to continue input (Y/n)?")
        val input_continue = readLine()
        return when {
            (
                input_continue!!.isEmpty() ||
                    input_continue!!.toLowerCase() == "yes" ||
                    input_continue!!.toLowerCase() == "y"
                ) -> mapOf(treasure_name to (treasure_weight to treasure_cost)) + treasure_input()

            (
                input_continue!!.toLowerCase() == "no" ||
                    input_continue!!.toLowerCase() == "n"
                ) -> mapOf(treasure_name to (treasure_weight to treasure_cost))

            else -> is_input_continue()
        }
    }
    return is_input_continue()
}

fun List<Pair<String, Pair<Int, Int>>>.getWeight(i: Int): Int {
    return this.map { it -> it.second.first }[i]
}

fun List<Pair<String, Pair<Int, Int>>>.getCost(i: Int): Int {
    return this.map { it -> it.second.second }[i]
}

fun List<Pair<String, Pair<Int, Int>>>.backPacking(backpack_size: Int): Set<String> {
    val result = mutableSetOf<String>()
    var price = Array<Array<Int>>(this.size + 1) { Array<Int>(backpack_size + 1) { 0 } }
    this.forEachIndexed { i, it ->
        var (name, value) = it
        var (w, c) = value
        for (j in 0..backpack_size) {
            if (j >= w) {
                price[i + 1][j] = if (price[i][j] > price[i][j - w] + c) price[i][j] else price[i][j - w] + c
            } else {
                price[i + 1][j] = price[i][j]
            }
        }
    }
    var temp = backpack_size
    var i = this.size
    while (i > 0) {
        if (price[i][temp] != price[i - 1][temp]) {
            result.add(this[i - 1].first)
            temp -= this[i - 1].second.first
        }
        i--
    }
    return result
}

fun main(args: Array<String>) {

    try {
        val treasures = treasure_input()
        println("Enter backpack size:")
        val backpack_size = readLine()!!.toInt()
        println(treasures)
        println(treasures.toList().backPacking(backpack_size))
    } catch (e: Exception) {
        println(e)
    }
}
