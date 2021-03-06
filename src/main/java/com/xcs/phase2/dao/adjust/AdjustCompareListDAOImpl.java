package com.xcs.phase2.dao.adjust;


import com.xcs.phase2.constant.Pattern;
import com.xcs.phase2.model.adjust.AdjustCompareList;
import com.xcs.phase2.request.adjust.AdjustCompareListgetByConAdvReq;
import com.xcs.phase2.request.adjust.AdjustCompareListgetByKeywordReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class AdjustCompareListDAOImpl extends AdjustExt implements AdjustCompareListDAO {

    private static final Logger log = LoggerFactory.getLogger(AdjustCompareListDAOImpl.class);

    @Override
    public List<AdjustCompareList> AdjustCompareListgetByKeyword(AdjustCompareListgetByKeywordReq req) {
        // TODO Auto-generated method stub

        String str = "";

        if(req.getACCOUNT_OFFICE_CODE() != null && !"".equals(req.getACCOUNT_OFFICE_CODE()) && !"00".equals(req.getACCOUNT_OFFICE_CODE())) {

            if(req.getACCOUNT_OFFICE_CODE().length() == 6) {

                if("00".equals(req.getACCOUNT_OFFICE_CODE().substring(0, 2))) {
                    str = " ";
                }else if("0000".equals(req.getACCOUNT_OFFICE_CODE().substring(2, 6))) {
                    str = " AND" +
                            "( " +
                            "  SUBSTR(OPS_LAWSUIT_STAFF.OPERATION_OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
                            "  OR SUBSTR(OPS_LAWSUIT_STAFF.MANAGEMENT_OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
                            "  OR SUBSTR(OPS_LAWSUIT_STAFF.REPRESENT_OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
                            "  OR SUBSTR(OPS_COMPARE_STAFF.OPERATION_OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
                            "  OR SUBSTR(OPS_COMPARE_STAFF.MANAGEMENT_OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
                            "  OR SUBSTR(OPS_COMPARE_STAFF.REPRESENT_OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
                            ") " ;
                }else if("00".equals(req.getACCOUNT_OFFICE_CODE().substring(4, 6))) {
                    str = " AND " +
                            "( " +
                            "  SUBSTR(OPS_LAWSUIT_STAFF.OPERATION_OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
                            "  OR SUBSTR(OPS_LAWSUIT_STAFF.MANAGEMENT_OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
                            "  OR SUBSTR(OPS_LAWSUIT_STAFF.REPRESENT_OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
                            "  OR SUBSTR(OPS_COMPARE_STAFF.OPERATION_OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
                            "  OR SUBSTR(OPS_COMPARE_STAFF.MANAGEMENT_OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
                            "  OR SUBSTR(OPS_COMPARE_STAFF.REPRESENT_OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
                            " )";
                }else {
                    str = " AND" +
                            " (" +
                            "  OPS_LAWSUIT_STAFF.OPERATION_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
                            "  OR OPS_LAWSUIT_STAFF.MANAGEMENT_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
                            "  OR OPS_LAWSUIT_STAFF.REPRESENT_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
                            "  OR OPS_COMPARE_STAFF.OPERATION_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
                            "  OR OPS_COMPARE_STAFF.MANAGEMENT_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
                            "  OR OPS_COMPARE_STAFF.REPRESENT_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
                            " )";
                }

            }

        }

        StringBuilder sqlBuilderDetail = new StringBuilder()
                .append("SELECT " +
                        " OPS_COMPARE.COMPARE_ID," +
                        " OPS_LAWSUIT.LAWSUIT_ID," +
                        " OPS_LAWSUIT.INDICTMENT_ID," +
                        " OPS_COMPARE.COMPARE_NO," +
                        " to_char(OPS_COMPARE.COMPARE_NO_YEAR,'"+ Pattern.FORMAT_DATETIME+"') as COMPARE_NO_YEAR," +
                        " to_char(OPS_COMPARE.COMPARE_DATE,'"+ Pattern.FORMAT_DATETIME+"') as COMPARE_DATE," +
                        " OPS_COMPARE_STAFF.TITLE_NAME_TH COMPARE_TITLE_NAME_TH," +
                        " OPS_COMPARE_STAFF.TITLE_NAME_EN COMPARE_TITLE_NAME_EN," +
                        " OPS_COMPARE_STAFF.TITLE_SHORT_NAME_TH COMPARE_TITLE_SHORT_NAME_TH," +
                        " OPS_COMPARE_STAFF.TITLE_SHORT_NAME_EN COMPARE_TITLE_SHORT_NAME_EN," +
                        " OPS_COMPARE_STAFF.FIRST_NAME COMPARE_FIRST_NAME," +
                        " OPS_COMPARE_STAFF.LAST_NAME COMPARE_LAST_NAME," +
                        " OPS_COMPARE_STAFF.OPERATION_POS_CODE COMPARE_OPERAT_POS_CODE," +
                        " OPS_COMPARE_STAFF.OPREATION_POS_NAME COMPARE_OPREAT_POS_NAME," +
                        " OPS_COMPARE_STAFF.OPREATION_POS_LEVEL COMPARE_OPREAT_POS_LEVEL," +
                        " OPS_COMPARE_STAFF.OPERATION_POS_LEVEL_NAME COMPARE_OPERAT_POS_LEVEL_NAME," +
                        " OPS_COMPARE_STAFF.OPERATION_OFFICE_CODE COMPARE_OPERAT_OFF_CODE," +
                        " OPS_COMPARE_STAFF.OPERATION_OFFICE_NAME COMPARE_OPERATION_OFF_NAME," +
                        " OPS_COMPARE_STAFF.OPERATION_OFFICE_SHORT_NAME COMPARE_OPERAT_OFF_SHORT_NAME," +
                        " OPS_COMPARE.OFFICE_NAME COMPARE_OFFICE_NAME," +
                        " OPS_COMPARE.IS_OUTSIDE COMPARE_IS_OUTSIDE," +
                        " OPS_ARREST.ARREST_CODE," +
                        " to_char(OPS_ARREST.OCCURRENCE_DATE,'"+ Pattern.FORMAT_DATETIME+"') as OCCURRENCE_DATE," +
                        " OPS_ARREST_STAFF.TITLE_NAME_TH ACCUSER_TITLE_NAME_TH," +
                        " OPS_ARREST_STAFF.TITLE_NAME_EN ACCUSER_TITLE_NAME_EN," +
                        " OPS_ARREST_STAFF.TITLE_SHORT_NAME_TH ACCUSER_TITLE_SHORT_NAME_TH," +
                        " OPS_ARREST_STAFF.TITLE_SHORT_NAME_EN ACCUSER_TITLE_SHORT_NAME_EN," +
                        " OPS_ARREST_STAFF.FIRST_NAME ACCUSER_FIRST_NAME," +
                        " OPS_ARREST_STAFF.LAST_NAME ACCUSER_LAST_NAME," +
                        " OPS_ARREST_STAFF.OPERATION_POS_CODE ACCUSER_OPERAT_POS_CODE," +
                        " OPS_ARREST_STAFF.OPREATION_POS_NAME ACCUSER_OPREAT_POS_NAME," +
                        " OPS_ARREST_STAFF.OPREATION_POS_LEVEL ACCUSER_OPREAT_POS_LEVEL," +
                        " OPS_ARREST_STAFF.OPERATION_POS_LEVEL_NAME ACCUSER_OPERAT_POS_LEVEL_NAME," +
                        " OPS_ARREST_STAFF.OPERATION_OFFICE_CODE ACCUSER_OPERAT_OFF_CODE," +
                        " OPS_ARREST_STAFF.OPERATION_OFFICE_NAME ACCUSER_OPERAT_OFF_NAME," +
                        " OPS_ARREST_STAFF.OPERATION_OFFICE_SHORT_NAME ACCUSER_OPERAT_OFF_SHORT_NAME," +
                        " OPS_ARREST.OFFICE_NAME ARREST_OFFICE_NAME," +
                        " MAS_LAW_GUILTBASE.GUILTBASE_NAME," +
                        " MAS_LAW_GROUP_SUBSECTION.SUBSECTION_NAME," +
                        " MAS_LAW_GROUP_SUBSECTION.SUBSECTION_DESC," +
                        " MAS_LAW_GROUP_SECTION.SECTION_NAME," +
                        " MAS_LAW_PENALTY.PENALTY_DESC," +
                        " OPS_LAWSUIT.IS_OUTSIDE LAWSUIT_IS_OUTSIDE," +
                        " OPS_LAWSUIT.LAWSUIT_NO," +
                        " to_char(OPS_LAWSUIT.LAWSUIT_NO_YEAR,'"+ Pattern.FORMAT_DATETIME+"') as LAWSUIT_NO_YEAR," +
                        " to_char(OPS_LAWSUIT.LAWSUIT_DATE,'"+ Pattern.FORMAT_DATETIME+"') as LAWSUIT_DATE," +
                        " OPS_LAWSUIT.OFFICE_NAME LAWSUIT_OFFICE_NAME," +
                        " OPS_LAWSUIT_STAFF.TITLE_NAME_TH LAWSUIT_TITLE_NAME_TH," +
                        " OPS_LAWSUIT_STAFF.TITLE_NAME_EN LAWSUIT_TITLE_NAME_EN," +
                        " OPS_LAWSUIT_STAFF.TITLE_SHORT_NAME_TH LAWSUIT_TITLE_SHORT_NAME_TH," +
                        " OPS_LAWSUIT_STAFF.TITLE_SHORT_NAME_EN LAWSUIT_TITLE_SHORT_NAME_EN," +
                        " OPS_LAWSUIT_STAFF.FIRST_NAME LAWSUIT_FIRST_NAME," +
                        " OPS_LAWSUIT_STAFF.LAST_NAME LAWSUIT_LAST_NAME," +
                        " OPS_LAWSUIT_STAFF.OPERATION_POS_CODE LAWSUIT_OPERAT_POS_CODE," +
                        " OPS_LAWSUIT_STAFF.OPREATION_POS_NAME LAWSUIT_OPREAT_POS_NAME," +
                        " OPS_LAWSUIT_STAFF.OPREATION_POS_LEVEL LAWSUIT_OPREAT_POS_LEVEL," +
                        " OPS_LAWSUIT_STAFF.OPERATION_POS_LEVEL_NAME LAWSUIT_OPERAT_POS_LEVEL_NAME," +
                        " OPS_LAWSUIT_STAFF.OPERATION_OFFICE_CODE LAWSUIT_OPERAT_OFF_CODE," +
                        " OPS_LAWSUIT_STAFF.OPERATION_OFFICE_NAME LAWSUIT_OPERAT_OFF_NAME," +
                        " OPS_LAWSUIT_STAFF.OPERATION_OFFICE_SHORT_NAME LAWSUIT_OPERAT_OFF_SHORT_NAME," +
                        " OPS_PROVE.PROVE_NO PROVE_NO," +
                        " to_char(OPS_PROVE.PROVE_NO_YEAR,'"+ Pattern.FORMAT_DATETIME+"') as PROVE_NO_YEAR," +
                        " OPS_PROVE.IS_OUTSIDE PROVE_IS_OUTSIDE," +
                        " to_char(OPS_PROVE.RECEIVE_DOC_DATE,'"+ Pattern.FORMAT_DATETIME+"') as RECEIVE_DOC_DATE," +
                        " OPS_PROVE.RECEIVE_OFFICE_NAME RECEIVE_OFFICE_NAME," +
                        " OPS_PROVE_STAFF.TITLE_NAME_TH PROVE_TITLE_NAME_TH," +
                        " OPS_PROVE_STAFF.TITLE_NAME_EN PROVE_TITLE_NAME_EN," +
                        " OPS_PROVE_STAFF.TITLE_SHORT_NAME_TH PROVE_TITLE_SHORT_NAME_TH," +
                        " OPS_PROVE_STAFF.TITLE_SHORT_NAME_EN PROVE_TITLE_SHORT_NAME_EN," +
                        " OPS_PROVE_STAFF.FIRST_NAME PROVE_FIRST_NAME," +
                        " OPS_PROVE_STAFF.LAST_NAME PROVE_LAST_NAME," +
                        " OPS_PROVE_STAFF.OPERATION_POS_CODE PROVE_OPERAT_POS_CODE," +
                        " OPS_PROVE_STAFF.OPREATION_POS_NAME PROVE_OPREAT_POS_NAME," +
                        " OPS_PROVE_STAFF.OPREATION_POS_LEVEL PROVE_OPREAT_POS_LEVEL," +
                        " OPS_PROVE_STAFF.OPERATION_POS_LEVEL_NAME PROVE_OPERAT_POS_LEVEL_NAME," +
                        " OPS_PROVE_STAFF.OPERATION_OFFICE_CODE PROVE_OPERAT_OFF_CODE," +
                        " OPS_PROVE_STAFF.OPERATION_OFFICE_NAME PROVE_OPERAT_OFF_NAME," +
                        " OPS_PROVE_STAFF.OPERATION_OFFICE_SHORT_NAME PROVE_OPERAT_OFF_SHORT_NAME" +
                        " FROM OPS_LAWSUIT" +
                        " LEFT JOIN OPS_COMPARE ON OPS_LAWSUIT.LAWSUIT_ID = OPS_COMPARE.LAWSUIT_ID" +
                        " AND OPS_COMPARE.IS_ACTIVE = 1" +
                        " LEFT JOIN OPS_COMPARE_STAFF ON OPS_COMPARE.COMPARE_ID = OPS_COMPARE_STAFF.COMPARE_ID" +
                        " AND OPS_COMPARE_STAFF.IS_ACTIVE = 1" +
                        " AND OPS_COMPARE_STAFF.CONTRIBUTOR_ID = 27" +
                        " INNER JOIN OPS_ARREST_INDICTMENT ON OPS_LAWSUIT.INDICTMENT_ID = OPS_ARREST_INDICTMENT.INDICTMENT_ID" +
                        " INNER JOIN MAS_LAW_GUILTBASE ON OPS_ARREST_INDICTMENT.GUILTBASE_ID = MAS_LAW_GUILTBASE.GUILTBASE_ID" +
                        " INNER JOIN MAS_LAW_GROUP_SUBSECTION_RULE ON MAS_LAW_GUILTBASE.SUBSECTION_RULE_ID = MAS_LAW_GROUP_SUBSECTION_RULE.SUBSECTION_RULE_ID" +
                        " INNER JOIN MAS_LAW_GROUP_SUBSECTION ON MAS_LAW_GROUP_SUBSECTION_RULE.SUBSECTION_ID = MAS_LAW_GROUP_SUBSECTION.SUBSECTION_ID" +
                        " INNER JOIN MAS_LAW_GROUP_SECTION ON MAS_LAW_GROUP_SUBSECTION_RULE.SECTION_ID = MAS_LAW_GROUP_SECTION.SECTION_ID" +
                        " INNER JOIN MAS_LAW_PENALTY ON MAS_LAW_GROUP_SECTION.SECTION_ID = MAS_LAW_PENALTY.SECTION_ID" +
                        " INNER JOIN OPS_ARREST ON OPS_ARREST_INDICTMENT.ARREST_ID = OPS_ARREST.ARREST_ID" +
                        " INNER JOIN OPS_ARREST_STAFF ON OPS_ARREST.ARREST_ID = OPS_ARREST_STAFF.ARREST_ID" +
                        " INNER JOIN OPS_LAWSUIT_STAFF ON OPS_LAWSUIT.LAWSUIT_ID = OPS_LAWSUIT_STAFF.LAWSUIT_ID" +
                        " LEFT JOIN OPS_PROVE ON OPS_LAWSUIT.LAWSUIT_ID = OPS_PROVE.LAWSUIT_ID" +
                        " AND OPS_PROVE.IS_ACTIVE = 1" +
                        " LEFT JOIN OPS_PROVE_STAFF ON OPS_PROVE.PROVE_ID = OPS_PROVE_STAFF.PROVE_ID" +
                        " AND OPS_PROVE_STAFF.IS_ACTIVE = 1" +
                        " AND OPS_PROVE_STAFF.CONTRIBUTOR_ID = 25" +
                        " WHERE OPS_LAWSUIT.IS_ACTIVE = 1" +
                        " AND OPS_ARREST_INDICTMENT.IS_ACTIVE = 1" +
                        " AND OPS_ARREST.IS_ACTIVE = 1" +
                        " AND OPS_ARREST_STAFF.IS_ACTIVE = 1" +
                        " AND OPS_ARREST_STAFF.CONTRIBUTOR_ID = 14" +
                        " AND OPS_LAWSUIT_STAFF.IS_ACTIVE = 1" +
                        " AND OPS_LAWSUIT_STAFF.CONTRIBUTOR_ID = 16" +
                        " AND " +
                        "(" +
                        "   LOWER(OPS_COMPARE.COMPARE_NO||EXTRACT(YEAR FROM OPS_COMPARE.COMPARE_NO_YEAR)) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "   OR LOWER(OPS_COMPARE_STAFF.TITLE_NAME_TH||OPS_COMPARE_STAFF.FIRST_NAME||OPS_COMPARE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "   OR LOWER(OPS_COMPARE_STAFF.TITLE_NAME_EN||OPS_COMPARE_STAFF.FIRST_NAME||OPS_COMPARE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "   OR LOWER(OPS_COMPARE_STAFF.TITLE_SHORT_NAME_TH||OPS_COMPARE_STAFF.FIRST_NAME||OPS_COMPARE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "   OR LOWER(OPS_COMPARE_STAFF.TITLE_SHORT_NAME_EN||OPS_COMPARE_STAFF.FIRST_NAME||OPS_COMPARE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "   OR LOWER(OPS_COMPARE.OFFICE_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "   OR LOWER(OPS_ARREST.ARREST_CODE) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "   OR LOWER(OPS_ARREST_STAFF.TITLE_NAME_TH||OPS_ARREST_STAFF.FIRST_NAME||OPS_ARREST_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "   OR LOWER(OPS_ARREST_STAFF.TITLE_NAME_EN||OPS_ARREST_STAFF.FIRST_NAME||OPS_ARREST_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "   OR LOWER(OPS_ARREST_STAFF.TITLE_SHORT_NAME_TH||OPS_ARREST_STAFF.FIRST_NAME||OPS_ARREST_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "   OR LOWER(OPS_ARREST_STAFF.TITLE_SHORT_NAME_EN||OPS_ARREST_STAFF.FIRST_NAME||OPS_ARREST_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))  " +
                        "   OR LOWER(OPS_ARREST.OFFICE_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "   OR LOWER(MAS_LAW_GROUP_SUBSECTION.SUBSECTION_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "   OR LOWER(MAS_LAW_GUILTBASE.GUILTBASE_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "   OR LOWER(OPS_LAWSUIT.LAWSUIT_NO||EXTRACT(YEAR FROM OPS_LAWSUIT.LAWSUIT_NO_YEAR)) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "   OR LOWER(OPS_LAWSUIT.OFFICE_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "   OR LOWER(OPS_LAWSUIT_STAFF.TITLE_NAME_TH||OPS_LAWSUIT_STAFF.FIRST_NAME||OPS_LAWSUIT_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "   OR LOWER(OPS_LAWSUIT_STAFF.TITLE_NAME_EN||OPS_LAWSUIT_STAFF.FIRST_NAME||OPS_LAWSUIT_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "   OR LOWER(OPS_LAWSUIT_STAFF.TITLE_SHORT_NAME_TH||OPS_LAWSUIT_STAFF.FIRST_NAME||OPS_LAWSUIT_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "   OR LOWER(OPS_LAWSUIT_STAFF.TITLE_SHORT_NAME_EN||OPS_LAWSUIT_STAFF.FIRST_NAME||OPS_LAWSUIT_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "   OR LOWER(OPS_PROVE.PROVE_NO||EXTRACT(YEAR FROM OPS_PROVE.PROVE_NO_YEAR)) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "   OR LOWER(OPS_PROVE.RECEIVE_OFFICE_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "   OR LOWER(OPS_PROVE_STAFF.TITLE_NAME_TH||OPS_PROVE_STAFF.FIRST_NAME||OPS_PROVE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "   OR LOWER(OPS_PROVE_STAFF.TITLE_NAME_EN||OPS_PROVE_STAFF.FIRST_NAME||OPS_PROVE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "   OR LOWER(OPS_PROVE_STAFF.TITLE_SHORT_NAME_TH||OPS_PROVE_STAFF.FIRST_NAME||OPS_PROVE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        "   OR LOWER(OPS_PROVE_STAFF.TITLE_SHORT_NAME_EN||OPS_PROVE_STAFF.FIRST_NAME||OPS_PROVE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getTEXT_SEARCH()+"%',' ',''))" +
                        ") "+str+" ORDER BY OPS_ARREST.ARREST_CODE, OPS_LAWSUIT.LAWSUIT_ID, OPS_COMPARE.COMPARE_ID DESC" );



        log.info("[SQL]  : " + sqlBuilderDetail.toString());
        System.out.println("[SQL] [AdjustCompareListgetByKeyword]  : " + sqlBuilderDetail.toString());

        @SuppressWarnings("unchecked")
        List<AdjustCompareList> dataList = getJdbcTemplate().query(sqlBuilderDetail.toString(), new RowMapper() {

            public AdjustCompareList mapRow(ResultSet rs, int rowNum) throws SQLException {
                AdjustCompareList item = new AdjustCompareList();

                item.setCOMPARE_ID(rs.getInt("COMPARE_ID"));
                item.setLAWSUIT_ID(rs.getInt("LAWSUIT_ID"));
                item.setINDICTMENT_ID(rs.getInt("INDICTMENT_ID"));
                item.setCOMPARE_NO(rs.getString("COMPARE_NO"));
                item.setCOMPARE_NO_YEAR(rs.getString("COMPARE_NO_YEAR"));
                item.setCOMPARE_DATE(rs.getString("COMPARE_DATE"));
                item.setCOMPARE_TITLE_NAME_TH(rs.getString("COMPARE_TITLE_NAME_TH"));
                item.setCOMPARE_TITLE_NAME_EN(rs.getString("COMPARE_TITLE_NAME_EN"));
                item.setCOMPARE_TITLE_SHORT_NAME_TH(rs.getString("COMPARE_TITLE_SHORT_NAME_TH"));
                item.setCOMPARE_TITLE_SHORT_NAME_EN(rs.getString("COMPARE_TITLE_SHORT_NAME_EN"));
                item.setCOMPARE_FIRST_NAME(rs.getString("COMPARE_FIRST_NAME"));
                item.setCOMPARE_LAST_NAME(rs.getString("COMPARE_LAST_NAME"));
                item.setCOMPARE_OPERAT_POS_CODE(rs.getString("COMPARE_OPERAT_POS_CODE"));
                item.setCOMPARE_OPREAT_POS_NAME(rs.getString("COMPARE_OPREAT_POS_NAME"));
                item.setCOMPARE_OPREAT_POS_LEVEL(rs.getString("COMPARE_OPREAT_POS_LEVEL"));
                item.setCOMPARE_OPERAT_POS_LEVEL_NAME(rs.getString("COMPARE_OPERAT_POS_LEVEL_NAME"));
//                item.setCOMPARE_OPERAT_OFF_CODE(rs.getString("COMPARE_OPERAT_OFF_CODE"));
//                item.setCOMPARE_OPERAT_OFF_NAME(rs.getString("COMPARE_OPERAT_OFF_NAME"));
//                item.setCOMPARE_OPERAT_OFF_SHORT_NAME(rs.getString("COMPARE_OPERAT_OFF_SHORT_NAME"));
                item.setCOMPARE_OFFICE_NAME(rs.getString("COMPARE_OFFICE_NAME"));
                item.setCOMPARE_IS_OUTSIDE(rs.getInt("COMPARE_IS_OUTSIDE"));
                
                item.setARREST_CODE(rs.getString("ARREST_CODE"));
                item.setOCCURRENCE_DATE(rs.getString("OCCURRENCE_DATE"));
                item.setACCUSER_TITLE_NAME_TH(rs.getString("ACCUSER_TITLE_NAME_TH"));
                item.setACCUSER_TITLE_NAME_EN(rs.getString("ACCUSER_TITLE_NAME_EN"));
                item.setACCUSER_TITLE_SHORT_NAME_TH(rs.getString("ACCUSER_TITLE_SHORT_NAME_TH"));
                item.setACCUSER_TITLE_SHORT_NAME_EN(rs.getString("ACCUSER_TITLE_SHORT_NAME_EN"));
                item.setACCUSER_FIRST_NAME(rs.getString("ACCUSER_FIRST_NAME"));
                item.setACCUSER_LAST_NAME(rs.getString("ACCUSER_LAST_NAME"));
                item.setACCUSER_OPERAT_POS_CODE(rs.getString("ACCUSER_OPERAT_POS_CODE"));
                item.setACCUSER_OPREAT_POS_NAME(rs.getString("ACCUSER_OPREAT_POS_NAME"));
                item.setACCUSER_OPREAT_POS_LEVEL(rs.getString("ACCUSER_OPREAT_POS_LEVEL"));
                item.setACCUSER_OPERAT_POS_LEVEL_NAME(rs.getString("ACCUSER_OPERAT_POS_LEVEL_NAME"));
//                item.setACCUSER_OPERAT_OFF_CODE(rs.getString("ACCUSER_OPERAT_OFF_CODE"));
//                item.setACCUSER_OPERAT_OFF_NAME(rs.getString("ACCUSER_OPERAT_OFF_NAME"));
//                item.setACCUSER_OPERAT_OFF_SHORT_NAME(rs.getString("ACCUSER_OPERAT_OFF_SHORT_NAME"));
                item.setARREST_OFFICE_NAME(rs.getString("ARREST_OFFICE_NAME"));
                
                item.setGUILTBASE_NAME(rs.getString("GUILTBASE_NAME"));
                item.setSUBSECTION_NAME(rs.getString("SUBSECTION_NAME"));
                item.setSUBSECTION_DESC(rs.getString("SUBSECTION_DESC"));
                item.setSECTION_NAME(rs.getString("SECTION_NAME"));
                item.setPENALTY_DESC(rs.getString("PENALTY_DESC"));
                
                item.setLAWSUIT_IS_OUTSIDE(rs.getInt("LAWSUIT_IS_OUTSIDE"));
                item.setLAWSUIT_NO(rs.getInt("LAWSUIT_NO"));
                item.setLAWSUIT_NO_YEAR (rs.getString("LAWSUIT_NO_YEAR"));
                item.setLAWSUIT_DATE (rs.getString("LAWSUIT_DATE"));
                item.setLAWSUIT_OFFICE_NAME(rs.getString("LAWSUIT_OFFICE_NAME"));
                item.setLAWSUIT_TITLE_NAME_TH(rs.getString("LAWSUIT_TITLE_NAME_TH"));
                item.setLAWSUIT_TITLE_NAME_EN(rs.getString("LAWSUIT_TITLE_NAME_EN"));
                item.setLAWSUIT_TITLE_SHORT_NAME_TH(rs.getString("LAWSUIT_TITLE_SHORT_NAME_TH"));
                item.setLAWSUIT_TITLE_SHORT_NAME_EN(rs.getString("LAWSUIT_TITLE_SHORT_NAME_EN"));
                item.setLAWSUIT_FIRST_NAME(rs.getString("LAWSUIT_FIRST_NAME"));
                item.setLAWSUIT_LAST_NAME(rs.getString("LAWSUIT_LAST_NAME"));
                item.setLAWSUIT_OPERAT_POS_CODE(rs.getString("LAWSUIT_OPERAT_POS_CODE"));
                item.setLAWSUIT_OPREAT_POS_NAME(rs.getString("LAWSUIT_OPREAT_POS_NAME"));
                item.setLAWSUIT_OPREAT_POS_LEVEL(rs.getString("LAWSUIT_OPREAT_POS_LEVEL"));
                item.setLAWSUIT_OPERAT_POS_LEVEL_NAME(rs.getString("LAWSUIT_OPERAT_POS_LEVEL_NAME"));
//                item.setLAWSUIT_OPERAT_OFF_CODE(rs.getString("LAWSUIT_OPERATION_OFF_CODE"));
//                item.setLAWSUIT_OPERAT_OFF_NAME(rs.getString("LAWSUIT_OPERATION_OFF_NAME"));
//                item.setLAWSUIT_OPERAT_OFF_SHORT_NAME(rs.getString("LAWSUIT_OPERATION_OFF_SHORT_NAME"));
                
                item.setPROVE_NO(rs.getInt("PROVE_NO"));
                item.setPROVE_NO_YEAR (rs.getString("PROVE_NO_YEAR"));
                item.setPROVE_IS_OUTSIDE(rs.getInt("PROVE_IS_OUTSIDE"));
                item.setRECEIVE_DOC_DATE (rs.getString("RECEIVE_DOC_DATE"));
                item.setRECEIVE_OFFICE_NAME(rs.getString("RECEIVE_OFFICE_NAME"));
                item.setPROVE_TITLE_NAME_TH(rs.getString("PROVE_TITLE_NAME_TH"));
                item.setPROVE_TITLE_NAME_EN(rs.getString("PROVE_TITLE_NAME_EN"));
                item.setPROVE_TITLE_SHORT_NAME_TH(rs.getString("PROVE_TITLE_SHORT_NAME_TH"));
                item.setPROVE_TITLE_SHORT_NAME_EN(rs.getString("PROVE_TITLE_SHORT_NAME_EN"));
                item.setPROVE_FIRST_NAME(rs.getString("PROVE_FIRST_NAME"));
                item.setPROVE_LAST_NAME(rs.getString("PROVE_LAST_NAME"));
                item.setPROVE_OPERAT_POS_CODE(rs.getString("PROVE_OPERAT_POS_CODE"));
                item.setPROVE_OPREAT_POS_NAME(rs.getString("PROVE_OPREAT_POS_NAME"));
                item.setPROVE_OPREAT_POS_LEVEL(rs.getString("PROVE_OPREAT_POS_LEVEL"));
                item.setPROVE_OPERAT_POS_LEVEL_NAME(rs.getString("PROVE_OPERAT_POS_LEVEL_NAME"));
//                item.setPROVE_OPERAT_OFF_CODE(rs.getString("PROVE_OPERAT_OFF_CODE"));
//                item.setPROVE_OPERAT_OFF_NAME(rs.getString("PROVE_OPERAT_OFF_NAME"));
//                item.setPROVE_OPERAT_OFF_SHORT_NAME(rs.getString("PROVE_OPERAT_OFF_SHORT_NAME"));
                return item;
            }
        });

        return dataList;
    }

    @Override
    public List<AdjustCompareList> AdjustCompareListgetByConAdv(AdjustCompareListgetByConAdvReq req) {
        // TODO Auto-generated method stub

        String str = "";
        String tempCOMPARE_DATE_FROM   = "";
        String tempCOMPARE_DATE_TO = "";
        String tempOCCURRENCE_DATE_FROM   = "";
        String tempOCCURRENCE_DATE_TO = "";
        String tempLAWSUIT_DATE_FROM   = "";
        String tempLAWSUIT_DATE_TO = "";
        String tempRECEIVE_DOC_DATE_FROM   = "";
        String tempRECEIVE_DOC_DATE_TO = "";


        if(!"".equals(req.getRECEIVE_DOC_DATE_FROM())) {
            tempRECEIVE_DOC_DATE_FROM = String.format("%s %s", req.getRECEIVE_DOC_DATE_FROM(),Pattern.TIME_FROM);
        }

        if(!"".equals(req.getRECEIVE_DOC_DATE_TO())) {
            tempRECEIVE_DOC_DATE_TO = String.format("%s %s", req.getRECEIVE_DOC_DATE_TO(),Pattern.TIME_TO);
        }


        if(!"".equals(req.getLAWSUIT_DATE_FROM())) {
            tempLAWSUIT_DATE_FROM = String.format("%s %s", req.getLAWSUIT_DATE_FROM(),Pattern.TIME_FROM);
        }

        if(!"".equals(req.getLAWSUIT_DATE_TO())) {
            tempLAWSUIT_DATE_TO = String.format("%s %s", req.getLAWSUIT_DATE_TO(),Pattern.TIME_TO);
        }

        if(!"".equals(req.getCOMPARE_DATE_FROM())) {
            tempCOMPARE_DATE_FROM = String.format("%s %s", req.getCOMPARE_DATE_FROM(),Pattern.TIME_FROM);
        }

        if(!"".equals(req.getCOMPARE_DATE_TO())) {
            tempCOMPARE_DATE_TO = String.format("%s %s", req.getCOMPARE_DATE_TO(),Pattern.TIME_TO);
        }

        if(!"".equals(req.getOCCURRENCE_DATE_FROM())) {
            tempOCCURRENCE_DATE_FROM = String.format("%s %s", req.getOCCURRENCE_DATE_FROM(),Pattern.TIME_FROM);
        }

        if(!"".equals(req.getOCCURRENCE_DATE_TO())) {
            tempOCCURRENCE_DATE_TO = String.format("%s %s", req.getOCCURRENCE_DATE_TO(),Pattern.TIME_TO);
        }

        if(req.getACCOUNT_OFFICE_CODE() != null && !"".equals(req.getACCOUNT_OFFICE_CODE()) && !"00".equals(req.getACCOUNT_OFFICE_CODE())) {

            if(req.getACCOUNT_OFFICE_CODE().length() == 6) {

                if("00".equals(req.getACCOUNT_OFFICE_CODE().substring(0, 2))) {
                    str = " ";
                }else if("0000".equals(req.getACCOUNT_OFFICE_CODE().substring(2, 6))) {
                    str = " AND" +
                            "( " +
                            "  SUBSTR(OPS_LAWSUIT_STAFF.OPERATION_OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
                            "  OR SUBSTR(OPS_LAWSUIT_STAFF.MANAGEMENT_OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
                            "  OR SUBSTR(OPS_LAWSUIT_STAFF.REPRESENT_OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
                            "  OR SUBSTR(OPS_COMPARE_STAFF.OPERATION_OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
                            "  OR SUBSTR(OPS_COMPARE_STAFF.MANAGEMENT_OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
                            "  OR SUBSTR(OPS_COMPARE_STAFF.REPRESENT_OFFICE_CODE,1,2) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,2) " +
                            ") " ;
                }else if("00".equals(req.getACCOUNT_OFFICE_CODE().substring(4, 6))) {
                    str = " AND " +
                            "( " +
                            "  SUBSTR(OPS_LAWSUIT_STAFF.OPERATION_OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
                            "  OR SUBSTR(OPS_LAWSUIT_STAFF.MANAGEMENT_OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
                            "  OR SUBSTR(OPS_LAWSUIT_STAFF.REPRESENT_OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
                            "  OR SUBSTR(OPS_COMPARE_STAFF.OPERATION_OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
                            "  OR SUBSTR(OPS_COMPARE_STAFF.MANAGEMENT_OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
                            "  OR SUBSTR(OPS_COMPARE_STAFF.REPRESENT_OFFICE_CODE,1,4) = SUBSTR('"+req.getACCOUNT_OFFICE_CODE()+"',1,4) " +
                            " )";
                }else {
                    str = " AND" +
                            " (" +
                            "  OPS_LAWSUIT_STAFF.OPERATION_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
                            "  OR OPS_LAWSUIT_STAFF.MANAGEMENT_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
                            "  OR OPS_LAWSUIT_STAFF.REPRESENT_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
                            "  OR OPS_COMPARE_STAFF.OPERATION_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
                            "  OR OPS_COMPARE_STAFF.MANAGEMENT_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
                            "  OR OPS_COMPARE_STAFF.REPRESENT_OFFICE_CODE = '"+req.getACCOUNT_OFFICE_CODE()+"'" +
                            " )";
                }

            }

        }

        StringBuilder sqlBuilder = new StringBuilder()
                .append("SELECT " +
                        "OPS_COMPARE.COMPARE_ID," +
                        "OPS_LAWSUIT.LAWSUIT_ID," +
                        "OPS_LAWSUIT.INDICTMENT_ID," +
                        "OPS_COMPARE.COMPARE_NO," +
                        "to_char(OPS_COMPARE.COMPARE_NO_YEAR,'"+ Pattern.FORMAT_DATETIME+"') as COMPARE_NO_YEAR," +
                        "to_char(OPS_COMPARE.COMPARE_DATE,'"+ Pattern.FORMAT_DATETIME+"') as COMPARE_DATE," +
                        "OPS_COMPARE_STAFF.TITLE_NAME_TH COMPARE_TITLE_NAME_TH," +
                        "OPS_COMPARE_STAFF.TITLE_NAME_EN COMPARE_TITLE_NAME_EN," +
                        "OPS_COMPARE_STAFF.TITLE_SHORT_NAME_TH COMPARE_TITLE_SHORT_NAME_TH," +
                        "OPS_COMPARE_STAFF.TITLE_SHORT_NAME_EN COMPARE_TITLE_SHORT_NAME_EN," +
                        "OPS_COMPARE_STAFF.FIRST_NAME COMPARE_FIRST_NAME," +
                        "OPS_COMPARE_STAFF.LAST_NAME COMPARE_LAST_NAME," +
                        "OPS_COMPARE_STAFF.OPERATION_POS_CODE COMPARE_OPERAT_POS_CODE," +
                        "OPS_COMPARE_STAFF.OPREATION_POS_NAME COMPARE_OPREAT_POS_NAME," +
                        "OPS_COMPARE_STAFF.OPREATION_POS_LEVEL COMPARE_OPREAT_POS_LEVEL," +
                        "OPS_COMPARE_STAFF.OPERATION_POS_LEVEL_NAME COMPARE_OPERAT_POS_LEVEL_NAME," +
                        "OPS_COMPARE_STAFF.OPERATION_OFFICE_CODE COMPARE_OPERAT_OFF_CODE," +
                        "OPS_COMPARE_STAFF.OPERATION_OFFICE_NAME COMPARE_OPERATION_OFF_NAME," +
                        "OPS_COMPARE_STAFF.OPERATION_OFFICE_SHORT_NAME COMPARE_OPERAT_OFF_SHORT_NAME," +
                        "OPS_COMPARE.OFFICE_NAME COMPARE_OFFICE_NAME," +
                        "OPS_COMPARE.IS_OUTSIDE COMPARE_IS_OUTSIDE," +
                        "OPS_ARREST.ARREST_CODE," +
                        "to_char(OPS_ARREST.OCCURRENCE_DATE,'"+ Pattern.FORMAT_DATETIME+"') as OCCURRENCE_DATE," +
                        "OPS_ARREST_STAFF.TITLE_NAME_TH ACCUSER_TITLE_NAME_TH," +
                        "OPS_ARREST_STAFF.TITLE_NAME_EN ACCUSER_TITLE_NAME_EN," +
                        "OPS_ARREST_STAFF.TITLE_SHORT_NAME_TH ACCUSER_TITLE_SHORT_NAME_TH," +
                        "OPS_ARREST_STAFF.TITLE_SHORT_NAME_EN ACCUSER_TITLE_SHORT_NAME_EN," +
                        "OPS_ARREST_STAFF.FIRST_NAME ACCUSER_FIRST_NAME," +
                        "OPS_ARREST_STAFF.LAST_NAME ACCUSER_LAST_NAME," +
                        "OPS_ARREST_STAFF.OPERATION_POS_CODE ACCUSER_OPERAT_POS_CODE," +
                        "OPS_ARREST_STAFF.OPREATION_POS_NAME ACCUSER_OPREAT_POS_NAME," +
                        "OPS_ARREST_STAFF.OPREATION_POS_LEVEL ACCUSER_OPREAT_POS_LEVEL," +
                        "OPS_ARREST_STAFF.OPERATION_POS_LEVEL_NAME ACCUSER_OPERAT_POS_LEVEL_NAME," +
                        "OPS_ARREST_STAFF.OPERATION_OFFICE_CODE ACCUSER_OPERAT_OFF_CODE," +
                        "OPS_ARREST_STAFF.OPERATION_OFFICE_NAME ACCUSER_OPERAT_OFF_NAME," +
                        "OPS_ARREST_STAFF.OPERATION_OFFICE_SHORT_NAME ACCUSER_OPERAT_OFF_SHORT_NAME," +
                        "OPS_ARREST.OFFICE_NAME ARREST_OFFICE_NAME," +
                        "MAS_LAW_GUILTBASE.GUILTBASE_NAME," +
                        "MAS_LAW_GROUP_SUBSECTION.SUBSECTION_NAME," +
                        "MAS_LAW_GROUP_SUBSECTION.SUBSECTION_DESC," +
                        "MAS_LAW_GROUP_SECTION.SECTION_NAME," +
                        "MAS_LAW_PENALTY.PENALTY_DESC," +
                        "OPS_LAWSUIT.IS_OUTSIDE LAWSUIT_IS_OUTSIDE," +
                        "OPS_LAWSUIT.LAWSUIT_NO," +
                        "to_char(OPS_LAWSUIT.LAWSUIT_NO_YEAR,'"+ Pattern.FORMAT_DATETIME+"') as LAWSUIT_NO_YEAR," +
                        "to_char(OPS_LAWSUIT.LAWSUIT_DATE,'"+ Pattern.FORMAT_DATETIME+"') as LAWSUIT_DATE," +
                        "OPS_LAWSUIT.OFFICE_NAME LAWSUIT_OFFICE_NAME," +
                        "OPS_LAWSUIT_STAFF.TITLE_NAME_TH LAWSUIT_TITLE_NAME_TH," +
                        "OPS_LAWSUIT_STAFF.TITLE_NAME_EN LAWSUIT_TITLE_NAME_EN," +
                        "OPS_LAWSUIT_STAFF.TITLE_SHORT_NAME_TH LAWSUIT_TITLE_SHORT_NAME_TH," +
                        "OPS_LAWSUIT_STAFF.TITLE_SHORT_NAME_EN LAWSUIT_TITLE_SHORT_NAME_EN," +
                        "OPS_LAWSUIT_STAFF.FIRST_NAME LAWSUIT_FIRST_NAME," +
                        "OPS_LAWSUIT_STAFF.LAST_NAME LAWSUIT_LAST_NAME," +
                        "OPS_LAWSUIT_STAFF.OPERATION_POS_CODE LAWSUIT_OPERAT_POS_CODE," +
                        "OPS_LAWSUIT_STAFF.OPREATION_POS_NAME LAWSUIT_OPREAT_POS_NAME," +
                        "OPS_LAWSUIT_STAFF.OPREATION_POS_LEVEL LAWSUIT_OPREAT_POS_LEVEL," +
                        "OPS_LAWSUIT_STAFF.OPERATION_POS_LEVEL_NAME LAWSUIT_OPERAT_POS_LEVEL_NAME," +
                        "OPS_LAWSUIT_STAFF.OPERATION_OFFICE_CODE LAWSUIT_OPERAT_OFF_CODE," +
                        "OPS_LAWSUIT_STAFF.OPERATION_OFFICE_NAME LAWSUIT_OPERAT_OFF_NAME," +
                        "OPS_LAWSUIT_STAFF.OPERATION_OFFICE_SHORT_NAME LAWSUIT_OPERAT_OFF_SHORT_NAME," +
                        "OPS_PROVE.PROVE_NO PROVE_NO," +
                        "to_char(OPS_PROVE.PROVE_NO_YEAR,'"+ Pattern.FORMAT_DATETIME+"') as PROVE_NO_YEAR," +
                        "OPS_PROVE.IS_OUTSIDE PROVE_IS_OUTSIDE," +
                        "to_char(OPS_PROVE.RECEIVE_DOC_DATE,'"+ Pattern.FORMAT_DATETIME+"') as RECEIVE_DOC_DATE," +
                        "OPS_PROVE.RECEIVE_OFFICE_NAME RECEIVE_OFFICE_NAME," +
                        "OPS_PROVE_STAFF.TITLE_NAME_TH PROVE_TITLE_NAME_TH," +
                        "OPS_PROVE_STAFF.TITLE_NAME_EN PROVE_TITLE_NAME_EN," +
                        "OPS_PROVE_STAFF.TITLE_SHORT_NAME_TH PROVE_TITLE_SHORT_NAME_TH," +
                        "OPS_PROVE_STAFF.TITLE_SHORT_NAME_EN PROVE_TITLE_SHORT_NAME_EN," +
                        "OPS_PROVE_STAFF.FIRST_NAME PROVE_FIRST_NAME," +
                        "OPS_PROVE_STAFF.LAST_NAME PROVE_LAST_NAME," +
                        "OPS_PROVE_STAFF.OPERATION_POS_CODE PROVE_OPERAT_POS_CODE," +
                        "OPS_PROVE_STAFF.OPREATION_POS_NAME PROVE_OPREAT_POS_NAME," +
                        "OPS_PROVE_STAFF.OPREATION_POS_LEVEL PROVE_OPREAT_POS_LEVEL," +
                        "OPS_PROVE_STAFF.OPERATION_POS_LEVEL_NAME PROVE_OPERAT_POS_LEVEL_NAME," +
                        "OPS_PROVE_STAFF.OPERATION_OFFICE_CODE PROVE_OPERAT_OFF_CODE," +
                        "OPS_PROVE_STAFF.OPERATION_OFFICE_NAME PROVE_OPERAT_OFF_NAME," +
                        "OPS_PROVE_STAFF.OPERATION_OFFICE_SHORT_NAME PROVE_OPERAT_OFF_SHORT_NAME" +
                        " FROM OPS_LAWSUIT" +
                        " LEFT JOIN OPS_COMPARE ON OPS_LAWSUIT.LAWSUIT_ID = OPS_COMPARE.LAWSUIT_ID" +
                        " AND OPS_COMPARE.IS_ACTIVE = 1" +
                        " LEFT JOIN OPS_COMPARE_STAFF ON OPS_COMPARE.COMPARE_ID = OPS_COMPARE_STAFF.COMPARE_ID" +
                        " AND OPS_COMPARE_STAFF.IS_ACTIVE = 1" +
                        " AND OPS_COMPARE_STAFF.CONTRIBUTOR_ID = 27" +
                        " INNER JOIN OPS_ARREST_INDICTMENT ON OPS_LAWSUIT.INDICTMENT_ID = OPS_ARREST_INDICTMENT.INDICTMENT_ID" +
                        " INNER JOIN MAS_LAW_GUILTBASE ON OPS_ARREST_INDICTMENT.GUILTBASE_ID = MAS_LAW_GUILTBASE.GUILTBASE_ID" +
                        " INNER JOIN MAS_LAW_GROUP_SUBSECTION_RULE ON MAS_LAW_GUILTBASE.SUBSECTION_RULE_ID = MAS_LAW_GROUP_SUBSECTION_RULE.SUBSECTION_RULE_ID" +
                        " INNER JOIN MAS_LAW_GROUP_SUBSECTION ON MAS_LAW_GROUP_SUBSECTION_RULE.SUBSECTION_ID = MAS_LAW_GROUP_SUBSECTION.SUBSECTION_ID" +
                        " INNER JOIN MAS_LAW_GROUP_SECTION ON MAS_LAW_GROUP_SUBSECTION_RULE.SECTION_ID = MAS_LAW_GROUP_SECTION.SECTION_ID" +
                        " INNER JOIN MAS_LAW_PENALTY ON MAS_LAW_GROUP_SECTION.SECTION_ID = MAS_LAW_PENALTY.SECTION_ID" +
                        " INNER JOIN OPS_ARREST ON OPS_ARREST_INDICTMENT.ARREST_ID = OPS_ARREST.ARREST_ID" +
                        " INNER JOIN OPS_ARREST_STAFF ON OPS_ARREST.ARREST_ID = OPS_ARREST_STAFF.ARREST_ID" +
                        " INNER JOIN OPS_LAWSUIT_STAFF ON OPS_LAWSUIT.LAWSUIT_ID = OPS_LAWSUIT_STAFF.LAWSUIT_ID" +
                        " LEFT JOIN OPS_PROVE ON OPS_LAWSUIT.LAWSUIT_ID = OPS_PROVE.LAWSUIT_ID" +
                        " AND OPS_PROVE.IS_ACTIVE = 1" +
                        " LEFT JOIN OPS_PROVE_STAFF ON OPS_PROVE.PROVE_ID = OPS_PROVE_STAFF.PROVE_ID" +
                        " AND OPS_PROVE_STAFF.IS_ACTIVE = 1" +
                        " AND OPS_PROVE_STAFF.CONTRIBUTOR_ID = 25" +
                        " WHERE OPS_LAWSUIT.IS_ACTIVE = 1" +
                        " AND OPS_ARREST_INDICTMENT.IS_ACTIVE = 1" +
                        " AND OPS_ARREST.IS_ACTIVE = 1" +
                        " AND OPS_ARREST_STAFF.IS_ACTIVE = 1" +
                        " AND OPS_ARREST_STAFF.CONTRIBUTOR_ID = 14" +
                        " AND OPS_LAWSUIT_STAFF.IS_ACTIVE = 1" +
                        " AND OPS_LAWSUIT_STAFF.CONTRIBUTOR_ID = 16"  );

        if(req.getCOMPARE_NO() != null && !"".equals(req.getCOMPARE_NO())) {
            sqlBuilder.append(" AND LOWER ( OPS_COMPARE.COMPARE_NO ) LIKE LOWER(REPLACE('%"+req.getCOMPARE_NO()+"%',' ','')) ");
        }

        if(req.getCOMPARE_NO_YEAR() != null && !"".equals(req.getCOMPARE_NO_YEAR())) {
            sqlBuilder.append(" AND EXTRACT(YEAR FROM OPS_COMPARE.COMPARE_NO_YEAR) = '"+req.getCOMPARE_NO_YEAR()+"' ");
        }

        if(req.getCOMPARE_DATE_FROM() != null && !"".equals(req.getCOMPARE_DATE_FROM()) && req.getCOMPARE_DATE_TO() != null && !"".equals(req.getCOMPARE_DATE_TO())) {
            sqlBuilder.append(" AND OPS_COMPARE.COMPARE_DATE BETWEEN  to_date(nvl('"+tempCOMPARE_DATE_FROM+"','0001-01-01 00:00'),'YYYY-MM-DD HH24:MI') and to_date(nvl('"+tempCOMPARE_DATE_TO+"','9999-12-31 23:59'),'YYYY-MM-DD HH24:MI')");
        }

        if(req.getCOMPARE_NAME() != null && !"".equals(req.getCOMPARE_NAME())) {
            sqlBuilder.append( "AND" +
                    " ( " +
                    "  LOWER(OPS_COMPARE_STAFF.TITLE_NAME_TH||OPS_COMPARE_STAFF.FIRST_NAME||OPS_COMPARE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getCOMPARE_NAME()+"%',' ','')) " +
                    "  OR LOWER(OPS_COMPARE_STAFF.TITLE_NAME_EN||OPS_COMPARE_STAFF.FIRST_NAME||OPS_COMPARE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getCOMPARE_NAME()+"%',' ','')) " +
                    "  OR LOWER(OPS_COMPARE_STAFF.TITLE_SHORT_NAME_TH||OPS_COMPARE_STAFF.FIRST_NAME||OPS_COMPARE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getCOMPARE_NAME()+"%',' ','')) " +
                    "  OR LOWER(OPS_COMPARE_STAFF.TITLE_SHORT_NAME_EN||OPS_COMPARE_STAFF.FIRST_NAME||OPS_COMPARE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getCOMPARE_NAME()+"%',' ','')) " +
                    " )");
        }

        if(req.getCOMPARE_OFFICE_NAME() != null && !"".equals(req.getCOMPARE_OFFICE_NAME())) {
            sqlBuilder.append(" AND LOWER ( OPS_COMPARE.OFFICE_NAME ) LIKE LOWER(REPLACE('%"+req.getARREST_OFFICE_NAME()+"%',' ','')) ");
        }

        if(req.getCOMPARE_IS_OUTSIDE() != null && req.getCOMPARE_IS_OUTSIDE() != 0) {
            sqlBuilder.append(" AND OPS_COMPARE.IS_OUTSIDE = '"+req.getCOMPARE_IS_OUTSIDE()+"' ");
        }

        if(req.getARREST_CODE() != null && !"".equals(req.getARREST_CODE())) {
            sqlBuilder.append(" AND LOWER (OPS_ARREST.ARREST_CODE) LIKE LOWER(REPLACE('%"+req.getARREST_CODE()+"%',' ','')) ");
        }

        if(req.getOCCURRENCE_DATE_FROM() != null && !"".equals(req.getOCCURRENCE_DATE_FROM()) && req.getOCCURRENCE_DATE_TO() != null && !"".equals(req.getOCCURRENCE_DATE_TO())) {
            sqlBuilder.append(" AND OPS_ARREST.OCCURRENCE_DATE BETWEEN  to_date(nvl('"+tempOCCURRENCE_DATE_FROM+"','0001-01-01 00:00'),'YYYY-MM-DD HH24:MI') and to_date(nvl('"+tempOCCURRENCE_DATE_TO+"','9999-12-31 23:59'),'YYYY-MM-DD HH24:MI')");
        }

        if(req.getARREST_NAME() != null && !"".equals(req.getARREST_NAME())) {
            sqlBuilder.append( "AND" +
                    " ( " +
                    "  LOWER(OPS_ARREST_STAFF.TITLE_NAME_TH||OPS_ARREST_STAFF.FIRST_NAME||OPS_ARREST_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getARREST_NAME()+"%',' ',''))" +
                    "  OR LOWER(OPS_ARREST_STAFF.TITLE_NAME_EN||OPS_ARREST_STAFF.FIRST_NAME||OPS_ARREST_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getARREST_NAME()+"%',' ',''))" +
                    "  OR LOWER(OPS_ARREST_STAFF.TITLE_SHORT_NAME_TH||OPS_ARREST_STAFF.FIRST_NAME||OPS_ARREST_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getARREST_NAME()+"%',' ',''))" +
                    "  OR LOWER(OPS_ARREST_STAFF.TITLE_SHORT_NAME_EN||OPS_ARREST_STAFF.FIRST_NAME||OPS_ARREST_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getARREST_NAME()+"%',' ',''))" +
                    " )");
        }

        if(req.getARREST_OFFICE_NAME() != null && !"".equals(req.getARREST_OFFICE_NAME())) {
            sqlBuilder.append(" AND LOWER (OPS_ARREST.OFFICE_NAME) LIKE LOWER(REPLACE('%"+req.getARREST_OFFICE_NAME()+"%',' ','')) ");
        }

        if(req.getSUBSECTION_NAME() != null && !"".equals(req.getSUBSECTION_NAME())) {
            sqlBuilder.append("  AND LOWER (MAS_LAW_GROUP_SUBSECTION.SUBSECTION_NAME) LIKE LOWER(REPLACE('%"+req.getSUBSECTION_NAME()+"%',' ','')) ");
        }

        if(req.getGUILTBASE_NAME() != null && !"".equals(req.getGUILTBASE_NAME())) {
            sqlBuilder.append("  AND LOWER (MAS_LAW_GUILTBASE.GUILTBASE_NAME) LIKE LOWER(REPLACE('%"+req.getGUILTBASE_NAME()+"%',' ','')) ");
        }

        if(req.getLAWSUIT_IS_OUTSIDE() != null && req.getLAWSUIT_IS_OUTSIDE() != 0) {
            sqlBuilder.append(" AND OPS_LAWSUIT.IS_OUTSIDE = '"+req.getLAWSUIT_IS_OUTSIDE()+"' ");
        }

        if(req.getLAWSUIT_NO() != null && !"".equals(req.getLAWSUIT_NO())) {
            sqlBuilder.append("  AND LOWER (OPS_LAWSUIT.LAWSUIT_NO) LIKE LOWER(REPLACE('%"+req.getLAWSUIT_NO()+"%',' ','')) ");
        }

        if(req.getLAWSUIT_NO_YEAR() != null && !"".equals(req.getLAWSUIT_NO_YEAR())) {
            sqlBuilder.append("  AND EXTRACT(YEAR FROM OPS_LAWSUIT.LAWSUIT_NO_YEAR) = '"+req.getLAWSUIT_NO_YEAR()+"' ");
        }


        if(req.getLAWSUIT_DATE_FROM() != null && !"".equals(req.getLAWSUIT_DATE_FROM()) && req.getLAWSUIT_DATE_TO() != null && !"".equals(req.getLAWSUIT_DATE_TO())) {
            sqlBuilder.append(" AND OPS_LAWSUIT.LAWSUIT_DATE BETWEEN  to_date(nvl('"+tempLAWSUIT_DATE_FROM+"','0001-01-01 00:00'),'YYYY-MM-DD HH24:MI') and to_date(nvl('"+tempLAWSUIT_DATE_TO+"','9999-12-31 23:59'),'YYYY-MM-DD HH24:MI')");
        }

        if(req.getLAWSUIT_OFFICE_NAME() != null && !"".equals(req.getLAWSUIT_OFFICE_NAME())) {
            sqlBuilder.append("  AND LOWER (OPS_LAWSUIT.OFFICE_NAME) LIKE LOWER(REPLACE('%"+req.getLAWSUIT_OFFICE_NAME()+"%',' ','')) ");
        }


        if(req.getLAWSUIT_NAME() != null && !"".equals(req.getLAWSUIT_NAME())) {
            sqlBuilder.append( "AND" +
                    " ( " +
                    "  LOWER(OPS_LAWSUIT_STAFF.TITLE_NAME_TH||OPS_LAWSUIT_STAFF.FIRST_NAME||OPS_LAWSUIT_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getLAWSUIT_NAME()+"%',' ',''))" +
                    "  OR LOWER(OPS_LAWSUIT_STAFF.TITLE_NAME_EN||OPS_LAWSUIT_STAFF.FIRST_NAME||OPS_LAWSUIT_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getLAWSUIT_NAME()+"%',' ','')) " +
                    "  OR LOWER(OPS_LAWSUIT_STAFF.TITLE_SHORT_NAME_TH||OPS_LAWSUIT_STAFF.FIRST_NAME||OPS_LAWSUIT_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getLAWSUIT_NAME()+"%',' ','')) " +
                    "  OR LOWER(OPS_LAWSUIT_STAFF.TITLE_SHORT_NAME_EN||OPS_LAWSUIT_STAFF.FIRST_NAME||OPS_LAWSUIT_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getLAWSUIT_NAME()+"%',' ','')) " +
                    " )");
        }

        if(req.getPROVE_IS_OUTSIDE() != null && req.getPROVE_IS_OUTSIDE() != 0) {
            sqlBuilder.append(" AND OPS_PROVE.IS_OUTSIDE = '"+req.getPROVE_IS_OUTSIDE()+"' ");
        }

        if(req.getPROVE_NO() != null && !"".equals(req.getPROVE_NO())) {
            sqlBuilder.append("  AND LOWER (OPS_PROVE.PROVE_NO) LIKE LOWER(REPLACE('%"+req.getPROVE_NO()+"%',' ','')) ");
        }

        if(req.getPROVE_NO_YEAR() != null && !"".equals(req.getPROVE_NO_YEAR())) {
            sqlBuilder.append("  AND EXTRACT(YEAR FROM OPS_PROVE.PROVE_NO_YEAR) = '"+req.getPROVE_NO_YEAR()+"' ");
        }

        if(req.getRECEIVE_DOC_DATE_FROM() != null && !"".equals(req.getRECEIVE_DOC_DATE_FROM()) && req.getRECEIVE_DOC_DATE_TO() != null && !"".equals(req.getRECEIVE_DOC_DATE_TO())) {
            sqlBuilder.append(" AND OPS_PROVE.RECEIVE_DOC_DATE BETWEEN  to_date(nvl('"+tempRECEIVE_DOC_DATE_FROM+"','0001-01-01 00:00'),'YYYY-MM-DD HH24:MI') and to_date(nvl('"+tempRECEIVE_DOC_DATE_TO+"','9999-12-31 23:59'),'YYYY-MM-DD HH24:MI')");
        }

        if(req.getRECEIVE_OFFICE_NAME() != null && !"".equals(req.getRECEIVE_OFFICE_NAME())) {
            sqlBuilder.append("  AND LOWER (OPS_PROVE.RECEIVE_OFFICE_NAME) LIKE LOWER(REPLACE('%"+req.getRECEIVE_OFFICE_NAME()+"%',' ','')) ");
        }

        if(req.getPROVE_NAME() != null && !"".equals(req.getPROVE_NAME())) {
            sqlBuilder.append( "AND" +
                    " ( " +
                    "  LOWER(OPS_PROVE_STAFF.TITLE_NAME_TH||OPS_PROVE_STAFF.FIRST_NAME||OPS_PROVE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getPROVE_NAME()+"%',' ','')) " +
                    "  OR LOWER(OPS_PROVE_STAFF.TITLE_NAME_EN||OPS_PROVE_STAFF.FIRST_NAME||OPS_PROVE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getPROVE_NAME()+"%',' ','')) " +
                    "  OR LOWER(OPS_PROVE_STAFF.TITLE_SHORT_NAME_TH||OPS_PROVE_STAFF.FIRST_NAME||OPS_PROVE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getPROVE_NAME()+"%',' ','')) " +
                    "  OR LOWER(OPS_PROVE_STAFF.TITLE_SHORT_NAME_EN||OPS_PROVE_STAFF.FIRST_NAME||OPS_PROVE_STAFF.LAST_NAME) LIKE LOWER(REPLACE('%"+req.getPROVE_NAME()+"%',' ','')) " +
                    " )");
        }




        sqlBuilder.append(str+ " ORDER BY OPS_ARREST.ARREST_CODE, OPS_LAWSUIT.LAWSUIT_ID, OPS_COMPARE.COMPARE_ID DESC ");



        log.info("[SQL]  : " + sqlBuilder.toString());

        @SuppressWarnings("unchecked")
        List<AdjustCompareList> dataList = getJdbcTemplate().query(sqlBuilder.toString(), new RowMapper() {

            public AdjustCompareList mapRow(ResultSet rs, int rowNum) throws SQLException {
                AdjustCompareList item = new AdjustCompareList();
                item.setCOMPARE_ID(rs.getInt("COMPARE_ID"));
                item.setLAWSUIT_ID(rs.getInt("LAWSUIT_ID"));
                item.setINDICTMENT_ID(rs.getInt("INDICTMENT_ID"));
                item.setCOMPARE_NO(rs.getString("COMPARE_NO"));
                item.setCOMPARE_NO_YEAR(rs.getString("COMPARE_NO_YEAR"));
                item.setCOMPARE_DATE(rs.getString("COMPARE_DATE"));
                item.setCOMPARE_TITLE_NAME_TH(rs.getString("COMPARE_TITLE_NAME_TH"));
                item.setCOMPARE_TITLE_NAME_EN(rs.getString("COMPARE_TITLE_NAME_EN"));
                item.setCOMPARE_TITLE_SHORT_NAME_TH(rs.getString("COMPARE_TITLE_SHORT_NAME_TH"));
                item.setCOMPARE_TITLE_SHORT_NAME_EN(rs.getString("COMPARE_TITLE_SHORT_NAME_EN"));
                item.setCOMPARE_FIRST_NAME(rs.getString("COMPARE_FIRST_NAME"));
                item.setCOMPARE_LAST_NAME(rs.getString("COMPARE_LAST_NAME"));
                item.setCOMPARE_OPERAT_POS_CODE(rs.getString("COMPARE_OPERAT_POS_CODE"));
                item.setCOMPARE_OPREAT_POS_NAME(rs.getString("COMPARE_OPREAT_POS_NAME"));
                item.setCOMPARE_OPREAT_POS_LEVEL(rs.getString("COMPARE_OPREAT_POS_LEVEL"));
                item.setCOMPARE_OPERAT_POS_LEVEL_NAME(rs.getString("COMPARE_OPERAT_POS_LEVEL_NAME"));
//                item.setCOMPARE_OPERAT_OFF_CODE(rs.getString("COMPARE_OPERAT_OFF_CODE"));
//                item.setCOMPARE_OPERAT_OFF_NAME(rs.getString("COMPARE_OPERAT_OFF_NAME"));
//                item.setCOMPARE_OPERAT_OFF_SHORT_NAME(rs.getString("COMPARE_OPERAT_OFF_SHORT_NAME"));
                item.setCOMPARE_OFFICE_NAME(rs.getString("COMPARE_OFFICE_NAME"));
                item.setCOMPARE_IS_OUTSIDE(rs.getInt("COMPARE_IS_OUTSIDE"));
                
                item.setARREST_CODE(rs.getString("ARREST_CODE"));
                item.setOCCURRENCE_DATE(rs.getString("OCCURRENCE_DATE"));
                item.setACCUSER_TITLE_NAME_TH(rs.getString("ACCUSER_TITLE_NAME_TH"));
                item.setACCUSER_TITLE_NAME_EN(rs.getString("ACCUSER_TITLE_NAME_EN"));
                item.setACCUSER_TITLE_SHORT_NAME_TH(rs.getString("ACCUSER_TITLE_SHORT_NAME_TH"));
                item.setACCUSER_TITLE_SHORT_NAME_EN(rs.getString("ACCUSER_TITLE_SHORT_NAME_EN"));
                item.setACCUSER_FIRST_NAME(rs.getString("ACCUSER_FIRST_NAME"));
                item.setACCUSER_LAST_NAME(rs.getString("ACCUSER_LAST_NAME"));
                item.setACCUSER_OPERAT_POS_CODE(rs.getString("ACCUSER_OPERAT_POS_CODE"));
                item.setACCUSER_OPREAT_POS_NAME(rs.getString("ACCUSER_OPREAT_POS_NAME"));
                item.setACCUSER_OPREAT_POS_LEVEL(rs.getString("ACCUSER_OPREAT_POS_LEVEL"));
                item.setACCUSER_OPERAT_POS_LEVEL_NAME(rs.getString("ACCUSER_OPERAT_POS_LEVEL_NAME"));
//                item.setACCUSER_OPERAT_OFF_CODE(rs.getString("ACCUSER_OPERAT_OFF_CODE"));
//                item.setACCUSER_OPERAT_OFF_NAME(rs.getString("ACCUSER_OPERAT_OFF_NAME"));
//                item.setACCUSER_OPERAT_OFF_SHORT_NAME(rs.getString("ACCUSER_OPERAT_OFF_SHORT_NAME"));
                item.setARREST_OFFICE_NAME(rs.getString("ARREST_OFFICE_NAME"));
                
                item.setGUILTBASE_NAME(rs.getString("GUILTBASE_NAME"));
                item.setSUBSECTION_NAME(rs.getString("SUBSECTION_NAME"));
                item.setSUBSECTION_DESC(rs.getString("SUBSECTION_DESC"));
                item.setSECTION_NAME(rs.getString("SECTION_NAME"));
                item.setPENALTY_DESC(rs.getString("PENALTY_DESC"));
                
                item.setLAWSUIT_IS_OUTSIDE(rs.getInt("LAWSUIT_IS_OUTSIDE"));
                item.setLAWSUIT_NO(rs.getInt("LAWSUIT_NO"));
                item.setLAWSUIT_NO_YEAR (rs.getString("LAWSUIT_NO_YEAR"));
                item.setLAWSUIT_DATE (rs.getString("LAWSUIT_DATE"));
                item.setLAWSUIT_OFFICE_NAME(rs.getString("LAWSUIT_OFFICE_NAME"));
                item.setLAWSUIT_TITLE_NAME_TH(rs.getString("LAWSUIT_TITLE_NAME_TH"));
                item.setLAWSUIT_TITLE_NAME_EN(rs.getString("LAWSUIT_TITLE_NAME_EN"));
                item.setLAWSUIT_TITLE_SHORT_NAME_TH(rs.getString("LAWSUIT_TITLE_SHORT_NAME_TH"));
                item.setLAWSUIT_TITLE_SHORT_NAME_EN(rs.getString("LAWSUIT_TITLE_SHORT_NAME_EN"));
                item.setLAWSUIT_FIRST_NAME(rs.getString("LAWSUIT_FIRST_NAME"));
                item.setLAWSUIT_LAST_NAME(rs.getString("LAWSUIT_LAST_NAME"));
                item.setLAWSUIT_OPERAT_POS_CODE(rs.getString("LAWSUIT_OPERAT_POS_CODE"));
                item.setLAWSUIT_OPREAT_POS_NAME(rs.getString("LAWSUIT_OPREAT_POS_NAME"));
                item.setLAWSUIT_OPREAT_POS_LEVEL(rs.getString("LAWSUIT_OPREAT_POS_LEVEL"));
                item.setLAWSUIT_OPERAT_POS_LEVEL_NAME(rs.getString("LAWSUIT_OPERAT_POS_LEVEL_NAME"));
//                item.setLAWSUIT_OPERAT_OFF_CODE(rs.getString("LAWSUIT_OPERATION_OFF_CODE"));
//                item.setLAWSUIT_OPERAT_OFF_NAME(rs.getString("LAWSUIT_OPERATION_OFF_NAME"));
//                item.setLAWSUIT_OPERAT_OFF_SHORT_NAME(rs.getString("LAWSUIT_OPERATION_OFF_SHORT_NAME"));
                
                item.setPROVE_NO(rs.getInt("PROVE_NO"));
                item.setPROVE_NO_YEAR (rs.getString("PROVE_NO_YEAR"));
                item.setPROVE_IS_OUTSIDE(rs.getInt("PROVE_IS_OUTSIDE"));
                item.setRECEIVE_DOC_DATE (rs.getString("RECEIVE_DOC_DATE"));
                item.setRECEIVE_OFFICE_NAME(rs.getString("RECEIVE_OFFICE_NAME"));
                item.setPROVE_TITLE_NAME_TH(rs.getString("PROVE_TITLE_NAME_TH"));
                item.setPROVE_TITLE_NAME_EN(rs.getString("PROVE_TITLE_NAME_EN"));
                item.setPROVE_TITLE_SHORT_NAME_TH(rs.getString("PROVE_TITLE_SHORT_NAME_TH"));
                item.setPROVE_TITLE_SHORT_NAME_EN(rs.getString("PROVE_TITLE_SHORT_NAME_EN"));
                item.setPROVE_FIRST_NAME(rs.getString("PROVE_FIRST_NAME"));
                item.setPROVE_LAST_NAME(rs.getString("PROVE_LAST_NAME"));
                item.setPROVE_OPERAT_POS_CODE(rs.getString("PROVE_OPERAT_POS_CODE"));
                item.setPROVE_OPREAT_POS_NAME(rs.getString("PROVE_OPREAT_POS_NAME"));
                item.setPROVE_OPREAT_POS_LEVEL(rs.getString("PROVE_OPREAT_POS_LEVEL"));
                item.setPROVE_OPERAT_POS_LEVEL_NAME(rs.getString("PROVE_OPERAT_POS_LEVEL_NAME"));
//                item.setPROVE_OPERAT_OFF_CODE(rs.getString("PROVE_OPERAT_OFF_CODE"));
//                item.setPROVE_OPERAT_OFF_NAME(rs.getString("PROVE_OPERAT_OFF_NAME"));
//                item.setPROVE_OPERAT_OFF_SHORT_NAME(rs.getString("PROVE_OPERAT_OFF_SHORT_NAME"));
                return item;
            }
        });

        return dataList;
    }
}
