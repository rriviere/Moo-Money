package com.riviere.moomoney.dao;

import java.util.ArrayList;
import java.util.List;

import com.riviere.moomoney.domain.FileMeta;
import com.riviere.moomoney.domain.FinancialTransaction;



/**
 * User details data access object interface
 * 
 * @author Richard Riviere
 * @date 26/11/2013
 */
public interface FilesDao {
    
	public FileMeta getFile(int fileId);
	public List<FinancialTransaction> getFileContent(int fileId);
    public List<FileMeta> listAllFiles();
    public long saveFile(final FileMeta file);
    public void deleteFile(int id);
    
}
