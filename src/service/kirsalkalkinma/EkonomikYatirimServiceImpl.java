package service.kirsalkalkinma;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.kirsalkalkinma.ekonomikyatirim.EkonomikYatirimDAO;
import forms.kirsalkalkinma.ekonomikyatirim.EkonomikYatirim;

@Service
public class EkonomikYatirimServiceImpl implements EkonomikYatirimService {
	@Autowired
	private EkonomikYatirimDAO ekonomikYatirimDAO;

	@Override
	public void save(EkonomikYatirim kategori) {
		ekonomikYatirimDAO.save(kategori);
	}

	@Override
	public void delete(Long id) {
		ekonomikYatirimDAO.delete(id);

	}

	@Override
	public EkonomikYatirim ekonomikYatirimGetir(Long id) {
		return ekonomikYatirimDAO.ekonomikYatirimGetir(id);
	}

	@Override
	public List<EkonomikYatirim> tumYatirimListesi() {
		return ekonomikYatirimDAO.tumYatirimListesi();
	}

	@Override
	public List<EkonomikYatirim> etapNoyaGoreListe(Integer etapNo) {
		// TODO Auto-generated method stub
		return ekonomikYatirimDAO.etapNoyaGoreListe(etapNo);
	}

}
