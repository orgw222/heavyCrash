package service.impl;

import dao.CommentDao;
import dao.FloorDao;
import dao.UserDao;
import dao.impl.CommentDaoImpl;
import dao.impl.FloorDaoImpl;
import dao.impl.UserDaoImpl;
import domain.Comment;
import domain.Floor;
import domain.PageBean;
import domain.User;
import service.FloorService;
import util.Base64Utils;

import java.util.List;

public class FloorServiceImpl implements FloorService {
    private FloorDao floorDao=new FloorDaoImpl();

    @Override
    public PageBean<Floor> pageQuery(int pid, int currentPage, int pageSize) {
        PageBean<Floor> pb=new PageBean<Floor>();
        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);

        int totalCount=floorDao.findTotalCount(pid);
        pb.setTotalCount(totalCount);
        int start=(currentPage-1)*pageSize;
        List<Floor> list=floorDao.findByPage(pid,start,pageSize);
        //初始化floor的User
        UserDao userDao=new UserDaoImpl();
        for(int i=0;i<list.size();i++){
            int uid=list.get(i).getUid();
            User u=userDao.findByUid(uid);
            list.get(i).setUser(u);
        }
//        //初始化floor的Commmet
//        CommentDao commentDao=new CommentDaoImpl();
//        for(int i=0;i<list.size();i++){
//            List<Comment> commentList=commentDao.findByFid(fid);
//            list.get(i).setCommentList(commentList);
//        }

        pb.setList(list);

        int totalPage=totalCount%pageSize==0?totalCount/pageSize:(totalCount/pageSize)+1;
        pb.setTotalPage(totalPage);
        return pb;
    }

    @Override
    public void createFloor(int pid, User user, String base64, String title) {

        String path="post/"+pid+"/";
        int order=floorDao.createFloor(pid,user,path,title);
        Base64Utils.saveBase64DataToPng(base64, String.valueOf(order),path);
    }
}
