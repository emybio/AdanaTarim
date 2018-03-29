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

import forms.kirsalkalkinma.ekonomikyatirim.EkonomikYatirim;
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
		System.out.println("ilce listesi : " + gencCiftci.list().iterator().next());
		return gencCiftci.list();
	}

	@Override
	public List<GencCiftci> kategoriListesi() {
		Criteria gencCiftci = sessionFactory.getCurrentSession().createCriteria(GencCiftci.class);
		gencCiftci.createAlias("kategori", "kategori");
		gencCiftci.setProjection(Projections.distinct(Projections.property("kategori.isim")));
		gencCiftci.addOrder(Order.asc("kategori.isim"));
		return gencCiftci.list();
	}

	@Override
	public JSONArray ilceyeGoreJSON(String ilce) {

		System.out.println("JSON ÝLCE : " + ilce);
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
			jsonObject.put("hibeTutari", tip.getHibeTutari());
			jsonObject.put("kapasite", tip.getKapasite());
			jsonObject.put("kapasiteBirim", tip.getKapasiteBirim());
			jsonObject.put("mahalle", tip.getMahalle().getIsim());
			jsonObject.put("kategori", tip.getKategori());
			jsonObject.put("adi", tip.getYararlaniciAdi());
			jsonObject.put("soyadi", tip.getYararlaniciSoyadi());

			donecek.add(jsonObject);
		}

		return (donecek);
	}

	@Override
	public List<GencCiftci> ilceVeKategoriyeGoreListe(Integer kategori, String ilce) {
		Criteria gencCiftci = sessionFactory.getCurrentSession().createCriteria(GencCiftci.class);
		gencCiftci.createAlias("kategori", "kategori");
		gencCiftci.add(Restrictions.eq("kategori.id", kategori));
		gencCiftci.createAlias("mahalle.tip", "mahalle");
		gencCiftci.add(Restrictions.eq("mahalle.isim", ilce));
		return gencCiftci.list();
	}
}
