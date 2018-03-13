package dao.kirsalkalkinma.ekonomikyatirim;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import forms.kirsalkalkinma.ekonomikyatirim.EkonomikYatirim;

@Transactional
@Repository
@SuppressWarnings("unchecked")
public class EkonomikYatirimDAOImpl implements EkonomikYatirimDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(EkonomikYatirim ekonikYatirim) {

		sessionFactory.getCurrentSession().saveOrUpdate(ekonikYatirim);

	}

	@Override
	public void delete(Long id) {
		sessionFactory.getCurrentSession().delete(ekonomikYatirimGetir(id));

	}

	@Override
	public EkonomikYatirim ekonomikYatirimGetir(Long id) {

		EkonomikYatirim yatirim = (EkonomikYatirim) sessionFactory.getCurrentSession().load(EkonomikYatirim.class, id);

		yatirim.getId();

		return yatirim;
	}

	@Override
	public List<EkonomikYatirim> tumYatirimListesi() {

		Criteria ekonomikYatirimList = sessionFactory.getCurrentSession().createCriteria(EkonomikYatirim.class);
		ekonomikYatirimList.addOrder(Order.asc("etapNo")).addOrder(Order.asc("yatirimciAdi"));

		return ekonomikYatirimList.list();
	}

	@Override
	public List<EkonomikYatirim> etapNoyaGoreListe(Integer etapNo) {
		Criteria ekonomikYatirimList = sessionFactory.getCurrentSession().createCriteria(EkonomikYatirim.class);
		ekonomikYatirimList.add(Restrictions.eq("etapNo", etapNo));
		return ekonomikYatirimList.list();
	}

	@Override
	public List<EkonomikYatirim> ilceyeGoreListe(String ilce) {
		Criteria ekonomikYatirimList = sessionFactory.getCurrentSession().createCriteria(EkonomikYatirim.class);
		ekonomikYatirimList.createAlias("ilce", "ilce");
		ekonomikYatirimList.add(Restrictions.eq("ilce.isim", ilce));
		ekonomikYatirimList.addOrder(Order.asc("etapNo"));
		return ekonomikYatirimList.list();
	}

	
	@Override
	public List<EkonomikYatirim> ilceListesi() {
		Criteria ekonomikYatirimList = sessionFactory.getCurrentSession().createCriteria(EkonomikYatirim.class);
		ekonomikYatirimList.createAlias("ilce", "ilce");
		ekonomikYatirimList.setProjection(Projections.distinct(Projections.property("ilce.isim")));
		ekonomikYatirimList.addOrder(Order.asc("ilce.isim"));
		return ekonomikYatirimList.list();
	}

}
