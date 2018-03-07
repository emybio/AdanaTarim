package dao.kirsalkalkinma.gencciftci;

import java.util.List;

import forms.kirsalkalkinma.gencciftci.GencCiftciKategori;

public interface GencCiftciKategoriDAO {
	public void ekle(GencCiftciKategori yerler);

	public List<GencCiftciKategori> tipGetir();

	public List<GencCiftciKategori> altTipGetir(Long Id, Boolean durum);

	public void tipsil(Long id);

	public GencCiftciKategori tipsGetir(Long id);

	public void kaydet(Object nesne);

	public Integer calistir(String sorgu);

	public Object getir(Long id, @SuppressWarnings("rawtypes") Class sinif);

	// public Boolean katidVarMi(Long katId);
	public Boolean isimVarMi(String isim);
}
