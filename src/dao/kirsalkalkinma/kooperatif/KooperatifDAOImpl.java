package dao.kirsalkalkinma.kooperatif;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import forms.kirsalkalkinma.kooperatif.Kooperatif;

@Repository
@Transactional
@SuppressWarnings("unchecked")
public class KooperatifDAOImpl implements KooperatifDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void save(Kooperatif kooperatif) {
		sessionFactory.getCurrentSession().saveOrUpdate(kooperatif);
	}

	@Override
	public void delete(Long id) {
		sessionFactory.getCurrentSession().delete(kooperatifGetir(id));

	}

	@Override
	public Kooperatif kooperatifGetir(Long id) {

		Kooperatif kooperatif = (Kooperatif) sessionFactory.getCurrentSession().get(Kooperatif.class, id);

		kooperatif.getId();

		return kooperatif;
	}

	@Override
	public List<Kooperatif> tumKooperatiflerler() {
		Criteria criteriaKoop = sessionFactory.getCurrentSession().createCriteria(Kooperatif.class);

		return criteriaKoop.list();
	}

	@Override
	public List<Kooperatif> ilceyeGoreKooperatifListesi(Long ilceID) {
		Criteria criteriaKoop = sessionFactory.getCurrentSession().createCriteria(Kooperatif.class);
		criteriaKoop.add(Restrictions.eq("kooperatifIlceID.id", ilceID));
		criteriaKoop.setProjection(Projections.distinct(Projections.property("kooperatifTurID")));
		criteriaKoop.addOrder(Order.asc("kooperatifTurID"));
		return criteriaKoop.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}

	@Override
	public List<Kooperatif> ilceListesi() {
		Criteria criteriaKoop = sessionFactory.getCurrentSession().createCriteria(Kooperatif.class);
		criteriaKoop.setProjection(Projections.distinct(Projections.property("kooperatifIlceID")));

		return criteriaKoop.list();
	}

	@Override
	public List<Kooperatif> turListesi() {
		Criteria criteriaKoop = sessionFactory.getCurrentSession().createCriteria(Kooperatif.class);
		criteriaKoop.setProjection(Projections.distinct(Projections.property("kooperatifTurID")));

		return criteriaKoop.list();
	}

	@Override
	public List<Kooperatif> tureGoreKooperatifListesi(Long turID) {
		Criteria criteriaKoop = sessionFactory.getCurrentSession().createCriteria(Kooperatif.class);
		criteriaKoop.add(Restrictions.eq("kooperatifTurID.id", turID));

		return criteriaKoop.list();
	}

	@Override
	public List<Kooperatif> kooperatifKartiGetir(Long koopId) {
		Criteria criteriaKoop = sessionFactory.getCurrentSession().createCriteria(Kooperatif.class);
		criteriaKoop.add(Restrictions.eq("id", koopId));

		return criteriaKoop.list();
	}

}
