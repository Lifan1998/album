package com.example.album.dao;

import com.example.album.domain.AlbumUpdateInfo;
import com.example.album.entity.Album;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Album)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-02 14:03:09
 */
public interface AlbumDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Album queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Album> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param album 实例对象
     * @return 对象列表
     */
    List<Album> queryAll(Album album);

    /**
     * 新增数据
     *
     * @param album 实例对象
     * @return 影响行数
     */
    int insert(Album album);

    /**
     * 修改数据
     *
     * @param album 实例对象
     * @return 影响行数
     */
    int update(Album album);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    AlbumUpdateInfo getRecentUpdateInfo(Integer userId);

    List<Album> queryByUserId(Integer userId);

}