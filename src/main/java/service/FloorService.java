package service;

import domain.Floor;
import domain.PageBean;
import domain.User;

public interface FloorService {
    PageBean<Floor> pageQuery(int pid, int currentPage, int pageSize);
    void createFloor(int pid, User user, String base64, String title);
}
