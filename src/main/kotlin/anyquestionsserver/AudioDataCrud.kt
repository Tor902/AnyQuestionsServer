package anyquestionsserver

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

interface AudioDataCrud : JpaRepository<QestionAnswerEntity, Long> {

}