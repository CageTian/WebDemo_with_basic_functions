package com.relation.scholar.dao;

import com.relation.pager.PageBean;
import com.relation.pager.sqlExpression;
import com.relation.scholar.domain.Scholar;
import com.relation.utils.JDBC.TxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by T.Cage on 2017/1/21.
 */
public class ScholarDao{
    private QueryRunner qr = new TxQueryRunner();
    private PageBean<Scholar> findByCriteria(List<sqlExpression>exprList, int pageCount) throws SQLException {
        int pageSize=10;
        StringBuilder whereSql=new StringBuilder(" WHERE 1=1 ");
        List<Object> params=new ArrayList<Object>();
        for(sqlExpression expr:exprList){
            whereSql.append(" and ").append(expr.getName()).append(" ").append(expr.getOperator()).append(" ");
            if(!expr.getOperator().equals("is null")) {
                whereSql.append("?");
                params.add(expr.getValue());
            }
        }

		/*
         * 3. 总记录数
		 */
//        int tr = 0;
//        String sql = "select count(*) from test_dataset4" + whereSql;
//        System.out.println(sql);
//        try {
//            Connection conn = this.getCon();
//            PreparedStatement pst = conn.prepareStatement(sql);
//            for (int i = 0; i < params.size(); i++)
//                pst.setObject(i + 1, params.get(i));
//            ResultSet rs = pst.executeQuery();
//            while(rs.next())
//                tr=rs.getInt(1);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        /*
		 * 3. 总记录数
		 */
        String sql = "select count(*) from test_dataset4" + whereSql;
        Number number = (Number)qr.query(sql, new ScalarHandler(), params.toArray());
        int totalRecord = number.intValue();//得到了总记录数
        System.out.println(totalRecord);
		/*
		 * 4. 得到beanList，即当前页记录
		 */
        sql = "select * from test_dataset4" + whereSql + " order by advisee limit ?,?";
        params.add((pageCount - 1) * pageSize);//当前页首行记录的下标
        params.add(pageSize);//一共查询几行，就是每页记录数

        List<Scholar> beanList = qr.query(sql, new BeanListHandler<Scholar>(Scholar.class),
                params.toArray());
        System.out.println(beanList);

		/*
		 * 5. 创建PageBean，设置参数
		 */
        PageBean<Scholar> pb = new PageBean<Scholar>();
		/*
		 * 其中PageBean没有url，这个任务由Servlet完成
		 */
        pb.setBeanList(beanList);
        pb.setPageCount(pageCount);
        pb.setPageSize(pageSize);
        pb.setTotalRecord(totalRecord);

        return pb;
    }
    /**
     * 按书名模糊查询
     * @param advisee
     * @param pc
     * @return
     * @throws SQLException
     */

    public PageBean<Scholar> findByAdvisee(String advisee, int pc) throws SQLException {
        List<sqlExpression> exprList = new ArrayList<sqlExpression>();
        exprList.add(new sqlExpression("advisee", "like", "%" + advisee + "%"));
        return findByCriteria(exprList, pc);
    }
    public Scholar  getScholarInfoByName(String bid) throws SQLException {
        String sql="SELECT * FROM test_dataset4 WHERE bid=?";
        return qr.query(sql,new BeanHandler<Scholar>(Scholar.class),bid);
    }
    @Test
    public void test() throws SQLException {
        List<sqlExpression>exprList=new ArrayList<sqlExpression>();
        exprList.add(new sqlExpression("advisee","like","%yan%"));
        exprList.add(new sqlExpression("h_index_student","=","1"));
        findByCriteria(exprList,2);
    }
}
