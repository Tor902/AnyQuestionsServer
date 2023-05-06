package anyquestionsserver

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.Id

class AudioDataBoundary {
    var id: String? = null
    var qTranscript: String? = null
    var aTranscript: String? = null
    var audioLen: Float? = null
    var audioBytes: ByteArray? = null
    var timestamp: LocalDateTime? = null
    var likes: Int? = null
    var courseId: String? = null
    var groupId: String? = null
    var lectureId: String? = null
}