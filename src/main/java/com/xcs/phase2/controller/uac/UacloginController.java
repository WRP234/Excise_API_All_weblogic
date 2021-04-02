package com.xcs.phase2.controller.uac;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.xcs.phase2.constant.Message;
import com.xcs.phase2.dao.uac.UacloginDAO;
import com.xcs.phase2.request.uac.UacloginReq;
import com.xcs.phase2.response.MessageResponse;

@RestController
public class UacloginController {
	
	private static final Logger log = LoggerFactory.getLogger(UacloginController.class);
	
	@Autowired
	private UacloginDAO uacloginDAO;
	
	@PostMapping(value = "/Uaclogin")
	public ResponseEntity Uaclogin(@RequestBody UacloginReq req) {
		
		log.info("============= Start API Uaclogin ================");
		MessageResponse msg = new MessageResponse();
		List<com.xcs.phase2.model.uac.Uaclogin> res =null;
		Boolean checkType = true;
		try {
			
			res =uacloginDAO.Uaclogin(req);
			
		} catch (Exception e) {
			checkType = false;
			msg.setIsSuccess(Message.FALSE);
			msg.setMsg(e.getMessage());	
		}
		log.info("============= Start API Uaclogin ================");
		return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
	}
}
