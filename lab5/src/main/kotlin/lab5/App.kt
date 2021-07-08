/*
4. Управление складом. Заполняются поля Товар и Количество. Система выводит промежуточную информацию о существующем
количестве товара и запрашивает подтверждение на добавление. При отсутствии такого товара на складе добавляется новая запись.
 */
package lab5
import java.sql.*

fun ResultSet.print() {
    val rsmd = this.getMetaData()
    val columnsNumber = rsmd.getColumnCount()
    println()
    for (i in 1..columnsNumber) {
        if (i > 1) print("   |   ")
        print("${rsmd.getColumnName(i)}")
    }
    println()
    while (this.next()) {
        for (i in 1..columnsNumber) {
            if (i > 1) print("   |   ")
            val columnValue = this.getString(i)
            print("$columnValue")
        }
        println()
    }
    println()
}

fun Connection.table_exists(table_name: String): Boolean {
    var rs = this.getMetaData().getTables(null, null, table_name, null)
    while (rs.next()) {
        val current_table_name = rs.getString("TABLE_NAME")
        if (current_table_name != null && current_table_name == table_name) {
            return true
        }
    }
    return false
}

fun init_table(conn: Connection) {
    var stmt = conn.createStatement()
    println("Creating table...")
    stmt!!.executeUpdate("DROP TABLE IF EXISTS products;")
    stmt!!.executeUpdate(
        "CREATE TABLE IF NOT EXISTS products" +
            "(id INTEGER PRIMARY KEY, name VARCHAR(20), amount INTEGER);"
    )
    println("Table successfully created...")
}

fun show_data(conn: Connection) {
    if (!conn.table_exists("products")) {
        println("No table \"products\" found... ")
        init_table(conn)
    }
    var stmt = conn.createStatement()
    println("Loading data from table...")
    var resultset = stmt.executeQuery("SELECT * FROM products;")
    println("Data successfully loaded...")
    resultset.print()
}

fun search_by_name(conn: Connection, product_name: String): Int? {
    println("Search $product_name in database...")
    var stmt = conn.createStatement()
    var resultset = stmt.executeQuery("SELECT id FROM products WHERE products.name == \"$product_name\";")
    if (resultset.next()) {
        return resultset.getInt("id")
    } else {
        return null
    }
}

fun user_shure(): Boolean {
    println()
    println("Are you shure to apply updates (Y/n)?")
    val input_continue = readLine()
    return when {
        (
            input_continue!!.isEmpty() ||
                input_continue.toLowerCase() == "yes" ||
                input_continue.toLowerCase() == "y"
            ) -> true
        (
            input_continue.toLowerCase() == "no" ||
                input_continue.toLowerCase() == "n"
            ) -> false

        else -> user_shure()
    }
}

fun insert_data(conn: Connection) {
    if (!conn.table_exists("products")) {
        println("No table \"products\" found... ")
        init_table(conn)
    }
    var stmt = conn.createStatement()
    println()
    println("Please, enter product name:")
    val product_name = readLine()!!.trim()
    val id = search_by_name(conn, product_name)
    if (id == null) {
        println("No products found in database...")
        println("Please, enter product amount:")
        val product_amount = readLine()!!.trim()
        if (product_amount.toInt() < 0) throw Exception("Error: Amount below zero!")
        if (!user_shure()) return
        stmt!!.executeUpdate("INSERT INTO products (name, amount) VALUES (\"$product_name\", $product_amount);")
        println("Product successfully insert...")
    } else {
        println("Product successfully found in database...")
        var resultset = stmt.executeQuery("SELECT amount FROM products WHERE products.id == $id;")
        val product_amount = resultset.getInt("amount")
        println("Founded: $product_name $product_amount")
        println("Please, enter new product amount:")
        val new_product_amount = readLine()!!.trim()
        if (new_product_amount.toInt() <0) throw Exception("Error: Amount below zero!")
        if (!user_shure()) return
        stmt!!.executeUpdate("UPDATE products SET amount=$new_product_amount WHERE products.id == $id;")
        println("Product amount successfully updated...")
    }
}

fun main(args: Array<String>) {
    if (args.size != 1) {
        println("Incorect argumnets.")
        println("You need pass task name as thirst argumnet:")
        println("  - \"init\" - create table")
        println("  - \"show\" - show data in table")
        println("  - \"insert\" - add data to table")
        return
    }
    val task_name = args[0]
    println("Program start with task \"$task_name\".")
    println("Connecting to database...")
    var conn: Connection
    try {
        conn = DriverManager.getConnection("jdbc:sqlite:./sampledb.db")
    } catch (ex: SQLException) {
        println("Connection failed. Error trace:")
        println(ex)
        ex.printStackTrace()
        return
    }
    println("Connected database successfully...")
    var stmt: Statement? = null
    var resultset: ResultSet? = null
    try {
        println("Start task \"$task_name\"...")
        when (task_name) {
            "init" -> init_table(conn)
            "show" -> show_data(conn)
            "insert" -> insert_data(conn)
            else -> println("Unknown task \"$task_name\" skipped...")
        }
        println("Task \"$task_name\" finished succefully...")
    } catch (ex: SQLException) {
        println("Task failed. Error trace:")
        println(ex)
        ex.printStackTrace()
    } catch (ex: Exception) {
        println("Task failed. Error trace:")
        println(ex)
    } finally {
        conn.close()
        println("Connection closing...")
    }
    println("Programm finished.")
}
