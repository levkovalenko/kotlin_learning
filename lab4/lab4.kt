/*
Напишите класс Student, предоставляющий информацию об имени студента методом getName()
и о его курсе методом getCourse().Напишите метод printStudents(List students, int course), 
который получает список студентов и номер курса и печатает в консоль имена тех студентов из списка, 
которые обучаются на данном курсе. Для обхода списка в этом методе используйте итератор. 

Протестируйте ваш метод (для этого предварительно придется создать десяток объектов класса Student
и поместить их в список)
 */

class Student(st_name: String, st_course: Int) {
    private var name: String
    private var course: Int

    init {
        name = st_name
        course = st_course
    }

    fun getCourse(): Int { return course }
    fun getName(): String { return name }
    override fun toString(): String { return "<$name; $course>" }
}

fun printStudents(students: List<Student>, course: Int) {
    val it = students.iterator()
    while (it.hasNext()) {
        val student = it.next()
        if (student.getCourse() == course) println(student)
    }
}

fun main(args: Array<String>) {
    if (args.size != 1) {
        println("Incorect argumnets")
        return
    }
    val course = args[0].toInt()
    val students = listOf(
        Student("a", 1),
        Student("b", 2),
        Student("c", 3),
        Student("d", 4),
        Student("e", 1),
        Student("f", 2),
        Student("g", 3),
        Student("h", 4),
        Student("j", 1),
        Student("i", 2),
        Student("k", 3),
        Student("l", 4),
    )
    printStudents(students, course)
}
