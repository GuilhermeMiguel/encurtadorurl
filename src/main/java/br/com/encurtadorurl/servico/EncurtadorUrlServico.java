package br.com.encurtadorurl.servico;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
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
			if(leArquivo(url)) {
				urlVO.setUrl(armazenaUrl.get(url));
			}
			else {
				armazenaUrl.put(url, compressUrl(url));	
				armazenaArquivo(armazenaUrl);
			}
		} catch (IOException e) {
			urlVO.setUrl(armazenaUrl.get(url));
			
			return urlVO;
		}
				
//		 byte[] r = "".getBytes();
		
//		byte n = 
//		for (byte teste : r) {
//			
//		}
		
		urlVO.setUrl(armazenaUrl.get(url));
		
		return urlVO;
	}
	
	public String compressUrl(String url) {
       
		return url.replace("http://", "#8")
				.replace("www.", "%$")
				.replace(".com.br", "@#");
    }
	
	public void armazenaArquivo(Map<String, String> urls) throws IOException {
		FileWriter arq = new FileWriter("C:\\Users\\global.guilherme\\Documents\\Guilherme\\GIT\\encurtadorurl\\grava-url.txt");
	    PrintWriter gravarArq = new PrintWriter(arq);
	 
	 urls.entrySet().forEach(x -> gravarArq.write(x.getKey() + " - " + x.getValue()));
	}
	
	private boolean leArquivo(String url) throws IOException {
		
		BufferedReader reader = new BufferedReader (new FileReader(new File("C:\\Users\\global.guilherme\\Documents\\Guilherme\\GIT\\encurtadorurl\\grava-url.txt")));
		String linha;	
		while ((linha = reader.readLine()) != null) {
		       if(linha.contains(url)){
		         return true;          
		       } 
		}
		return false;
	}
}