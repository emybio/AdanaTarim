package service.kirsalkalkinma;

import java.util.List;

import forms.kirsalkalkinma.gencciftci.GencCiftciKategori;

public interface GencCiftciKategoriService {
	public void ekle(GencCiftciKategori yerler);

	public List<GencCiftciKategori> tipGetir();

	public List<GencCiftciKategori> altTipGetir(Long Id, Boolean durum);

	public void tipsil(Long id);

	public GencCiftciKategori tipsGetir(Long id);

	public void kaydet(Object nesne);

	public Integer calistir(String sorgu);

	public Object getir(Long id, @SuppressWarnings("rawtypes") Class sinif);

	public boolean isimVarMi(String isim);
}
