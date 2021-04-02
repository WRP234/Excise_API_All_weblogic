package com.xcs.phase2.dao.uac;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.xcs.phase2.model.uac.Uaclogin;
import com.xcs.phase2.request.uac.UacloginReq;

@Service
@Transactional
public class UacloginDAOImpl extends UacExt implements UacloginDAO {
	
	private static final Logger log = LoggerFactory.getLogger(UacloginDAO.class);
	
	@Override
	public List<Uaclogin> Uaclogin(UacloginReq req) {
		
		StringBuilder sqlBuilder = new StringBuilder()
				.append("  SELECT DISTINCT" +
						"  UAC_USER_ACCOUNT.*," +
						"  MAS_STAFF.*" +
						"  " +
						"  FROM UAC_USER_ACCOUNT" +
						"  LEFT JOIN MAS_STAFF ON UAC_USER_ACCOUNT.STAFF_ID = MAS_STAFF.STAFF_ID" +
						"  WHERE UAC_USER_ACCOUNT.IS_ACTIVE = 1" +
						"  AND UAC_USER_ACCOUNT.IS_SIGN_ON = 0" );
		if (!StringUtils.isEmpty(req.getUSER_NAME())) {
			sqlBuilder.append(" AND LOWER (UAC_USER_ACCOUNT.USER_NAME) LIKE LOWER(REPLACE ('%" + req.getUSER_NAME()+ "%','',''))");
		}
		if (!StringUtils.isEmpty(req.getPASSWORD())) {
			sqlBuilder.append(" AND LOWER (UAC_USER_ACCOUNT.PASSWORD) LIKE LOWER(REPLACE ('%" + req.getPASSWORD()+ "%','','')) ");
		}
		
		log.info("[SQL] : " + sqlBuilder.toString());
		
		@SuppressWarnings("unchecked")
		List<Uaclogin> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {
			
			public Uaclogin mapRow(ResultSet rs, int rowNum) throws SQLException {
				Uaclogin item = new Uaclogin();
				item.setUSER_ACCOUNT_ID(rs.getInt("USER_ACCOUNT_ID"));
				item.setSTAFF_ID(rs.getInt("STAFF_ID"));
				item.setROLE_ID(rs.getString("ROLE_ID"));
				item.setUSER_TYPE(rs.getString("USER_TYPE"));
				item.setUSER_NAME(rs.getNString("USER_NAME"));
				item.setPASSWORD(rs.getNString("PASSWORD"));
				item.setSIGN_ON_IP(rs.getNString("SIGN_ON_IP"));
				item.setAPPROVE_CODE(rs.getNString("APPROVE_CODE"));
				item.setIS_ACTIVE(rs.getNString("IS_ACTIVE"));
				item.setIS_SIGN_ON(rs.getNString("IS_SIGN_ON"));
				
				return item;
			}
		});
		
		return dataList;
	}

}
