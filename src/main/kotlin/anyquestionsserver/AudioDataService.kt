package anyquestionsserver

interface AudioDataService {
    fun create(audioData: AudioDataBoundary): AudioDataBoundary
    fun getAll(size:Int, page:Int): List<AudioDataBoundary>
    fun cleanup()

}