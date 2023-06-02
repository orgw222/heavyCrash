package dao.impl;

import dao.LikeDao;
import domain.Favorite;
import domain.Like;
import domain.Post;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.util.List;

public class LikeDaoimpl implements LikeDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public Like findByPid(int pid) {
        Like like=null;
        try {
            String sql = " select * from favornum where pid = ? ";
            like = template.queryForObject(sql, new
                    BeanPropertyRowMapper<Like>(Like.class), pid);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return like;

    }

    @Override
    public int findCountByPid(int pid) {
        String sql = "SELECT favor FROM favornum WHERE pid = ?";
        return template.queryForObject(sql,Integer.class,pid);
    }

    @Override
    public void add(int pid) {
        String sql="update favornum set favor=favor+1 WHERE pid= ?";
        template.update(sql,pid);
    }

    @Override
    public List<Like> findByPage(int pid, int start, int pageSize, String pname) {

        return null;
    }


}
