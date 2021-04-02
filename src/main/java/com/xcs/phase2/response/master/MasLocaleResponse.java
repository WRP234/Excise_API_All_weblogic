package com.xcs.phase2.response.master;

import com.xcs.phase2.model.master.MasLocale;
import lombok.Data;

import java.util.List;

@Data
public class MasLocaleResponse extends MasterResponse {
	
	private List<MasLocale> RESPONSE_DATA;

}
