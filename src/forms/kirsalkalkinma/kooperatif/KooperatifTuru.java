package forms.kirsalkalkinma.kooperatif;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "kooperatif_turu", schema = "public")
public class KooperatifTuru {

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "tabloSequnce", sequenceName = "kooperatif_tur_id_seq")
	@GeneratedValue(generator = "tabloSequnce")
	public long id;

	@Column(name = "tur_adi")
	public String turAdi;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTurAdi() {
		return turAdi;
	}

	public void setTurAdi(String turAdi) {
		this.turAdi = turAdi;
	}

}
