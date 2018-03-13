package dao.kirsalkalkinma.ekonomikyatirim;

import java.util.List;

import forms.kirsalkalkinma.ekonomikyatirim.EkonomikYatirim_Ust_Kategori;

public interface EkonomikYatirimUstKategoriDAO {

	public void save(EkonomikYatirim_Ust_Kategori kategori);

	public void delete(Long id);

	public EkonomikYatirim_Ust_Kategori ekonomilYatirimKategoriGetir(Long id);

	public List<EkonomikYatirim_Ust_Kategori> tumEkonomikYatirimKategoriListesi();

}
