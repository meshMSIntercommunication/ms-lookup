package com.service.mslookup.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin
@RestController
public class MsLookupController {

  @Operation(summary = "Put your endpoint description for swagger here. Do this for each endpoint.")
	@GetMapping("/")
		public String placeHolder() {
		return "hello ms-lookup";
	}


}
