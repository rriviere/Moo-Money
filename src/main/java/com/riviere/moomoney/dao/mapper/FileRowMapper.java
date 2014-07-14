package com.riviere.moomoney.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.riviere.moomoney.domain.FileMeta;

/**
 * @author Richard Riviere
 * @date 26/11/2013
 */
public class FileRowMapper implements RowMapper<FileMeta>{
	
	private static final String FILE_ID = "file_id";
	private static final String FILENAME = "filename";
	private static final String FILE_NOTES = "file_notes";
	private static final String FILE_SIZE = "file_size";
	private static final String FILE_TYPE = "file_type";	
	private static final String FILE = "file";
	
	@Autowired
	private List<String> supportUsers;	

	public FileMeta mapRow(ResultSet rs, int rowNum) throws SQLException {

		Long fileId = rs.getLong(FILE_ID);
		String filename = rs.getString(FILENAME);
		String fileNotes = rs.getString(FILE_NOTES);
		Long fileSize = rs.getLong(FILE_SIZE);
		String fileType = rs.getString(FILE_TYPE);
		byte[] bytes = rs.getBytes(FILE);
		
		FileMeta fileMeta = new FileMeta();
		fileMeta.setFileId(fileId);
		fileMeta.setFileName(filename);
		fileMeta.setFileNotes(fileNotes);
		fileMeta.setFileSize(fileSize);
		fileMeta.setFileType(fileType);
		fileMeta.setBytes(bytes);
		
		return fileMeta;
	}

}
