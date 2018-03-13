package service.kirsalkalkinma;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.kirsalkalkinma.ekonomikyatirim.EkonomikYatirimUstKategoriDAO;
import forms.kirsalkalkinma.ekonomikyatirim.EkonomikYatirim_Ust_Kategori;

@Service
public class EkonomikYatirimUstKategoriServiceImpl implements EkonomikYatirimUstKategoriService {

	@Autowired
	private EkonomikYatirimUstKategoriDAO ekonomikYatirimKategoriDAO;

	@Override
	public void save(EkonomikYatirim_Ust_Kategori ekonomikYatirimKategori) {
		ekonomikYatirimKategoriDAO.save(ekonomikYatirimKategori);
	}

	@Override
	public void delete(Long id) {
		ekonomikYatirimKategoriDAO.delete(id);

	}

	@Override
	public EkonomikYatirim_Ust_Kategori ekonomikYatirimKategoriGetir(Long id) {
		return ekonomikYatirimKategoriDAO.ekonomilYatirimKategoriGetir(id);
	}

	@Override
	public List<EkonomikYatirim_Ust_Kategori> tumEkonomikYatirimKategoriListesi() {
		// TODO Auto-generated method stub
		return ekonomikYatirimKategoriDAO.tumEkonomikYatirimKategoriListesi();
	}

}
