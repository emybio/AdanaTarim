package dao.kirsalkalkinma.gencciftci;

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
		gencCiftci.createAlias("ilce", "ilce");
		gencCiftci.add(Restrictions.eq("ilce.isim", ilce));

		return gencCiftci.list();
	}

	@Override
	public List<GencCiftci> kategoriyeGoreListe(String kategori) {
		Criteria gencCiftci = sessionFactory.getCurrentSession().createCriteria(GencCiftci.class);
		gencCiftci.createAlias("kategori", "kategori");
		gencCiftci.add(Restrictions.eq("kategori.isim", kategori));

		return gencCiftci.list();
	}

	@Override
	public List<GencCiftci> ilceListesi() {
		Criteria gencCiftci = sessionFactory.getCurrentSession().createCriteria(GencCiftci.class);
		gencCiftci.createAlias("ilce", "ilce");
		gencCiftci.setProjection(Projections.distinct(Projections.property("ilce.isim")));
		gencCiftci.addOrder(Order.asc("ilce.isim"));
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
}
