package dao.hayvansagligi;

import java.util.List;

import forms.hayvansagligi.AsiKarti;

public interface AsiKartiDao {
	public void save(AsiKarti asiKarti);

	public void delete(Long id);

	public AsiKarti kartGetir(Long id);

	public List<AsiKarti> tumListe();

	public Long ilceninToplamBorcu(Long ilceID);
}
