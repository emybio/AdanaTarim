package service.kirsalkalkinma;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.kirsalkalkinma.gencciftci.GencCiftciKategoriDAO;
import forms.kirsalkalkinma.gencciftci.GencCiftciKategori;

@Service
public class GencCiftciKategoriServiceImpl implements GencCiftciKategoriService {
	@Autowired
	GencCiftciKategoriDAO yerEklemeDao;

	@Override
	@Transactional
	public void ekle(GencCiftciKategori yerler) {
		yerEklemeDao.ekle(yerler);
	}

	@Override
	@Transactional
	public List<GencCiftciKategori> tipGetir() {
		return yerEklemeDao.tipGetir();
	}

	@Override
	@Transactional
	public List<GencCiftciKategori> altTipGetir(Long Id, Boolean durum) {
		return yerEklemeDao.altTipGetir(Id, durum);
	}

	@Override
	@Transactional
	public GencCiftciKategori tipsGetir(Long id) {
		return yerEklemeDao.tipsGetir(id);
	}

	@Override
	@Transactional
	public void tipsil(Long id) {
		yerEklemeDao.tipsil(id);
	}

	@Override
	@Transactional
	public void kaydet(Object nesne) {
		yerEklemeDao.kaydet(nesne);
	}

	@Override
	@Transactional
	public Integer calistir(String sorgu) {
		return yerEklemeDao.calistir(sorgu);
	}

	@Override
	@Transactional
	public Object getir(Long id, @SuppressWarnings("rawtypes") Class sinif) {
		return yerEklemeDao.getir(id, sinif);
	}

	// @Transactional
	// public boolean katidVarMi(Long katId) {
	// return sabitTipsDAO.katidVarMi( katId);
	// }

	@Override
	@Transactional
	public boolean isimVarMi(String isim) {
		return yerEklemeDao.isimVarMi(isim);
	}

}
