package br.com.hdi.corretores.service;

import br.com.hdi.corretores.model.CorretorComStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class CorretorComStatusService {

	@Autowired
	private WebClient webClientCadastroCorretores;

	public CorretorComStatus obterPorDocument(String document) throws Exception {

		Mono<CorretorComStatus> monoCorretor = this.webClientCadastroCorretores
			.method(HttpMethod.GET)
			.uri("/broker/{document}", document)
			.retrieve()
			.onStatus(status -> status.value() == HttpStatus.NOT_FOUND.value(),
					response -> Mono.error(new Exception("Corretor não cadastrado")))
			.bodyToMono(CorretorComStatus.class);


		Mono<CorretorComStatus> monoStatus = this.webClientCadastroCorretores
				.method(HttpMethod.GET)
				.uri("/brokerData/{code}", monoCorretor.block().getCode())
				.retrieve()
				.onStatus(status -> status.value() == HttpStatus.NOT_FOUND.value(),
						response -> Mono.error(new Exception("Corretor não cadastrado")))
				.bodyToMono(CorretorComStatus.class);

		CorretorComStatus corretorComStatus = Mono.zip(monoCorretor, monoStatus).map(tuple -> {
			tuple.getT1().setActive(tuple.getT2().getActive());
			tuple.getT1().setCommissionRate(tuple.getT2().getCommissionRate());
			return tuple.getT1();
		}).block();


		if (corretorComStatus.getActive() == false) {
			throw new Exception("Corretor com status inativo");
		}

		return corretorComStatus;
	}

}
