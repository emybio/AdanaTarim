package dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import forms.Kullanici;
import forms.KullaniciPicture;

@Repository
public class KullaniciDAOImpl implements KullaniciDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public Kullanici kullaniciGiris(String isim, String sifre) {

		@SuppressWarnings("unused")
		Kullanici sonuc = null;
		/*
		 * currentSession() kullandığımız için session.close() yapmamıza gerek
		 * yok, transaction otomatik olarak temizlenecek (flush) ve kapanacak. eğer
		 * session.open() kullansaydık manuel olarak session kapatmamız
		 * (session.close()) gerekecekti.
		 */
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Kullanici.class);
		// criteria.add(Example.create(kullanici));
		criteria.add(Restrictions.eq("isimSoyisim", isim));
		criteria.add(Restrictions.eq("sifre", sifre));
		@SuppressWarnings("unchecked")
		List<Kullanici> kullaniciListe = criteria.list();
		if (kullaniciListe != null && kullaniciListe.size() > 0) {
			return kullaniciListe.get(0);
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Kullanici> kullanici() {
		Criteria critKullanici = sessionFactory.getCurrentSession().createCriteria(Kullanici.class);
		critKullanici.addOrder(Order.asc("isimSoyisim"));
		List<Kullanici> kullaniciListe = critKullanici.list();

		return kullaniciListe;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Kullanici> kullaniciGetir(Long id) {
		Criteria critKullanici = sessionFactory.getCurrentSession().createCriteria(Kullanici.class);

		critKullanici.add(Restrictions.eq("id", id));
		// ProjectionList projList = Projections.projectionList();
		// projList.add(Projections.property("isimSoyisim"));
		// projList.add(Projections.property("sicilNo"));
		// projList.add(Projections.property("birim"));
		// projList.add(Projections.property("unvan"));
		// projList.add(Projections.property("cepTelefonu"));
		// projList.add(Projections.property("ePosta"));
		// critKullanici.setProjection(Projections.distinct(projList));

		System.out.println("Kullanici DAOImpl kullaniciGetir: " + critKullanici.list().get(0));
		return critKullanici.list();
	}

	@SuppressWarnings("unused")
	@Override
	@Transactional
	public Kullanici kullaniciGetirr(Long id) {
		org.hibernate.Session session = sessionFactory.openSession();
		Kullanici kullanici = (Kullanici) sessionFactory.getCurrentSession().load(Kullanici.class, id);
		kullanici.getId();
		return kullanici;
	}

	@Override
	@Transactional
	public void kullaniciEkle(Kullanici kullanici) {

		sessionFactory.getCurrentSession().saveOrUpdate(kullanici);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.KullaniciDAO#aktifKullaniciListesi(java.lang.Boolean)
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Kullanici> aktifKullaniciListesi(String birim, char durum) {
		Criteria critKullanici = sessionFactory.getCurrentSession().createCriteria(Kullanici.class);
		critKullanici.addOrder(Order.asc("isimSoyisim"));
		critKullanici.add(Restrictions.eq("durum", durum));
		critKullanici.add(Restrictions.eq("birim", birim));
		List<Kullanici> kullaniciListe = critKullanici.list();

		return kullaniciListe;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.KullaniciDAO#kullaniciBul(java.lang.Long)
	 */
	@Transactional
	@Override
	public Kullanici kullaniciBul(Long id) {
		Criteria crtKullanici = sessionFactory.getCurrentSession().createCriteria(Kullanici.class);
		crtKullanici.add(Restrictions.eq("id", id));
		// Kullanici kullanici = (Kullanici)
		// sessionFactory.getCurrentSession().load(Kullanici.class, id);
		// kullanici.getId();
		return (Kullanici) crtKullanici.uniqueResult();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dao.KullaniciDAO#kullaniciEkle2(forms.KullaniciPicture)
	 */
	@SuppressWarnings("null")
	@Override
	@Transactional
	public void kullaniciEkle2(KullaniciPicture kullaniciPicture) {
		Kullanici kullanici = null;

		if (kullaniciPicture.getFileData() != null) {
			byte[] image = kullaniciPicture.getFileData().getBytes();
			if (image != null && image.length > 0)
				kullanici.setPic(image);
		}
		sessionFactory.getCurrentSession().saveOrUpdate(kullanici);
	}
}
