package anyquestionsserver

import java.time.LocalDate
import java.time.LocalTime
import javax.persistence.*



//@Entity
//@Table(name = "AudioData")
//class AudioDataEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    var id: Long? = null
//
//    @Column(name = "question_transcript")
//    var questionTranscript: String? = null
//
//    @Column(name = "answer_transcript")
//    var answerTranscript: String? = null
//
//    @Column(name = "course_id")
//    var courseId: String? = null
//
//    @Column(name = "group_id")
//    var groupId: String? = null
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "lecture_id")
//    var lecture: LectureEntity? = null
//}
//
//@Entity
//@Table(name = "Lecture")
//class LectureEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    var id: Long? = null
//
//    @Column(name = "date")
//    var date: LocalDate? = null
//
//    @Column(name = "time")
//    var time: LocalTime? = null
//
//    @Column(name = "permission")
//    var permission: String? = null
//
//    @Column(name = "code")
//    var code: String? = null
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "lecturer_id")
//    var lecturer: LecturerEntity? = null
//}
//
//@Entity
//@Table(name = "Lecturer")
//class LecturerEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    var id: Long? = null
//
//    @Column(name = "name")
//    var name: String? = null
//}

//@Entity
//@Table(name="QUESTIONS")
//@SecondaryTable(name="QUESTIONS", pkJoinColumns=[PrimaryKeyJoinColumn(name="Q_ID")])
//class AudioDataEntity() {
//    @Id  @GeneratedValue var id: Long? = null
//    var courseId: String? = null
//    var groupId: String? = null
//    var lectureId: String? = null
//    var aTranscript: String? = null
//    var qAudioLen: Float? = null
//    var aAudioLen: Float? = null
//    @Column(table="QUESTIONS")
//    var qTranscript: String? = null
//}
//    @Lob var moreAttributes: String? = null
//
@Entity
@Table(name="AUDIODATA")
//@SecondaryTable(name="AUDIODATA_COURSE", pkJoinColumns=[PrimaryKeyJoinColumn(name="COURSE_ID")])
class AudioDataEntity() {
    @Id  @GeneratedValue var id: Long? = null
    var groupId: String? = null
    var lectureId: String? = null

    @Column(length = 1000)
    var qTranscript: String? = null

    @Column(length = 1000)
    var aTranscript: String? = null
    var qAudioLen: Float? = null
    var aAudioLen: Float? = null

//    @Column(table="AUDIODATA_COURSE")
    var courseId: String? = null
}
