package com.rh.caj.fare.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.rh.caj.fare.infra.client.RestClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FareApplicationService {

	@Value("${fare.table}")
	String fareTable;

	@Value("${fare.rate}")
	int fareRate;
	
	@Value("${fare.stationAPI}")
	String stationAPI;
	
	@Autowired
	RestClient restClient;

	public JsonNode getFareTable(String type) throws JsonMappingException, JsonProcessingException {
		// call master-file to validate station ...
		String stations=restClient.call(stationAPI);
		log.info(stations);
		
		// fetch fare table
		ObjectMapper mapper = new ObjectMapper();
		JsonNode fareTableObj = mapper.readTree(fareTable);
		if(type.equals("B")) {
			fareTableObj.forEach(item -> {
				((ObjectNode) item).put("nak", !"-".equals(item.get("nak").asText()) ? Integer.toString(Integer.valueOf(item.get("nak").asText())*fareRate) : "-");
				((ObjectNode) item).put("tpe", !"-".equals(item.get("tpe").asText()) ? Integer.toString(Integer.valueOf(item.get("tpe").asText())*fareRate) : "-");
				((ObjectNode) item).put("bac", !"-".equals(item.get("bac").asText()) ? Integer.toString(Integer.valueOf(item.get("bac").asText())*fareRate) : "-");
				((ObjectNode) item).put("tay", !"-".equals(item.get("tay").asText()) ? Integer.toString(Integer.valueOf(item.get("tay").asText())*fareRate) : "-");
				((ObjectNode) item).put("hsc", !"-".equals(item.get("hsc").asText()) ? Integer.toString(Integer.valueOf(item.get("hsc").asText())*fareRate) : "-");
				((ObjectNode) item).put("mil", !"-".equals(item.get("mil").asText()) ? Integer.toString(Integer.valueOf(item.get("mil").asText())*fareRate) : "-");
				((ObjectNode) item).put("tac", !"-".equals(item.get("tac").asText()) ? Integer.toString(Integer.valueOf(item.get("tac").asText())*fareRate) : "-");
				((ObjectNode) item).put("cha", !"-".equals(item.get("cha").asText()) ? Integer.toString(Integer.valueOf(item.get("cha").asText())*fareRate) : "-");
				((ObjectNode) item).put("yul", !"-".equals(item.get("yul").asText()) ? Integer.toString(Integer.valueOf(item.get("yul").asText())*fareRate) : "-");
				((ObjectNode) item).put("chy", !"-".equals(item.get("chy").asText()) ? Integer.toString(Integer.valueOf(item.get("chy").asText())*fareRate) : "-");
				((ObjectNode) item).put("tan", !"-".equals(item.get("tan").asText()) ? Integer.toString(Integer.valueOf(item.get("tan").asText())*fareRate) : "-");
				((ObjectNode) item).put("zuy", !"-".equals(item.get("zuy").asText()) ? Integer.toString(Integer.valueOf(item.get("zuy").asText())*fareRate) : "-");
			});
		}
		return fareTableObj;
	}
}
