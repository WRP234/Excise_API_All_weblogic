package com.xcs.phase2.request.master;

import lombok.Data;

@Data
public class MasLocalegetByConReq extends MasterRequest {
	
	private String SUB_DISTRICT_ID;
	private String TEXT_SEARCH;

}
