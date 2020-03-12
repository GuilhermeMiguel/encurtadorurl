package br.com.encurtadorurl.servico;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.com.encurtadorurl.vo.UrlVO;

@Service
public class EncurtadorUrlServico {

	public UrlVO encurtarUrl(String url) {
		
		UrlVO urlVO = new UrlVO();
		
		Map<String, String> armazenaUrl = new HashMap<>(); 
		
		try {
			String recuperaArquivo = leArquivo(url);
			
			if(recuperaArquivo != null) {
				urlVO.setUrl(recuperaArquivo.split("- ")[0].trim());
			}
			else {
				armazenaUrl.put(url, compressUrl(url));	
				armazenaArquivo(armazenaUrl);
				urlVO.setUrl("Gravada - " + url + " -- Encurtada - " + compressUrl(url));
			}
		} 
		catch (IOException e) {
			urlVO.setUrl(armazenaUrl.get(url));
		}
		
		return urlVO;
	}
	
	
	public UrlVO retornarUrl(String url) {
		
		UrlVO urlVO = new UrlVO();
		
		urlVO.setUrl(url + " - " + descompressUrl(url));
		return urlVO;
	}


	public String compressUrl(String url) {
       
		return url.replace("http://", "#+")
				.replace("https://", "#*")
				.replace("www.", "#$")
				.replace(".com.br", "#@")
				.replace(".com", "#!");
    }
	
	public String descompressUrl(String url) {
		
		return url.replace("#+", "http://")
				.replace("#*", "https://")
				.replace("#$", "www.")
				.replace("#@", ".com.br")
				.replace("#!", ".com");
	}
	
	public String compressUrl2(String url) {
	       
		String out = "";
		int sum = 1;
		for (int i = 0; i < url.length() - 1; i++) {
			if(url.charAt(i) == url.charAt(i+1)) {
				sum++;
			} else {
				out = out + url.charAt(i) + sum;
				sum = 1;
			}
		}
		out = out + url.charAt(url.length() - 1) + sum;
		
		return out.length() < url.length() ? out : url;
		
    }
	
	public void armazenaArquivo(Map<String, String> urls) throws IOException {
		
		FileWriter arq = new FileWriter("grava-url.txt", true);
	    PrintWriter gravarArq = new PrintWriter(arq);
	 
	    urls.entrySet().forEach(x -> gravarArq.write(x.getKey() + " - " + x.getValue()+ "\n"));
	 
	    gravarArq.close();
	}
	
	
	@SuppressWarnings("resource") //Suprime esses alerts do eclipse 
	private String leArquivo(String url) throws IOException {
		
		BufferedReader reader = new BufferedReader (new FileReader(new File("grava-url.txt").getCanonicalPath()));
		String linha;	
		while ((linha = reader.readLine()) != null) {
		       if(linha.contains(url)){
		         return linha;          
		       } 
		}
		return null;
	}
}
