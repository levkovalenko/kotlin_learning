

fun main(args: Array<String>) {
    val cost_1_ticket: Int = 15
    val cost_10_ticket: Int = 125
    val cost_60_ticket: Int = 440
    var (n,) = readLine()!!.split(' ').map(String::toInt)
    var use_60_ticket: Int = 0
    var use_10_ticket: Int = 0
    var use_1_ticket: Int = 0

    if (n % 60 <= cost_60_ticket / cost_10_ticket * 10 + (cost_60_ticket % cost_10_ticket) / cost_1_ticket) {
        use_60_ticket = n / 60
        n = n % 60
        if (n % 10 <= cost_10_ticket / cost_1_ticket) {
            use_10_ticket = n / 10
            use_1_ticket = n % 10
        } else {
            use_10_ticket = n / 10 + 1
            use_1_ticket = 0
        }
    } else {
        use_60_ticket = n / 60 + 1
        use_10_ticket = 0
        use_1_ticket = 0
    }
    println(use_1_ticket)
    println(use_10_ticket)
    println(use_60_ticket)
}
