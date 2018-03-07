package dao.kirsalkalkinma.gencciftci;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import forms.kirsalkalkinma.gencciftci.GencCiftciKategori;

@Transactional
@Repository

public class GencCiftciKategoriDAOImpl implements GencCiftciKategoriDAO {

	@Autowired
	SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.YerEklemeDAO#ekle(forms.Yerler)
	 */
	@Override
	public void ekle(GencCiftciKategori yerler) {
		sessionFactory.getCurrentSession().saveOrUpdate(yerler);

	}

	@Override
	public List<GencCiftciKategori> tipGetir() {
		Criteria criteriaSabittips = sessionFactory.getCurrentSession().createCriteria(GencCiftciKategori.class);
		// //System.out.println("*******************************************1");
		criteriaSabittips.addOrder(Order.asc("isim"));
		criteriaSabittips.add(Restrictions.isNull("tip"));
		@SuppressWarnings("unchecked")
		List<GencCiftciKategori> sonuc = criteriaSabittips.list();
		return sonuc;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GencCiftciKategori> altTipGetir(Long Id, Boolean durum) {
		List<GencCiftciKategori> sonuc = null;
		if (Id == null)
			sonuc = tipGetir();
		else {
			Criteria criteriaSabittips = sessionFactory.getCurrentSession().createCriteria(GencCiftciKategori.class);
			// //System.out.println("*******************************************1");
			criteriaSabittips.addOrder(Order.asc("isim"));
			criteriaSabittips.add(Restrictions.eq("tip.id", Id));
			criteriaSabittips.add(Restrictions.eq("durum", durum));
			sonuc = criteriaSabittips.list();
			// System.out.println("*******************************************2");
			// System.out.println("************"+sonuc.toArray().length);
		}
		return sonuc;
	}

	@Override
	public GencCiftciKategori tipsGetir(Long id) {
		// System.out.println("******************************1");
		GencCiftciKategori tips = (GencCiftciKategori) sessionFactory.getCurrentSession().load(GencCiftciKategori.class,
				id);
		// System.out.println("******************************"+
		tips.getId();
		return tips;
	}

	@Override
	public void tipsil(Long id) {
		sessionFactory.getCurrentSession().delete(tipsGetir(id));
	}

	@Override
	public void kaydet(Object nesne) {
		sessionFactory.getCurrentSession().saveOrUpdate(nesne);
	}

	@Override
	public Integer calistir(String sorgu) {
		return sessionFactory.getCurrentSession().createQuery(sorgu).executeUpdate();
	}

	@Override
	public Object getir(Long id, @SuppressWarnings("rawtypes") Class sinif) {
		// load yerine get kullanildi getCurrentSession yeri be
		Object nesne = sessionFactory.getCurrentSession().get(sinif, id);
		return nesne;
	}

	@Override
	public Boolean isimVarMi(String isim) {
		Criteria criteriaSabittips = sessionFactory.getCurrentSession().createCriteria(GencCiftciKategori.class);
		criteriaSabittips.add(Restrictions.eq("isim", isim));
		@SuppressWarnings("rawtypes")
		List sonucList = criteriaSabittips.list();
		return (sonucList != null && sonucList.size() > 0);
	}

}
