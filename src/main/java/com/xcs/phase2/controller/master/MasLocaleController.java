package com.xcs.phase2.controller.master;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.xcs.phase2.dao.master.MasLocaleDAO;
import com.xcs.phase2.request.master.MasLocalegetByConReq;
import com.xcs.phase2.constant.Message;
import com.xcs.phase2.response.MessageResponse;
import com.xcs.phase2.response.master.MasLocaleResponse;


@RestController
public class MasLocaleController {
	
	private static final Logger log = LoggerFactory.getLogger(MasLocaleController.class);
	
	@Autowired
	private MasLocaleDAO masLocaleDAO;
	
	@PostMapping(value = "/MasLocalegetByCon")
	public ResponseEntity MasLocalegetByCon(@RequestBody MasLocalegetByConReq req) {
		
		log.info("============= Start API MasLocalegetByCon ================");
		MessageResponse msg = new MessageResponse();
		MasLocaleResponse res = null;
		Boolean checkType = true;
		try {
			res = masLocaleDAO.MasLocalegetByConList(req);
		} catch (Exception e) {
			checkType =false;
			msg.setIsSuccess(Message.FALSE);
			msg.setMsg(e.getMessage());
		}
		log.info("============= Start API MasLocalegetByCon ================");
		return new ResponseEntity(checkType ? res : msg, HttpStatus.OK);
	}

}
