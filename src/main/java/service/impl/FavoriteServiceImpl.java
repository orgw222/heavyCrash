package service.impl;

import dao.FavoriteDao;
import dao.FloorDao;
import dao.LikeDao;
import dao.impl.FavoriteDaoImpl;
import dao.impl.FloorDaoImpl;
import dao.impl.LikeDaoimpl;
import domain.Favorite;
import domain.PageBean;
import domain.Post;
import service.FavoriteService;

import java.util.List;

public class FavoriteServiceImpl implements FavoriteService {
    private FavoriteDao favoriteDao = new FavoriteDaoImpl();
    private FloorDao floorDao = new FloorDaoImpl();

    @Override
    public boolean isFavorite(String pid, int uid) {
        Favorite favorite = favoriteDao.findByPidAndUid(Integer.parseInt(pid), uid);
        return favorite != null;//如果对象有值，则为 true，反之，则为 false
    }

    @Override
    public void add(String pid, int uid) {
        favoriteDao.add(Integer.parseInt(pid),uid);
    }

    @Override
    public PageBean<Post> pageQuery(int uid, int currentPage, int pageSize, String pname) {
        PageBean<Post> pageBean = new PageBean<>();
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        int totalCount = favoriteDao.findTotal(uid, pname);
        pageBean.setTotalCount(totalCount);

        int start = (currentPage-1) * pageSize;
        List<Post> list = favoriteDao.findByPage(uid, start, pageSize, pname);
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
    public void delete(int pid, int uid) {
        favoriteDao.deleteByPidAndUid(pid, uid);
    }

}
