package anyquestionsserver

import com.fasterxml.jackson.databind.ObjectMapper
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
        if(boundary.aTranscript != null) {
            entity.aTranscript = boundary.aTranscript
            val byteArrayInputStream = ByteArrayInputStream(boundary.aAudioBytes)
            val audioInputStream = AudioSystem.getAudioInputStream(byteArrayInputStream)
            val format = audioInputStream.format
            val frameLength = audioInputStream.frameLength
            val frameRate = format.frameRate
            val durationInSeconds = frameLength / frameRate
            entity.aAudioLen = durationInSeconds
            entity.aTranscript = boundary.aTranscript
        }

        if(boundary.qTranscript != null) {
            entity.qTranscript = boundary.qTranscript
            val byteArrayInputStream = ByteArrayInputStream(boundary.qAudioBytes)
            val audioInputStream = AudioSystem.getAudioInputStream(byteArrayInputStream)
            val format = audioInputStream.format
            val frameLength = audioInputStream.frameLength
            val frameRate = format.frameRate
            val durationInSeconds = frameLength / frameRate
            entity.qAudioLen = durationInSeconds
            entity.qTranscript = boundary.qTranscript
        }

        entity.courseId = boundary.courseId
        entity.groupId = boundary.groupId
        entity.classId = boundary.classId
        entity.moreAttributes = ObjectMapper().writeValueAsString(boundary.moreAttributes)
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
        boundary.courseId =  entity.courseId
        boundary.moreAttributes = ObjectMapper().readValue(entity.moreAttributes, Map::class.java) as Map<String, Any>


        return boundary
    }


}