package com.example.demo.upload;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

	void init();

	String store(MultipartFile file,Long id);

	Stream<Path> loadAll();

	Path load(String filename);

	Resource loadAsResource(String filename);
	
	void delete(String filename);

	void deleteAll();

}
