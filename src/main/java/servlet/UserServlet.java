package servlet;

import domain.PageBean;
import domain.ResultInfo;
import domain.User;
import org.apache.commons.beanutils.BeanUtils;
import service.UserService;
import service.impl.UserServiceImpl;
import util.Base64Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private UserService userService=new UserServiceImpl();

    public void login(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
        Map<String,String[]> parameterMap=request.getParameterMap();
        User user=new User();
        try {
            BeanUtils.populate(user,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        User u=userService.login(user);
        ResultInfo info=new ResultInfo();
        if(u==null){
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误！");
        }
        else {
            info.setFlag(true);
            request.getSession().setAttribute("user",u);
        }
        writeJsonValue(response,info);
    }

    public void getInformation(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
        User user = (User) request.getSession().getAttribute("user");
        User u=userService.login(user);
        writeJsonValue(response,user);
    }

    public void setInformation(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
        User oldUser = (User) request.getSession().getAttribute("user");
        String oldPassword = request.getParameter("oldPassword");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String data = request.getParameter("data");
        String image = "user/"+ oldUser.getUsername() + ".png";
        User newUser = userService.change(oldUser,oldPassword,password,name,phone,email,image);
        ResultInfo info=new ResultInfo();
        if(newUser==null){
            info.setFlag(false);
            info.setErrorMsg("密码错误！");
        }
        else {
            info.setFlag(true);
            if (data!=null&&data.length()>0){
                Base64Utils.saveBase64DataToPng(data,oldUser.getUsername(),"user/");
            }
            request.getSession().setAttribute("user",newUser);
        }
        writeJsonValue(response,info);
    }

    public void regist(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
        Map<String,String[]> parameterMap=request.getParameterMap();
        User user=new User();
        try {
            BeanUtils.populate(user,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        boolean flag=userService.regist(user);
        ResultInfo info=new ResultInfo();
        info.setFlag(flag);
        if(!flag){
            info.setErrorMsg("注册失败！");
        }
        else{
            request.getSession().setAttribute("user",user);
        }
        writeJsonValue(response,info);
    }

    public void find(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
        Object user=request.getSession().getAttribute("user");
        writeJsonValue(response,user);
    }

    public void exit(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath()+"/login.html");
    }
}
