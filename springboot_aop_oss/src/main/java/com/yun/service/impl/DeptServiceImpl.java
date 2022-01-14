package com.yun.service.impl;

import com.yun.dao.DeptDao;
import com.yun.entity.Dept;
import com.yun.service.DeptService;
import com.yun.util.MyConstants;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/*import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;*/

/**
 * 部门表(Dept)表服务实现类
 *
 * @author makejava
 * @since 2021-12-13 14:17:39
 */
@Service("deptService")
public class DeptServiceImpl implements DeptService {
    @Resource
    private DeptDao deptDao;

    @Override
    public List<Dept> queryAll() {
        return deptDao.queryAll();
    }

    @Override
    public boolean deleteBatch(List<Long> myList) {
        return deptDao.deleteBatch(myList);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param deptId 主键
     * @return 实例对象
     */
    @Override
    public Dept queryById(Integer deptId) {
        return this.deptDao.queryById(deptId);
    }


   /* @Override
    public Page<Dept> queryByPage(Dept dept, PageRequest pageRequest) {
        long total = this.deptDao.count(dept);
        return new PageImpl<>(this.deptDao.queryAllByLimit(dept, pageRequest), pageRequest, total);
    }*/

    @Override
    public List<Dept> selectAll(Long page, Long limit) {
        //优化代码，不分页的时候，默认第一页，一页显示10条
        if (page == null) {
            page = MyConstants.page;
            limit = MyConstants.limit;
        }
        return deptDao.selectAllDept(page, limit);
    }

    /**
     * 新增数据
     *
     * @param dept 实例对象
     * @return 实例对象
     */
    @Override
    public boolean insert(Dept dept) {
        int i = this.deptDao.insert(dept);
        if (i>0){
            return true;
        }
        return false;
    }

    /**
     * 修改数据
     *
     * @param dept 实例对象
     * @return 实例对象
     */
    @Override
    public boolean update(Dept dept) {
        int update = this.deptDao.update(dept);
        if (update>0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 通过主键删除数据
     *
     * @param deptId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer deptId) {
        return this.deptDao.deleteById(deptId) > 0;
    }
}
