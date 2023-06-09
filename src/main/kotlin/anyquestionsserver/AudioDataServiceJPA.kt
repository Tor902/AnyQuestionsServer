package anyquestionsserver

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class AudioDataServiceJPA(
    @Autowired val audioDataCrud:AudioDataCrud,
    @Autowired val lectureCrud:LectureCrud,
    @Autowired val audioConverter:AudioDataConverter,
    @Autowired val lectureConverter:LectureConverter
) : AudioDataService{
    @Transactional
    override fun create(audioData: AudioDataBoundary, nextQA: String): AudioDataBoundary {
        audioData.id = null
        return this.audioConverter.
                toBoundary(
                    this.audioDataCrud.save(
                        this.audioConverter.toEntity(
                            audioData, nextQA
                        )
                    )
                )
    }

    override fun newLecture(groupId: String, lectureNumber: String, permission: Boolean): LectureBoundary{
        // audioDataService.newLecture(groupId, lectureNumber, permission)
        var lecture = LectureEntity()
        lecture.date = Date()
        lecture.permission = permission
        lecture.groupId = groupId.toLong()
        lecture.lectureNumber = lectureNumber.toInt()
        var lectureNumberStr = lectureNumber
        if(lectureNumber.toInt() < 10){
            lectureNumberStr = "0$lectureNumber"
        }
        lecture.id = (groupId + lectureNumberStr).toLong()
        this.lectureCrud.save(lecture)
        return this.lectureConverter.toBoundary(lecture)
    }
}