/**
 * 
 */
package dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import forms.Arac;
import forms.AracTalep;

/**
 * @author lenovo
 *
 */
@Repository
public class AracTalepDAOImpl implements AracTalepDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public void save(AracTalep arac) {
		sessionFactory.getCurrentSession().saveOrUpdate(arac);

	}

	@Override
	@Transactional
	public void delete(Long id) {
		sessionFactory.getCurrentSession().delete(aracCikisGetir(id));
	}

	@Override
	@Transactional
	public AracTalep aracCikisGetir(Long id) {
		AracTalep arac = (AracTalep) sessionFactory.getCurrentSession().get(AracTalep.class, id);

		arac.getId();

		return arac;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<AracTalep> tumAracCikislari() {

		Criteria criteriaArac = sessionFactory.getCurrentSession().createCriteria(AracTalep.class);
		// criteriaArac.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		criteriaArac.addOrder(Order.asc("tarih"));
		criteriaArac.addOrder(Order.desc("islemZamani"));
		List<AracTalep> aracCikisListesi = criteriaArac.list();

		return aracCikisListesi;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<AracTalep> kullaniciyaGoreCikisListesi(Long kullaniciID) {
		System.out.println("kullaniciID: " + kullaniciID);
		Criteria criteriaArac = sessionFactory.getCurrentSession().createCriteria(AracTalep.class);
		criteriaArac.add(Restrictions.eq("islemyapan.id", kullaniciID));
		List<AracTalep> aracCikisListesi = criteriaArac.list();

		return aracCikisListesi;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<AracTalep> resmiPlakayaGoreCikisListesi(String plaka) {
		Criteria criteriaArac = sessionFactory.getCurrentSession().createCriteria(Arac.class);
		criteriaArac.add(Restrictions.eq("resmiPlaka", plaka));
		List<AracTalep> aracCikisListesi = criteriaArac.list();

		return aracCikisListesi;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<AracTalep> ozelPlakayaGoreCikisListesi(String plaka) {
		Criteria criteriaArac = sessionFactory.getCurrentSession().createCriteria(AracTalep.class);
		criteriaArac.add(Restrictions.eq("ozelPlaka", plaka));
		List<AracTalep> aracCikisListesi = criteriaArac.list();

		return aracCikisListesi;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<AracTalep> cikisYapanPersonelListesi() {
		Criteria criteriaPersonel = sessionFactory.getCurrentSession().createCriteria(Arac.class);
		criteriaPersonel.createAlias("kullaniciList", "kullanici");

		ProjectionList list = Projections.projectionList();
		list.add((Projections.property("kullanici.id")));
		list.add((Projections.property("kullanici.adi")));
		criteriaPersonel.setProjection(Projections.distinct(list));

		criteriaPersonel.addOrder(Order.asc("kullanici.adi"));
		// System.out.println("aracdao: " + criteriaPersonel.list().toString());
		return criteriaPersonel.list();
	}

	@SuppressWarnings("rawtypes")
	@Override
	@Transactional
	public boolean ayniGorevdenVarMi(long mahalleID, String tarih) {
		Criteria criteriaArac = sessionFactory.getCurrentSession().createCriteria(AracTalep.class);

		criteriaArac.add(Restrictions.eq("mahalle.id", mahalleID));
		criteriaArac.add(Restrictions.eq("tarih", tarih));

		List sonucList = criteriaArac.list();
		return (sonucList != null && sonucList.size() > 0);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<AracTalep> gorevBul(String tarih) {
		Criteria criteriaGorevBul = sessionFactory.getCurrentSession().createCriteria(Arac.class);
		System.out.println("aracDao gorevBUL Tarih: " + tarih);

		criteriaGorevBul.add(Restrictions.eq("tarih", tarih));

		return criteriaGorevBul.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();

	}

}
