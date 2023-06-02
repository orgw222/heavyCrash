package service;

import domain.PageBean;
import domain.Post;
import domain.User;

public interface PostService {
     PageBean<Post> pageQuery(int uid, int currentPage, int pageSize, String pname);
     void delete(int pid);

    int createPost(User user, String title, int cid);
}
