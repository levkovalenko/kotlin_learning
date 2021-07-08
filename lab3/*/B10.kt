/*
Имеется специальное устройство, представляющее собой  конвейер из cells ячеек
(нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
Строка commands содержит последовательность команд, выполняемых данным устройством,
например +>+>+>+>+  Каждая команда кодируется одним специальным символом:
> - сдвиг датчика вправо на 1 ячейку;
< - сдвиг датчика влево на 1 ячейку;
+ - увеличение значения в ячейке под датчиком на 1 ед.;
- - уменьшение значения в ячейке под датчиком на 1 ед.;
[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
(комбинация [] имитирует цикл)   пробел - пустая команда
Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
После выполнения limit команд или всех команд из commands следует прекратить
выполнение последовательности команд.
*/
/*
Учитываются все команды, в том числе несостоявшиеся переходы
("[" при значении под датчиком не равном 0 и "]" при  значении под датчиком равном 0) и пробелы.
 Вернуть список размера cells, содержащий элементы ячеек устройства после
 завершения выполнения последовательности.  Например, для 10 ячеек и командной строки
 +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 То же исключение формируется, если у символов [ ] не оказывается пары.
 Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 то есть если в программе присутствует некорректный символ или непарная скобка, т
 о должно быть выброшено IllegalArgumentException. IllegalArgumentException должен
 бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 */

class IllegalStateException(message: String) : Exception(message)

class StackMachine(stack_size: Int, stack_limit: Int = 1000) {
    var size: Int
    var index: Int
    var stack: Array<Int>
    var limit: Int
    val valid_chars: Array<Char> = arrayOf('<', '>', ' ', '+', '-', '[', ']')
    var debug = false

    init {
        size = stack_size
        index = size / 2
        stack = Array(stack_size) { 0 }
        limit = stack_limit
    }

    fun debug() {
        debug = !debug
    }

    fun checkValidChar(commands: String) {
        for (c in commands) {
            if (c !in valid_chars) {
                throw IllegalArgumentException("Illegal character: $c")
            }
        }
    }

    fun checkBrackets(commands: String) {
        var ch: Int = 0

        for (c in commands) {
            if (c == '[') ch++
            if (c == ']') {
                ch--
                if (ch < 0) {
                    throw IllegalArgumentException("Illegal brackets combination: Too many ]")
                }
            }
        }
        if (ch != 0) throw IllegalArgumentException("Illegal brackets combination: Too many [")
    }

    fun run(commands: String): Array<Int> {
        checkBrackets(commands)
        checkValidChar(commands)
        algorythm(commands)
        return stack
    }

    fun algorythm(commands: String) {
        var startCycle: Array<Int> = arrayOf()
        var intro_plus: Int = 0
        var intro_minus: Int = 0
        var count_operation: Int = 0
        var i: Int = 0

        while (i < (commands.length)) {
            if (debug) println(stack.joinToString(" "))
            if (debug) println(commands[i])
            if (count_operation < limit) {
                when (commands[i]) {
                    ' ' -> {
                        count_operation++
                    }
                    '<' -> {
                        index--
                        if (index < 0) {
                            throw IllegalStateException("Illegal state: index < 0")
                        }
                        count_operation++
                    }
                    '>' -> {
                        index++
                        if (index > size - 1) {
                            throw IllegalStateException("Illegal state: index > $size")
                        }
                        count_operation++
                    }
                    '+' -> {
                        stack[index]++
                        count_operation++
                    }
                    '-' -> {
                        stack[index]--
                        count_operation++
                    }
                    '[' -> {
                        count_operation++
                        if (stack[index] == 0) {
                            intro_plus++
                            count_operation++
                            do {
                                i++
                                if (i > commands.length - 1) {
                                    throw IllegalStateException("Illegal state: out of comand size")
                                }
                                if (commands[i] == '[') intro_plus++
                                if (commands[i] == ']') intro_plus--
                                count_operation++
                            } while ((intro_plus != 0) and (count_operation < limit))
                            intro_plus = 0
                        }
                    }
                    ']' -> {
                        count_operation++
                        if (stack[index] != 0) {
                            intro_minus++
                            count_operation++
                            do {
                                i--
                                if (i < 0) {
                                    throw IllegalStateException("Illegal state: out of comand size")
                                }
                                if (commands[i] == ']') intro_minus++
                                if (commands[i] == '[') intro_minus--
                                count_operation++
                            } while (!((commands[i] != '[') and (intro_minus == 0)) and (count_operation < limit))
                            intro_minus = 0
                        }
                    }
                }
            } else break
            i++
        }
    }
}

fun main() {

    val init_args = arrayOf<Pair<String, Int>>(
        "+>+>+>+>+" to 10,
        "+>+>+>+<+<+<+<+<+<+<<++" to 20,
        ">+[>[++++++++++>]<<<<<<<<<<<+[+>-]+<+]" to 30,
        "[]]" to 10,
        "+[ ]" to 10,
        "+[a]" to 10,
        "+[>+]" to 10,
    )

    for ((comand, stack_size) in init_args) {
        val machine = StackMachine(stack_size)
        // machine.debug()
        var res: Array<Int>
        println("result:")
        try {
            res = machine.run(comand)
            println(res.joinToString(" "))
        } catch (e: IllegalArgumentException) {
            println(e)
        } catch (e: IllegalStateException) {
            println(e)
        }
        println()
    }
}
