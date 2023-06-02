package service;

import domain.User;

public interface UserService {
    User login(User user);

    boolean regist(User user);
    User change(User oldUser, String oldPassword, String password, String name, String phone, String email, String image);

}
