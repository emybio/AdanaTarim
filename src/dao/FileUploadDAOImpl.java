package dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import forms.FileUpload;

@Repository
public class FileUploadDAOImpl implements FileUploadDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public void upload(FileUpload file) {
		sessionFactory.getCurrentSession().saveOrUpdate(file);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<FileUpload> getAllList() {
		Criteria getAll = sessionFactory.getCurrentSession().createCriteria(FileUpload.class);
		return getAll.list();
	}

	@Override
	@Transactional
	public FileUpload getFileById(Long id) {
		FileUpload file = (FileUpload) sessionFactory.getCurrentSession().load(FileUpload.class, id);
		file.getId();
		return file;
	}

	@Override
	@Transactional
	public void delete(Long id) {
		sessionFactory.getCurrentSession().delete(getFileById(id));

	}

}
