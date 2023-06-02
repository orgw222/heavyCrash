package service;

import domain.PageBean;
import domain.Post;

public interface LikeService {
    void add(int pid);
    int getLikes(int pid);
}
