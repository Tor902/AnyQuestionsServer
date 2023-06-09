package anyquestionsserver

import org.springframework.stereotype.Component
import java.io.ByteArrayInputStream
import java.util.*
import javax.sound.sampled.AudioSystem

@Component
class LectureConverter {

    fun toBoundary(entity: LectureEntity): LectureBoundary {
        var boundary = LectureBoundary()
        boundary.id = entity.id.toString()
        boundary.date = entity.date
        boundary.permission = entity.permission
        boundary.groupId = entity.groupId.toString()
        boundary.lectureNumber = entity.lectureNumber
        return boundary
    }

}