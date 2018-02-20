package com.natarajan.fileupload;

import java.sql.Timestamp;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.natarajan.fileupload.dao.DocumentDao;
import com.natarajan.fileupload.model.Document;

@SpringBootApplication
public class SpringBootWebApplication implements CommandLineRunner {
	
	@Autowired
	DocumentDao documentDao;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringBootWebApplication.class, args);
    }
    
    @Override
	public void run(String... args) throws Exception {

    	Document doc = new Document();
    	doc.setFileName("testFile");
    	doc.setDocumentPath("/path/to/doc");
    	doc.setCreatedBy("testUser");
    	doc.setFileType("doc");
    	doc.setCreatedDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
    	doc.setKeyWords("Test,Upload,File");
		logger.info("Inserting -> {}", documentDao.insert(doc));
		
		doc.setKeyWords("New,Test,Upload,File");

		logger.info("Update ", documentDao.update(doc));

		logger.info("All docs -> {}", documentDao.findAll());
	}

}