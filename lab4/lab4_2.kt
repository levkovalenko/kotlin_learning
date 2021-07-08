/*
Напишите методы union(Set set1, Set set2)и intersect(Set set1, Set set2), 
реализующих операции объединения и пересечения двух множеств. 

Протестируйте работу этих методах на двух предварительно заполненных множествах.
(Вам понадобится написать вспомогательный метод, выводящий все элементы множества в консоль).
 */

fun Set<Any>.union(set: Set<Any>): Set<Any> {
    fun set_filter(b: Any): Boolean {
        for (a in this) {
            if (a.hashCode() == b.hashCode()) return false
        }
        return true
    }
    var res = mutableListOf<Any>()
    val filtred_set = set.filter { it -> set_filter(it) }
    for (it in this) {
        res.add(it)
    }
    for (it in filtred_set) {
        res.add(it)
    }
    return res.toSet()
}

fun Set<Any>.intersects(set: Set<Any>): Set<Any> {
    fun set_filter(b: Any): Boolean {
        for (a in this) {
            if (a.hashCode() == b.hashCode()) return true
        }
        return false
    }
    var res = mutableListOf<Any>()
    val filtred_set = set.filter { it -> set_filter(it) }
    for (it in filtred_set) {
        res.add(it)
    }
    return res.toSet()
}

fun Set<Any>.printlnSet() {
    print("( ")
    for (it in this) {
        print("$it ")
    }
    println(")")
}

fun main(args: Array<String>) {
    val a = setOf(1, 2, 3, 4, 5, 6, 7)
    val b = setOf(5, 6, 7, 8, 9, 10)
    a.union(b).printlnSet()
    a.intersects(b).printlnSet()
}
