package dao.kirsalkalkinma.ekonomikyatirim;

import java.util.List;

import forms.kirsalkalkinma.EkonomikYatirim_Kategori;

public interface EkonomikYatirimKategoriDAO {

	public void save(EkonomikYatirim_Kategori kategori);

	public void delete(Long id);

	public EkonomikYatirim_Kategori ekonomilYatirimKategoriGetir(Long id);

	public List<EkonomikYatirim_Kategori> tumEkonomikYatirimKategoriListesi();

}
