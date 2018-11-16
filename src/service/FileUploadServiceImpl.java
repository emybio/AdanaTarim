package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.FileUploadDAO;
import forms.FileUpload;

@Service
public class FileUploadServiceImpl implements FileUploadService {
	@Autowired
	FileUploadDAO fileDAO;

	@Override
	public void upload(FileUpload file) {

		fileDAO.upload(file);

	}

	@Override
	public List<FileUpload> getAllList() {

		return fileDAO.getAllList();
	}

	@Override
	public FileUpload getFileById(Long id) {
		// TODO Auto-generated method stub
		return fileDAO.getFileById(id);
	}

	@Override
	public void delete(Long id) {
		fileDAO.delete(id);

	}

}
