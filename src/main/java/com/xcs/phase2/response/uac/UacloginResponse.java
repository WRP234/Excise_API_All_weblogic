package com.xcs.phase2.response.uac;

import java.util.List;

import com.xcs.phase2.model.uac.Uaclogin;

import lombok.Data;

@Data
public class UacloginResponse extends UacResponse{
	
	private List<Uaclogin> RESPONSE_DATA;
	
}
