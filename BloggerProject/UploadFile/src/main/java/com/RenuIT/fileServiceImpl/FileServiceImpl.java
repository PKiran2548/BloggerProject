package com.RenuIT.fileServiceImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.RenuIT.service.FileService;

@Service
public class FileServiceImpl implements FileService{

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		
		// File Name
		String fileName = file.getOriginalFilename();
		
		// file path
		String filePath = path+ File.separator + fileName ;
		
		// create folder if not exist
		
		File f = new File(path);
		
		
		if (!f.exists()) {
			f.mkdir(); // if create folder if not exist
		}
		
		// copy file
		
		Files.copy(file.getInputStream(),Paths.get(filePath) );
		
		return fileName;
	}

}
