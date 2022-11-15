package com.rh.caj.fare.infra.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestClient {

	@Autowired
	private RestTemplate restTemplate;

	public String call(String url) {
		return restTemplate.getForObject(url, String.class);
	}

}
