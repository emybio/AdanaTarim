package forms;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "arac_talep", schema = "public")
public class AracTalep implements java.io.Serializable {
	private static final long serialVersionUID = 4418029727139184238L;

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "tabloSequnce", sequenceName = "arac_talep_id_seq")
	@GeneratedValue(generator = "tabloSequnce")
	private Long id;

	@ManyToOne()
	@JoinColumn(name = "islemyapan")
	private Kullanici islemyapan;

	@ManyToOne()
	@JoinColumn(name = "ilce")
	private Yerler ilce;

	@ManyToOne()
	@JoinColumn(name = "mahalle")
	private Yerler mahalle;

	@Column(name = "tarih")
	private String tarih;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "islemzamani")
	private Date islemZamani;

	@Column(name = "isactive")
	private Boolean isActive;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Kullanici getIslemyapan() {
		return islemyapan;
	}

	public void setIslemyapan(Kullanici islemyapan) {
		this.islemyapan = islemyapan;
	}

	public Yerler getIlce() {

		if (ilce == null) {
			ilce = new Yerler();
		}

		return ilce;
	}

	public void setIlce(Yerler ilce) {
		this.ilce = ilce;
	}

	public Yerler getMahalle() {

		if (mahalle == null) {
			mahalle = new Yerler();
		}
		return mahalle;
	}

	public void setMahalle(Yerler mahalle) {
		this.mahalle = mahalle;
	}

	public String getTarih() {
		return tarih;
	}

	public void setTarih(String tarih) {
		this.tarih = tarih;
	}

	public Date getIslemZamani() {
		return islemZamani;
	}

	public void setIslemZamani(Date islemZamani) {
		this.islemZamani = islemZamani;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
