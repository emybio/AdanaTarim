package service.havansagligi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.hayvansagligi.AsiCesitleriDAO;
import forms.hayvansagligi.AsiCesitleri;
@Service
public class AsiCesitleriServiceImpl implements AsiCesitleriService {

	@Autowired
	AsiCesitleriDAO asiCesitleriDao;

	@Override
	public List<AsiCesitleri> tumListe() {
		// TODO Auto-generated method stub
		return asiCesitleriDao.tumAsilar();
	}

}
