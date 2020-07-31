package com.example.atm.service;

import com.example.atm.model.AccountViewer;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

@Service
public class AtmService {

    private RestTemplate restTemplate = getRestTemplate();

    private RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        try {
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            keyStore.load(new FileInputStream("./practiceKey.p12"), "password".toCharArray());
            SSLContextBuilder sslContextBuilder = new SSLContextBuilder();
            sslContextBuilder.useProtocol("TLS");
            sslContextBuilder.loadKeyMaterial(keyStore, "password".toCharArray());
            sslContextBuilder.loadTrustMaterial(new TrustSelfSignedStrategy());
            SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContextBuilder.build());
            CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory).build();
            HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
            requestFactory.setConnectTimeout(10000);
            requestFactory.setReadTimeout(10000);

            restTemplate = new RestTemplate(requestFactory);
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
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
        String url = "https://localhost:8445/account/" + username + "/deposit?depositAmount=" + depositAmount;
        return restTemplate.exchange(url, HttpMethod.PUT, entity, AccountViewer.class).getBody();

    }

    public AccountViewer withdraw(String username, int withdrawalAmount) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        String url = "https://localhost:8445/account/" + username + "/withdraw?withdrawalAmount=" + withdrawalAmount;
        return restTemplate.exchange(url, HttpMethod.PUT, entity, AccountViewer.class).getBody();
    }
}
