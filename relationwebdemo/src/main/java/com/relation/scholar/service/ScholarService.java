package com.relation.scholar.service;

import com.relation.pager.PageBean;
import com.relation.scholar.dao.ScholarDao;
import com.relation.scholar.domain.Scholar;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

/**
 * Created by T.Cage on 2017/1/21.
 */
public class ScholarService {
    private ScholarDao scholarDao=new ScholarDao();
    public PageBean<Scholar>findByAdvisee(String advisee, int pc){
        try {
            return scholarDao.findByAdvisee(advisee,pc);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
    public Scholar getScholarInfo(String bid){
        try {
            return scholarDao.getScholarInfoByName(bid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
