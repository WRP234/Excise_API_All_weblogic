package com.xcs.phase2.model.master;

import lombok.Data;

@Data
public class MasLocale extends MasterProductModel {
	
	private int SUB_DISTRICT_ID;
     private String SUB_DISTRICT_NAME_TH;
     private String SUB_DISTRICT_NAME_EN;
     private String DISTRICT_NAME_TH;
     private String DISTRICT_NAME_EN;
     private String PROVINCE_NAME_TH;
     private String PROVINCE_NAME_EN;
     private String IS_ACTIVE;

}
