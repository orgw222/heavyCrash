package dao.impl;

import dao.PostshowDao;
import domain.Post;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.util.List;

public class PostshowDaoimpl implements PostshowDao {
    private JdbcTemplate jdbcTemplate=new JdbcTemplate(JDBCUtils.getDataSource());


    @Override
    public int findTotal(int cid,String pname) {
        if (pname == "") {
            if (cid == 0) {
                String sql = "SELECT count(*) FROM draw.post;";
                return jdbcTemplate.queryForObject(sql, Integer.class);
            } else {
                String sql = "SELECT count(*) FROM draw.post where cid = ?;";
                return jdbcTemplate.queryForObject(sql, Integer.class, cid);
            }
        } else {
            pname="%" + pname + "%";
            if (cid == 0) {
                String sql = "SELECT count(*) FROM draw.post where title like ?;";
                return jdbcTemplate.queryForObject(sql, Integer.class, pname);
            } else {
                String sql = "SELECT count(*) FROM draw.post where cid = 1 and title like ?;";
                return jdbcTemplate.queryForObject(sql, Integer.class, cid, pname);
            }

        }
    }

    @Override
    public List<Post> findByPage(int cid, int start, int pageSize,String pname) {
        if(pname=="")
        {
            if(cid==0)
            {
                String sql="SELECT * FROM draw.post limit ?,?;";
                return  jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Post.class),start,pageSize);
            }
            else {
                String sql = "SELECT * FROM draw.post where cid = ? limit ?,?;";
                return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Post.class), cid, start, pageSize);
            }
        }
        else {
            if(cid==0)
            { pname="%" + pname + "%";
                String sql="SELECT * FROM draw.post where title like ? limit ?,?;";
                return  jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Post.class),pname,start,pageSize);
            }
            else {
                String sql = "SELECT * FROM draw.post where cid = ?  and title like ? limit ?,?;";
                return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Post.class), cid,pname, start, pageSize);
            }
        }

    }
}
