package com.riviere.moomoney.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.riviere.moomoney.dao.FilesDao;
import com.riviere.moomoney.domain.FileMeta;
import com.riviere.moomoney.domain.Transaction;

@Service
public class FilesManagerImpl implements FilesManager {

	@Autowired
	private FilesDao filesDao;

	public FileMeta getFile(int fileId) {
		return filesDao.getFile(fileId);
	}
	
	public List<Transaction> getFileContent(int fileId) {
		return filesDao.getFileContent(fileId);
	}	

	public List<FileMeta> listAllFiles() {
		return filesDao.listAllFiles();
	}

	public long saveFile(FileMeta file) {
		return filesDao.saveFile(file);
	}

	public void deleteFile(int id) {
		filesDao.deleteFile(id);
	}
	
}
