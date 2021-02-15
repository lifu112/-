package com.lifu.web;

import com.google.gson.Gson;
import com.lifu.bean.User;
import com.lifu.service.UserService;
import com.lifu.service.impl.UserServiceImpl;
import com.lifu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @Description
 * @Author lifu
 * @Date 2021/1/29 10:32
 */
public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();

    /**
     * 处理登录业务
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //处理登录业务
        User user = userService.login(new User(null, username, password, null));
        if(user == null){
            System.out.println("登录失败");
            //把错误信息和需要回显表单信息返回到request域
            req.setAttribute("msg","用户或密码错误");
            req.setAttribute("username",username);
            //跳回登录页面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }else {
            System.out.println("登录成功");
            //保存用户信息到session域
            req.getSession().setAttribute("user",user);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }
    }

    /**
     * 处理注销的业务
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //销毁session中的用户信息（或者销毁session)
        req.getSession().invalidate();
        //回到首页,请求重定向
        resp.sendRedirect(req.getContextPath());
    }

    /**
     * 处理注册业务
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
        //获取session中的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //删除session中的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        User user = WebUtils.copyParamToBean(req.getParameterMap(),new User());
        //检查验证码，整个固定的...
        if(token != null && token.equalsIgnoreCase(code)){
            //检查用户名
            if(userService.existsUsername(username)){
                System.out.println("用户名已存在");
                //把错误信息和需要回显表单信息返回到request域
                req.setAttribute("msg","用户名已存在");
                req.setAttribute("username",username);
                req.setAttribute("email",email);
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            }else {
                userService.register(user);
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
            }
        }else {
            //跳回注册页面
            req.setAttribute("msg","验证码不正确");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }
    }

    protected void ajaxUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数
        String username = req.getParameter("username");
        Boolean existUsername = userService.existsUsername(username);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("existUsername",existUsername);
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        resp.getWriter().write(json);
    }
}
