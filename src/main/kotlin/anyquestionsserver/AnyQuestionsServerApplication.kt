package anyquestionsserver


import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AnyQuestionsServerApplication

fun main(args: Array<String>) {
	runApplication<AnyQuestionsServerApplication>(*args)
}






// add metadata to .wav file
//	val tika = Tika()
//
//	val course_tag = "course"
//	val class_tag = "class"
//	val course_value = "course id=2223"
//	val class_value = "class id=2223.1"
//	val file = File("C:/temp2.wav")
//	val metadata_add = Metadata()
//	metadata_add.set(course_tag, course_value)
//	metadata_add.set(class_tag, class_value)
//	FileInputStream(file).use { input ->
//		val content = tika.parseToString(input, metadata_add)
//	}
//
//	val outputStream = FileOutputStream(file)
//	val handler = BodyContentHandler(-1)
//	val context = ParseContext()
//
//	tika.parser.parse(FileInputStream(file), handler, metadata_add, context)
//	outputStream.write(handler.toString().toByteArray())
//	outputStream.close()



// extract metadata from .wav file

//	val file2 = File("C:/Users/torh2/Downloads/temp2.wav")
//	val parser = AutoDetectParser()
//	val handler = BodyContentHandler()
//	val metadata = Metadata()
//	val context = ParseContext()
//	FileInputStream(file2).use {
//		parser.parse(it, handler, metadata, context)
//	}



// do something with the output and error messages