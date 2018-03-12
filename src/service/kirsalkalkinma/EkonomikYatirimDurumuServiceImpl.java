package service.kirsalkalkinma;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.kirsalkalkinma.ekonomikyatirim.EkonomikYatirimDurumuDAO;
import forms.kirsalkalkinma.ekonomikyatirim.EkonomikYatirimDurumu;

@Service
public class EkonomikYatirimDurumuServiceImpl implements EkonomikYatirimDurumuService {
	@Autowired
	private EkonomikYatirimDurumuDAO ekonomikDurumDAO;

	@Override
	public List<EkonomikYatirimDurumu> tumDurumListesi() {
		return ekonomikDurumDAO.tumDurumListesi();
	}

}
