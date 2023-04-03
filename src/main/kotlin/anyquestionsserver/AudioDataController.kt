package anyquestionsserver

import com.google.cloud.speech.v1p1beta1.*
import com.google.protobuf.ByteString
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import java.io.ByteArrayInputStream
import javax.sound.sampled.AudioSystem


@RestController
class AudioDataController (
    @Autowired val audioDataService: AudioDataService
    ) {

    @RequestMapping(
        path = ["/audio/{courseId}/{groupId}/{lectureId}"],
        method = [RequestMethod.POST],
        consumes = [MediaType.APPLICATION_OCTET_STREAM_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun processAudios(
        @RequestBody audioData: ByteArray,
        @PathVariable courseId: String,
        @PathVariable groupId: String,
        @PathVariable lectureId: String,
    ): AudioDataBoundary {

        val transcripts: SpeechRecognitionAlternative = processAudioData(audioData)

        var audioDataBoundary = AudioDataBoundary()
        audioDataBoundary.qAudioBytes = audioData
        audioDataBoundary.qTranscript = transcripts.transcript.toString()
        print(transcripts.transcript.toString())
        audioDataBoundary.courseId = courseId
        audioDataBoundary.groupId = groupId
        audioDataBoundary.lectureId = lectureId



        return audioDataService.create(audioDataBoundary)
    }
}

private fun processAudioData(audioData: ByteArray): SpeechRecognitionAlternative  {

    val byteArrayInputStream = ByteArrayInputStream(audioData)
    val audioInputStream = AudioSystem.getAudioInputStream(byteArrayInputStream)
    val format = audioInputStream.format
    val sampleRate = format.sampleRate.toInt()
    val channels = format.channels

    val audioBytes: ByteString = ByteString.copyFrom(audioData)
    val recognitionConfig: RecognitionConfig = RecognitionConfig.newBuilder()
        .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
        .setLanguageCode("en-US")
        .setSampleRateHertz(sampleRate)
        .setAudioChannelCount(channels)
        .build()

    val audio: RecognitionAudio = RecognitionAudio.newBuilder()
        .setContent(audioBytes)
        .build()

    // Create the speech client and make the API call
    val speechClient: SpeechClient = SpeechClient.create()
    val response: RecognizeResponse = speechClient.recognize(recognitionConfig, audio)
    // Extract the transcription from the response
    val result: SpeechRecognitionResult = response.resultsList[0]
    print("\n\nStart here:\n")
    print(result)
    print("\n\nEnd here\n")
    return result.alternativesList[0]

}