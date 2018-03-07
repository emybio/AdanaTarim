package service.kirsalkalkinma;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.kirsalkalkinma.ekonomikyatirim.EkonomikYatirimKategoriDAO;
import forms.kirsalkalkinma.ekonomikyatirim.EkonomikYatirim_Kategori;

@Service
public class EkonomikYatirimKategoriServiceImpl implements EkonomikYatirimKategoriService {

	@Autowired
	private EkonomikYatirimKategoriDAO ekonomikYatirimKategoriDAO;

	@Override
	public void save(EkonomikYatirim_Kategori ekonomikYatirimKategori) {
		ekonomikYatirimKategoriDAO.save(ekonomikYatirimKategori);
	}

	@Override
	public void delete(Long id) {
		ekonomikYatirimKategoriDAO.delete(id);

	}

	@Override
	public EkonomikYatirim_Kategori ekonomikYatirimKategoriGetir(Long id) {
		return ekonomikYatirimKategoriDAO.ekonomilYatirimKategoriGetir(id);
	}

	@Override
	public List<EkonomikYatirim_Kategori> tumEkonomikYatirimKategoriListesi() {
		// TODO Auto-generated method stub
		return ekonomikYatirimKategoriDAO.tumEkonomikYatirimKategoriListesi();
	}

}
