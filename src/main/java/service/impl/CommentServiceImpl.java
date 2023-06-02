package service.impl;

import dao.CommentDao;
import dao.UserDao;
import dao.impl.CommentDaoImpl;
import dao.impl.UserDaoImpl;
import domain.Comment;
import domain.PageBean;
import domain.User;
import service.CommentService;

import java.util.List;

public class CommentServiceImpl implements CommentService {
    private CommentDao commentDao=new CommentDaoImpl();

    @Override
    public PageBean<Comment> pageQuery(int fid, int currentPage, int pageSize) {
        PageBean<Comment> pb=new PageBean<Comment>();
        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);

        int totalCount=commentDao.findTotalCount(fid);
        pb.setTotalCount(totalCount);
        int start=(currentPage-1)*pageSize;
        List<Comment> list=commentDao.findByPage(fid,start,pageSize);
        //初始化Comment的User
        UserDao userDao=new UserDaoImpl();
        for(int i=0;i<list.size();i++){
            int uid=list.get(i).getUid();
            User u=userDao.findByUid(uid);
            list.get(i).setUser(u);
        }

        pb.setList(list);

        int totalPage=totalCount%pageSize==0?totalCount/pageSize:(totalCount/pageSize)+1;
        pb.setTotalPage(totalPage);
        return pb;
    }

    @Override
    public void createComment(int uid, int fid, String content) {
        commentDao.createComment(uid,fid,content);
    }
}
