package dao;



import domain.Favorite;
import domain.Post;

import java.util.List;

public interface FavoriteDao {
    Favorite findByPidAndUid(int pid, int uid);

    int findCountByPid(int pid);

    void add(int rid, int uid);
    int findTotal(int uid, String pname);
    List<Post> findByPage(int uid, int start, int pageSize, String pname);
    void deleteByPidAndUid(int pid, int uid);
}
