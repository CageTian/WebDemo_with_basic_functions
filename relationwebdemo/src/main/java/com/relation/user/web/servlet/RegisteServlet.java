package com.relation.user.web.servlet;

import com.relation.user.domain.User;
import com.relation.user.service.UserException;
import com.relation.user.service.UserService;
import com.relation.utils.BaseServlet;
import com.relation.utils.Common.CommonUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by T.Cage on 2016/12/22.
 */
@WebServlet(name = "RegisteServlet",value = "/RegisteServlet")
public class RegisteServlet extends BaseServlet {
    private UserService userService=new UserService();
    public String ajaxVerifyName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        boolean b=userService.ajaxVerifyUsername(username);
        response.getWriter().print(b);
        return null;
    }
    public String ajaxVerifyEmail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email=request.getParameter("email");
        boolean b=userService.ajaxVerifyEmail(email);
        response.getWriter().print(b);
        return null;
    }
    public String ajaxVerifyCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String verifyCode=request.getParameter("verifyCode");
        String vcode= (String) request.getSession().getAttribute("verCode");
        boolean b=verifyCode.toLowerCase().equals(vcode);
        response.getWriter().print(b);
        //System.out.println(vcode + " "+ b);
        return null;
    }
    public String regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User formUser= CommonUtils.toBean(request.getParameterMap(),User.class);

            System.out.println(formUser.getVerifyCode() );
            System.out.println(formUser.getUsername() );
            System.out.println(formUser.getPassword() );
            System.out.println(formUser.getEmail() );

        Map<String,String> errors = validateRegist(formUser, request.getSession());
        if(errors.size() > 0) {
            request.setAttribute("form", formUser);
            request.setAttribute("errors", errors);
            System.out.println("erro...");
            RequestDispatcher requestDispatcher=request.getRequestDispatcher("/jsps/user/regist.jsp");
            requestDispatcher.forward(request,response);
            return null;
        }

        userService.regist(formUser);
        //System.out.println("regist11()...");

        request.setAttribute("code","success");
        request.setAttribute("msg","注册成功！请到邮箱进行激活");

        RequestDispatcher requestDispatcher=request.getRequestDispatcher("/jsps/msg.jsp");
        requestDispatcher.forward(request,response);
        return null;
    }
    public void activation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code=request.getParameter("activation");
        String username=request.getParameter("username");
        try {
            userService.activation(username,code);
        } catch (UserException e) {
            request.setAttribute("code", "error");//通知msg.jsp显示对号
            request.setAttribute("msg", e.getMessage());
            RequestDispatcher requestDispatcher=request.getRequestDispatcher("/jsps/msg.jsp");
            requestDispatcher.forward(request,response);
        }
        request.setAttribute("code", "success");//通知msg.jsp显示对号
        request.setAttribute("msg", "恭喜，激活成功，请马上登录！");
        RequestDispatcher requestDispatcher=request.getRequestDispatcher("/jsps/msg.jsp");
        requestDispatcher.forward(request,response);
//        return null;
    }/*
	 * 注册校验
	 * 对表单的字段进行逐个校验，如果有错误，使用当前字段名称为key，错误信息为value，保存到map中
	 * 返回map
	 */
    private Map<String,String> validateRegist(User formUser, HttpSession session) {
        Map<String,String> errors = new HashMap<String,String>();
		/*
		 * 1. 校验登录名
		 */
        String username = formUser.getUsername();
        if(username == null || username.trim().isEmpty()) {
            errors.put("username", "用户名不能为空！");
        } else if(username.length() < 3 || username.length() > 20) {
            errors.put("username", "用户名长度必须在3~20之间！");
        } else if(!userService.ajaxVerifyUsername(username)) {
            errors.put("username", "用户名已被注册！");
        }

		/*
		 * 2. 校验登录密码
		 */
        String password = formUser.getPassword();
        if(password == null || password.trim().isEmpty()) {
            errors.put("password", "密码不能为空！");
        } else if(password.length() < 3 || password.length() > 20) {
            errors.put("password", "密码长度必须在3~20之间！");
        }

		/*
		 * 3. 确认密码校验
		 */
        String repassword = formUser.getRepassword();
        if(repassword == null || repassword.trim().isEmpty()) {
            errors.put("repassword", "确认密码不能为空！");
        } else if(!repassword.equals(password)) {
            errors.put("repassword", "两次输入不一致！");
        }

		/*
		 * 4. 校验email
		 */
        String email = formUser.getEmail();
        if(email == null || email.trim().isEmpty()) {
            errors.put("email", "Email不能为空！");
        } else if(!email.matches("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})$")) {
            errors.put("email", "Email格式错误！");
        } else if(!userService.ajaxVerifyEmail(email)) {
            errors.put("email", "Email已被注册！");
        }

		/*
		 * 5. 验证码校验
		 */
        String verifyCode = formUser.getVerifyCode();
        String vcode = (String) session.getAttribute("verCode");
        if(verifyCode == null || verifyCode.trim().isEmpty()) {
            errors.put("verifyCode", "验证码不能为空！");
        } else if(!verifyCode.equalsIgnoreCase(vcode)) {
            errors.put("verifyCode", "验证码错误！");
        }

        return errors;
    }

}
