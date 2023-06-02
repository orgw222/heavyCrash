package dao.impl;

import dao.UserDao;
import util.JDBCUtils;
import domain.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate jdbcTemplate=new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public User findByUsernameAndPassword(String username, String password) {
        User user=null;
        String sql="select * from user where username = ? and password = ?";
        try {
            user=jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),username,password);
        } catch (DataAccessException e) {
        }
        return user;
    }

    @Override
    public User findByUsername(String username) {
        String sql="select * from user where username = ?";
        User user= null;
        try {
            user = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),username);
        } catch (Exception e) {
        }
        return user;
    }

    @Override
    public void save(User user) {
        String sql = "insert into user(username,password,name,phone,email) " +
                "values(?,?,?,?,?)";
        //2.执行 sql
        jdbcTemplate.update(sql,user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getPhone(),
                user.getEmail());
    }

    @Override
    public User findByUid(int uid) {
        String sql="select * from user where uid = ?";
        User user= null;
        try {
            user = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),uid);
        } catch (Exception e) {
        }
        return user;
    }

    @Override
    public User findByUidAndPassword(int uid, String password) {
        User user=null;
        String sql="select * from user where uid = ? and password = ?";
        try {
            user=jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),uid,password);
        } catch (DataAccessException e) {
        }
        return user;
    }

    @Override
    public void changePassword(int uid, String password) {
        String sql = "update user set password = ? where uid = ?";
        //2.执行 sql
        jdbcTemplate.update(sql,password,uid);
    }

    @Override
    public void changeName(int uid, String name) {
        String sql = "update user set name = ? where uid = ?";
        //2.执行 sql
        jdbcTemplate.update(sql,name,uid);
    }

    @Override
    public void changePhone(int uid, String phone) {
        String sql = "update user set phone = ? where uid = ?";
        //2.执行 sql
        jdbcTemplate.update(sql,phone,uid);
    }

    @Override
    public void changeEmail(int uid, String email) {
        String sql = "update user set email = ? where uid = ?";
        //2.执行 sql
        jdbcTemplate.update(sql,email,uid);
    }

    @Override
    public void changeImage(int uid, String image) {
        String sql = "update user set image = ? where uid = ?";
        //2.执行 sql
        jdbcTemplate.update(sql,image,uid);
    }

}
