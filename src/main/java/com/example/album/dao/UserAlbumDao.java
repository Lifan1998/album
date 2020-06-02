package com.example.album.dao;

import com.example.album.entity.UserAlbum;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (UserAlbum)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-02 13:42:48
 */
public interface UserAlbumDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserAlbum queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<UserAlbum> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param userAlbum 实例对象
     * @return 对象列表
     */
    List<UserAlbum> queryAll(UserAlbum userAlbum);

    /**
     * 新增数据
     *
     * @param userAlbum 实例对象
     * @return 影响行数
     */
    int insert(UserAlbum userAlbum);

    /**
     * 修改数据
     *
     * @param userAlbum 实例对象
     * @return 影响行数
     */
    int update(UserAlbum userAlbum);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}