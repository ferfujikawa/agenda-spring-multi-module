package com.agendaspring.thymeleaf.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.agendaspring.thymeleaf.configs.ApiRestTemplate;
import com.agendaspring.thymeleaf.dtos.AlteracaoPrazoTarefaDTO;
import com.agendaspring.thymeleaf.dtos.CadastroTarefaDTO;
import com.agendaspring.thymeleaf.dtos.DataTableDTO;
import com.agendaspring.thymeleaf.dtos.HistoricoTarefaDTO;
import com.agendaspring.thymeleaf.dtos.ListaPaginadaDTO;
import com.agendaspring.thymeleaf.dtos.RegistrarAnotacaoTarefaDTO;
import com.agendaspring.thymeleaf.dtos.TarefaDTO;

@Service
public class TarefaApiService implements ITarefaApiService {

    private String apiUrl;
    private RestTemplate restTemplate;

    public TarefaApiService(
        @Value("${api.url}") String apiUrl,
        ApiRestTemplate apiRestTemplate)
        throws Exception {

        this.apiUrl = apiUrl;
        this.restTemplate = apiRestTemplate;
    }

    @Override
    public DataTableDTO<TarefaDTO> listarTarefas(int draw, int inicioPagina, int tamanhoPagina) {
        
        ResponseEntity<DataTableDTO<TarefaDTO>> response = restTemplate.exchange(
            apiUrl + "/tarefas?draw={draw}&inicioPagina={inicioPagina}&tamanhoPagina={tamanhoPagina}",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<DataTableDTO<TarefaDTO>>() {},
            draw,
            inicioPagina,
            tamanhoPagina);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        }

        return null;
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

    @Override
    public Boolean registrarAnotacao(
        UUID tarefaId,
        RegistrarAnotacaoTarefaDTO registroAnotacao) {
        
        ResponseEntity<String> response = restTemplate.postForEntity(
            apiUrl + "/tarefas/{tarefaId}/registrar-anotacao",
            registroAnotacao,
            String.class,
            tarefaId);

        return response.getStatusCode().is2xxSuccessful();
    }

    @Override
    public Boolean cancelar(UUID tarefaId, RegistrarAnotacaoTarefaDTO registroAnotacao) {
        
        try {
            restTemplate.put(
                apiUrl + "/tarefas/{tarefaId}/cancelar",
                registroAnotacao,
                tarefaId);

            return true;
        } catch (RestClientException ex) {
            return false;
        }
    }

    @Override
    public Boolean marcarTarefaVisualizada(UUID tarefaId) {
        
        try {
            restTemplate.put(
                apiUrl + "/tarefas/{tarefaId}/marcar-visualizada",
                null,
                tarefaId);

            return true;
        } catch (RestClientException ex) {
            return false;
        }
    }

    @Override
    public Boolean marcarTarefaNaoVisualizada(UUID tarefaId) {
        
        try {
            restTemplate.put(
                apiUrl + "/tarefas/{tarefaId}/marcar-nao-visualizada",
                null,
                tarefaId);

            return true;
        } catch (RestClientException ex) {
            return false;
        }
    }

    @Override
    public Boolean concluir(UUID tarefaId, RegistrarAnotacaoTarefaDTO registroAnotacao) {
        
        try {
            restTemplate.put(
                apiUrl + "/tarefas/{tarefaId}/concluir",
                registroAnotacao,
                tarefaId);

            return true;
        } catch (RestClientException ex) {
            return false;
        }
    }

    @Override
    public Boolean alterarPrazoTarefa(UUID tarefaId, AlteracaoPrazoTarefaDTO tarefa) {
        
        try {
            restTemplate.put(
                apiUrl + "/tarefas/{tarefaId}/alterar-prazo",
                tarefa,
                tarefaId);

            return true;
        } catch (RestClientException ex) {
            return false;
        }
    }

}
