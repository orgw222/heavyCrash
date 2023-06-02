package service.impl;

import dao.*;
import dao.impl.*;
import domain.Like;
import domain.PageBean;
import domain.Post;
import domain.User;
import service.PostshowService;

import java.util.List;

public class PostshowServiceimpl implements PostshowService {
    private PostshowDao postshowDao = new PostshowDaoimpl();
    private FloorDao floorDao = new FloorDaoImpl();
    private LikeDao likeDao=new LikeDaoimpl();
    private FavoriteDao favoriteDao=new FavoriteDaoImpl();
    @Override
    public PageBean<Post> pageQuery(int cid, int currentPage, int pageSize,String pname) {
         PageBean pageBean =new PageBean();
         pageBean.setCurrentPage(currentPage);
         pageBean.setPageSize(pageSize);
         int totalCount = postshowDao.findTotal(cid,pname);
         pageBean.setTotalCount(totalCount);
         int start =(currentPage-1)*pageSize;
         List<Post> list = postshowDao.findByPage(cid,start,pageSize,pname);
//         List<Like> like = likeDao.findByPage()
        for (int i = 0; i<list.size(); i++){
            Post p = list.get(i);
            p.setFloorList(floorDao.findByPid(p.getPid()));
//            System.out.println("likenum:"+likeDao.findCountByPid(p.getPid()));

            p.setFavornum(likeDao.findCountByPid(p.getPid()));
            p.setCollectnum(favoriteDao.findCountByPid(p.getPid()));
            list.set(i, p);
        }
        UserDao userDao=new UserDaoImpl();
        for(int i=0;i<list.size();i++){
            int uid=list.get(i).getUid();
            User u=userDao.findByUid(uid);
            list.get(i).setUser(u);
        }
         pageBean.setList(list);
         int totalPage =totalCount%pageSize ==0 ? totalCount/pageSize:totalCount/pageSize + 1;
         pageBean.setTotalPage(totalPage);


        return pageBean;
    }
}
