import android.os.Build
import androidx.annotation.RequiresApi
import org.jsoup.Jsoup
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

@RequiresApi(Build.VERSION_CODES.O)
fun main() {

    val ege_number_id = arrayOf(
        258, 289, 290, 202, 286, 337, 287, 206, 352, 344,
        343, 346, 302, 303, 297, 307, 305, 306, 309, 310,
        312, 313, 314, 321, 322, 323
    )

    var group = 0

    for(id in ege_number_id) {
        val rootDir = Paths.get("app/src/main/assets/answers")
        group++
        val dir = Files.createTempDirectory(rootDir, "rus_${group}_")

        val doc =
            Jsoup.connect("https://rus-ege.sdamgia.ru/test?filter=all&category_id=${id}&print=true&ans=true")
                .get()
        val elems = doc.getElementsByAttributeValue("class", "answer")
        println(elems.size)
        for(i in 0 until elems.size) {
            val File_name = "$i.html"
            var text = elems[i].toString()
            text = text.substringAfter("Ответ: ")
            text = text.substringBefore("</span>")
            val fos = File("$dir/$File_name")
            fos.writeText(text)
        }

    }

}