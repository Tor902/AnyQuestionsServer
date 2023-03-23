package anyquestionsserver

interface AudioDataService {
    fun create(user: AudioDataBoundary): AudioDataBoundary
    fun getAll(size:Int, page:Int): List<AudioDataBoundary>
    fun cleanup()

}