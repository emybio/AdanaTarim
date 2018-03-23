package forms;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import forms.kirsalkalkinma.ekonomikyatirim.EkonomikYatirim;
import forms.kirsalkalkinma.gencciftci.GencCiftci;
import forms.kirsalkalkinma.kooperatif.Kooperatif;

@Entity
@Table(name = "tum_tablolar", schema = "public")
public class TumTablolar implements java.io.Serializable {
	private static final long serialVersionUID = 4418029727139184238L;

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "tabloSequnce", sequenceName = "tum_tablolar_id_seq")
	@GeneratedValue(generator = "tabloSequnce")
	private Long id;

	@OneToOne
	@JoinColumn(name = "arac_id")
	private Arac arac;

	@OneToOne
	@JoinColumn(name = "satis_id")
	private AraziIslemHareketleri satisIslemleri;

	@OneToOne
	@JoinColumn(name = "koop_id")
	private Kooperatif koop;

	@OneToOne
	@JoinColumn(name = "genc_id")
	private GencCiftci gencCiftci;

	@OneToOne
	@JoinColumn(name = "ekonomik_id")
	private EkonomikYatirim ekonomiYatirim;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Arac getArac() {
		return arac;
	}

	public void setArac(Arac arac) {
		this.arac = arac;
	}

	public AraziIslemHareketleri getSatisIslemleri() {
		return satisIslemleri;
	}

	public void setSatisIslemleri(AraziIslemHareketleri satisIslemleri) {
		this.satisIslemleri = satisIslemleri;
	}

	public Kooperatif getKoop() {
		return koop;
	}

	public void setKoop(Kooperatif koop) {
		this.koop = koop;
	}

	public GencCiftci getGencCiftci() {
		return gencCiftci;
	}

	public void setGencCiftci(GencCiftci gencCiftci) {
		this.gencCiftci = gencCiftci;
	}

	public EkonomikYatirim getEkonomiYatirim() {
		return ekonomiYatirim;
	}

	public void setEkonomiYatirim(EkonomikYatirim ekonomiYatirim) {
		this.ekonomiYatirim = ekonomiYatirim;
	}

	public TumTablolar(Arac arac, AraziIslemHareketleri satisIslemleri, Kooperatif koop, GencCiftci gencCiftci,
			EkonomikYatirim ekonomiYatirim) {
		super();
		this.arac = arac;
		this.satisIslemleri = satisIslemleri;
		this.koop = koop;
		this.gencCiftci = gencCiftci;
		this.ekonomiYatirim = ekonomiYatirim;
	}

	@Override
	public String toString() {
		return "TumTablolar [id=" + id + ", arac=" + arac + ", satisIslemleri=" + satisIslemleri + ", koop=" + koop
				+ ", gencCiftci=" + gencCiftci + ", ekonomiYatirim=" + ekonomiYatirim + ", getId()=" + getId()
				+ ", getArac()=" + getArac() + ", getSatisIslemleri()=" + getSatisIslemleri() + ", getKoop()="
				+ getKoop() + ", getGencCiftci()=" + getGencCiftci() + ", getEkonomiYatirim()=" + getEkonomiYatirim()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arac == null) ? 0 : arac.hashCode());
		result = prime * result + ((ekonomiYatirim == null) ? 0 : ekonomiYatirim.hashCode());
		result = prime * result + ((gencCiftci == null) ? 0 : gencCiftci.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((koop == null) ? 0 : koop.hashCode());
		result = prime * result + ((satisIslemleri == null) ? 0 : satisIslemleri.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TumTablolar other = (TumTablolar) obj;
		if (arac == null) {
			if (other.arac != null)
				return false;
		} else if (!arac.equals(other.arac))
			return false;
		if (ekonomiYatirim == null) {
			if (other.ekonomiYatirim != null)
				return false;
		} else if (!ekonomiYatirim.equals(other.ekonomiYatirim))
			return false;
		if (gencCiftci == null) {
			if (other.gencCiftci != null)
				return false;
		} else if (!gencCiftci.equals(other.gencCiftci))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (koop == null) {
			if (other.koop != null)
				return false;
		} else if (!koop.equals(other.koop))
			return false;
		if (satisIslemleri == null) {
			if (other.satisIslemleri != null)
				return false;
		} else if (!satisIslemleri.equals(other.satisIslemleri))
			return false;
		return true;
	}

}
