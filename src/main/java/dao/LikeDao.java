package dao;



import domain.Favorite;
import domain.Like;
import domain.Post;

import java.util.List;

public interface LikeDao {
    Like findByPid(int pid);
    int findCountByPid(int pid);
    void add(int pid);
    List<Like> findByPage(int pid, int start, int pageSize, String pname);

}
