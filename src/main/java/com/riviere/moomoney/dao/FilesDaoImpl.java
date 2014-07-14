package com.riviere.moomoney.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.riviere.moomoney.dao.mapper.FileRowMapper;
import com.riviere.moomoney.domain.FileMeta;
import com.riviere.moomoney.exception.MooMoneyException;
 
public class FilesDaoImpl extends AbstractDao implements FilesDao {
        
	private static final String GET_FILE_SQL = 
			" SELECT "
			+ "	file_id, "
			+ " filename, "
			+ " file_notes, "
			+ " file_size, "
			+ " file_type, "
			+ " file "
			+ " FROM file "
			+ " WHERE file_id = ? ";
	
	private static final String GET_ALL_FILES = 
			" SELECT "
			+ "	file_id, "
			+ " filename, "
			+ " file_notes, "
			+ " file_size, "
			+ " file_type, "
			+ " file "
			+ " FROM file ";

	private static final String SAVE_FILE_SQL = 
			" INSERT INTO file ( filename, file_notes, file_size, file_type, file)"
			+ "	VALUES (?, ?, ?, ?, ?) ";
	
	private static final String DELETE_FILE_SQL = 
			" DELETE FROM file WHERE file_id = ? ";
	
    public FileMeta getFile(int fileId) throws MooMoneyException {
        FileMeta fileMeta = 
        		(FileMeta) getJdbcTemplate().
        			queryForObject(GET_FILE_SQL, new Object[] {fileId},new FileRowMapper());
        return fileMeta;
    }

    public List<FileMeta> listAllFiles() throws MooMoneyException { 
        List<FileMeta> files = 
        		getJdbcTemplate().query(GET_ALL_FILES, new BeanPropertyRowMapper<FileMeta>(FileMeta.class));
        return files;
    }

    public long saveFile(final FileMeta file) throws MooMoneyException {
    	long key = -1;
    	synchronized(this) {    		
    		KeyHolder keyHolder = new GeneratedKeyHolder();
    		getJdbcTemplate().update(new PreparedStatementCreator() {
 
    			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
    				PreparedStatement statement = con.prepareStatement(SAVE_FILE_SQL, new String[]{"FILE_ID"});
                    statement.setString(1, file.getFileName());
                    statement.setString(2, file.getFileNotes());
                    statement.setLong(3, file.getFileSize());
                    statement.setString(4, file.getFileType());
                    statement.setBytes(5, file.getBytes());
                    return statement;
                } 
            }, keyHolder);
    		if (keyHolder != null){
    			key = keyHolder.getKey().longValue();
    		}
        }
    	return key;
    }
 
 
    public void deleteFile(int id) throws MooMoneyException {
        try {
            getJdbcTemplate().update(DELETE_FILE_SQL, new Object[] {id});
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}