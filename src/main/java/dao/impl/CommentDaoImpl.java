package dao.impl;

import dao.CommentDao;
import domain.Comment;
import domain.Floor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.util.List;

public class CommentDaoImpl implements CommentDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());


    @Override
    public int findTotalCount(int fid) {
        String sql="select count(*) from comment where fid=?";
        return template.queryForObject(sql,Integer.class,fid);
    }

    @Override
    public List<Comment> findByPage(int fid, int start, int pageSize) {
        String sql = "select * from comment where fid = ? order by cid asc limit ? , ?";
        return template.query(sql,new BeanPropertyRowMapper<Comment>(Comment.class),fid,start,pageSize);
    }

    @Override
    public void createComment(int uid, int fid, String content) {
        String sql="insert into comment(uid,fid,content) values(?,?,?)";
        template.update(sql,uid,fid,content);
    }
}
