package servlet;

import domain.*;
import service.*;
import service.impl.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "PostServlet", value = "/post/*")
public class PostServlet extends BaseServlet {
    private FavoriteService favoriteService = new FavoriteServiceImpl();
    private PostService postService = new PostServiceImpl();
    private FloorService floorService=new FloorServiceImpl();
    private CommentService commentService=new CommentServiceImpl();
    private LikeService likeService=new LikeServiceimpl();
    public void addLike(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        //1. 获取线路 rid
        String pid = request.getParameter("pid");
        //2. 调用 service 添加
        likeService.add(Integer.parseInt(pid));
//        int reslike=likeService.getLikes(Integer.parseInt(pid));
//        writeJsonValue(response,reslike);
    }
    public void pageQueryLikes(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {

        String pidStr = request.getParameter("pid");


        int pid = Integer.parseInt(pidStr);

        int reslike=likeService.getLikes(pid);
        writeJsonValue(response,reslike);
    }
    public void pageQueryFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String pname = request.getParameter("pname");
        User user = (User) request.getSession().getAttribute("user");
        int uid;//用户 id
        if(user == null){
            //用户尚未登录
            uid = 0;
        }else{
            //用户已经登录
            uid = user.getUid();
        }

        int currentPage = 1;
        if (currentPageStr != null && currentPageStr.length() > 0){
            currentPage = Integer.parseInt(currentPageStr);
        }
        int pageSize = 6;
        if (pageSizeStr != null && pageSizeStr.length() > 0){
            pageSize = Integer.parseInt(pageSizeStr);
        }
        PageBean<Post> pageBean = favoriteService.pageQuery(uid, currentPage, pageSize, pname);
        writeJsonValue(response, pageBean);
    }

    public void pageQueryUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String pname = request.getParameter("pname");
        User user = (User) request.getSession().getAttribute("user");
        int uid;//用户 id
        if(user == null){
            //用户尚未登录
            uid = 0;
        }else{
            //用户已经登录
            uid = user.getUid();
        }

        int currentPage = 1;
        if (currentPageStr != null && currentPageStr.length() > 0){
            currentPage = Integer.parseInt(currentPageStr);
        }
        int pageSize = 6;
        if (pageSizeStr != null && pageSizeStr.length() > 0){
            pageSize = Integer.parseInt(pageSizeStr);
        }
        PageBean<Post> pageBean = postService.pageQuery(uid, currentPage, pageSize, pname);
        writeJsonValue(response, pageBean);
    }

    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        //1. 获取线路 id
        String pid = request.getParameter("pid");
        //2. 获取当前登录的用户 user
        User user = (User) request.getSession().getAttribute("user");
        int uid;//用户 id
        if(user == null){
            //用户尚未登录
            //uid = 0;
            writeJsonValue(response, false);

        }else{
            //用户已经登录
            uid = user.getUid();
            //3. 调用 FavoriteService 查询是否收藏
            boolean flag = favoriteService.isFavorite(pid, uid);
            //4. 写回客户端
            writeJsonValue(response, flag);
        }

    }

    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        //1. 获取线路 rid
        String pid = request.getParameter("pid");
        //2. 获取当前登录的用户
        User user = (User) request.getSession().getAttribute("user");
        int uid;//用户 id
        if(user == null){
            //用户尚未登录
            return ;
        }else{
            //用户已经登录
            uid = user.getUid();
        }
        //3. 调用 service 添加
        favoriteService.add(pid,uid);

    }
    public void deleteFavorite(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        //1. 获取线路 rid
        String pidStr = request.getParameter("pid");
        int pid = Integer.parseInt(pidStr);
        //2. 获取当前登录的用户
        User user = (User) request.getSession().getAttribute("user");
        int uid;//用户 id
        if(user == null){
            //用户尚未登录
            return ;
        }else{
            //用户已经登录
            uid = user.getUid();
        }
        //3. 调用 service 删除
        favoriteService.delete(pid,uid);
    }

    public void deleteByPid(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        //1. 获取线路 rid
        String pidStr = request.getParameter("pid");
        int pid = Integer.parseInt(pidStr);
        //2. 调用 service 删除
        postService.delete(pid);
    }

    //分割线 分割线 分割线 分割线 分割线 分割线 分割线 分割线 分割线 分割线 分割线 分割线 分割线 分割线 分割线

    public void createPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        String title=request.getParameter("title");
        int cid=Integer.parseInt(request.getParameter("cid"));
        String base64=request.getParameter("img");
        User user=(User)request.getSession().getAttribute("user");
        int pid=postService.createPost(user,title,cid);
        String realPath = getServletContext().getRealPath("/");
        File file = new File(realPath+"../../src/main/webapp/images/post/"+pid+"/");
        file.mkdirs();
        floorService.createFloor(pid,user,base64,title);
    }

    public void createFloor(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        String intro=request.getParameter("intro");
        String base64=request.getParameter("img");
        User user=(User)request.getSession().getAttribute("user");
        String pidStr=(String)request.getSession().getAttribute("savedPid");
        System.out.println("create:"+pidStr);
        request.getSession().removeAttribute("savedPid");
        int pid=Integer.parseInt(pidStr);
        floorService.createFloor(pid,user,base64,intro);
        writeJsonValue(response,pidStr);
    }

    public void draw(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        String title=request.getParameter("title");
//        int cid=Integer.parseInt(request.getParameter("cid"));
        request.getSession().setAttribute("savedTitle",title);
//        request.getSession().setAttribute("savedCid",String.valueOf(cid));
        request.getSession().setAttribute("savedUrl","createPost.html");
    }

    public void drawImgUpload(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        String pidStr=(String)request.getSession().getAttribute("savedPid");
        System.out.println("upload:"+pidStr);
        request.getSession().removeAttribute("savedPid");
        request.getSession().setAttribute("savedPid",pidStr);
        String base64=request.getParameter("base64");
        request.getSession().setAttribute("savedImgBase64",base64);
        String url=(String) request.getSession().getAttribute("savedUrl");
        request.getSession().removeAttribute("savedUrl");
        writeJsonValue(response,url);
    }

    public void returnToCreate(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        String base64=(String) request.getSession().getAttribute("savedImgBase64");
        request.getSession().removeAttribute("savedImgBase64");
        writeJsonValue(response,base64);
    }

    public void initCreate(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        String title=(String) request.getSession().getAttribute("savedTitle");
//        int cid=Integer.parseInt((String) request.getSession().getAttribute("savedCid"));
//        System.out.println("cid"+cid);
//        System.out.println("title"+title);
        request.getSession().removeAttribute("savedTitle");
//        request.getSession().removeAttribute("savedCid");
        Post post=new Post();
//        post.setCid(cid);
        post.setTitle(title);
        writeJsonValue(response,post);
    }

    public void myReCreation(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        String pid=request.getParameter("pid");
        String path=request.getParameter("path");
        request.getSession().setAttribute("savedPid",pid);
        request.getSession().setAttribute("savedPath",path);
        request.getSession().setAttribute("savedUrl","reCreatePost.html");
    }

    public void initDraw(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        String path=(String)request.getSession().getAttribute("savedPath");
        if(path == null){
            path="";
        }
        else{
            path="images/"+path;
        }
        request.getSession().removeAttribute("savedPath");
        writeJsonValue(response,path);
    }

    public void pageQueryPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        String currentPageStr=request.getParameter("currentPage");
        String pidStr = request.getParameter("pid");

        int pid=0;
        if(pidStr != null && pidStr.length() > 0){
            pid = Integer.parseInt(pidStr);
        }
        int currentPage = 0;//当前页码，如果不传递，则默认为第一页
        if(currentPageStr != null && currentPageStr.length() > 0){
            currentPage = Integer.parseInt(currentPageStr);
        }else{
            currentPage = 1;
        }
        int pageSize=5;
        PageBean<Floor> pb=floorService.pageQuery(pid,currentPage,pageSize);
        writeJsonValue(response,pb);
    }

    public void pageQueryFloor(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        String currentPageStr=request.getParameter("currentPage");
        String fidStr = request.getParameter("fid");

        int fid=0;
        if(fidStr != null && fidStr.length() > 0){
            fid = Integer.parseInt(fidStr);
        }
        int currentPage = 0;//当前页码，如果不传递，则默认为第一页
        if(currentPageStr != null && currentPageStr.length() > 0){
            currentPage = Integer.parseInt(currentPageStr);
        }else{
            currentPage = 1;
        }
        int pageSize=3;
        PageBean<Comment> pb= commentService.pageQuery(fid,currentPage,pageSize);
        writeJsonValue(response,pb);
    }

    public void createComment(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        String content=request.getParameter("content");
        int fid=Integer.parseInt(request.getParameter("fid"));
        int uid=((User)request.getSession().getAttribute("user")).getUid();
        commentService.createComment(uid,fid,content);
    }

}
