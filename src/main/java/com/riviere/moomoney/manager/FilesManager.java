package com.riviere.moomoney.manager;

import java.util.List;

import com.riviere.moomoney.domain.FileMeta;
import com.riviere.moomoney.domain.Transaction;



/**
 * User details data access object interface
 * 
 * @author Richard Riviere
 * @date 26/11/2013
 */
public interface FilesManager {
    
	public FileMeta getFile(int fileId);
	public List<Transaction> getFileContent(int fileId);
    public List<FileMeta> listAllFiles();
    public long saveFile(final FileMeta file);
    public void deleteFile(int id);
    
}
