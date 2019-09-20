package service.havansagligi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.hayvansagligi.AsiKartiDao;
import forms.hayvansagligi.AsiKarti;

@Service
public class AsiKartiServiceImpl implements AsiKartiService {

	@Autowired
	public AsiKartiDao asiKartiDao;

	@Override
	public void save(AsiKarti asiKarti) {
		asiKartiDao.save(asiKarti);

	}

	@Override
	public void delete(Long id) {
		asiKartiDao.delete(id);

	}

	@Override
	public AsiKarti kartGetir(Long id) {

		return asiKartiDao.kartGetir(id);
	}

	@Override
	public List<AsiKarti> tumListe() {
		return asiKartiDao.tumListe();
	}

	@Override
	public Long ilceninToplamBorcu(Long ilceID) {
		// TODO Auto-generated method stub
		return asiKartiDao.ilceninToplamBorcu(ilceID);
	}

}
