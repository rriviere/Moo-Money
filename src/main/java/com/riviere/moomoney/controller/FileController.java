package com.riviere.moomoney.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.riviere.moomoney.domain.FileMeta;
import com.riviere.moomoney.manager.FilesManager;

@Controller
@RequestMapping("/controller")
public class FileController {

	@Autowired
	FilesManager filesManager;
		
	/***************************************************
	 * URL: /rest/controller/upload  
	 * upload(): receives files
	 * @param request : MultipartHttpServletRequest auto passed
	 * @param response : HttpServletResponse auto passed
	 * @return LinkedList<FileMeta> as json format
	 ****************************************************/
	@RequestMapping(value="/upload.htm", method = RequestMethod.POST)
	public @ResponseBody LinkedList<FileMeta> upload (
			MultipartHttpServletRequest request, 
			HttpServletResponse response) {
 
		LinkedList<FileMeta> files = new LinkedList<FileMeta>();
		/*System.out.println("files:" + files);
		System.out.println("request.getFileNames():" + request.getFileNames());*/
		
		//1. build an iterator
		 Iterator<String> itr =  request.getFileNames();
		 MultipartFile mpf = null;

		 //2. get each file
		 while(itr.hasNext()){
			 
			 //2.1 get next MultipartFile
			 mpf = request.getFile(itr.next()); 
			 System.out.println(mpf.getOriginalFilename() +" uploaded! "+files.size());

			 //2.2 if files > 10 remove the first from the list
			 if(files.size() >= 10)
				 files.pop();
			 
			 //2.3 create new fileMeta
			 FileMeta fileMeta = new FileMeta();
			 fileMeta.setFileName(mpf.getOriginalFilename());
			 fileMeta.setFileNotes("");
			 fileMeta.setFileSize(mpf.getSize()/1024);// mpf.getSize()/1024+" Kb"
			 fileMeta.setFileType(mpf.getContentType());
			 
			 try {
				fileMeta.setBytes(mpf.getBytes());
				long fileId = filesManager.saveFile(fileMeta);
				fileMeta.setFileId(fileId);
				// copy file to local disk (make sure the path "e.g. D:/temp/files" exists)
				//FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream("C:/temp/files/"+mpf.getOriginalFilename()));	
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 //2.4 add to files
			 files.add(fileMeta);
			 
		 }
		 
		// result will be like this
		// [{"fileName":"app_engine-85x77.png","fileSize":"8 Kb","fileType":"image/png"},...]
		return files;
 
	}
	
	/***************************************************
	 * URL: /rest/controller/get/{value}
	 * get(): get file as an attachment
	 * @param response : passed by the server
	 * @param value : value from the URL
	 * @return void
	 ****************************************************/
	@RequestMapping(value = "/get/{value}.htm", method = RequestMethod.GET)
	 public void get(HttpServletResponse response,@PathVariable String value){
		 FileMeta getFile = filesManager.getFile(Integer.parseInt(value));
		 try {		
			 	response.setContentType(getFile.getFileType());
			 	response.setHeader("Content-disposition", "attachment; filename=\""+getFile.getFileName()+"\"");
		        FileCopyUtils.copy(getFile.getBytes(), response.getOutputStream());
		 }catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		 }
	 }	

}
