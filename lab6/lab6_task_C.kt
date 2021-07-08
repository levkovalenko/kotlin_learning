/*
Необходимо разработать класс хранения битовых множеств максимум из 100 элементов. 
В разработанном классе реализуйте метод отрицание множества. В классе реализовать конструктор копирования. 
При реализации методов предполагается, что методы нельзя вызвать, если множество не инициализировалось. 
*/

class Bits(size: Int) {
    private var bits: String = buildZeroString(size)
    private val validator = "[0-1]+".toRegex()
    var size: Int = size
        private set

    private fun buildZeroString(bitSize: Int): String {
        return "".padStart(bitSize, '0')
    }

    constructor(values: String) : this(values.length) {
        if (!validator.matches(values)) {
            throw Exception("Error: Invalid bit string. It must contains only 0 and 1.")
        }
        bits = values
    }

    constructor(other: Bits) : this(other.bits)

    operator fun not(): Bits {
        return Bits(
            bits.map { it ->
                when (it) {
                    '0' -> '1'
                    '1' -> '0'
                    else -> throw Exception("Invalid character in bits: $it.")
                }
            }.joinToString("")
        )
    }

    override fun toString(): String = "<$bits>"
}

class BitSets(values: Array<Bits>) {
    var mas: Array<Bits> = Array<Bits>(100) { Bits(1) }

    constructor(values: List<Bits>) : this(values.toTypedArray())
    constructor(values: Array<String>) : this(values.map { it -> Bits(it) })
    // constructor(vararg values: Bits): this(values.asList().toTypedArray())
    constructor(others: BitSets) : this(others.mas)

    init {
        mas = values
    }

    fun notIndex(index: Int) {
        mas[index] = !mas[index]
    }

    override fun toString(): String = mas.joinToString(", ")
}

fun main() {
    var bits = Bits("1000101110101")
    println(bits)

    var notBits = !bits
    println(notBits)

    var newBits = Bits(notBits)
    println(newBits)

    var bitset = BitSets(listOf(bits, notBits, newBits))
    println(bitset)

    bitset.notIndex(2)
    println(bitset)
}
