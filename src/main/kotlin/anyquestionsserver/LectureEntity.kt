package anyquestionsserver

import java.util.*
import javax.persistence.*


@Entity
@Table(name="LECTURE_XXX")
class LectureEntity() {
    @Id  @GeneratedValue var id: Long? = null
    var date: Date? = null
    var permission: String? = null
    var groupId: String? = null
    var lectureNumber: Long? = null
    var lecturerId: Long? = null
}