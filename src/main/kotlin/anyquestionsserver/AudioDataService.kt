package anyquestionsserver

interface AudioDataService {
    fun create(audioData: AudioDataBoundary, nextQA: String): AudioDataBoundary

    fun newLecture(groupId: String, lectureNumber: String, permission: Boolean): LectureBoundary

}