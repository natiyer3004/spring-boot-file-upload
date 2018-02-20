package com.natarajan.fileupload.dao;

import java.util.List;

import com.natarajan.fileupload.model.Document;

public interface DocumentDao {

	public List<Document> findAll();

	public Document findById(long id);

	public int deleteById(long id);

	public int insert(Document document);

	public int update(Document document);
}
