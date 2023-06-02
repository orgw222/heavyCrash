package service;

import domain.PageBean;
import domain.Post;

public interface PostshowService {
    PageBean<Post> pageQuery(int cid,int currentPage,int pageSize,String pname);

}
