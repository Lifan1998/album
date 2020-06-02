package com.example.album.service;

import com.example.album.entity.Album;
import java.util.List;

/**
 * (Album)表服务接口
 *
 * @author makejava
 * @since 2020-06-02 14:03:09
 */
public interface AlbumService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Album queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Album> queryAllByLimit(int offset, int limit);
    
    List<Album> queryAll(Album album);

    /**
     * 新增数据
     *
     * @param album 实例对象
     * @return 实例对象
     */
    Album insert(Album album);

    /**
     * 修改数据
     *
     * @param album 实例对象
     * @return 实例对象
     */
    Album update(Album album);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}