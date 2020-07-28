package com.example.atm.service;

import com.example.atm.model.AccountViewer;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;

@Service
public class AtmService {

    private RestTemplate restTemplate = getRestTemplate();

    private RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        KeyStore keyStore;

        HttpComponentsClientHttpRequestFactory requestFactory = null;

        try {
            keyStore = KeyStore.getInstance("PKCS12");
            ClassPathResource classPathResource = new ClassPathResource("");
            InputStream inputStream = classPathResource.getInputStream();
            keyStore.load(inputStream, "keyStore".toCharArray());

            SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(new SSLContextBuilder().loadTrustMaterial(null, new TrustSelfSignedStrategy()).loadKeyMaterial(keyStore, "password".toCharArray()).build(), NoopHostnameVerifier.INSTANCE);

            HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory).setMaxConnTotal(Integer.valueOf(5)).setMaxConnPerRoute(Integer.valueOf(5)).build();

            requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
            requestFactory.setReadTimeout(Integer.valueOf(10000));
            requestFactory.setConnectTimeout(Integer.valueOf(10000));

            restTemplate.setRequestFactory(requestFactory);
        } catch (KeyStoreException | IOException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        return restTemplate;
    }

    public AccountViewer deposit(String username, int depositAmount) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        String url = "http://localhost:8082/" + username + "/deposit?depositAmount=" + depositAmount;
        return restTemplate.exchange(url, HttpMethod.PUT, entity, AccountViewer.class).getBody();

    }

    public AccountViewer withdraw(String username, int withdrawalAmount) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        String url = "http://localhost:8082/" + username + "/withdraw?withdrawalAmount=" + withdrawalAmount;
        return restTemplate.exchange(url, HttpMethod.PUT, entity, AccountViewer.class).getBody();
    }
}
