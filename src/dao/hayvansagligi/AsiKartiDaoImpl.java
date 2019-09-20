package dao.hayvansagligi;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import forms.hayvansagligi.AsiKarti;

@Repository
public class AsiKartiDaoImpl implements AsiKartiDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	@Override
	public void save(AsiKarti asiKarti) {
		sessionFactory.getCurrentSession().saveOrUpdate(asiKarti);

	}

	@Override
	@Transactional
	public void delete(Long id) {
		sessionFactory.getCurrentSession().delete(kartGetir(id));

	}

	@Override
	@Transactional
	public AsiKarti kartGetir(Long id) {
		AsiKarti kart = (AsiKarti) sessionFactory.getCurrentSession().load(AsiKarti.class, id);
		kart.getId();
		return kart;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<AsiKarti> tumListe() {
		Criteria criteriaAri = sessionFactory.getCurrentSession().createCriteria(AsiKarti.class);
		return criteriaAri.list();
	}

	@SuppressWarnings({  "unchecked" })
	@Override
	@Transactional
	public Long ilceninToplamBorcu(Long ilceID) {

		Criteria criteriaAsi = sessionFactory.getCurrentSession().createCriteria(AsiKarti.class);

		criteriaAsi.createAlias("ilce", "ilce");
		criteriaAsi.add(Restrictions.eq("ilce.id", ilceID));
		List<AsiKarti> list = criteriaAsi.list();
		int toplam = 0;

		for (AsiKarti asi : list)
			toplam += asi.getBirimFiyati() * asi.getMiktar();

		return (long) toplam;

	}

}
