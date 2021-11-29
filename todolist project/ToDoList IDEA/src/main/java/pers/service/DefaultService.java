package pers.service;

import org.springframework.stereotype.Service;
import pers.dao.DefaultDao;
import pers.utils.ObjectUtils;
import pers.utils.CommonUtils;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class DefaultService<T> {

    private String tableName;
    private String idName;
    // 除id以外的字段名
    private String[] fields;
    private int len = 0;
    private Class<T> tClass;

    @Resource
    private DefaultDao defaultDao;

    public DefaultService() {}

    public DefaultService(String tableName, String idName, Class<T> c) {
        this.tableName = tableName;
        this.idName = idName;
        List<String> nameList = new LinkedList<>();
        for (String name : ObjectUtils.getAttrNames(c)) {
            if (!name.equals(idName)) {
                nameList.add(name);
            }
        }
        fields = nameList.toArray(new String[0]);
        len = fields.length;
        tClass = c;
    }

    /**
     * 查询全部数据
     */
    public List<Map<String, Object>> selectAll() {
        return defaultDao.select("SELECT * FROM `" + tableName + "`");
    }

    /**
     * 查询开始数目到记录数目个数的数据
     */
    public List<Map<String, Object>> selectLimit(int start, int num) {
        return defaultDao.select("SELECT * FROM `" + tableName + "` LIMIT " + start + ", " + num);
    }

    /**
     * 查询记录数目的数据
     */
    public List<Map<String, Object>> selectLimitRecord(int num) {
        return defaultDao.select("SELECT * FROM `" + tableName + "` LIMIT " + num);
    }

    /**
     * 查询数据数量
     */
    public int selectLength() {
        return defaultDao.selectCount("SELECT COUNT(*) FROM `" + tableName + "`");
    }

    /**
     * 查询指定id的数据
     */
    public Map<String, Object> selectById(int id) {
        return defaultDao.selectOne("SELECT * FROM `" + tableName + "` WHERE `" + idName + "` = " + id);
    }

    /**
     * 查询指定列名的指定数据
     */
    public List<Map<String, Object>> selectByCol(String key, String value) {
        return defaultDao.select("SELECT * FROM `" + tableName + "` WHERE " + key + " = " + value);
    }

    /**
     * 查询指定列名指定位置的指定数据
     */
    public List<Map<String, Object>> selectByColLimit(String key,
                                                      String value,
                                                      int start,
                                                      int end) {
        if (!CommonUtils.isNumber(value)) {
            value = CommonUtils.str(value);
        }
        return defaultDao.select("SELECT * FROM `" + tableName + "` WHERE " + key + " = " + value + " LIMIT " + start + ", " + end);
    }

    /**
     * 删除指定id的数据
     */
    public void deleteById(int id) {
        defaultDao.delete("DELETE FROM `" + tableName + "` WHERE `" + idName + "` = " + id);
    }

    /**
     * 添加新的信息
     */
    public void insert(T entity) {
        insert(ObjectUtils.getAttrsMap(entity));
    }

    /**
     * 插入新的信息
     */
    public void insert(Map<String, Object> map) {
        StringBuilder sql = new StringBuilder("INSERT INTO `" + tableName + "` (");

        int mapSize = map.size();

        int count = 0;
        for (String key : map.keySet()) {
            sql.append(CommonUtils.anti(key));
            if (count < mapSize - 1) {
                sql.append(", ");
            }
            count++;
        }

        sql.append(") VALUES (");

        count = 0;
        for (String key : map.keySet()) {
            Object value = map.get(key);
            if (value instanceof String) {
                value = CommonUtils.str(value.toString());
            }
            sql.append(value);
            if (count < mapSize - 1) {
                sql.append(", ");
            }
            count++;
        }

        sql.append(")");
        System.out.println(sql);
        defaultDao.insert(sql.toString());
    }

    /**
     * 更新信息
     */
    public void update(Map<String, Object> map) {
        StringBuilder sql = new StringBuilder("UPDATE `" + tableName + "` SET ");

        int count = 0;
        for (String key : map.keySet()) {
            if (key.equals(idName)) {
                count++;
                continue;
            }
            Object value = map.get(key);
            if (value instanceof String) {
                value = CommonUtils.str(value.toString());
            }
            sql.append("`").append(key).append("`").append("=").append(value);
            if (count < map.size() - 1) {
                sql.append(", ");
            }
            count++;
        }

        sql.append(" WHERE `").append(idName).append("` = ").append(map.get(idName));

        defaultDao.update(sql.toString());
    }

    /**
     * 更新数据指定内容
     */
    public void updateUnique(int id, String key, Object value) {
        String str = value.toString();
        if (!CommonUtils.isNumber(str)) {
            str = CommonUtils.str(str);
        }
        defaultDao.update("UPDATE `" + tableName + "` SET " +
                key + " = " + str + " WHERE `" + idName + "` = " + id);
    }
}
