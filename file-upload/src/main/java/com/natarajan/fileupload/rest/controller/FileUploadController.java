package com.natarajan.fileupload.rest.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.natarajan.fileupload.dao.DocumentDao;
import com.natarajan.fileupload.model.Document;
import com.natarajan.fileupload.util.GlobalProperties;

@RestController
public class FileUploadController {

	private final Logger logger = LoggerFactory
			.getLogger(FileUploadController.class);

	@Autowired
	DocumentDao documentDao;
	
	@Autowired
	GlobalProperties properties;

	@PostMapping("/rest/api/upload")
	public ResponseEntity<String> uploadFile(
			@RequestParam("documentPath") String documentPath,
			@RequestParam("keyWords") String keyWords,
			@RequestParam("file") MultipartFile file) {

		Long documentId;

		if (file != null && !file.isEmpty()) {
			try {
				documentId = saveFile(documentPath, keyWords, file);
			} catch (IOException e) {
				return new ResponseEntity<String>("File could not be saved",
						HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<String>("File is empty", HttpStatus.OK);
		}

		return new ResponseEntity<String>("File successfully saved with id "
				+ String.valueOf(documentId), HttpStatus.OK);
	}

	// save file
	private Long saveFile(String documentPath, String keyWords,
			MultipartFile file) throws IOException {

		//save file to the disk
		byte[] bytes = file.getBytes();
		String uploadFolder = properties.getUploadFolder();
		String originalFilename = file.getOriginalFilename();
		Path path = Paths.get(uploadFolder + originalFilename);
		Files.write(path, bytes);
		
		//save metadata to h2
		Document doc = new Document();
		doc.setDocumentPath(uploadFolder+"/"+originalFilename);
		doc.setFileName(originalFilename);
		if (originalFilename.indexOf(".") > 0)
			doc.setFileType(originalFilename.substring(originalFilename.lastIndexOf(".")+1, originalFilename.length()));
		doc.setKeyWords(keyWords);
		doc.setCreatedBy("test_user");
		doc.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		return Long.valueOf(documentDao.insert(doc));

	}
}
