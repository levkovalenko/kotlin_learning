/*
Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида  "706 - % 717 % 703".  
В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;  
число соответствует удачному прыжку, - пропущенной попытке, % заступу.  

Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).  
При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */

fun input_validator(input_string: String?): Int {

    if (input_string == null || input_string!!.trim().isEmpty()) return -1
    val validator = "[0-9]+".toRegex()
    try {
        val words = input_string!!.split(" ").map { it ->
            when {
                it == "-" -> -1
                it == "%" -> -1
                validator.matches(it) -> it.toInt()

                else -> throw Exception("incorrect symbol!")
            }
        }
        return words.fold(-1) { x, y -> if (x > y) x else y }
    } catch (e: Exception) {
        return -1
    }
}

fun main(args: Array<String>) {
    println("Enter result string:")
    val input_string = readLine()
    println(input_validator(input_string))
}
