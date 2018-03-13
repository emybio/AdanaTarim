package dao.kirsalkalkinma.ekonomikyatirim;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import forms.kirsalkalkinma.ekonomikyatirim.EkonomikYatirim_Kategori;
import forms.kirsalkalkinma.ekonomikyatirim.EkonomikYatirim_Ust_Kategori;

@Transactional
@Repository
public class EkonomikYatirimUstKategoriDAOImpl implements EkonomikYatirimUstKategoriDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(EkonomikYatirim_Ust_Kategori kategori) {
		sessionFactory.getCurrentSession().saveOrUpdate(kategori);
	}

	@Override
	public void delete(Long id) {
		sessionFactory.getCurrentSession().delete(ekonomilYatirimKategoriGetir(id));
	}

	@Override
	public EkonomikYatirim_Ust_Kategori ekonomilYatirimKategoriGetir(Long id) {

		EkonomikYatirim_Ust_Kategori kategori = (EkonomikYatirim_Ust_Kategori) sessionFactory.getCurrentSession()
				.load(EkonomikYatirim_Ust_Kategori.class, id);

		kategori.getId();
		return kategori;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EkonomikYatirim_Ust_Kategori> tumEkonomikYatirimKategoriListesi() {

		Criteria tumekonomikYatirimKategoriListesi = sessionFactory.getCurrentSession()
				.createCriteria(EkonomikYatirim_Ust_Kategori.class);

		return tumekonomikYatirimKategoriListesi.list();
	}

}
