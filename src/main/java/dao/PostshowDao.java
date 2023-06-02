package dao;

import domain.Post;

import java.util.List;

public interface PostshowDao {
    int findTotal(int cid,String pname);
    List<Post>findByPage(int cid,int start,int pageSize,String pname);

}
