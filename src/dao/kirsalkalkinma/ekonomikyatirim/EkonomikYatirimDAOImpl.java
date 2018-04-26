package dao.kirsalkalkinma.ekonomikyatirim;

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

import forms.Arac;
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

	@Override
	public JSONArray ilceyeGoreJSON(String ilce) {
		Criteria ekonomikYatirim = sessionFactory.getCurrentSession().createCriteria(EkonomikYatirim.class);
		ekonomikYatirim.createAlias("ilce", "ilce");
		ekonomikYatirim.add(Restrictions.eq("ilce.isim", ilce));
		ekonomikYatirim.addOrder(Order.asc("etapNo"));

		JSONArray donecek = new JSONArray();
		List<EkonomikYatirim> IslemListesi = new ArrayList<EkonomikYatirim>();
		IslemListesi = ekonomikYatirim.list();
		Iterator<EkonomikYatirim> iterator = IslemListesi.iterator();
		while (iterator.hasNext()) {
			JSONObject jsonObject = new JSONObject();
			EkonomikYatirim tip = iterator.next();
			jsonObject.put("id", tip.getId());
			jsonObject.put("durum", tip.getDurum().getDurumAdi());
			jsonObject.put("etapNo", tip.getEtapNo());
			jsonObject.put("hibeTutari", tip.getHibeTutari());
			jsonObject.put("ilce", tip.getIlce().getIsim());
			jsonObject.put("islamYapan", tip.getIslemYapan().getAdi());
			jsonObject.put("islamZamani", tip.getIslemZamani());
			jsonObject.put("istihdam", tip.getIstihdam());
			jsonObject.put("kapasite", tip.getKapasite());
			jsonObject.put("kapasiteBirim", tip.getKapasiteBirim());
			jsonObject.put("kategori", tip.getKategori().getKategoriAdi());
			jsonObject.put("projeAdi", tip.getProjeAdi());
			jsonObject.put("projeBedeli", tip.getProjeBedeli());
			jsonObject.put("yatirimciAdi", tip.getYatirimciAdi());

			donecek.add(jsonObject);
		}

		return (donecek);
	}

	@Override
	public List<EkonomikYatirim> etapNoyaGoreGetir(Integer etapNo) {
		Criteria ekonomikYatirimList = sessionFactory.getCurrentSession().createCriteria(EkonomikYatirim.class);
		ekonomikYatirimList.createAlias("ilce", "ilce");
		ekonomikYatirimList.add(Restrictions.eq("etapNo", etapNo));
		ekonomikYatirimList.addOrder(Order.asc("ilce.isim"));
		return ekonomikYatirimList.list();
	}

	@Override
	public boolean kayitVarmi(int kategori, String isim) {
		Criteria criteriaArac = sessionFactory.getCurrentSession().createCriteria(EkonomikYatirim.class);

		criteriaArac
				.add(Restrictions.and(Restrictions.eq("etapNo", kategori)).add(Restrictions.eq("yatirimciAdi", isim)));

		List<EkonomikYatirim> sonucList = criteriaArac.list();
		return (sonucList != null && sonucList.size() > 0);
	}

	@Override
	public List<EkonomikYatirim> ilceVeKategoriyeGoreListe(int etapNo, Long kategori) {
		Criteria ekonomikYatirimList = sessionFactory.getCurrentSession().createCriteria(EkonomikYatirim.class);
		ekonomikYatirimList.createAlias("ilce", "ilce");
		ekonomikYatirimList.createAlias("kategori", "kategori");
		ekonomikYatirimList
				.add(Restrictions.and(Restrictions.eq("etapNo", etapNo)).add(Restrictions.eq("kategori.id", kategori)));
		ekonomikYatirimList.addOrder(Order.asc("ilce.isim"));
		return ekonomikYatirimList.list();
	}

	@Override
	public List<EkonomikYatirim> etapNoLisetsi() {
		Criteria ekonomikYatirimList = sessionFactory.getCurrentSession().createCriteria(EkonomikYatirim.class);
		ekonomikYatirimList.setProjection(Projections.distinct(Projections.property("etapNo")));
		ekonomikYatirimList.addOrder(Order.desc("etapNo"));
		return ekonomikYatirimList.list();
	}

	@Override
	public List<EkonomikYatirim> kategoriLisetsi() {
		Criteria ekonomikYatirimList = sessionFactory.getCurrentSession().createCriteria(EkonomikYatirim.class);
		ekonomikYatirimList.createAlias("kategori", "kategori");
		ekonomikYatirimList.setProjection(Projections.distinct(Projections.property("kategori.kategoriAdi")));
		return ekonomikYatirimList.list();
	}
}
