package com.agendaspring.thymeleaf.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.client.RestTemplate;

import com.agendaspring.thymeleaf.dtos.CadastroTarefaDTO;
import com.agendaspring.thymeleaf.dtos.HistoricoTarefaDTO;
import com.agendaspring.thymeleaf.dtos.ListaPaginadaDTO;
import com.agendaspring.thymeleaf.dtos.TarefaDTO;

@Service
public class TarefaApiService implements ITarefaApiService {

    private String apiUrl;
    private RestTemplate restTemplate;

    public TarefaApiService(
        @Value("${api.url}") String apiUrl) {

        this.apiUrl = apiUrl;
        this.restTemplate = new RestTemplate();
    }

    @Override
    public TarefaDTO cadastrarTarefa(CadastroTarefaDTO cadastroTarefaDTO) throws BadRequest {
  
        ResponseEntity<TarefaDTO> response = restTemplate.postForEntity(apiUrl + "/tarefas", cadastroTarefaDTO, TarefaDTO.class);
        
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }

        return null;
    }

    @Override
    public ListaPaginadaDTO<HistoricoTarefaDTO> listarHistoricoDeTarefa(UUID tarefaId, int pagina, int tamanhoPagina) {

        ResponseEntity<ListaPaginadaDTO<HistoricoTarefaDTO>> response = restTemplate.exchange(
            apiUrl + "/tarefas/{tarefaId}/historico?pagina={pagina}&tamanhoPagina={tamanhoPagina}",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<ListaPaginadaDTO<HistoricoTarefaDTO>>() {},
            tarefaId,
            pagina,
            tamanhoPagina);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }

        return null;
    }

}
