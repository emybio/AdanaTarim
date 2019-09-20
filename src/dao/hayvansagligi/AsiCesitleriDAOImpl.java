package dao.hayvansagligi;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import forms.hayvansagligi.AsiCesitleri;

@Repository
public class AsiCesitleriDAOImpl implements AsiCesitleriDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Transactional
	@Override
	public List<AsiCesitleri> tumAsilar() {
		Criteria tumListe = sessionFactory.getCurrentSession().createCriteria(AsiCesitleri.class);

		return tumListe.list();
	}

}
