package br.com.encurtadorurl.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.encurtadorurl.servico.EncurtadorUrlServico;
import br.com.encurtadorurl.vo.UrlVO;

@Service
public class EncurtadorUrlFacade {

	@Autowired
	private EncurtadorUrlServico encurtadorUrlService;
	
	public UrlVO trabalhaComUrl(String url) {
		
		if(url.contains("#+") || url.contains("#*") || url.contains("#$") || url.contains("#@") || url.contains("#!")){
			return encurtadorUrlService.retornarUrl(url);
		}
		
		return encurtadorUrlService.encurtarUrl(url);
	}

	
}
