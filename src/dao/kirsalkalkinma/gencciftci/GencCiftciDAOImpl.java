package dao.kirsalkalkinma.gencciftci;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import forms.kirsalkalkinma.gencciftci.GencCiftci;

@Transactional
@Repository
public class GencCiftciDAOImpl implements GencCiftciDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(GencCiftci gencCiftci) {
		sessionFactory.getCurrentSession().saveOrUpdate(gencCiftci);
	}

	@Override
	public void delete(Long id) {
		sessionFactory.getCurrentSession().delete(gencCiftciGetir(id));

	}

	@Override
	public GencCiftci gencCiftciGetir(Long id) {
		GencCiftci gencCiftci = (GencCiftci) sessionFactory.getCurrentSession().load(GencCiftci.class, id);

		gencCiftci.getId();

		return gencCiftci;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GencCiftci> tumGencCiftciler() {
		Criteria tumListe = sessionFactory.getCurrentSession().createCriteria(GencCiftci.class);

		tumListe.addOrder(Order.asc("yil"));

		return tumListe.list();
	}

}
