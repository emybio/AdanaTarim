package dao;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import forms.AraziIslemHareketleri;

@Repository
public class RaporlarDAOImpl implements RaporlarDAO {
	@Autowired
	SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public ArrayList<AraziIslemHareketleri> raporListesi() {
		Criteria criteriaDemirbas = sessionFactory.getCurrentSession()
				.createCriteria(AraziIslemHareketleri.class);
		criteriaDemirbas.addOrder(Order.desc("islemZamani"));

		ArrayList<AraziIslemHareketleri> liste = new ArrayList<AraziIslemHareketleri>(
				criteriaDemirbas.list());

		// List<AraziİslemHareketleri> araziIslemListesi = new
		// ArrayList<AraziİslemHareketleri>();
		// araziIslemListesi = criteriaDemirbas.list();
		// Iterator<AraziİslemHareketleri> iterator =
		// araziIslemListesi.iterator();
		// AraziİslemHareketleri tip = null;
		// ArrayList list = new ArrayList();
		// while (iterator.hasNext()) {
		// list.add(iterator.next().getIlce());
		// list.add(iterator.next().getIslemTipi());
		// }

		return liste;

	}
}
