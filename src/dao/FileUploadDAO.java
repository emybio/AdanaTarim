package dao;

import java.util.List;

import forms.FileUpload;

public interface FileUploadDAO {

	public void upload(FileUpload file);

	public List<FileUpload> getAllList();
	
	public FileUpload getFileById(Long id);
}
