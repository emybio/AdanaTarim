package forms.kirsalkalkinma;

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

import forms.Kullanici;
import forms.Yerler;

@Entity
@Table(name = "aricilik", schema = "public")
public class Aricilik implements java.io.Serializable {
	private static final long serialVersionUID = 4418029727139184238L;
	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "tabloSequnce", sequenceName = "aricilik_id_seq")
	@GeneratedValue(generator = "tabloSequnce")
	public long id;

	@ManyToOne()
	@JoinColumn(name = "ilce_id")
	public Yerler ilce;

	@Column(name = "adi_soyadi")
	public String yararlaniciAdiSoyadi;

	@Column(name = "baba_adi")
	public String yararlaniciBabaAdiSoyadi;

	@Column(name = "tel_no")
	public String telNo;

	@Column(name = "dogum_tarihi")
	public String dogumTarihi;

	@Column(name = "fatura_bedeli")
	public Integer faturaBedeli;

	@Column(name = "yil")
	public Integer yil;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "islem_zamani")
	public Date islemZamani;

	@ManyToOne()
	@JoinColumn(name = "islem_yapan")
	public Kullanici islemYapan;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Yerler getIlce() {
		if (ilce == null)
			ilce = new Yerler();
		return ilce;
	}

	public void setIlce(Yerler ilce) {

		this.ilce = ilce;
	}

	public String getYararlaniciAdiSoyadi() {
		return yararlaniciAdiSoyadi;
	}

	public void setYararlaniciAdiSoyadi(String yararlaniciAdiSoyadi) {
		this.yararlaniciAdiSoyadi = yararlaniciAdiSoyadi;
	}

	public String getYararlaniciBabaAdiSoyadi() {
		return yararlaniciBabaAdiSoyadi;
	}

	public void setYararlaniciBabaAdiSoyadi(String yararlaniciBabaAdiSoyadi) {
		this.yararlaniciBabaAdiSoyadi = yararlaniciBabaAdiSoyadi;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public String getDogumTarihi() {
		return dogumTarihi;
	}

	public void setDogumTarihi(String dogumTarihi) {
		this.dogumTarihi = dogumTarihi;
	}

	public Integer getFaturaBedeli() {
		return faturaBedeli;
	}

	public void setFaturaBedeli(Integer faturaBedeli) {
		this.faturaBedeli = faturaBedeli;
	}

	public Integer getYil() {
		return yil;
	}

	public void setYil(Integer yil) {
		this.yil = yil;
	}

	public Date getIslemZamani() {
		return islemZamani;
	}

	public void setIslemZamani(Date islemZamani) {
		this.islemZamani = islemZamani;
	}

	public Kullanici getIslemYapan() {
		if (islemYapan == null)
			islemYapan = new Kullanici();
		return islemYapan;
	}

	public void setIslemYapan(Kullanici islemYapan) {

		this.islemYapan = islemYapan;
	}

}
