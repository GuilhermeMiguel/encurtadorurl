package br.com.encurtadorurl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.encurtadorurl.facade.EncurtadorUrlFacade;
import br.com.encurtadorurl.vo.UrlVO;


@Controller
@RestController
@RequestMapping("/encurtar")
public class EncurtarUrlController {

	@Autowired
	private EncurtadorUrlFacade encurtadorUrlFacade;
	
	
	@GetMapping(produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ResponseEntity<UrlVO> RespostaTrabalharComUrl(@RequestParam(required = false) String url) {
		
		UrlVO urlEncurtada = encurtadorUrlFacade.trabalhaComUrl(url);
		
		if(urlEncurtada == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);			
		}
		else {
			return new ResponseEntity<>(urlEncurtada, HttpStatus.OK);
		}
	}
}
