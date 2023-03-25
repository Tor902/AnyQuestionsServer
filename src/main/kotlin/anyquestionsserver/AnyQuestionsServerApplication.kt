package anyquestionsserver


import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AnyQuestionsServerApplication

fun main(args: Array<String>) {
	runApplication<AnyQuestionsServerApplication>(*args)
}
