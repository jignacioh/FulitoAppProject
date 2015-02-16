package com.studiodevelopers.fulito.request;

import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;
import com.octo.android.robospice.retry.DefaultRetryPolicy;
import com.studiodevelopers.fulito.model.Campeonato;

import org.apache.http.protocol.RequestContent;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ignacio on 06/02/2015.
 */
public class CampeonatoRequest extends SpringAndroidSpiceRequest<Campeonato> {
    public CampeonatoRequest() {
        super(Campeonato.class);
        		DefaultRetryPolicy policy = new DefaultRetryPolicy(1,
				2500, 1f);
		setRetryPolicy(policy);
    }

    @Override
    public Campeonato loadDataFromNetwork() throws Exception {

        String url = String.format("http://www.futbolperuano.hol.es/login.php?idCampeonato=%s", "1");
        return getRestTemplate().getForObject(url, Campeonato.class);

    }
    public String createCacheKey() {
        return "campeonato";
    }


    //String url = String.format("http://www.futbolperuano.hol.es/login.php");
    //MultiValueMap<String, String> part = new LinkedMultiValueMap<String, String>();

    // part.add("idCampeonato", "1");
    //HttpHeaders headerer2 = new HttpHeaders();

    //headerer2.setContentType(MediaType.APPLICATION_JSON);
    //HttpEntity<MultiValueMap<String, String>> request2 = new HttpEntity<MultiValueMap<String, String>>(
    //      part, headerer2);
    //RestTemplate restTemplate = getRestTemplate();
    //restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());

    //RestTemplate template = getRestTemplate();
    //manageTimeOuts(template);
    //List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
    //messageConverters.add(new FormHttpMessageConverter());
    //messageConverters.add(new StringHttpMessageConverter());
    //messageConverters.add(new MappingJackson2HttpMessageConverter());
    //template.setMessageConverters(messageConverters);
    //template.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
    //HttpMethod mMethod = HttpMethod.GET;
    // HttpEntity<RequestContent> requestEntity = new HttpEntity<RequestContent>(
    //         mrequest, headers);
    //ResponseEntity<Campeonato> res = template.exchange(
    //        "http://www.futbolperuano.hol.es/login.php?idCampeonato=1", mMethod, request2, Campeonato.class);


    //return res.getBody();



}
