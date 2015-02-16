package com.studiodevelopers.fulito.request;

import android.app.Application;

import com.octo.android.robospice.SpringAndroidSpiceService;
import com.octo.android.robospice.persistence.CacheManager;
import com.octo.android.robospice.persistence.exception.CacheCreationException;
import com.octo.android.robospice.persistence.springandroid.json.jackson.JacksonObjectPersisterFactory;

import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by Ignacio on 10/02/2015.
 */
public class JsonSpiceService extends SpringAndroidSpiceService {

    @Override
    public CacheManager createCacheManager( Application application ) throws CacheCreationException {
        CacheManager cacheManager = new CacheManager();
        JacksonObjectPersisterFactory jacksonObjectPersisterFactory = new JacksonObjectPersisterFactory( application );
        cacheManager.addPersister( jacksonObjectPersisterFactory );
        return cacheManager;
    }

    @Override
    public RestTemplate createRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        manageTimeOuts(restTemplate);
        //find more complete examples in RoboSpice Motivation app
        //to enable Gzip compression and setting request timeouts.

        // web services support json responses
        MappingJacksonHttpMessageConverter jsonConverter = new MappingJacksonHttpMessageConverter();
        FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
        final List<HttpMessageConverter< ? >> listHttpMessageConverters = restTemplate.getMessageConverters();

        listHttpMessageConverters.add( jsonConverter );
        listHttpMessageConverters.add( formHttpMessageConverter );
        listHttpMessageConverters.add( stringHttpMessageConverter );
        restTemplate.setMessageConverters( listHttpMessageConverters );
        return restTemplate;
    }
    private void manageTimeOuts(RestTemplate restTemplate) {
        // set timeout for requests
        ClientHttpRequestFactory factory = restTemplate.getRequestFactory();
        if (factory instanceof HttpComponentsClientHttpRequestFactory) {
            HttpComponentsClientHttpRequestFactory advancedFactory = (HttpComponentsClientHttpRequestFactory) factory;
            advancedFactory.setConnectTimeout(20000);
            advancedFactory.setReadTimeout(10000);
        } else if (factory instanceof SimpleClientHttpRequestFactory) {
            SimpleClientHttpRequestFactory advancedFactory = (SimpleClientHttpRequestFactory) factory;
            advancedFactory.setConnectTimeout(20000);
            advancedFactory.setReadTimeout(10000);
        }
    }
}