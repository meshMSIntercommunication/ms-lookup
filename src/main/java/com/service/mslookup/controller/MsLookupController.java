package com.service.mslookup.controller;

import io.swagger.v3.oas.annotations.Operation;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.env.Environment;
import com.service.mslookup.models.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;



@CrossOrigin
@RestController
public class MsLookupController {
  
 


    @Operation(summary = "Put your endpoint description for swagger here. Do this for each endpoint.")
	    @PostMapping("/msNameCheck")
	    public List<LookupResponse> checkMsName(@RequestParam("msNameRequested") String msNameRequested) {
	        Map<String, String> env = System.getenv();
	        System.out.println("DATA PASSED IS: "+ msNameRequested.toString());
	        // Java 8
	        env.forEach((k, v) -> System.out.println(k + ":" + v));

	        Map<String, String> linkedHashMap = new LinkedHashMap<>();
		boolean found = false;
		    
		for (Map.Entry<String, String> confEnvVars : env.entrySet()) {
	            if(confEnvVars.getKey().contains("CONFIG_")){
	                linkedHashMap.put(confEnvVars.getKey(), confEnvVars.getValue());
	            }
	        }
	        for (Map.Entry<String, String> confEnvVars : env.entrySet()) {
	            if(confEnvVars.getValue().equals(msNameRequested)){
			System.out.println("FOUND EXISTING MS NAME: " + msNameRequested);
	                found = true;
			break;
	            }
	        }
		if(found == true){
		            return Arrays.asList(new LookupResponse("Name" + "'" + msNameRequested + "'" +   " is already taken",false ));
		}
		else{
			    return Arrays.asList(new LookupResponse("Name" +"'" + msNameRequested + "'" +   " is available",true ));
		 }
		    

	    }
	    
	    //gets all PW prefixed envs, just adapt for 'CONF_' and test in dep
	    @GetMapping("/msNameMap")
	    public List<EnvMap> getMsNameMap() {
	        Map<String, String> env = System.getenv();
	        // Java 8
	        env.forEach((k, v) -> System.out.println(k + ":" + v));
//	        String ms1test = environment.getProperty("environment.CONF_MS0");
	        Map<String, String> linkedHashMap = new LinkedHashMap<>();

	        for (Map.Entry<String, String> confEnvVars : env.entrySet()) {
	            if(confEnvVars.getKey().contains("CONFIG_")){
	                linkedHashMap.put(confEnvVars.getKey(), confEnvVars.getValue());
	            }
	        }
	        //add hashmap to serializable pojo and return it
	        System.out.println("Filtered Map: " + linkedHashMap);

	        
	        return Arrays.asList(new EnvMap(linkedHashMap));
	    }
	
	
    
}

