package cn.zhu4wp.seckill.mybatis.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class DateTypeHandler extends BaseTypeHandler<Date> {
    /**
     * 将 Date 类型转为 Long 传入数据库
     * @param ps
     * @param i
     * @param parameter
     * @param jdbcType
     * @throws SQLException
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Date parameter, JdbcType jdbcType) throws SQLException {
        ps.setLong(i,parameter.getTime());
    }

    /**
     * 将数据库中的 long 转为 Date 给客户端
     * @param rs
     * @param columnName
     * @return
     * @throws SQLException
     */
    @Override
    public Date getNullableResult(ResultSet rs, String columnName) throws SQLException {
        long dateDB = rs.getLong(columnName);
        if(dateDB != 0){
            return new Date(dateDB);
        }
        return null;
    }

    /**
     * 将数据库中的 long 转为 Date 给客户端
     * @param rs
     * @param columnIndex
     * @return
     * @throws SQLException
     */
    @Override
    public Date getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        long dateDB = rs.getLong(columnIndex);
        if(dateDB != 0){
            return new Date(dateDB);
        }
        return null;
    }

    /**
     * 将数据库中的 long 转为 Date 给客户端
     * @param cs
     * @param columnIndex
     * @return
     * @throws SQLException
     */
    @Override
    public Date getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        long dateDB = cs.getLong(columnIndex);
        if(dateDB != 0){
            return new Date(dateDB);
        }
        return null;
    }
}
