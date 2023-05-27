package anyquestionsserver

import java.util.*
import javax.persistence.*


@Entity
@Table(name="LECTURER_XXX")
class LecturerEntity() {
    @Id
    @GeneratedValue
    var id: String? = null
    var fName: String? = null
    var lName: String? = null
    var phone: String? = null
    var email: Date? = null
    var password: String? = null
}

//@Entity
//@Table(name="AUDIODATA")
////@SecondaryTable(name="AUDIODATA_COURSE", pkJoinColumns=[PrimaryKeyJoinColumn(name="COURSE_ID")])
//class AudioDataEntity() {
//    @Id
//    @GeneratedValue
//    var id: Long? = null
//    var groupId: String? = null
//    var lectureId: String? = null
//
//    @Column(length = 1000)
//    var qTranscript: String? = null
//
//    @Column(length = 1000)
//    var aTranscript: String? = null
//    var qAudioLen: Float? = null
//    var aAudioLen: Float? = null
//
//    //    @Column(table="AUDIODATA_COURSE")
//    var courseId: String? = null
//}