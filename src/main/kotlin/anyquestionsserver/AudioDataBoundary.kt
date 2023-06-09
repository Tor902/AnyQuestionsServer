package anyquestionsserver

import java.util.*

class AudioDataBoundary {
    var id: String? = null
    var qTranscript: String? = null
    var aTranscript: String? = null
    var audioLen: Float? = null
    var audioBytes: ByteArray? = null
    var timestamp: Date? = null
    var likes: Int? = null
    var courseId: String? = null
    var groupId: String? = null
    var lectureId: String? = null
}

class QABoundary{
    var id: String? = null
    var question: String? = null
    var answer: String? = null
    var likes: Int? = null
    var timestamp: Date? = null
}