package anyquestionsserver

import org.springframework.stereotype.Component
import java.io.ByteArrayInputStream
import java.util.*
import javax.sound.sampled.AudioSystem

@Component
class AudioDataConverter {

    fun toEntity(boundary : AudioDataBoundary, nextQA: String): QestionAnswerEntity {
        var entity = QestionAnswerEntity()

        entity.id = boundary.lectureId + nextQA
        entity.aTranscript = boundary.aTranscript
        entity.qTranscript = boundary.qTranscript
        entity.audioLen = getAudioDuration(boundary.audioBytes!!)
        entity.lectureId = boundary.lectureId!!.toLong()
        entity.timestamp = Date()
        if(boundary.likes != null){
            entity.likes = boundary.likes
        }else{
            entity.likes = 0
        }
        return entity

    }

    fun toBoundary(entity: QestionAnswerEntity): AudioDataBoundary {
        var boundary = AudioDataBoundary()

        if(entity.id != null){
            boundary.id = entity.id
        }

        boundary.qTranscript = entity.qTranscript
        boundary.aTranscript = entity.aTranscript
        boundary.audioLen = entity.audioLen
        boundary.timestamp = entity.timestamp
        boundary.likes = entity.likes
        boundary.lectureId =  entity.lectureId.toString()

        return boundary
    }

    fun getAudioDuration(audioBytes: ByteArray): Float {
        // Load the byte array into an AudioInputStream
        val byteArrayInputStream = ByteArrayInputStream(audioBytes)
        val audioInputStream = AudioSystem.getAudioInputStream(byteArrayInputStream)

        // Get the audio format
        val format = audioInputStream.format

        // Calculate the duration of the audio based on the byte array's size and sample rate
        val frameLength = audioInputStream.frameLength
        val frameRate = format.frameRate
        val durationInSeconds = frameLength / frameRate

        return durationInSeconds
    }
}