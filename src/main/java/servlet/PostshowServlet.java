package servlet;

import domain.PageBean;
import service.PostshowService;
import service.impl.PostshowServiceimpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/postshow/*")
public class PostshowServlet extends BaseServlet {
    private PostshowService postshowService= new PostshowServiceimpl();
    public void pageQuery(HttpServletRequest request, HttpServletResponse response){
        String currentPageStr =request.getParameter("currentPage");
        String pageSizeStr =request.getParameter("pageSize");
        String cidStr =request.getParameter("cid");
        String pname=request.getParameter("pname");
        int cid=0;
        if(cidStr!=null&&cidStr.length()>0)
        {
            cid=Integer.parseInt(cidStr);
        }
        int pageSize=0;
        if(pageSizeStr!=null&&pageSizeStr.length()>0)
        {
            pageSize=Integer.parseInt(pageSizeStr);
        }
        int currentPage=0;
        if(currentPageStr!=null&&currentPageStr.length()>0)
        {
            currentPage=Integer.parseInt(currentPageStr);
        }


        PageBean pageBean= postshowService.pageQuery(cid,currentPage,pageSize,pname);
        try {
            writeJsonValue(response,pageBean);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
