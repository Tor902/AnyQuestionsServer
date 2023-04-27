package anyquestionsserver

import org.springframework.stereotype.Component
import java.io.ByteArrayInputStream
import javax.sound.sampled.AudioSystem

@Component
class LectureConverter {

    fun toEntity(boundary : LectureBoundary): LectureEntity {
        var entity = LectureEntity()
//        if (boundary.id != null) {
//            entity.id = boundary.id!!.toLong()
//        }
        return entity
    }

    fun toBoundary(entity: LectureEntity): LectureBoundary {
        var boundary = LectureBoundary()

        if(entity.id != null){
            boundary.id = entity.id!!.toString()
        }


        return boundary
    }

}