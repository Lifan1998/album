package com.example.album.service.impl;

import com.example.album.entity.Album;
import com.example.album.dao.AlbumDao;
import com.example.album.service.AlbumService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Album)表服务实现类
 *
 * @author makejava
 * @since 2020-06-02 14:03:10
 */
@Service
public class AlbumServiceImpl implements AlbumService {
    @Resource
    private AlbumDao albumDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Album queryById(Integer id) {
        return this.albumDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Album> queryAllByLimit(int offset, int limit) {
        return this.albumDao.queryAllByLimit(offset, limit);
    }
    
    @Override
    public List<Album> queryAll(Album album) {
        return this.albumDao.queryAll(album);
    }

    /**
     * 新增数据
     *
     * @param album 实例对象
     * @return 实例对象
     */
    @Override
    public Album insert(Album album) {
        this.albumDao.insert(album);
        return album;
    }

    /**
     * 修改数据
     *
     * @param album 实例对象
     * @return 实例对象
     */
    @Override
    public Album update(Album album) {
        this.albumDao.update(album);
        return this.queryById(album.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.albumDao.deleteById(id) > 0;
    }
}