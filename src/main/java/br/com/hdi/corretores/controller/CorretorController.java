package br.com.hdi.corretores.controller;

import br.com.hdi.corretores.model.CorretorComStatus;
import br.com.hdi.corretores.model.RetornoApi;
import br.com.hdi.corretores.service.CorretorComStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CorretorController {

	@Autowired
	private CorretorComStatusService corretorComStatusService;

	@GetMapping("/broker/comstatus/{document}")
	public ResponseEntity<RetornoApi> obterCorretorComStatus(@PathVariable String document) {

		try {

			CorretorComStatus corretorComStatus = this.corretorComStatusService.obterPorDocument(document);

			ResponseEntity retornoApiResponseEntity = new ResponseEntity(new RetornoApi<CorretorComStatus>().adicionarEntitadeRetorno(corretorComStatus).getBody(), HttpStatus.OK);

			return retornoApiResponseEntity;
		} catch (Exception ex) {
			return new ResponseEntity (new RetornoApi<CorretorComStatus>().adicionarMsgErro(ex.getMessage()), HttpStatus.BAD_REQUEST);
		}

	}

}
