package dao.kirsalkalkinma;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import forms.kirsalkalkinma.Aricilik;

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class AricilikDAOImpl implements AricilikDAO {
	@Autowired
	private SessionFactory sessionFactory;

	// private Criteria criteriaAri;
	//
	// public void setCriteriaAri(Criteria criteriaAri) {
	// criteriaAri =
	// sessionFactory.getCurrentSession().createCriteria(Aricilik.class);
	// this.criteriaAri = criteriaAri;
	//
	// }

	@Override
	public void save(Aricilik ari) {
		sessionFactory.getCurrentSession().saveOrUpdate(ari);

	}

	@Override
	public void delete(Long id) {
		sessionFactory.getCurrentSession().delete(ariciGetir(id));
	}

	@Override
	public Aricilik ariciGetir(Long id) {
		Aricilik ari = (Aricilik) sessionFactory.getCurrentSession().load(Aricilik.class, id);
		ari.getId();
		return ari;
	}

	@Override
	public List<Aricilik> tumListe() {
		Criteria criteriaAri = sessionFactory.getCurrentSession().createCriteria(Aricilik.class);
		return criteriaAri.list();
	}

}
