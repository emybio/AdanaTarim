package forms.hayvansagligi;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sun.istack.internal.NotNull;

import forms.Yerler;

@Entity
@Table(name = "asi_karti", schema = "public")
public class AsiKarti {

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "tabloSequnce", sequenceName = "asi_karti_id_seq")
	@GeneratedValue(generator = "tabloSequnce")
	private Long id;

	@JoinColumn(name = "asi_cinsi")
	@ManyToOne
	private AsiCesitleri asiCinsi;

	@Column(name = "verilis_tarih")
	private String verilisTarih;

	@Column(name = "teslim_alan_ad_soyad")
	private String teslimAlanAdSoyad;

	@Column(name = "miktar")
	private Long miktar;

	@Column(name = "birim_fiyati")
	private Long birimFiyati;

	@Column(name = "odeme_tarihi")
	private String odemeTarihi;

	@Column(name = "odeme_tutari")
	private Long odemeTutari;

	@Column(name = "odeme_yeri")
	private String odemeYeri;

	@JoinColumn
	@ManyToOne()
	@NotNull
	public Yerler ilce;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AsiCesitleri getAsiCinsi() {
		if (asiCinsi == null)
			asiCinsi = new AsiCesitleri();

		return asiCinsi;
	}

	public void setAsiCinsi(AsiCesitleri asiCinsi) {
		this.asiCinsi = asiCinsi;
	}

	public String getVerilisTarih() {

		return verilisTarih;
	}

	public void setVerilisTarih(String verilisTarih) {
		this.verilisTarih = verilisTarih;
	}

	public String getTeslimAlanAdSoyad() {
		return teslimAlanAdSoyad;
	}

	public void setTeslimAlanAdSoyad(String teslimAlanAdSoyad) {
		this.teslimAlanAdSoyad = teslimAlanAdSoyad;
	}

	public Long getMiktar() {
		return miktar;
	}

	public void setMiktar(Long miktar) {
		this.miktar = miktar;
	}

	public Long getBirimFiyati() {
		return birimFiyati;
	}

	public void setBirimFiyati(Long birimFiyati) {
		this.birimFiyati = birimFiyati;
	}

	public String getOdemeTarihi() {
		return odemeTarihi;
	}

	public void setOdemeTarihi(String odemeTarihi) {
		this.odemeTarihi = odemeTarihi;
	}

	public Long getOdemeTutari() {
		return odemeTutari;
	}

	public void setOdemeTutari(Long odemeTutari) {
		this.odemeTutari = odemeTutari;
	}

	public String getOdemeYeri() {
		return odemeYeri;
	}

	public void setOdemeYeri(String odemeYeri) {
		this.odemeYeri = odemeYeri;
	}

	public Yerler getIlce() {
		if (ilce == null)

			ilce = new Yerler();
		return ilce;
	}

	public void setIlce(Yerler ilce) {
		this.ilce = ilce;
	}

	@Override
	public String toString() {
		return "AsiKarti [id=" + id + ", asiCinsi=" + asiCinsi + ", verilisTarih=" + verilisTarih
				+ ", teslimAlanAdSoyad=" + teslimAlanAdSoyad + ", miktar=" + miktar + ", birimFiyati=" + birimFiyati
				+ ", odemeTarihi=" + odemeTarihi + ", odemeTutari=" + odemeTutari + ", odemeYeri=" + odemeYeri
				+ ", ilce=" + ilce + ", getId()=" + getId() + ", getAsiCinsi()=" + getAsiCinsi()
				+ ", getVerilisTarih()=" + getVerilisTarih() + ", getTeslimAlanAdSoyad()=" + getTeslimAlanAdSoyad()
				+ ", getMiktar()=" + getMiktar() + ", getBirimFiyati()=" + getBirimFiyati() + ", getOdemeTarihi()="
				+ getOdemeTarihi() + ", getOdemeTutari()=" + getOdemeTutari() + ", getOdemeYeri()=" + getOdemeYeri()
				+ ", getIlce()=" + getIlce() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

}
