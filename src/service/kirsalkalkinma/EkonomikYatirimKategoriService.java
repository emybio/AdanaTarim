package service.kirsalkalkinma;

import java.util.List;

import forms.kirsalkalkinma.ekonomikyatirim.EkonomikYatirim_Kategori;

public interface EkonomikYatirimKategoriService {

	public void save(EkonomikYatirim_Kategori kategori);

	public void delete(Long id);

	public EkonomikYatirim_Kategori ekonomikYatirimKategoriGetir(Long id);

	public List<EkonomikYatirim_Kategori> tumEkonomikYatirimKategoriListesi();
}
