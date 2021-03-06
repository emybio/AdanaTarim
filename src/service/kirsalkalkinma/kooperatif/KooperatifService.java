package service.kirsalkalkinma.kooperatif;

import java.util.List;

import org.json.simple.JSONArray;

import forms.kirsalkalkinma.kooperatif.Kooperatif;

public interface KooperatifService {
	public void save(Kooperatif kooperatif);

	public void delete(Long id);

	public Kooperatif kooperatifGetir(Long id);

	public List<Kooperatif> tumKooperatiflerler();

	public List<Kooperatif> ilceyeGoreKooperatifListesi(Long ilceID);

	public List<Kooperatif> ilceListesi();

	public List<Kooperatif> turListesi();

	public List<Kooperatif> tureGoreKooperatifListesi(Long turID);

	public List<Kooperatif> kooperatifKartiGetir(Long koopId);

	public List<Kooperatif> tureVeIlceyeGoreKooperatifListesi(Long turID, String ilce);

	public Long ilceyeVeTureGoreKayitSayisi(Long tur, String ilce);

	public JSONArray tureVeIlceyeGoreKooperatiflerJSON(Long turID, String ilce);

	public List<Kooperatif> durumListesi();

	public List<Kooperatif> durumaGoreGetir(String durum);
	
	public List<Kooperatif> koopBul(String koopAdi);
}
