package com.xcs.phase2.dao.uac;

import java.util.List;

import com.xcs.phase2.model.uac.Uaclogin;
import com.xcs.phase2.request.uac.UacloginReq;

public interface UacloginDAO {
	public List<Uaclogin> Uaclogin(UacloginReq req);
}
