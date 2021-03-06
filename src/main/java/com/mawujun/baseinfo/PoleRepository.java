package com.mawujun.baseinfo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.mawujun.permission.User;
import com.mawujun.repository.IRepository;
import com.mawujun.utils.page.Pager;
/**
 * @author mawujun qq:16064988 e-mail:16064988@qq.com 
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface PoleRepository extends IRepository<Pole, String>{

//	/**
//	 * 王片区里面添加Pole
//	 * @author mawujun email:160649888@163.com qq:16064988
//	 * @param area_id
//	 * @param pole_id
//	 */
//	public void savePoles(@Param("area_id")String area_id,@Param("pole_id")String pole_id);
//	/**
//	 * 把pole从某个area中移除
//	 * @author mawujun email:160649888@163.com qq:16064988
//	 * @param pole_id
//	 */
//	public void deletePoles(@Param("pole_id")String pole_id);
	
	public List<Pole> queryEquipments(@Param("id")String id);
	
	public Pager<Pole> queryPolesByWorkunit(Pager<Pole> params);
	public void addWorkunit(@Param("workunit_id")String workunit_id,@Param("pole_id")String pole_id);
	public void removeWorkunit(@Param("workunit_id")String workunit_id,@Param("pole_id")String pole_id);
	
	public Pager<Pole> queryPageFilteContain(Pager<Pole> params);
	
	public List<PoleVO> queryPolesAndEquipments(@Param("customer_id")String customer_id);
	public int query_count_equipment_in_pole(@Param("pole_id")String pole_id);
	
	//======================下面的是地图上用的
	public List<Pole> queryNoLngLatPole();
	public Pager<Pole> queryNoLngLatPole(Pager<Pole> page);
	
	public void updateCoordes(@Param("longitude")String longitude,@Param("latitude")String latitude,@Param("pole_id")String pole_id);
	//查询某个客户下的点位
	public Pager<Pole> queryPoles4Map(Pager<Pole> page);
	//查询某个客户下的点位
	public List<Pole> queryPoles4Map(Map<String,Object> params);
	public List<Pole> queryBrokenPoles();
	
	public Pole geetFullAddress(@Param("pole_id")String pole_id);
	
	public List<Pole> queryNoTransformPoles();
	public void updateOrginLngLatByPoleCode(@Param("code")String code,@Param("longitude")String longitude,@Param("latitude")String latitude,@Param("customer_2")String customer_2);
	public void updateLngLatByPoleCode(@Param("code")String code,@Param("longitude")String longitude,@Param("latitude")String latitude);
}


