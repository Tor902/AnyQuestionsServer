package anyquestionsserver

import javax.persistence.*

@Entity
@Table(name="AUDIODATA")
class AudioDataEntity() {
    @Id  @GeneratedValue var id: Long? = null
    var courseId: String? = null
    var groupId: String? = null
    var classId: String? = null
    var qTranscript: String? = null
    var aTranscript: String? = null
    var qAudioLen: Float? = null
    var aAudioLen: Float? = null
//    @Lob var moreAttributes: String? = null
}