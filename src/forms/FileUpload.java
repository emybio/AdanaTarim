package forms;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "file_upload", schema = "public")
public class FileUpload {
	@Id
	@Column(name = "id")
	@SequenceGenerator(name = "tabloSequnce", sequenceName = "file_upload_id_seq")
	@GeneratedValue(generator = "tabloSequnce")
	private Long id;

	@Column(name = "dosya_adi")
	private String dosyaAdi;

	@Column(name = "dosya_tanimi")
	private String dosyaTanimi;
	
	@Lob
	@Column(name = "file_data")
	private byte[] fileData;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDosyaAdi() {
		return dosyaAdi;
	}

	public void setDosyaAdi(String dosyaAdi) {
		this.dosyaAdi = dosyaAdi;
	}

	public String getDosyaTanimi() {
		return dosyaTanimi;
	}

	public void setDosyaTanimi(String dosyaTanimi) {
		this.dosyaTanimi = dosyaTanimi;
	}

	public byte[] getFileData() {
		return fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}

	public FileUpload(String dosyaAdi, String dosyaTanimi, byte[] fileData) {
		this.dosyaAdi = dosyaAdi;
		this.dosyaTanimi = dosyaTanimi;
		this.fileData = fileData;
	}
	
public FileUpload() {}	
}
