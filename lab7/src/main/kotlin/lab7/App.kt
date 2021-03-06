/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package lab7

import freemarker.template.Configuration
import freemarker.template.Template
import freemarker.template.TemplateExceptionHandler
import java.util.Locale
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.math.PI
import kotlin.math.round

data class Figure(val figure: String, val volume: Double)

fun Double.round(decimals: Int): Double {
    var multiplier: Double = 1.0
    repeat(decimals) { multiplier *= 10 }
    return round(this * multiplier) / multiplier
}

@WebServlet("/hello")
open class HomeServl : HttpServlet() {
    override fun doGet(req: HttpServletRequest, res: HttpServletResponse) {
        get_template(res, mapOf("test" to "test"))
    }

    override fun doPost(req: HttpServletRequest, res: HttpServletResponse) {
        req.setCharacterEncoding("UTF-8")
        val figure_type = req.getParameter("figure_type")
        val precision = req.getParameter("precision")!!.toInt()
        var volume: Double = 0.0
        when (figure_type) {
            "паралелепипед" -> {
                val a = req.getParameter("sidea")!!.toDouble()
                val b = req.getParameter("sideb")!!.toDouble()
                val c = req.getParameter("sidec")!!.toDouble()
                volume = a * b * c
            }
            "тетраэдр" -> {
                val s = req.getParameter("square")!!.toDouble()
                val h = req.getParameter("height")!!.toDouble()
                volume = s * h / 3
            }
            "куб" -> {
                val a = req.getParameter("side")!!.toDouble()
                volume = a * a * a
            }
            "шар" -> {
                val r = req.getParameter("radius")!!.toDouble()
                volume = r * r * r * PI * 4 / 3
            }
            "сфера" -> {
                val r1 = req.getParameter("radius1")!!.toDouble()
                val r2 = req.getParameter("radius2")!!.toDouble()
                volume = r1 * r1 * r1 * PI * 4 / 3 - r2 * r2 * r2 * PI * 4 / 3
            }
        }
        get_template(res, mapOf("figure" to Figure(figure_type, volume.round(precision))))
    }

    fun get_template(res: HttpServletResponse, template: Any) {
        res.setCharacterEncoding("UTF-8")
        var freemarkerConfiguration: Configuration = Configuration(Configuration.VERSION_2_3_23)
        freemarkerConfiguration.setClassForTemplateLoading(this.javaClass, "/")
		freemarkerConfiguration.setEncoding(Locale("ru"), "UTF-8")
		freemarkerConfiguration.templateExceptionHandler = TemplateExceptionHandler.HTML_DEBUG_HANDLER
        freemarkerConfiguration.logTemplateExceptions = false

        var carListTemplate: Template = freemarkerConfiguration.getTemplate("templates/figures.ftl", Locale("ru"), "UTF-8")
        carListTemplate.process(template, res.writer)
        res.writer.close()
    }
}
