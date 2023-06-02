package dao.impl;

import dao.FavoriteDao;
import dao.FloorDao;
import domain.Floor;
import domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.util.List;

public class FloorDaoImpl implements FloorDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<Floor> findByPid(int pid) {
        String sql = "select * from floor where pid = ? order by floor.num asc";
        return template.query(sql,new BeanPropertyRowMapper<Floor>(Floor.class), pid);
    }

    @Override
    public int findTotalCount(int pid) {
        String sql="select count(*) from floor where pid=?";
        return template.queryForObject(sql,Integer.class,pid);
    }

    @Override
    public List<Floor> findByPage(int pid, int start, int pageSize) {
        String sql = "select * from floor where pid = ? order by num asc limit ? , ?";
        return template.query(sql,new BeanPropertyRowMapper<Floor>(Floor.class),pid,start,pageSize);
    }

    @Override
    public int createFloor(int pid, User user, String path, String title) {
        String orderSql="select max(num) from floor where pid =?";
        int order = 1;
        try{
             order=template.queryForObject(orderSql,Integer.class,pid)+1;
        }catch (Exception e){};
        String sql="insert into floor(pid,uid,image,content,num) values(?,?,?,?,?)";
        template.update(sql,pid,user.getUid(),path+order+".png",title,order);
        return order;
    }
}
