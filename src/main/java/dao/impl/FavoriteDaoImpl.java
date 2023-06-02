package dao.impl;

import dao.FavoriteDao;
import domain.Favorite;
import domain.Post;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.util.ArrayList;
import java.util.List;

public class FavoriteDaoImpl implements FavoriteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public Favorite findByPidAndUid(int pid, int uid) {
        Favorite favorite = null;
        try {
            String sql = " select * from favorite where pid = ? and uid = ?";
            favorite = template.queryForObject(sql, new
                    BeanPropertyRowMapper<Favorite>(Favorite.class), pid, uid);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return favorite;
    }

    @Override
    public int findCountByPid(int pid) {
        String sql = "SELECT COUNT(*) FROM favorite WHERE pid = ?";
        return template.queryForObject(sql,Integer.class,pid);
    }

    @Override
    public void add(int pid, int uid) {
        String sql = "insert into favorite values(?,?)";
        template.update(sql,uid,pid);
        int f_data=findCountByPid(pid)+1;
        String sql1= "update favornum set collect=? WHERE pid= ?";
        template.update(sql1,f_data,pid);
    }

    @Override
    public int findTotal(int uid, String pname) {
        String sql = "select count(*) from post where pid in (select pid from favorite where uid = ?) and title like ?";
        return template.queryForObject(sql, Integer.class, uid, "%"+pname+"%");
    }

    @Override
    public List<Post> findByPage(int uid, int start, int pageSize, String pname) {
        //String sql = "SELECT * FROM draw.floor where floor.order=1 and pid in (select pid from favorite where uid = ?)";
        String sql = "select * from post where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);
        List params = new ArrayList();
        if (uid != 0){
            sb.append(" and pid in (select pid from favorite where uid = ?) ");
            params.add(uid);
        }
        if (pname != null && pname.length()>0){
            sb.append(" and title like ? ");
            params.add("%"+pname+"%");
        }
        sb.append(" limit ?, ? ");

        sql = sb.toString();

        params.add(start);
        params.add(pageSize);
        List<Post> list = template.query(sql,new BeanPropertyRowMapper<>(Post.class), params.toArray());

        return list;
    }

    @Override
    public void deleteByPidAndUid(int pid, int uid) {
        String sql = "delete from favorite where pid = ? and uid = ?";
        template.update(sql, pid, uid);
    }
}
