package com.bikkadit.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bikkadit.helper.FileResponse;

import com.bikkadit.service.FileServiceI;

@RestController
@RequestMapping("/file")
public class FileController {

	@Autowired
	private FileServiceI fileService;

	@Value("${project.image}")
	private String path;

	@PostMapping("/image")
	public ResponseEntity<FileResponse> uploadFile(@RequestParam("image") MultipartFile image) {
		String fileName = null;
		try {
			fileName = fileService.uploadImage(path, image);
		} catch (IOException e) {

			e.printStackTrace();
			return new ResponseEntity<>(new FileResponse(null, "File not uploaded "), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(new FileResponse(fileName, "File uploaded sucessfully"), HttpStatus.OK);
	}

}
