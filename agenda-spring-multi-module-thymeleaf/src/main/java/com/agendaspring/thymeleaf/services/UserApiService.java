package com.agendaspring.thymeleaf.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.agendaspring.thymeleaf.dtos.LoginValidoDTO;
import com.agendaspring.thymeleaf.dtos.RealizarLoginDTO;

@Service
public class UserApiService {

    private String apiUrl;

    public UserApiService(@Value("${api.url}") String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String realizarLogin(RealizarLoginDTO realizarLoginDTO) throws HttpClientErrorException {

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<LoginValidoDTO> response = restTemplate.postForEntity(
            apiUrl + "/login",
            realizarLoginDTO,
            LoginValidoDTO.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            
            return response.getBody().getToken();
        }

        return null;
    }
}