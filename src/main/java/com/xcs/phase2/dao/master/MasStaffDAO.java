package com.xcs.phase2.dao.master;


import com.xcs.phase2.model.master.MasStaff;
import com.xcs.phase2.request.master.MasStaffgetByConAdvReq;
import com.xcs.phase2.request.master.MasStaffgetByConReq;
import com.xcs.phase2.response.master.MasStaffgetByConResponse;
import com.xcs.phase2.response.master.MasStaffinsAllResponse;

import java.util.List;

public interface MasStaffDAO {

    public List<MasStaff> MasStaffgetByCon(MasStaffgetByConReq req);
    public List<MasStaff> MasStaffgetByConAdv(MasStaffgetByConAdvReq req);
    public MasStaffgetByConResponse MasStaffgetByConNew(MasStaffgetByConReq req); //"Method is not have in controller."//
    public MasStaff MasStaffgetById(int staffId); //"Method is not have in controller."//
    public MasStaffinsAllResponse MasStaffinsAll(MasStaff req);
    

}
