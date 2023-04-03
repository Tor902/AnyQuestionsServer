package anyquestionsserver

import org.springframework.stereotype.Component
import java.io.ByteArrayInputStream
import javax.sound.sampled.AudioSystem

@Component
class AudioDataConverter {

    fun toEntity(boundary : AudioDataBoundary): AudioDataEntity {
        var entity = AudioDataEntity()
        if(boundary.id != null){
            entity.id = boundary.id!!.toLong()
        }

        if(boundary.aTranscript != null && boundary.aAudioBytes != null) {
            entity.aTranscript = boundary.aTranscript
            entity.aAudioLen = getAudioDuration(boundary.aAudioBytes!!)
            entity.aTranscript = boundary.aTranscript
        }

        if(boundary.qTranscript != null && boundary.qAudioBytes != null) {
            entity.qTranscript = boundary.qTranscript
            entity.qAudioLen = getAudioDuration(boundary.qAudioBytes!!)
            entity.qTranscript = boundary.qTranscript
        }

        entity.courseId = boundary.courseId
        entity.groupId = boundary.groupId
        entity.lectureId = boundary.lectureId
        return entity

    }

    fun toBoundary(entity: AudioDataEntity): AudioDataBoundary {
        var boundary = AudioDataBoundary()

        if(entity.id != null){
            boundary.id = entity.id!!.toString()
        }

        boundary.qTranscript = entity.qTranscript
        boundary.aTranscript = entity.aTranscript
        boundary.courseId =  entity.courseId
        boundary.groupId =  entity.groupId
        boundary.lectureId =  entity.lectureId
//        boundary.moreAttributes = ObjectMapper().readValue(entity.moreAttributes, Map::class.java) as Map<String, Any>


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