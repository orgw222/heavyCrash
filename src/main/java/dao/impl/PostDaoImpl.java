package dao.impl;

import dao.PostDao;
import domain.Post;
import domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.util.ArrayList;
import java.util.List;

public class PostDaoImpl implements PostDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public int findTotal(int uid, String pname) {
        String sql = "select count(*) from post where uid = ? and title like ?";
        return template.queryForObject(sql, Integer.class, uid, "%"+pname+"%");
    }

    @Override
    public List<Post> findByPage(int uid, int start, int pageSize, String pname) {
        String sql = "select * from post where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);
        List params = new ArrayList();
        if (uid != 0){
            sb.append(" and uid = ? ");
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
    public void deleteByPid(int pid) {
        String sql = "delete from post where pid = ?";
        template.update(sql, pid);
        sql="delete from favornum where pid = ?";//添加帖子收藏情况的删除
        template.update(sql, pid);
    }

    @Override
    public int createPost(User user, String title, int cid) {
        String sql= "insert into post(uid,title,cid) values(?,?,?)";
        template.update(sql,user.getUid(),title,cid);

        sql="select pid from post where uid= ? and title = ? and cid = ?";
        int i=template.queryForObject(sql,Integer.class,user.getUid(),title,cid);
        String sql2= "insert into favornum(pid,favor,collect) values(?,?,?)";
        template.update(sql2,i,0,0);
        return template.queryForObject(sql,Integer.class,user.getUid(),title,cid);
    }
}
