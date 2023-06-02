package dao;

import domain.Favorite;
import domain.Post;
import domain.User;

import java.util.List;

public interface PostDao {

    int findTotal(int uid, String pname);
    List<Post> findByPage(int uid, int start, int pageSize, String pname);
    void deleteByPid(int pid);

    int createPost(User user, String title, int cid);
}
