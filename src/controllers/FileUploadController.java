package controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import dao.FileUploadDAO;
import forms.FileUpload;

@Controller
public class FileUploadController {
	@Autowired
	FileUploadDAO fileRepository;

	@RequestMapping("/welcomepage")
	public String welcomepage(ModelMap model) {

		model.put("title", "Adana Ýl Tarým");

		return "welcomepage";
	}

	@RequestMapping("/upload")
	public String upload() {

		return "upload";
	}

	@ResponseBody
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(@RequestParam("uploadfile") MultipartFile file, HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		FileUpload filemode = null;
		try {

			if (file != null)
				// save file to PostgreSQL
				filemode = new FileUpload(file.getOriginalFilename(), file.getContentType(), file.getBytes());
			if (file.getContentType().equals("application/pdf") || file.getContentType().equals("application/msword")
					|| file.getContentType()
							.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document"))

			{
				fileRepository.upload(filemode);
				// file = null;
				return "Dosya yükleme basarili! -> dosya adi = " + file.getOriginalFilename();
			} else
				return "pdf,word,exel,ppt,txt Formati Disinda Yükleme Yapamazsiniz.Yükleme Yapilan Format : "
						+ file.getContentType();

		} catch (Exception e) {
			return "FAIL! Maybe You had uploaded the file before or the file's size > 500KB";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/api/file/all", method = RequestMethod.GET)
	public List<FileUpload> getListFiles() {
		return fileRepository.getAllList();
	}

	// @RequestMapping(value = "/api/file/{id}", method = RequestMethod.GET)
	// public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
	// Optional<FileUpload> fileOptional = fileRepository.getFileById(id);
	//
	// if (fileOptional.isPresent()) {
	// FileUpload file = fileOptional.get();
	// return ResponseEntity.ok()
	// .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +
	// file.getDosyaAdi() + "\"")
	// .body(file.getFileData());
	// }
	//
	// return ResponseEntity.status(404).body(null);
	// }

	@ResponseBody
	@RequestMapping(value = "/api/file/{id}", method = RequestMethod.GET)
	public String downloadDocument(@PathVariable Long id, HttpServletResponse response) throws IOException {
		FileUpload document = fileRepository.getFileById(id);
		response.setContentType(document.getDosyaTanimi());
		response.setContentLength(document.getFileData().length);
		response.setHeader("Content-Disposition", "attachment; filename=\"" + document.getDosyaAdi() + "\"");

		FileCopyUtils.copy(document.getFileData(), response.getOutputStream());

		return "dosya indiriliyor :" + id;
	}

}
