package service.impl;

import dao.LikeDao;
import dao.impl.LikeDaoimpl;
import service.LikeService;

public class LikeServiceimpl implements LikeService {
    private LikeDao likeDao=new LikeDaoimpl();
    @Override
    public void add(int pid) {
        likeDao.add(pid);
    }

    @Override
    public int getLikes(int pid) {
        return likeDao.findCountByPid(pid);
    }
}
