package com.xcs.phase2.dao.master;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

//import com.xcs.phase2.dao.master.MasLocaleDAO;
import com.xcs.phase2.model.master.MasLocale;
import com.xcs.phase2.request.master.MasLocalegetByConReq;
import com.xcs.phase2.response.master.MasLocaleResponse;

@Service
@Transactional
public class MasLocaleDAOImpl extends MasterExt implements MasLocaleDAO{
	
	private static final Logger log = LoggerFactory.getLogger(MasLocaleDAOImpl.class);
	
	@Override
	public List<MasLocale> MasLocalegetByCon(MasLocalegetByConReq req) {
		
		String str = "";
		
		if(!StringUtils.isEmpty(req.getSUB_DISTRICT_ID())) {
			str = " AND MAS_SUB_DISTRICT.SUB_DISTRICT_ID = '"+req.getSUB_DISTRICT_ID()+"'";
		}
		
		StringBuilder sqlBuilder = new StringBuilder()
				.append(" SELECT DISTINCT  "+
						" MAS_SUB_DISTRICT.SUB_DISTRICT_ID, "+
						" MAS_SUB_DISTRICT.SUB_DISTRICT_NAME_TH, "+
						" MAS_SUB_DISTRICT.SUB_DISTRICT_NAME_EN, "+
						" MAS_DISTRICT.DISTRICT_NAME_TH, "+
						" MAS_DISTRICT.DISTRICT_NAME_EN, "+
						" MAS_PROVINCE.PROVINCE_NAME_TH, "+
						" MAS_PROVINCE.PROVINCE_NAME_EN  "+
						"    " +
						" FROM MAS_SUB_DISTRICT "+
						" INNER JOIN MAS_DISTRICT ON MAS_SUB_DISTRICT.DISTRICT_ID = MAS_DISTRICT.DISTRICT_ID "+
						" INNER JOIN MAS_PROVINCE ON MAS_DISTRICT.PROVINCE_ID = MAS_PROVINCE.PROVINCE_ID "+
						" WHERE MAS_SUB_DISTRICT.IS_ACTIVE = 1 "+
						" AND MAS_DISTRICT.IS_ACTIVE = 1 "+
						" AND MAS_PROVINCE.IS_ACTIVE = 1 "+
						" AND "+
						" ( "+
						" LOWER(MAS_SUB_DISTRICT.SUB_DISTRICT_NAME_TH) LIKE LOWER ('%"+req.getTEXT_SEARCH()+"%') "+
						" OR LOWER(MAS_SUB_DISTRICT.SUB_DISTRICT_NAME_EN) LIKE LOWER ('%"+req.getTEXT_SEARCH()+"%') "+
						" OR LOWER(MAS_DISTRICT.DISTRICT_NAME_TH) LIKE LOWER ('%"+req.getTEXT_SEARCH()+"%') "+
						" OR LOWER(MAS_DISTRICT.DISTRICT_NAME_EN) LIKE LOWER ('%"+req.getTEXT_SEARCH()+"%') "+
						" OR LOWER(MAS_PROVINCE.PROVINCE_NAME_TH) LIKE LOWER ('%"+req.getTEXT_SEARCH()+"%') "+
						" OR LOWER(MAS_PROVINCE.PROVINCE_NAME_EN) LIKE LOWER ('%"+req.getTEXT_SEARCH()+"%') "+
						" ) "+ str + " ORDER BY SUB_DISTRICT_NAME_EN DESC " );
		
		log.info("[SQL] : " + sqlBuilder.toString());
		
		@SuppressWarnings("unchecked")
		List<MasLocale> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {
			
			public MasLocale mapRow(ResultSet rs, int rowNum) throws SQLException {
				MasLocale item = new MasLocale();
				item.setSUB_DISTRICT_ID(rs.getInt("SUB_DISTRICT_ID"));
				item.setSUB_DISTRICT_NAME_EN(rs.getString("SUB_DISTRICT_NAME_EN"));
				item.setSUB_DISTRICT_NAME_EN(rs.getString("SUB_DISTRICT_NAME_EN"));
				item.setDISTRICT_NAME_TH(rs.getString("DISTRICT_NAME_TH"));
				item.setDISTRICT_NAME_EN(rs.getString("DISTRICT_NAME_EN"));
				item.setPROVINCE_NAME_TH(rs.getString("PROVINCE_NAME_TH"));
				item.setPROVINCE_NAME_EN(rs.getString("PROVINCE_NAME_EN"));
				return item;
				
			}
		});
		
		return dataList;
			
		}
	
		@Override
		public MasLocaleResponse MasLocalegetByConList(MasLocalegetByConReq req) {
			MasLocaleResponse masLocaleResponse = new MasLocaleResponse();
			masLocaleResponse.setRESPONSE_DATA(this.MasLocalegetByCon(req));
			return masLocaleResponse;
		}
	}


