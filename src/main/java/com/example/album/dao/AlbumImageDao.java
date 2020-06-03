package com.example.album.dao;

import com.example.album.entity.AlbumImage;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (AlbumImage)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-02 13:40:38
 */
public interface AlbumImageDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AlbumImage queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<AlbumImage> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param albumImage 实例对象
     * @return 对象列表
     */
    List<AlbumImage> queryAll(AlbumImage albumImage);

    /**
     * 新增数据
     *
     * @param albumImage 实例对象
     * @return 影响行数
     */
    int insert(AlbumImage albumImage);

    /**
     * 修改数据
     *
     * @param albumImage 实例对象
     * @return 影响行数
     */
    int update(AlbumImage albumImage);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    int deleteByImageId(Integer imageId);

}