package com.xcs.phase2.request.uac;

import lombok.Data;

@Data
public class UacloginReq extends UacRequest {
	
	private String USER_NAME;
	private String PASSWORD;

}
