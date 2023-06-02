package dao;

import domain.Comment;

import java.util.List;

public interface CommentDao {
    int findTotalCount(int fid);

    List<Comment> findByPage(int fid, int start, int pageSize);

    void createComment(int uid, int fid, String content);
}
