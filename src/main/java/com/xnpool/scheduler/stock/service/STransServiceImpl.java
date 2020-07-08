/**
 * 版权声明： 版权所有 违者必究 2020
*/
package com.xnpool.scheduler.stock.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xnpool.scheduler.stock.entity.STrans;
import com.xnpool.scheduler.stock.mapper.STransMapper;


/**
 * <p>业务接口实现类</p>
 * <p>个股总账</p>
 *
 * @author gaog
 * @since 2020-06-27 11:01:21
 */
@Service
@Transactional
public class STransServiceImpl extends ServiceImpl<STransMapper, STrans> implements STransService {

    @Override
    public STrans getTrans(Long userId, String code) {
        QueryWrapper<STrans> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid",userId);
        queryWrapper.eq("f57",code);
        return baseMapper.selectOne(queryWrapper);
    }

}