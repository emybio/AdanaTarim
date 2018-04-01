package dao.kirsalkalkinma.gencciftci;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import forms.kirsalkalkinma.gencciftci.GencCiftci;

@Transactional
@Repository
@SuppressWarnings("unchecked")
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

	@Override
	public List<GencCiftci> tumGencCiftciler() {
		Criteria tumListe = sessionFactory.getCurrentSession().createCriteria(GencCiftci.class);

		tumListe.addOrder(Order.asc("yil"));
		tumListe.addOrder(Order.asc("yararlaniciSoyadi"));

		return tumListe.list();
	}

	@Override
	public List<GencCiftci> ilceyeGoreListe(String ilce) {
		Criteria gencCiftci = sessionFactory.getCurrentSession().createCriteria(GencCiftci.class);
		gencCiftci.createAlias("mahalle.tip", "mahalle");
		gencCiftci.add(Restrictions.eq("mahalle.isim", ilce));

		return gencCiftci.list();
	}

	@Override
	public List<GencCiftci> kategoriyeGoreListe(Integer kategori) {
		Criteria gencCiftci = sessionFactory.getCurrentSession().createCriteria(GencCiftci.class);
		gencCiftci.createAlias("kategori", "kategori");
		gencCiftci.add(Restrictions.eq("kategori.id", kategori));

		return gencCiftci.list();
	}

	@Override
	public List<GencCiftci> ilceListesi() {
		Criteria gencCiftci = sessionFactory.getCurrentSession().createCriteria(GencCiftci.class);
		// gencCiftci.createAlias("mahalle", "mahalle"); 
		gencCiftci.createAlias("mahalle", "mahalle");
		gencCiftci.setProjection(Projections.distinct(Projections.property("mahalle.tip")));
		gencCiftci.addOrder(Order.asc("mahalle.tip"));
		return gencCiftci.list();
	}
	
	
	@Override
	public List<GencCiftci> mahalleListesi() {
		Criteria gencCiftci = sessionFactory.getCurrentSession().createCriteria(GencCiftci.class);
		// gencCiftci.createAlias("mahalle", "mahalle");
		gencCiftci.createAlias("mahalle", "mahalle");
		gencCiftci.setProjection(Projections.distinct(Projections.property("mahalle")));
		gencCiftci.addOrder(Order.asc("mahalle"));
		return gencCiftci.list();
	}

	@Override
	public List<GencCiftci> kategoriListesi() {
		Criteria gencCiftci = sessionFactory.getCurrentSession().createCriteria(GencCiftci.class);
		gencCiftci.createAlias("kategori", "kategori");
		gencCiftci.setProjection(Projections.distinct(Projections.property("kategori")));
		gencCiftci.addOrder(Order.asc("kategori"));
		return gencCiftci.list();
	}

	@Override
	public JSONArray ilceyeGoreJSON(String ilce) {

		Criteria gencCiftci = sessionFactory.getCurrentSession().createCriteria(GencCiftci.class);
		gencCiftci.createAlias("mahalle.tip", "mahalle");
		// gencCiftci.createAlias("mahalle.tip.isim", "isim");
		gencCiftci.add(Restrictions.eq("mahalle.isim", ilce));
		// gencCiftci.addOrder(Order.asc("mahalle.isim"));

		JSONArray donecek = new JSONArray();
		List<GencCiftci> IslemListesi = new ArrayList<GencCiftci>();
		IslemListesi = gencCiftci.list();
		Iterator<GencCiftci> iterator = IslemListesi.iterator();
		while (iterator.hasNext()) {
			JSONObject jsonObject = new JSONObject();
			GencCiftci tip = iterator.next();
			jsonObject.put("id", tip.getId());
			jsonObject.put("kapasite", tip.getKapasite());
			jsonObject.put("kapasiteBirim", tip.getKapasiteBirim());
			jsonObject.put("kategori", tip.getKategori());
			jsonObject.put("hibeTutari", tip.getHibeTutari());
			jsonObject.put("mahalle", tip.getMahalle().getIsim());
			jsonObject.put("adi", tip.getYararlaniciAdi());
			jsonObject.put("soyadi", tip.getYararlaniciSoyadi());
			jsonObject.put("yil", tip.getYil());

			donecek.add(jsonObject);
		}

		return (donecek);
	}

	@Override
	public List<GencCiftci> ilceVeKategoriyeGoreListe(String kategori, String ilce, Integer yil) {

		Criteria gencCiftci = sessionFactory.getCurrentSession().createCriteria(GencCiftci.class);
		gencCiftci.createAlias("kategori", "kategori");
		gencCiftci.createAlias("mahalle.tip", "mahalle");

		if (!ilce.isEmpty() && kategori.isEmpty() && yil == null) {
			gencCiftci.add(Restrictions.eq("mahalle.isim", ilce));
			System.out.println("1");
		} else if (ilce.isEmpty() && kategori.isEmpty() && yil != null) {
			gencCiftci.add(Restrictions.eq("yil", yil));
			System.out.println("2");

		} else if (ilce.isEmpty() && !kategori.isEmpty() && yil == null) {
			gencCiftci.add(Restrictions.eq("kategori.isim", kategori));
		} else if (!ilce.isEmpty() && yil != null && kategori.isEmpty()) {
			System.out.println("3");
			gencCiftci.add(Restrictions.eq("mahalle.isim", ilce));
			gencCiftci.add(Restrictions.eq("yil", yil));

		} else if (!ilce.isEmpty() && !kategori.isEmpty() && yil == null) {
			System.out.println("4");
			gencCiftci.add(Restrictions.eq("mahalle.isim", ilce));
			gencCiftci.add(Restrictions.eq("kategori.isim", kategori));

		} else if (ilce.isEmpty() && !kategori.isEmpty() && yil != null) {
			System.out.println("5");
			gencCiftci.add(Restrictions.eq("yil", yil));
			gencCiftci.add(Restrictions.eq("kategori.isim", kategori));

		} else if (!ilce.isEmpty() && !kategori.isEmpty() && yil != null) {
			System.out.println("6");
			gencCiftci.add(Restrictions.eq("yil", yil));
			gencCiftci.add(Restrictions.eq("kategori.isim", kategori));
			gencCiftci.add(Restrictions.eq("mahalle.isim", ilce));

		} else if (ilce.isEmpty() && yil == null && kategori.isEmpty()) {
			System.out.println("7");
			return gencCiftci.list();
		}
		return gencCiftci.list();
	}

	@Override
	public Long ilceyeVeYillaraGoreKayitSayisi(Integer yil, String ilce) {
		Criteria gencCiftci = sessionFactory.getCurrentSession().createCriteria(GencCiftci.class);

		if (yil != null) {
			gencCiftci.add(Restrictions.eq("yil", yil));
		}
		gencCiftci.createAlias("mahalle.tip", "mahalle");
		gencCiftci.add(Restrictions.eq("mahalle.isim", ilce));
		gencCiftci.add(Restrictions.eq("yil", yil));
		gencCiftci.setProjection(Projections.rowCount());
		return (Long) gencCiftci.uniqueResult();
	}

	@Override
	public List<GencCiftci> yillaraGoreListe(Integer yil) {
		Criteria gencCiftci = sessionFactory.getCurrentSession().createCriteria(GencCiftci.class);
		if (yil != null)
			gencCiftci.add(Restrictions.eq("yil", yil));

		return gencCiftci.list();
	}

	@Override
	public List<GencCiftci> yilListesi() {
		Criteria gencCiftci = sessionFactory.getCurrentSession().createCriteria(GencCiftci.class);
		gencCiftci.setProjection(Projections.distinct(Projections.property("yil")));
		return gencCiftci.list();
	}

	@Override
	public Long ilceyeVeKategoriyeGoreKayitSayisi(Long kategori, Long ilce) { 
		Criteria gencCiftci = sessionFactory.getCurrentSession().createCriteria(GencCiftci.class);

		gencCiftci.createAlias("mahalle", "mahalle");
		gencCiftci.createAlias("kategori", "kategori");
		gencCiftci.add(Restrictions.eq("mahalle.id", ilce));
		gencCiftci.add(Restrictions.eq("kategori.id", kategori));
		gencCiftci.setProjection(Projections.rowCount());
		return (Long) gencCiftci.uniqueResult();
	}
}
