package com.bookRealm.api_v1.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.bookRealm.api_v1.dto.AudioEpRequestDto;
import com.bookRealm.api_v1.dto.CustomErrorResponse;
import com.bookRealm.api_v1.entity.AudioEpisodes;
import com.bookRealm.api_v1.exception.CustomException;
import com.bookRealm.api_v1.service.AudioEpisodesService;

@RestController
@RequestMapping("/api/v1")
public class AudioEpController {
	
	private AudioEpisodesService audioEpisodesService;

	@Autowired
	public AudioEpController(AudioEpisodesService audioEpisodesService) {
		super();
		this.audioEpisodesService = audioEpisodesService;
	}
	
	
	//get audio episodes
	@GetMapping("/audioEp")
	public List<AudioEpisodes> getAllAudioEp(){
		return audioEpisodesService.finalAll();
	}
	
	
	//get audio episodes by id
	@GetMapping("/audioEp/{id}")
	public AudioEpisodes getAudioEpById(@PathVariable Integer id) {
		return audioEpisodesService.findById(id);
	}
	
	//create audio episode
	@PostMapping("/audioEp")
	public ResponseEntity<AudioEpisodes> createAudioEp(@RequestParam("imagFile")MultipartFile imagFile,@RequestParam("audioFile")MultipartFile audioFile,@RequestParam("audioEp") String audioEpRequestDto) {
		
		
		if(imagFile.isEmpty() || !imagFile.getContentType().equals("image/jpeg")) {
			//return new ResponseEntity<>("File is of not jpeg type.",HttpStatus.UNSUPPORTED_MEDIA_TYPE);
            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(null);

		}
		
		if(audioFile.isEmpty() || !audioFile.getContentType().equals("audio/mpeg")) {
			//return new ResponseEntity<>("File is of not mp3 type.",HttpStatus.UNSUPPORTED_MEDIA_TYPE);
            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(null);

		}
		
		AudioEpisodes audioEpisodes=audioEpisodesService.createAudioEp(imagFile, audioFile, audioEpRequestDto);
		return ResponseEntity.ok(audioEpisodes);					
		

		
	}
	
	//update audio episode
	@PutMapping("/audioEp/{id}")
	public ResponseEntity<AudioEpisodes> updateAudioEp(@PathVariable Integer id,@RequestParam("imagFile")MultipartFile imagFile,@RequestParam("audioFile")MultipartFile audioFile,@ModelAttribute AudioEpRequestDto audioEpRequestDto) {
		
		AudioEpisodes audioEpisodes=audioEpisodesService.findById(id);
		
		if(audioEpisodes==null) {
			throw new CustomException("Audio Episode with id not found="+id);
		}
		
		return ResponseEntity.ok(audioEpisodesService.updateAudioEp(id, imagFile, audioFile, audioEpRequestDto));
		
	}
	
	//delete audio eipsode
	@DeleteMapping("/audioEp/{id}")
	public String deleteAudioEp(@PathVariable int id)
	{
		
		
		audioEpisodesService.deleteById(id);
		
		return "Deleted Audio Episode with  id-" + id;
	}
	
	
	//get audio episode by book id
	@GetMapping("/audioEp/books/{id}")
	public List<AudioEpisodes> getAudioEpByBookId(@PathVariable Integer id){
		
		return audioEpisodesService.findAudioEpByBookId(id);
	}
	
	
	
	
	//Exeption handling
	
//	@ExceptionHandler
//	public ResponseEntity<CustomErrorResponse> handleExcetion(RuntimeException e){
//		
//		CustomErrorResponse customErrorResponse=new CustomErrorResponse();
//		
//		customErrorResponse.setSuccess(false);
//		customErrorResponse.setCurrentTime(System.currentTimeMillis());
//		customErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
//		customErrorResponse.setMessage(e.getMessage());
//		
//		return new ResponseEntity<>(customErrorResponse,HttpStatus.NOT_FOUND);
//		
//	}
//	

}
