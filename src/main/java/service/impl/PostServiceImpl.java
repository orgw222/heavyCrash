package service.impl;

import dao.FloorDao;
import dao.PostDao;
import dao.impl.FloorDaoImpl;
import dao.impl.PostDaoImpl;
import domain.PageBean;
import domain.Post;
import domain.User;
import service.PostService;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class PostServiceImpl implements PostService {
    private FloorDao floorDao = new FloorDaoImpl();
    private PostDao postDao = new PostDaoImpl();
    @Override
    public PageBean<Post> pageQuery(int uid, int currentPage, int pageSize, String pname) {
        PageBean<Post> pageBean = new PageBean<>();
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        int totalCount = postDao.findTotal(uid, pname);
        pageBean.setTotalCount(totalCount);

        int start = (currentPage-1) * pageSize;
        List<Post> list = postDao.findByPage(uid, start, pageSize, pname);
        for (int i = 0; i<list.size(); i++){
            Post p = list.get(i);
            p.setFloorList(floorDao.findByPid(p.getPid()));
            list.set(i, p);
        }
        pageBean.setList(list);
        int totalPage = totalCount % pageSize == 0 ? totalCount/pageSize: totalCount/pageSize+1;
        pageBean.setTotalPage(totalPage);

        return pageBean;
    }

    @Override
    public void delete(int pid) {
        postDao.deleteByPid(pid);
    }

    @Override
    public int createPost(User user, String title, int cid) {
        int pid= postDao.createPost(user,title,cid);
        return pid;
    }
}
