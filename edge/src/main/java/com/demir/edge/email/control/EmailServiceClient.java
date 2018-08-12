package com.demir.edge.email.control;

import com.demir.edge.email.entity.Email;
import com.demir.edge.email.entity.EmailSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Service
public class EmailServiceClient {

    @Autowired
    RestTemplate remote;
    @Value("${core.path}")
    String corePath;
    @Value("${core.email.resource}")
    String emailResources;

    public List<Email> findAll() {
        ResponseEntity<List<Email>> response = remote.exchange(corePath +"/emails", HttpMethod.GET, null, new ParameterizedTypeReference<List<Email>>(){});
        return response.getBody();
    }

    public Long save(String email) {
        URI location = remote.postForLocation(corePath + "/emails", email);
        String id = location.getPath().substring(location.getPath().lastIndexOf("/") + 1);
        return Long.valueOf(id);
    }

    public void delete(Long id) {
        remote.delete(corePath + "/emails/" + id);
    }

    public EmailSummary summaryReport() {
        return remote.getForObject(corePath + "/emails/summary", EmailSummary.class);
    }

    public void update(Long id, String email) {
        remote.put(corePath + "/emails/" + id, email);
    }

    public Email find(Long id) {
        return remote.getForObject(corePath + "/emails/" + id, Email.class);
    }
}
