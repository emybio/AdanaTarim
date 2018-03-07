package dao.kirsalkalkinma.kooperatif;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import forms.kirsalkalkinma.kooperatif.KooperatifTuru;

@Repository
@Transactional
public class KooperatifTurDAOImpl implements KooperatifTurDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void save(KooperatifTuru kooperatif) {
		sessionFactory.getCurrentSession().saveOrUpdate(kooperatif);
	}

	@Override
	public void delete(Long id) {
		sessionFactory.getCurrentSession().delete(kooperatifGetir(id));

	}

	@Override
	public KooperatifTuru kooperatifGetir(Long id) {

		KooperatifTuru kooperatif = (KooperatifTuru) sessionFactory.getCurrentSession().load(KooperatifTuru.class, id);

		kooperatif.getId();

		return kooperatif;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<KooperatifTuru> tumKooperatiflerler() {
		Criteria criteriaKoop = sessionFactory.getCurrentSession().createCriteria(KooperatifTuru.class);

		return criteriaKoop.list();
	}

}
