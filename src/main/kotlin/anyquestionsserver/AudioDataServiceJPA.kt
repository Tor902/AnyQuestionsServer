package anyquestionsserver

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AudioDataServiceJPA(
    @Autowired val crud:AudioDataCrud,
    @Autowired val converter:AudioDataConverter
) : AudioDataService{
    @Transactional
    override fun create(audioData: AudioDataBoundary): AudioDataBoundary {
        audioData.id = null
        return this.converter.
                toBoundary(
                    this.crud.save(
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
}