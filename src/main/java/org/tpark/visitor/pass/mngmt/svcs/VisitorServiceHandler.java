package org.tpark.visitor.pass.mngmt.svcs;

import java.io.IOException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.tpark.visitor.pass.mngmt.svcs.model.Visitor;
import org.tpark.visitor.pass.mngmt.svcs.model.VisitorEntity;
@RestController
@RequestMapping("/api/visitor/**")
public class VisitorServiceHandler{
	
	@Autowired
 	private VisitorRepository vRepo;
	
	@Autowired
 	private TestVisitorRepository testRepo;
 	
	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity savePass(@RequestBody Visitor guest){	
		VisitorEntity guestEntity = new VisitorEntity();
		BeanUtils.copyProperties(guest, guestEntity);
		long passId=vRepo.savePass(guestEntity);
		vRepo.saveImage(guestEntity);		
		
		
		try {
			testRepo.getImage(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity(passId, headers, HttpStatus.OK);
	
	}

}
