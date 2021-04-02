package com.xcs.phase2.model.uac;

import java.util.List;

import com.xcs.phase2.model.master.MasStaff;

import lombok.Data;

@Data
public class Uaclogin extends UacloginModel {
	
	private int USER_ACCOUNT_ID;
	private int STAFF_ID;
	private String ROLE_ID;
	private String USER_TYPE;
	private String USER_NAME;
	private String PASSWORD;
	private String SIGN_ON_IP;
	private String APPROVE_CODE;
	private String IS_ACTIVE;
	private String IS_SIGN_ON;
	private List<MasStaff> UacMasStaff;

}
