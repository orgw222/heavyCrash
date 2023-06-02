package dao;

import domain.Floor;
import domain.User;

import java.util.List;

public interface FloorDao {
    List<Floor> findByPid(int pid);

    int findTotalCount(int pid);

    List<Floor> findByPage(int pid, int start, int pageSize);

    int createFloor(int pid, User user, String path, String title);
}
