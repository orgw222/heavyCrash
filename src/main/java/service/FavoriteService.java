package service;

import domain.Favorite;
import domain.Floor;
import domain.PageBean;
import domain.Post;

public interface FavoriteService {
    boolean isFavorite(String pid, int uid);
    void add(String pid, int uid);
    PageBean<Post> pageQuery(int uid, int currentPage, int pageSize, String pname);
    void delete(int pid, int uid);
}
