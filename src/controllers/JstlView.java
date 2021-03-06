
package controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.InternalResourceView;

public class JstlView extends InternalResourceView {
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// Determine the path for the request dispatcher.
		String dispatcherPath = prepareForRendering(request, response);

		// set original view being asked for as a request parameter
		request.setAttribute("partial", dispatcherPath);
		request.setAttribute("title", model.get("title"));
		request.setAttribute("durumListesi", model.get("durumListesi"));
		request.setAttribute("islemListesi", model.get("islemListesi"));
		request.setAttribute("ilceListesi", model.get("ilceListesi"));
		request.setAttribute("araziIslem", model.get("araziIslem"));
		request.setAttribute("ilceler", model.get("ilceler"));
		request.setAttribute("kullanici", model.get("kullanici"));
		request.setAttribute("tusYazisi", model.get("tusYazisi"));
		request.setAttribute("islemTipineGöreListe", model.get("islemTipineGöreListe"));
		request.setAttribute("raporlarListesi", model.get("raporlarListesi"));
		request.setAttribute("id", model.get("id"));
		request.setAttribute("donemAy", model.get("donemAy"));
		request.setAttribute("devriIstenenParselSayisi", model.get("devriIstenenParselSayisi"));
		request.setAttribute("devriIstenenParselAlani", model.get("devriIstenenParselAlani"));
		request.setAttribute("izinVerilenParselSayisi", model.get("izinVerilenParselSayisi"));
		request.setAttribute("izinVerilenParselAlani", model.get("izinVerilenParselAlani"));
		request.setAttribute("izinVerilmeyenParselSayisi", model.get("izinVerilmeyenParselSayisi"));
		request.setAttribute("izinVerilmeyenParselAlani", model.get("izinVerilmeyenParselAlani"));

		request.setAttribute("izinFormu", model.get("izinFormu"));
		request.setAttribute("sayfalar", model.get("sayfalar"));
		request.setAttribute("sayfaNo", model.get("sayfaNo"));
		request.setAttribute("kullaniciListesi", model.get("kullaniciListesi"));
		request.setAttribute("kullaniciListesi2", model.get("kullaniciListesi2"));

		request.setAttribute("izinListesi", model.get("izinListesi"));
		request.setAttribute("tumListe", model.get("tumListe"));
		request.setAttribute("kategoriListesi", model.get("kategoriListesi"));
		request.setAttribute("tips", model.get("tips"));
		request.setAttribute("tipListesi", model.get("tipListesi"));
		request.setAttribute("altTipListesi", model.get("altTipListesi"));
		request.setAttribute("markaListesi", model.get("markaListesi"));
		request.setAttribute("modelListesi", model.get("modelListesi"));
		request.setAttribute("durumListesi", model.get("durumListesi"));
		request.setAttribute("saatler", model.get("saatler"));
		request.setAttribute("aylar", model.get("aylar"));
		request.setAttribute("yillar", model.get("yillar"));
		request.setAttribute("secilenYil", model.get("secilenYil"));
		request.setAttribute("secilenIlce", model.get("secilenIlce"));
		request.setAttribute("secilenKategori", model.get("secilenKategori"));
		request.setAttribute("secilenMahalleID", model.get("secilenMahalleID"));
		request.setAttribute("secilenMahalleIsim", model.get("secilenMahalleIsim"));
		request.setAttribute("arac", model.get("arac"));
		request.setAttribute("aracTalep", model.get("aracTalep"));
		request.setAttribute("kooperatif", model.get("kooperatif"));
		request.setAttribute("aricilik", model.get("aricilik"));

		request.setAttribute("resim", model.get("resim"));

		request.setAttribute("aracCikisListesi", model.get("aracCikisListesi"));

		request.setAttribute("aracCikisListesi", model.get("aracCikisListesi"));
		request.setAttribute("turListesi", model.get("turListesi"));

		request.setAttribute("ekonomikYatirim", model.get("ekonomikYatirim"));
		request.setAttribute("projeAdListesi", model.get("projeAdListesi"));
		request.setAttribute("gencCiftci", model.get("gencCiftci"));
		request.setAttribute("girisYapanKullanici", model.get("girisYapanKullanici"));
		request.setAttribute("download", model.get("download"));
		request.setAttribute("dosyaDurumu", model.get("dosyaDurumu"));
		request.setAttribute("url", model.get("url"));
		request.setAttribute("message", model.get("message"));
		request.setAttribute("errorMessage", model.get("errorMessage"));
		request.setAttribute("errors", model.get("errors"));
		request.setAttribute("roll", model.get("roll"));
		request.setAttribute("roller", model.get("roller"));
		request.setAttribute("dataPoints", model.get("dataPoints"));
		request.setAttribute("dataPoints1", model.get("dataPoints1"));
		request.setAttribute("birim", model.get("birim"));
		request.setAttribute("etapNoListesi", model.get("etapNoListesi"));
		request.setAttribute("asiKarti", model.get("asiKarti"));
		request.setAttribute("tumAsiKartListesi", model.get("tumAsiKartListesi"));
		request.setAttribute("tumAsiCesitleri", model.get("tumAsiCesitleri"));

		request.setAttribute("toplam", model.get("toplam"));
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		// force everything to be template.jsp
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/template.jsp");

		requestDispatcher.include(request, response);

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		try {
			chain.doFilter(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}