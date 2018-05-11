package dao.kirsalkalkinma.kooperatif;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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

	@Override
	public List<Kooperatif> tureVeIlceyeGoreKooperatifListesi(Long turID, String ilce) {
		Criteria criteriaKoop = sessionFactory.getCurrentSession().createCriteria(Kooperatif.class);
		criteriaKoop.createAlias("kooperatifIlceID", "kooperatifIlceID");

		criteriaKoop.add(Restrictions.eq("kooperatifIlceID.isim", ilce));
		criteriaKoop.add(Restrictions.eq("kooperatifTurID.id", turID));

		criteriaKoop.addOrder(Order.asc("kooperatifTurID"));
		return criteriaKoop.list();
	}

	@Override
	public Long ilceyeVeTureGoreKayitSayisi(Long tur, String ilce) {
		Criteria criteriaKoop = sessionFactory.getCurrentSession().createCriteria(Kooperatif.class);

		criteriaKoop.createAlias("kooperatifIlceID", "tip");
		// criteriaKoop.createAlias("tip.tip", "tip2");
		criteriaKoop.createAlias("kooperatifTurID", "kategori");
		criteriaKoop.add(Restrictions.and(Restrictions.eq("tip.isim", ilce)).add(Restrictions.eq("kategori.id", tur)));
		criteriaKoop.setProjection(Projections.rowCount());
		return (Long) criteriaKoop.uniqueResult();
	}

	@Override
	public JSONArray tureVeIlceyeGoreKooperatiflerJSON(Long turID, String ilce) {

		Criteria criteriaKoop = sessionFactory.getCurrentSession().createCriteria(Kooperatif.class);
		criteriaKoop.createAlias("kooperatifIlceID", "kooperatifIlceID");

		criteriaKoop.add(Restrictions.eq("kooperatifIlceID.isim", ilce));
		criteriaKoop.add(Restrictions.eq("kooperatifTurID.id", turID));

		criteriaKoop.addOrder(Order.asc("kooperatifTurID"));
		JSONArray donecek = new JSONArray();
		List<Kooperatif> IslemListesi = new ArrayList<Kooperatif>();
		IslemListesi = criteriaKoop.list();
		Iterator<Kooperatif> iterator = IslemListesi.iterator();
		while (iterator.hasNext()) {
			JSONObject jsonObject = new JSONObject();
			Kooperatif tip = iterator.next();
			jsonObject.put("id", tip.getId());
			jsonObject.put("kooperatifAdi", tip.getKooperatifAdi());

			donecek.add(jsonObject);
		}

		return (donecek);
	}

	@Override
	public List<Kooperatif> durumListesi() {
		Criteria criteriaKoop = sessionFactory.getCurrentSession().createCriteria(Kooperatif.class);
		criteriaKoop.addOrder(Order.asc("durum"));
		criteriaKoop.setProjection(Projections.distinct(Projections.property("durum")));
		criteriaKoop.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteriaKoop.list();
	}

	@Override
	public List<Kooperatif> durumaGoreGetir(String durum) {
		Criteria criteriaKoop = sessionFactory.getCurrentSession().createCriteria(Kooperatif.class);
		criteriaKoop.add(Restrictions.eq("durum", durum));
		return criteriaKoop.list();
	}

	@Override
	public List<Kooperatif> koopBul(String koopAdi) {
		Criteria criteriaKoopBul = sessionFactory.getCurrentSession().createCriteria(Kooperatif.class);

		criteriaKoopBul.add((Restrictions.disjunction()
				.add(Restrictions.or(Restrictions.ilike("kooperatifAdi", "%" + koopAdi + "%"))
						.add(Restrictions.like("kooperatifAdi", "%" + koopAdi + "%"))

		)));

		return criteriaKoopBul.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();

	}

}
