package com.natarajan.fileupload.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.natarajan.fileupload.dao.DocumentDao;
import com.natarajan.fileupload.model.Document;

@Repository
public class DocumentDaoImpl implements DocumentDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	class DocumentRowMapper implements RowMapper<Document> {
		@Override
		public Document mapRow(ResultSet rs, int rowNum) throws SQLException {
			Document document = new Document();
			document.setId(rs.getLong("id"));
			document.setDocumentPath(rs.getString("documentPath"));
			document.setFileName(rs.getString("fileName"));
			document.setFileType(rs.getString("fileType"));
			document.setKeyWords(rs.getString("keyWords"));
			document.setCreatedBy(rs.getString("createdBy"));
			document.setCreatedDate(rs.getTimestamp("createdDate"));
			return document;
		}
	}

	public List<Document> findAll() {
		return jdbcTemplate.query("select * from document",
				new DocumentRowMapper());
	}

	public Document findById(long id) {
		return jdbcTemplate.queryForObject("select * from document where id=?",
				new Object[] { id }, new BeanPropertyRowMapper<Document>(
						Document.class));
	}

	public int deleteById(long id) {
		return jdbcTemplate.update("delete from document where id=?",
				new Object[] { id });
	}

	public int insert(Document document) {
		return jdbcTemplate.update(
				"insert into document (documentPath, fileName, fileType, keyWords, createdBy, createdDate ) "
						+ "values(?, ?, ?, ?, ?, ?)", new Object[] { document.getDocumentPath(),
								document.getFileName(), document.getFileType(), document.getKeyWords(), document.getCreatedBy(), document.getCreatedDate() });
	}

	public int update(Document document) {
		return jdbcTemplate.update("update document "
				+ " set documentPath = ?, fileName = ?, fileType = ?, keyWords = ?, createdBy = ?, createdDate = ? " + " where id = ?",
				new Object[] { document.getDocumentPath(),
						document.getFileName(), document.getFileType(), document.getKeyWords(), document.getCreatedBy(), document.getCreatedDate(),
						document.getId() });
	}

}
