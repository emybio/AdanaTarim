package forms.hayvansagligi;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import forms.Kullanici;

@Entity
@Table(name = "asi_cesitleri", schema = "public")
public class AsiCesitleri {

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "tabloSequnce", sequenceName = "asi_cesitleri_id_seq")
	@GeneratedValue(generator = "tabloSequnce")
	private Long id;

	@Column(name = "asi_adi")
	public String asiAdi;
	@Column(name = "eklenme_tarihi")
	public String eklenmeTarihi;

	@JoinColumn(name = "kullanici_id")
	@ManyToOne()
	public Kullanici kullanici;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAsiAdi() {
		return asiAdi;
	}

	public void setAsiAdi(String asiAdi) {
		this.asiAdi = asiAdi;
	}

	public String getEklenmeTarihi() {
		return eklenmeTarihi;
	}

	public void setEklenmeTarihi(String eklenmeTarihi) {
		this.eklenmeTarihi = eklenmeTarihi;
	}

	public Kullanici getKullanici() {
		return kullanici;
	}

	public void setKullanici(Kullanici kullanici) {
		this.kullanici = kullanici;
	}

	@Override
	public String toString() {
		return "AsiCesitleri [id=" + id + ", asiAdi=" + asiAdi + ", eklenmeTarihi=" + eklenmeTarihi + ", kullanici="
				+ kullanici + ", getId()=" + getId() + ", getAsiAdi()=" + getAsiAdi() + ", getEklenmeTarihi()="
				+ getEklenmeTarihi() + ", getKullanici()=" + getKullanici() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	public AsiCesitleri(String asiAdi, String eklenmeTarihi, Kullanici kullanici) {
		super();
		this.asiAdi = asiAdi;
		this.eklenmeTarihi = eklenmeTarihi;
		this.kullanici = kullanici;
	}

	public AsiCesitleri() {

	}

}
