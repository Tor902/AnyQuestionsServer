package anyquestionsserver

import com.google.cloud.speech.v1p1beta1.*
import com.google.protobuf.ByteString
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.io.File
import java.nio.file.Files

@SpringBootApplication
class AnyQuestionsServerApplication

fun main(args: Array<String>) {
	runApplication<AnyQuestionsServerApplication>(*args)
}
