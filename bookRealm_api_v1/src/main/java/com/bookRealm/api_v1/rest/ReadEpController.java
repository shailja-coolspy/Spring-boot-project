package com.bookRealm.api_v1.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import com.bookRealm.api_v1.dto.ReadEpRequestDto;
import com.bookRealm.api_v1.entity.ReadEpisodes;
import com.bookRealm.api_v1.service.ReadEpisodesService;

@RestController
@RequestMapping("/api/v1")
public class ReadEpController {
	
	private ReadEpisodesService readEpisodesService;

	@Autowired
	public ReadEpController(ReadEpisodesService readEpisodesService) {
		super();
		this.readEpisodesService = readEpisodesService;
	}
	
	
	@GetMapping("/readEp")
	public List<ReadEpisodes> getAllReadEp(){
		
		return readEpisodesService.finalAll();
	}
	
	
	@GetMapping("/readEp/{id}")
	public ReadEpisodes getReadEpById(@PathVariable Integer id) {
		
		return readEpisodesService.findById(id);
	}
	
	
	@PostMapping("/readEp")
	public ResponseEntity<ReadEpisodes> createReadEp(@RequestParam("imagFile") MultipartFile file,@ModelAttribute ReadEpRequestDto readEpRequestDto) {
		
		if(file.isEmpty() || !file.getContentType().equals("image/jpeg")) {
			//return new ResponseEntity<>("File is of not jpeg type.",HttpStatus.UNSUPPORTED_MEDIA_TYPE);
            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(null);

		}
		
		return ResponseEntity.ok(readEpisodesService.createReadEp(file, readEpRequestDto));

	}
	
	
	@PutMapping("/readEp/{id}")
	public ResponseEntity<ReadEpisodes> updateReadEp(@PathVariable Integer id,@RequestParam("imagFile") MultipartFile file,@ModelAttribute ReadEpRequestDto readEpRequestDto) {
		
		ReadEpisodes readEpisodes=readEpisodesService.findById(id);
		
		if(readEpisodes==null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

		}
		
		if(file.isEmpty() || !file.getContentType().equals("image/jpeg")) {
			//return new ResponseEntity<>("File is of not jpeg type.",HttpStatus.UNSUPPORTED_MEDIA_TYPE);
            return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(null);

		}
		
		return ResponseEntity.ok(readEpisodesService.updateReadEp(id,file, readEpRequestDto));

	}
	
	
	@DeleteMapping("/readEp/{id}")
	public String deleteById(@PathVariable Integer id) {
		readEpisodesService.deleteById(id);
		
		return "Read Episode with id deleted success="+id;
	}

	
	@GetMapping("/readEp/books/{id}")
	public List<ReadEpisodes> getReadEpByBookId(@PathVariable Integer id){
		return readEpisodesService.findReadEpByBookId(id);
	}
	

}
