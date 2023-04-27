package anyquestionsserver

import org.springframework.data.jpa.repository.JpaRepository

interface LectureCrud : JpaRepository<LectureEntity, Long> {

}