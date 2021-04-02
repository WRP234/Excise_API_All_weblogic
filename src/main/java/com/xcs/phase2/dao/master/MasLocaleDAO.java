package com.xcs.phase2.dao.master;

import java.util.List;

import com.xcs.phase2.model.master.MasLocale;
import com.xcs.phase2.request.master.MasLocalegetByConReq;
import com.xcs.phase2.response.master.MasLocaleResponse;

public interface MasLocaleDAO {
	
	public List<MasLocale> MasLocalegetByCon(MasLocalegetByConReq req);
	public MasLocaleResponse MasLocalegetByConList(MasLocalegetByConReq req);

}
