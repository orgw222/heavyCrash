package dao;

import domain.User;

public interface UserDao {
    User findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);

    void save(User user);

    User findByUid(int uid);

    User findByUidAndPassword(int uid, String password);

    void changePassword(int uid, String password);
    void changeName(int uid ,String name);
    void changePhone(int uid, String phone);
    void changeEmail(int uid, String email);
    void changeImage(int uid, String image);
}
