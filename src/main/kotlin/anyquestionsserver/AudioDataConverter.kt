package anyquestionsserver

import org.springframework.stereotype.Component
import java.io.ByteArrayInputStream
import java.time.LocalDateTime
import javax.sound.sampled.AudioSystem

@Component
class AudioDataConverter {

    fun toEntity(boundary : AudioDataBoundary): QestionAnswerEntity {
        var entity = QestionAnswerEntity()
        if(boundary.id != null){
            entity.id = boundary.id!!.toLong()
        }

        entity.aTranscript = boundary.aTranscript
        entity.qTranscript = boundary.qTranscript
        entity.audioLen = getAudioDuration(boundary.audioBytes!!)
        entity.lectureId = boundary.lectureId!!.toLong()
        entity.timestamp = LocalDateTime.now()
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
            boundary.id = entity.id!!.toString()
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