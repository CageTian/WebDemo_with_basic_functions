package com.relation.scholar.web.servlet;

import com.relation.pager.PageBean;
import com.relation.scholar.domain.Scholar;
import com.relation.scholar.service.ScholarService;
import com.relation.utils.BaseServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by T.Cage on 2017/1/21.
 */
@WebServlet(name = "ScholarServlet",urlPatterns = "/ScholarServlet")
public class ScholarServlet extends BaseServlet {
    private ScholarService scholarService=new ScholarService();
    private int getPageCount(HttpServletRequest request){
        int pc=1;
        String param=request.getParameter("pc");
        if(param!=null&&!param.trim().isEmpty()){
            try{
                pc=Integer.parseInt(param);
            }catch (RuntimeException e){}
        }
        return pc;
    }
    private String getUrl(HttpServletRequest req) {
        String url = req.getRequestURI() + "?" + req.getQueryString();
		/*
		 * 如果url中存在pc参数，截取掉，如果不存在那就不用截取。
		 */
        int index = url.lastIndexOf("&pc=");
        if(index != -1) {
            url = url.substring(0, index);
        }
        return url;
    }
    public void ScholarInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bid=request.getParameter("bid");
        Scholar scholar=scholarService.getScholarInfo(bid);
        request.setAttribute("scholar", scholar);
        RequestDispatcher requestDispatcher=request.getRequestDispatcher("/jsps/scholarInfo/info.jsp");
        requestDispatcher.forward(request,response);
    }
    public String findByAdvisee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
		 * 1. 得到pc：如果页面传递，使用页面的，如果没传，pc=1
		 */
        int pc = getPageCount(request);
		/*
		 * 2. 得到url：...
		 */
        String url = getUrl(request);
		/*
		 * 3. 获取查询条件，本方法就是cid，即分类的id
		 */
        //Scholar criteria = CommonUtils.toBean(request.getParameterMap(), Scholar.class);
		/*
		 * 4. 使用pc和cid调用service#findByCategory得到PageBean
		 */
        PageBean<Scholar> pb = scholarService.findByAdvisee(request.getParameter("advisee"),pc);
		/*
		 * 5. 给PageBean设置url，保存PageBean，转发到/jsps/book/list.jsp
		 */
        pb.setUrl(url);
        //System.out.println(url);
        request.setAttribute("pb", pb);
        RequestDispatcher requestDispatcher=request.getRequestDispatcher("/jsps/result.jsp");
        requestDispatcher.forward(request,response);
        return "f:/jsps/book/list.jsp";
    }
}

