package anyquestionsserver

import com.google.cloud.speech.v1p1beta1.*
import com.google.protobuf.ByteString
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*


@RestController
class AudioDataController (
    @Autowired val audioDataService: AudioDataService
    ) {

    @RequestMapping(
        path = ["/audio"],
        method = [RequestMethod.POST],
        consumes = [MediaType.APPLICATION_OCTET_STREAM_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun processAudios(@RequestBody audioData: ByteArray): String {
        // Process audio data here

        val audioBytes: ByteString = ByteString.copyFrom(audioData)
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
        return alternative.transcript
        // Process audio data here using the internalAudioData object



   }
//    @PostMapping("/audio")
//    fun processAudio(@RequestBody audioData: AudioDataBoundary): AudioDataBoundary{
//        // Convert the boundary class to the internal data class
////        val internalAudioData = AudioData(audioData.audioBytes)
//// Configure the speech recognition request
//        val audioBytes: ByteString = ByteString.copyFrom(audioData.aAudioBytes)
//        val recognitionConfig: RecognitionConfig = RecognitionConfig.newBuilder()
//            .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
//            .setLanguageCode("en-US")
//            .setSampleRateHertz(16000)
//            .build()
//
//        val audio: RecognitionAudio = RecognitionAudio.newBuilder()
//            .setContent(audioBytes)
//            .build()
//
//        // Create the speech client and make the API call
//        val speechClient: SpeechClient = SpeechClient.create()
//        val response: RecognizeResponse = speechClient.recognize(recognitionConfig, audio)
//
//        // Extract the transcription from the response
//        val result: SpeechRecognitionResult = response.resultsList[0]
//        val alternative: SpeechRecognitionAlternative = result.alternativesList[0]
//
//        // Return the transcription
//        audioData.aTranscript =  alternative.transcript
//        // Process audio data here using the internalAudioData object
//
//        return audioData
//
//   }
}

