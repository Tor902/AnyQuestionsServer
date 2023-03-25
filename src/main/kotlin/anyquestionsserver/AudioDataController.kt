package anyquestionsserver

import com.google.cloud.speech.v1p1beta1.*
import com.google.protobuf.ByteString
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController


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
    fun processAudios(
        @RequestBody audioData: ByteArray,
    ): AudioDataBoundary {

        val transcripts: SpeechRecognitionAlternative = processAudioData(audioData)

        var audioDataBoundary = AudioDataBoundary()
        audioDataBoundary.qAudioBytes = audioData
        audioDataBoundary.qTranscript = transcripts.transcript.toString()

        return audioDataService.create(audioDataBoundary)
    }
}

private fun processAudioData(audioData: ByteArray): SpeechRecognitionAlternative  {

    val audioBytes: ByteString = ByteString.copyFrom(audioData)
    val recognitionConfig: RecognitionConfig = RecognitionConfig.newBuilder()
        .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
        .setLanguageCode("en-US")
        .setSampleRateHertz(48000)
        .setAudioChannelCount(2)
        .build()

    val audio: RecognitionAudio = RecognitionAudio.newBuilder()
        .setContent(audioBytes)
        .build()

    // Create the speech client and make the API call
    val speechClient: SpeechClient = SpeechClient.create()
    val response: RecognizeResponse = speechClient.recognize(recognitionConfig, audio)

    // Extract the transcription from the response
    val result: SpeechRecognitionResult = response.resultsList[0]
    return result.alternativesList[0]
}