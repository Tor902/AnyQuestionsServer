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

private fun processAudioData(audioData: ByteArray): SpeechRecognitionAlternative  {

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
    val result: SpeechRecognitionResult = response.resultsList[0]
//    var speakerTag1 = arrayListOf<String>()
//    var speakerTag2 = arrayListOf<String>()
//    var speaker1Index = 0
//    var speaker2Index = 0
//
//    var lastSpeakerTag = 0
//    var speakerTag1Index = -1
//    var speakerTag2Index = -1
//
//    var currentTag = 1
//    for (wordData in response.resultsList[response.resultsList.size-1].alternativesList[0].wordsList) {
//
//        if (wordData.speakerTag == 1) {
//            if (lastSpeakerTag != 1) {
//                speakerTag1Index++
//                speakerTag1.add("")
//            }
//            speakerTag1[speakerTag1Index] += "${wordData.word} "
//            lastSpeakerTag = 1
//
//        } else if (wordData.speakerTag == 2) {
//            if (lastSpeakerTag != 2) {
//                speakerTag2Index++
//                speakerTag2.add("")
//            }
//            speakerTag2[speakerTag2Index] += "${wordData.word} "
//            lastSpeakerTag = 2
//        }
//    }
//
//    val speaker1Size = speakerTag1.size
//    val speaker2Size = speakerTag2.size
//    val conversationSize = speaker1Size + speaker2Size
//
//    speaker1Index = 0
//    speaker2Index = 0
//
//    for (i in 0 until conversationSize) {
//        if (i % 2 == 0 && speaker1Index < speaker1Size) {
//            println("Speaker 1: ${speakerTag1[speaker1Index]}")
//            speaker1Index++
//        } else if (i % 2 == 1 && speaker2Index < speaker2Size) {
//            println("Speaker 2: ${speakerTag2[speaker2Index]}")
//            speaker2Index++
//        }
//    }




//        if(wordData.speakerTag != currentTag) {
//            currentTag = wordData.speakerTag
//
//        }
//        if (wordData.speakerTag == 1) {
//            speakerTag1[speaker1Index] += " " + wordData.word
//        } else if (wordData.speakerTag == 2) {
//            speakerTag2 += " " + wordData.word
//        }
//    }

    print("\n\nStart here:\n")
    print(result)
    print("\n\nEnd here\n")

    return result.alternativesList[0]

}
