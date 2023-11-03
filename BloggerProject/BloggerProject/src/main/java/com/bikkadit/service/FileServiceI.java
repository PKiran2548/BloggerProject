package com.bikkadit.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface FileServiceI {

	String uploadImage (String path , MultipartFile file) throws IOException;
}
