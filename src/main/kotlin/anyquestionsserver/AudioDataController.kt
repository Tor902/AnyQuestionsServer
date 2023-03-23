package anyquestionsserver

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
class AudioDataController (
    @Autowired val audioDataService: AudioDataService
    ) {

    @PostMapping("/audio")
    fun processAudio(@RequestBody audioData: AudioDataBoundary): AudioDataBoundary{
        // Convert the boundary class to the internal data class
//        val internalAudioData = AudioData(audioData.audioBytes)

        // Process audio data here using the internalAudioData object

        return audioData

    }
}
