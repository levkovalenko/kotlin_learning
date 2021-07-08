/*
Для заданного списка пар "акция"-"стоимость" вернуть ассоциативный массив, 
содержащий для каждой акции ее усредненную стоимость. 
Например:  averageStockPrice(listOf("MSFT" to 100.0, "MSFT" to 200.0, "NFLX" to 40.0))      
-> mapOf("MSFT" to 150.0, "NFLX" to 40.0)
 */

fun pair_input(): List<Pair<String, Int>> {
    println("Please enter pair:")
    println("Action name:")
    val input_string = readLine()
    if (input_string!!.isEmpty()) {
        throw Exception("Error: empty action name!")
    }
    val action_name = input_string.toString()
    println("Action cost:")
    val action_cost = readLine()!!.toInt()
    if (action_cost < 0) {
        throw Exception("Error: action cost is bellow zero!")
    }
    fun is_input_continue(): List<Pair<String, Int>> {
        println("Do you want to continue input (Y/n)?")
        val input_continue = readLine()
        return when {
            (
                input_continue!!.isEmpty() ||
                    input_continue.toLowerCase() == "yes" ||
                    input_continue.toLowerCase() == "y"
                ) -> listOf(action_name to action_cost) + pair_input()

            (
                input_continue.toLowerCase() == "no" ||
                    input_continue.toLowerCase() == "n"
                ) -> listOf(action_name to action_cost)

            else -> is_input_continue()
        }
    }
    return is_input_continue()
}

fun main(args: Array<String>) {

    try {
        val pairlist = pair_input()
        val map_str2int = pairlist.groupBy(
            { it.first }, { it.second }
        ).map { (k, v) -> k to v.average() }

        println(map_str2int)
    } catch (e: Exception) {
        println(e)
    }
}
