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

	val newcredentialsPath = System.getenv("GOOGLE_APPLICATION_CREDENTIALS")
	if (newcredentialsPath != null) {
		println("Google Cloud credentials path: $newcredentialsPath")
	} else {
		println("Google Cloud credentials path not set.")
	}

//	print("File path of creds: $GOOGLE_APPLICATION_CREDENTIALS")
	val filePath = "C:/Users/torh2/Downloads/vid3.wav"
	val audioBytes: ByteString = ByteString.copyFrom(Files.readAllBytes(File(filePath).toPath()))
	val recognitionConfig: RecognitionConfig = RecognitionConfig.newBuilder()
		.setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
		.setLanguageCode("en-US")
		.setSampleRateHertz(16000)
		.build()

	val audio: RecognitionAudio = RecognitionAudio.newBuilder()
		.setContent(audioBytes)
		.build()

	// Create the speech client and make the API call
	val speechClient: SpeechClient = SpeechClient.create()
	val response: RecognizeResponse = speechClient.recognize(recognitionConfig, audio)

	// Extract the transcription from the response
	val result: SpeechRecognitionResult = response.resultsList[0]
	val alternative: SpeechRecognitionAlternative = result.alternativesList[0]

	// Return the transcription
	print(alternative.transcript)
	// Process audio data here using the internalAudioData object

//	runApplication<AnyQuestionsServerApplication>(*args)
}
