package br.com.encurtadorurl.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.encurtadorurl.servico.EncurtadorUrlServico;
import br.com.encurtadorurl.vo.UrlVO;

@Service
public class EncurtadorUrlFacade {

	@Autowired
	private EncurtadorUrlServico encurtadorUrlService;
	
	public UrlVO encurtarUrl(String url) {
		
		return encurtadorUrlService.encurtarUrl(url);
	}

	
}
