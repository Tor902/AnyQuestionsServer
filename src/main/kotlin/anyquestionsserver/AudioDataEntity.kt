package anyquestionsserver

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import javax.persistence.*


@Entity
@Table(name="QUESTIONS_ANSWERS")
class QestionAnswerEntity() {
    @Id  @GeneratedValue var id: Long? = null
    @Column(length = 1000)
    var qTranscript: String? = null
    var aTranscript: String? = null
    var audioLen: Float? = null
    var timestamp: LocalDateTime? = null
    var likes: Int? = null
    var lectureId: Long? = null
}
