package forms.kirsalkalkinma.kooperatif;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import forms.Kullanici;
import forms.Yerler;

@Entity
@Table(name = "kooperatif", schema = "public")
public class Kooperatif implements java.io.Serializable {
	private static final long serialVersionUID = 4418029727139184238L;

	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "tabloSequnce", sequenceName = "kooperatif_id_seq")
	@GeneratedValue(generator = "tabloSequnce")
	public long id;

	@Column(name = "kooperatif_adi")
	public String kooperatifAdi;

	@Column(name = "uygulama_projesi")
	public String uygulamaProjesi;

	@Column(name = "kurulus_yili")
	public String kurulusYili;

	@Column(name = "intibak_yili")
	public String intibakYili;

	@Column(name = "ortak_sayisi")
	public Integer ortakSayisi;

	@ManyToOne
	@JoinColumn(name = "kooperatif_ilce_id")
	public Yerler kooperatifIlceID;

	@ManyToOne
	@JoinColumn(name = "kooperatif_tur_id")
	public KooperatifTuru kooperatifTurID;

	@ManyToOne
	@JoinColumn(name = "islem_yapan")
	public Kullanici islemYapan;

	@LazyCollection(LazyCollectionOption.FALSE)
	@ElementCollection()
	@CollectionTable(name = "yonetim_kurulu")
	public List<String> yonetimKuruluList;

	@LazyCollection(LazyCollectionOption.FALSE)
	@ElementCollection()
	@CollectionTable(name = "denetim_kurulu")
	public List<String> denetimKuruluList;

	@Column(name = "son_genel_kurul_tarihi")
	public String sonGenelKurulTarihi;

	@Column(name = "adres")
	public String adres;

	@Column(name = "telefon")
	public String telefon;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "islem_zamani")
	public Date islemZamani;

	@ElementCollection
	@CollectionTable(name = "iletisim_bilgileri")
	public Map<String, String> iletisimBilgileri = new HashMap<String, String>();

	@Column(name = "aktif")
	public Boolean aktif;

	@Column(name = "dagilmis")
	public Boolean dagilmis;

	@Column(name = "koop_bsk")
	public String koopBask;

	@Column(name = "vergi_no")
	public String vergiNo;

	@Column(name = "durum")
	public String durum;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUygulamaProjesi() {
		return uygulamaProjesi;
	}

	public void setUygulamaProjesi(String uygulamaProjesi) {
		this.uygulamaProjesi = uygulamaProjesi;
	}

	public String getKurulusYili() {
		return kurulusYili;
	}

	public void setKurulusYili(String kurulusYili) {
		this.kurulusYili = kurulusYili;
	}

	public Integer getOrtakSayisi() {
		return ortakSayisi;
	}

	public void setOrtakSayisi(Integer ortakSayisi) {
		this.ortakSayisi = ortakSayisi;
	}

	public Yerler getKooperatifIlceID() {

		if (kooperatifIlceID == null)
			kooperatifIlceID = new Yerler();
		return kooperatifIlceID;
	}

	public void setKooperatifIlceID(Yerler kooperatifIlceID) {
		this.kooperatifIlceID = kooperatifIlceID;
	}

	public KooperatifTuru getKooperatifTurID() {

		if (kooperatifTurID == null)
			kooperatifTurID = new KooperatifTuru();
		return kooperatifTurID;
	}

	public void setKooperatifTurID(KooperatifTuru kooperatifTurID) {
		this.kooperatifTurID = kooperatifTurID;
	}

	public List<String> getYonetimKuruluList() {

		return yonetimKuruluList;
	}

	public void setYonetimKuruluList(List<String> yonetimKuruluList) {
		this.yonetimKuruluList = yonetimKuruluList;
	}

	public List<String> getDenetimKuruluList() {

		return denetimKuruluList;
	}

	public void setDenetimKuruluList(List<String> denetimKuruluList) {
		this.denetimKuruluList = denetimKuruluList;
	}

	public String getSonGenelKurulTarihi() {
		return sonGenelKurulTarihi;
	}

	public void setSonGenelKurulTarihi(String sonGenelKurulTarihi) {
		this.sonGenelKurulTarihi = sonGenelKurulTarihi;
	}

	public Map<String, String> getIletisimBilgileri() {
		return iletisimBilgileri;
	}

	public void setIletisimBilgileri(Map<String, String> iletisimBilgileri) {
		this.iletisimBilgileri = iletisimBilgileri;
	}

	public String getKooperatifAdi() {
		return kooperatifAdi;
	}

	public void setKooperatifAdi(String kooperatifAdi) {
		this.kooperatifAdi = kooperatifAdi;
	}

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public Kullanici getIslemYapan() {

		if (islemYapan == null)
			islemYapan = new Kullanici();
		return islemYapan;
	}

	public void setIslemYapan(Kullanici islemYapan) {
		this.islemYapan = islemYapan;
	}

	public Date getIslemZamani() {
		return islemZamani;
	}

	public void setIslemZamani(Date islemZamani) {
		this.islemZamani = islemZamani;
	}

	public Boolean getAktif() {
		return aktif;
	}

	public void setAktif(Boolean aktif) {
		this.aktif = aktif;
	}

	public Boolean getDagilmis() {
		return dagilmis;
	}

	public void setDagilmis(Boolean dagilmis) {
		this.dagilmis = dagilmis;
	}

	public String getVergiNo() {
		return vergiNo;
	}

	public void setVergiNo(String vergiNo) {
		this.vergiNo = vergiNo;
	}

	public String getDurum() {
		return durum;
	}

	public void setDurum(String durum) {
		this.durum = durum;
	}

	public String getIntibakYili() {
		return intibakYili;
	}

	public void setIntibakYili(String intibakYili) {
		this.intibakYili = intibakYili;
	}

	public String getKoopBask() {
		return koopBask;
	}

	public void setKoopBask(String koopBask) {
		this.koopBask = koopBask;
	}

}
