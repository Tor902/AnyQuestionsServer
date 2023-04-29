package anyquestionsserver

import org.springframework.data.jpa.repository.JpaRepository

interface AudioDataCrud : JpaRepository<QestionAnswerEntity, Long> {

}