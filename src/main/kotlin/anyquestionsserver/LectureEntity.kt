package anyquestionsserver

import java.util.*
import javax.persistence.*


@Entity
@Table(name="LECTURES")
class LectureEntity() {
    @Id var id: Long? = null
    var date: Date? = null
    var permission: Boolean? = null
    var lectureNumber: Int? = null

    @Column(name = "group_id")
    var groupId: Long? = null
}