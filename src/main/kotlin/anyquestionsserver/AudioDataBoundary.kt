package anyquestionsserver

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.Id

class AudioDataBoundary {
    var id: String? = null
    var qTranscript: String? = null
    var aTranscript: String? = null
    var qAudioLen: Float? = null
    var aAudioLen: Float? = null
    var qAudioBytes: ByteArray? = null
    var aAudioBytes: ByteArray? = null
    var qTimestamp: LocalDateTime? = null
    var likes: Int? = null
    var courseId: String? = null
    var groupId: String? = null
    var lectureId: String? = null
}