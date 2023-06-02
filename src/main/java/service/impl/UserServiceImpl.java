package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import domain.User;
import service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao=new UserDaoImpl();

    @Override
    public User login(User user) {
        return userDao.findByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean regist(User user) {
        User byUsername=userDao.findByUsername(user.getUsername());
        if(byUsername!=null){
            return false;
        }
        userDao.save(user);
        return true;
    }

    @Override
    public User change(User oldUser, String oldPassword, String password, String name, String phone, String email, String image) {
        User newUser = userDao.findByUidAndPassword(oldUser.getUid(), oldPassword);
        if (newUser!=null){
            if (password != null && password.length() > 0){
                userDao.changePassword(oldUser.getUid(), password);
                newUser.setPassword(password);
            }
            if (name != null && name.length() > 0){
                userDao.changeName(oldUser.getUid(), name);
                newUser.setName(name);
            }
            if (phone != null && phone.length() > 0){
                userDao.changePhone(oldUser.getUid(), phone);
                newUser.setPhone(phone);
            }
            if (email != null && email.length() > 0){
                userDao.changeEmail(oldUser.getUid(), email);
                newUser.setEmail(email);
            }
            if (image != null && image.length() > 0){
                userDao.changeImage(oldUser.getUid(), image);
                newUser.setImage(image);
            }
            return newUser;
        }else {
            return null;
        }
    }
}
