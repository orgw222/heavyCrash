package service;

import domain.Comment;
import domain.PageBean;

public interface CommentService {
    PageBean<Comment> pageQuery(int fid, int currentPage, int pageSize);

    void createComment(int uid, int fid, String content);
}
