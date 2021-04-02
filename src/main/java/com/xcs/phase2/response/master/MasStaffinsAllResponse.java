package com.xcs.phase2.response.master;

import lombok.Data;

@Data
public class MasStaffinsAllResponse extends MasterResponse {
	
	private String IsSuccess;
	private String Msg;
	private int STAFF_ID;

}
