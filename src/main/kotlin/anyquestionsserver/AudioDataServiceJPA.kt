package anyquestionsserver

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.*

@Service
class AudioDataServiceJPA(
    @Autowired val audioDataCrud:AudioDataCrud,
    @Autowired val LectureCrud:LectureCrud,
    @Autowired val converter:AudioDataConverter
) : AudioDataService{
    @Transactional
    override fun create(audioData: AudioDataBoundary): AudioDataBoundary {
        audioData.id = null
        return this.converter.
                toBoundary(
                    this.audioDataCrud.save(
                        this.converter.toEntity(
                            audioData
                        )
                    )
                )
    }

    override fun getAll(size: Int, page: Int): List<AudioDataBoundary> {
        TODO("Not yet implemented")
    }

    override fun cleanup() {
        TODO("Not yet implemented")
    }

    override fun newLecture(courseId: String, groupId: String, live: Boolean, lecturerId: Long) {
        var lecture = LectureEntity()
        lecture.date = LocalDateTime.now()
        lecture.permission = live.toString()
        lecture.groupId = groupId
        lecture.lecturerId = lecturerId
        this.LectureCrud.save(lecture)

    }
}