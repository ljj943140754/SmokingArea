package com.smokeroom.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.common.bean.ResultData;
import com.smokeroom.entity.Activity;
import com.smokeroom.entity.ActivityJoinIn;
import com.smokeroom.entity.ext.activityVO;
import com.smokeroom.mapper.ActivityJoinInMapper;
import com.smokeroom.mapper.ActivityMapper;
import com.smokeroom.service.IActivityJoinInService;
/**
 * <p>
 *  活动报名实现类
 * </p>
 *
 * @author haiger412
 * @since 2019-10-30
 */
@Service
public class IActivityJoinInServiceImpl implements IActivityJoinInService{
	
	@Autowired
	private ActivityMapper activitymapper;
	
	@Autowired
	private ActivityJoinInMapper activityjoinInmapper;
	
	@Override
	public ResultData activityPocessing(ActivityJoinIn actJo) {
		//查看自己是否已报名过该活动
		ActivityJoinIn activityJoinIn = activityjoinInmapper.checkSign(actJo);
		if(activityJoinIn!=null){
			System.err.println("该活动已经报名...");
			return ResultData.fail("已报名...");
		}
		
		Activity act = new Activity();
		act.setAt_id(actJo.getJi_at_id());
		List<activityVO> list = activitymapper.getActivity(act);
		if(list.size()>0){
			if(list.get(0).getAt_sign_count() >= list.get(0).getAt_max_count()){
				System.err.println("报名人数已满");
				return ResultData.fail("活动人数已满！");
			}else{
				System.err.println("报名人数未满");
				Activity act1 = new Activity();
				act1.setAt_id(actJo.getJi_at_id());
				act1.setAt_sign_count(list.get(0).getAt_sign_count()+1);
				activitymapper.updateSignCount(act1);
				activityjoinInmapper.insert(actJo);
				System.err.println("报名成功！");
				return ResultData.success("报名成功！");
			}
		}
		return ResultData.fail("请稍后再试...");
	}

	
	
	
	
	
	@Override
	public int count(Wrapper<ActivityJoinIn> queryWrapper) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BaseMapper<ActivityJoinIn> getBaseMapper() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActivityJoinIn getById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getMap(Wrapper<ActivityJoinIn> queryWrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <V> V getObj(Wrapper<ActivityJoinIn> queryWrapper, Function<? super Object, V> mapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActivityJoinIn getOne(Wrapper<ActivityJoinIn> queryWrapper, boolean throwEx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ActivityJoinIn> list(Wrapper<ActivityJoinIn> queryWrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<ActivityJoinIn> listByIds(Collection<? extends Serializable> idList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<ActivityJoinIn> listByMap(Map<String, Object> columnMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> listMaps(Wrapper<ActivityJoinIn> queryWrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <V> List<V> listObjs(Wrapper<ActivityJoinIn> queryWrapper, Function<? super Object, V> mapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPage<ActivityJoinIn> page(IPage<ActivityJoinIn> page, Wrapper<ActivityJoinIn> queryWrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPage<Map<String, Object>> pageMaps(IPage<ActivityJoinIn> page, Wrapper<ActivityJoinIn> queryWrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Wrapper<ActivityJoinIn> queryWrapper) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeById(Serializable id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeByIds(Collection<? extends Serializable> idList) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeByMap(Map<String, Object> columnMap) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean save(ActivityJoinIn entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveBatch(Collection<ActivityJoinIn> entityList, int batchSize) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveOrUpdate(ActivityJoinIn entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveOrUpdateBatch(Collection<ActivityJoinIn> entityList, int batchSize) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(ActivityJoinIn entity, Wrapper<ActivityJoinIn> updateWrapper) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateBatchById(Collection<ActivityJoinIn> entityList, int batchSize) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateById(ActivityJoinIn entity) {
		// TODO Auto-generated method stub
		return false;
	}

}
