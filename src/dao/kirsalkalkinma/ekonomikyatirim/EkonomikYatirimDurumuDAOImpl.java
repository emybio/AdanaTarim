package dao.kirsalkalkinma.ekonomikyatirim;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import forms.kirsalkalkinma.ekonomikyatirim.EkonomikYatirimDurumu;

@Transactional
@Repository
public class EkonomikYatirimDurumuDAOImpl implements EkonomikYatirimDurumuDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<EkonomikYatirimDurumu> tumDurumListesi() {

		Criteria ekonomikYatirimList = sessionFactory.getCurrentSession().createCriteria(EkonomikYatirimDurumu.class);

		return ekonomikYatirimList.list();
	}

}
