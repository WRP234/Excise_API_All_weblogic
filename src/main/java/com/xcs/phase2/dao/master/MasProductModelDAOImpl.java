package com.xcs.phase2.dao.master;


import com.xcs.phase2.constant.Message;
import com.xcs.phase2.model.master.MasProductModel;
import com.xcs.phase2.request.master.MasProductModelgetByConReq;
import com.xcs.phase2.request.master.MasProductModelupdDeleteReq;
import com.xcs.phase2.response.master.MasProductModelinsAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class MasProductModelDAOImpl extends MasterExt implements MasProductModelDAO {

    private static final Logger log = LoggerFactory.getLogger(MasProductModelDAOImpl.class);

    @Override
    public List<MasProductModel> MasProductModelgetByCon(MasProductModelgetByConReq req) {

        StringBuilder sqlBuilder = new StringBuilder()
                .append("    SELECT * " +
                        "    FROM MAS_PRODUCT_MODEL" +
                        "    WHERE  IS_ACTIVE =1 ");

        if(!StringUtils.isEmpty(req.getTEXT_SEARCH())){
            sqlBuilder.append("  AND   (" +
                    "        LOWER(MAS_PRODUCT_MODEL.PRODUCT_MODEL_NAME_TH) LIKE LOWER('%"+req.getTEXT_SEARCH()+"%')" +
                    "        OR LOWER(MAS_PRODUCT_MODEL.PRODUCT_MODEL_NAME_EN) LIKE LOWER('%"+req.getTEXT_SEARCH()+"%')" +
                    "    )" );
        }

        if(!StringUtils.isEmpty(req.getPRODUCT_MODEL_ID())){
            sqlBuilder.append(" AND MAS_PRODUCT_MODEL.PRODUCT_MODEL_ID = '"+req.getPRODUCT_MODEL_ID()+"' ");
        }

        if(!StringUtils.isEmpty(req.getPRODUCT_GROUP_CODE())){
            sqlBuilder.append("  AND MAS_PRODUCT_MODEL.PRODUCT_GROUP_CODE = '"+req.getPRODUCT_GROUP_CODE()+"' ");
        }

        if(!StringUtils.isEmpty(req.getPRODUCT_CATEGORY_CODE())){
            sqlBuilder.append(" AND MAS_PRODUCT_MODEL.PRODUCT_CATEGORY_CODE = '"+req.getPRODUCT_CATEGORY_CODE()+"' ");
        }

        //sqlBuilder.append(" GROUP BY MAS_PRODUCT_BRAND.PRODUCT_BRAND_ID,MAS_PRODUCT_BRAND.BRAND_NAME ");

        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<MasProductModel> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public MasProductModel mapRow(ResultSet rs, int rowNum) throws SQLException {
                MasProductModel item = new MasProductModel();
                item.setPRODUCT_MODEL_ID(rs.getInt("PRODUCT_MODEL_ID"));
                item.setPRODUCT_MODEL_CODE(rs.getString("PRODUCT_MODEL_CODE"));
                item.setPRODUCT_MODEL_NAME_TH(rs.getString("PRODUCT_MODEL_NAME_TH"));
                item.setPRODUCT_MODEL_NAME_EN(rs.getString("PRODUCT_MODEL_NAME_EN"));
                item.setIS_ACTIVE(rs.getInt("IS_ACTIVE"));
//                item.setCREATE_DATE(rs.getString("CREATE_DATE"));
//                item.setCREATE_USER_ACCOUNT_ID(rs.getLong("CREATE_USER_ACCOUNT_ID"));
//                item.setUPDATE_DATE(rs.getString("UPDATE_DATE"));
//                item.setUPDATE_USER_ACCOUNT_ID(rs.getLong("UPDATE_USER_ACCOUNT_ID"));
                item.setPRODUCT_GROUP_CODE(rs.getString("PRODUCT_GROUP_CODE"));
                item.setPRODUCT_CATEGORY_CODE(rs.getString("PRODUCT_CATEGORY_CODE"));
                return item;

            }
        });
        return dataList;
    }

    @Override
    public MasProductModelinsAllResponse MasProductModelinsAll(MasProductModel req) {
    	
    	MasProductModelinsAllResponse res = new MasProductModelinsAllResponse ();
    	
    	String PRODUCT_MODEL_ID = getSequences("SELECT MAS_PRODUCT_MODEL_SEQ1.NEXTVAL FROM DUAL");
		
		log.info("[getSequences] PRODUCT_MODEL_ID : "+PRODUCT_MODEL_ID);
		
		StringBuilder sqlBuilder = new StringBuilder()
                .append("INSERT INTO MAS_PRODUCT_MODEL(" +
                		"PRODUCT_MODEL_ID, " +
                		"PRODUCT_MODEL_CODE, " +
                		"PRODUCT_MODEL_NAME_TH, " +
                		"PRODUCT_MODEL_NAME_EN, " +
                		"IS_ACTIVE, " +
                		"CREATE_DATE, " +
                		"PRODUCT_GROUP_CODE, " +
                		"PRODUCT_CATEGORY_CODE) " +
                        "VALUES ( " +
                        "'"+PRODUCT_MODEL_ID+"'," +
	    	    		"'"+req.getPRODUCT_MODEL_CODE()+"'," +
	    	    		"'"+req.getPRODUCT_MODEL_NAME_TH()+"'," +
			    		"'"+req.getPRODUCT_MODEL_NAME_EN()+"'," +
			    		"'"+req.getIS_ACTIVE()+"'," +
			    		"CURRENT_TIMESTAMP," +
			    		"'"+req.getPRODUCT_GROUP_CODE()+"'," +
			    		"'"+req.getPRODUCT_CATEGORY_CODE()+"' )");
		
		log.info("[SQL MasProductModelinsAll] : "+sqlBuilder.toString());
		
		getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});
        res.setPRODUCT_MODEL_ID(Integer.parseInt(PRODUCT_MODEL_ID));

        res.setIsSuccess(Message.TRUE);
        res.setMsg(Message.COMPLETE);

        return res;
		
    }

    @Override
    public Boolean MasProductModelupdAll(MasProductModel req) {
		
    	StringBuilder sqlBuilder = new StringBuilder()
                .append("UPDATE MAS_PRODUCT_MODEL SET "+
                		"PRODUCT_MODEL_CODE = " +"'"+req.getPRODUCT_MODEL_CODE()+"'," +
                		"PRODUCT_MODEL_NAME_TH = " +"'"+req.getPRODUCT_MODEL_NAME_TH()+"'," +
                		"PRODUCT_MODEL_NAME_EN = " +"'"+req.getPRODUCT_MODEL_NAME_EN()+"'," +
                		"IS_ACTIVE = " +"'"+req.getIS_ACTIVE()+"'," +
                		"PRODUCT_GROUP_CODE = " +"'"+req.getPRODUCT_GROUP_CODE()+"'," +
                		"PRODUCT_CATEGORY_CODE = " +"'"+req.getPRODUCT_CATEGORY_CODE()+"' "+
                		
                        "WHERE PRODUCT_MODEL_ID = " +"'"+req.getPRODUCT_MODEL_ID()+"'" );
	    	    		
		log.info("[SQL MasProductModelupdAll] : "+sqlBuilder.toString());
		
		getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});
    	
    	
        return true;
    }

    @Override
    public Boolean MasProductModelupdDelete(MasProductModelupdDeleteReq req) {
    	 
    	StringBuilder sqlBuilder = new StringBuilder()
                 .append("UPDATE MAS_PRODUCT_MODEL SET IS_ACTIVE = 0 WHERE PRODUCT_MODEL_ID = "+req.getPRODUCT_MODEL_ID());
    	
    	log.info("[SQL MasProductModelupdDelete]  : " + sqlBuilder.toString());
    	
    	getJdbcTemplate().update(sqlBuilder.toString(), new Object[]{});
    	
        return true;
    }
}
