package anyquestionsserver

import java.time.LocalDateTime
import java.util.*
import javax.persistence.*


@Entity
@Table(name="LECTURE")
class LectureEntity() {
    @Id  @GeneratedValue var id: Long? = null
    var date: LocalDateTime? = null
    var permission: String? = null
    var groupId: String? = null
    var lectureNumber: Long? = null
    var lecturerId: Long? = null
}