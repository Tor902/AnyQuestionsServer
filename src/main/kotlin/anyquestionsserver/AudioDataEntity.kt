package anyquestionsserver

import java.time.LocalDate
import java.time.LocalTime
import java.util.Date
import javax.persistence.*


@Entity
@Table(name="QUESTIONS_ANSWERS")
class QestionAnswerEntity() {
    @Id var id: String? = null
    @Column(length = 1000)
    var qTranscript: String? = null
    var aTranscript: String? = null
    var audioLen: Float? = null
    var timestamp: Date? = null
    var likes: Int? = null
    var lectureId: Long? = null
}
