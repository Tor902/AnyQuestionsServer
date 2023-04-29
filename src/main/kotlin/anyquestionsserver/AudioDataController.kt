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
        path = ["/audio/{courseId}/{groupId}/{lectureId}/{type}"],
        method = [RequestMethod.POST],
        consumes = [MediaType.APPLICATION_OCTET_STREAM_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun processAudios(
        @RequestBody audioData: ByteArray,
        @PathVariable courseId: String,
        @PathVariable groupId: String,
        @PathVariable type: String,
        @PathVariable lectureId: String,
    ): AudioDataBoundary {

        val transcripts: Pair<String, String> = processAudioData(audioData)
        val qTranscript = transcripts.first
        val aTranscript = transcripts.first

        var audioDataBoundary = AudioDataBoundary()

        audioDataBoundary.qTranscript = qTranscript
        audioDataBoundary.aTranscript = aTranscript

        audioDataBoundary.courseId = courseId
        audioDataBoundary.groupId = groupId
        audioDataBoundary.lectureId = lectureId

        return audioDataService.create(audioDataBoundary)
    }

    @RequestMapping(
        path = ["/start/{courseId}/{groupId}/{lectureId}/{live}/{lecturerId}"],
        method = [RequestMethod.POST],
//        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun startLecture(
        @PathVariable courseId: String,
        @PathVariable groupId: String,
        @PathVariable lectureId: String,
        @PathVariable lecturerId: Long,
        @PathVariable live: Boolean,
    ){
        // add 'new' to dropdown

        // add time to lecture + round up to lectures timing

        // if lecture already exists continue its summary

        audioDataService.newLecture(courseId, groupId, live, lecturerId)
    }

}

private fun processAudioData(audioData: ByteArray): Pair<String, String>  {

    val byteArrayInputStream = ByteArrayInputStream(audioData)
    val audioInputStream = AudioSystem.getAudioInputStream(byteArrayInputStream)
    val format = audioInputStream.format
    val sampleRate = format.sampleRate.toInt()
    val channels = 1

    val diarizationConfig: SpeakerDiarizationConfig = SpeakerDiarizationConfig.newBuilder()
        .setEnableSpeakerDiarization(true)
        .setMaxSpeakerCount(2)
        .build()


    var speechContext = SpeechContext.newBuilder()
        .addPhrases("software engineering")
        .addPhrases("thank you")
        .addPhrases("Afeka")
        .build()

    val audioBytes: ByteString = ByteString.copyFrom(audioData)
    val recognitionConfig: RecognitionConfig = RecognitionConfig.newBuilder()
        .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
        .setLanguageCode("en-US")
        .setSampleRateHertz(sampleRate)
        .setAudioChannelCount(channels)
        .setEnableSeparateRecognitionPerChannel(true)
        .setEnableAutomaticPunctuation(true)
        .setDiarizationConfig(diarizationConfig)
        .setModel("latest_long")
        .addSpeechContexts(speechContext)
        .build()

    val audio: RecognitionAudio = RecognitionAudio.newBuilder()
        .setContent(audioBytes)
        .build()

    // Create the speech client and make the API call
    val speechClient: SpeechClient = SpeechClient.create()
    val response: RecognizeResponse = speechClient.recognize(recognitionConfig, audio)
    // Extract the transcription from the response
    var transcript = ""

    val speaker1Words = StringBuilder()
    val speaker2Words = StringBuilder()
    for (wordData in response.resultsList[response.resultsList.size-1].alternativesList[0].wordsList){
        if (wordData.speakerTag == 1) {
            speaker1Words.append(wordData.word)
        } else if (wordData.speakerTag == 2) {
            speaker2Words.append(wordData.word)
        }
    }

        return Pair(speaker1Words.toString(), speaker2Words.toString())
    }


//    print("\n\nStart here:\n")
//    print(transcript)
//    print("\n\nEnd here\n")
//
//    return transcript


