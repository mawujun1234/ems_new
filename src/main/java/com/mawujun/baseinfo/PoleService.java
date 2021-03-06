package com.mawujun.baseinfo;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mawujun.exception.BusinessException;
import com.mawujun.repository.cnd.Cnd;
import com.mawujun.service.AbstractService;
import com.mawujun.task.TaskRepository;
import com.mawujun.utils.M;
import com.mawujun.utils.page.Pager;


/**
 * @author mawujun qq:16064988 e-mail:16064988@qq.com 
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional(propagation=Propagation.REQUIRED)
public class PoleService extends AbstractService<Pole, String>{

	@Autowired
	private PoleRepository poleRepository;
	@Autowired
	private TaskRepository taskRepository;
	//@Autowired
	//private MapService mapService;
	
	private HashMap<String,Pole> poles_cache=new HashMap<String,Pole>();
	@Override
	public Pole get(String id) {
		Pole store=poles_cache.get(id);
		if(store==null){
			return poleRepository.get(id);
		} else {
			return store;
		}
	}
	@Override
	public String create(Pole entity) {
		this.getRepository().create(entity);
//		//获取该点位的经纬度
//		if(StringUtils.hasText(entity.getAddress())){
//			String[] coorde= mapService.getLngLat(entity.geetFullAddress());
//			if(coorde!=null){
//				entity.setLongitude(coorde[0]);
//				entity.setLatitude(coorde[1]);
//				this.getRepository().update(entity);
//			}
//		}
		return entity.getId();
	}
	@Override
	public void update(Pole entity) {
		//去获取当前点位的地址，然后比对是否一致，如果不一致，就进行经纬度初始化
		//如果新地址获取不到经纬，就把经纬度设置为null
//		Pole temp=this.getRepository().geetFullAddress(entity.getId());
//		if(!temp.geetFullAddress().equals(entity.geetFullAddress())){
//			String[] coorde= mapService.getLngLat(entity.geetFullAddress());
//			if(coorde!=null){
//				entity.setLongitude(coorde[0]);
//				entity.setLatitude(coorde[1]);
//			} else {
//				entity.setLongitude(null);
//				entity.setLatitude(null);
//			}
//		}
		
		this.getRepository().update(entity);
	}
	
	@Override
	public void delete(Pole pole) {
		//判断点位是否已经被任务所关联了
		int count=taskRepository.queryCount(Cnd.count(M.Task.id).andEquals(M.Task.pole_id, pole.getId()));
		if(count>0){
			throw new BusinessException("不能删除,有任务关联，请发送点位取消任务!");
		}
		this.getRepository().delete(pole);
	}
	@Override
	public PoleRepository getRepository() {
		return poleRepository;
	}
	
//	public void savePoles(String area_id,String[] pole_ids) {	
//		for(int i=0;i<pole_ids.length;i++){
//			poleRepository.savePoles(area_id, pole_ids[i]);
//		}
//	}
//	public void deletePoles(String area_id,String[] pole_ids) {	
//		for(int i=0;i<pole_ids.length;i++){
//			poleRepository.deletePoles(pole_ids[i]);
//		}
//	}
	
	public List<Pole> queryEquipments(String id){
		return poleRepository.queryEquipments(id);
	}
	
	public Pager<Pole> queryPolesByWorkunit(Pager<Pole> params){
		return poleRepository.queryPolesByWorkunit(params);
	}
	
	public List<PoleVO> queryPolesAndEquipments(String customer_id) {
		return poleRepository.queryPolesAndEquipments(customer_id);
	}
	
	public Pager<Pole> queryPageFilteContain(Pager<Pole> params) {
		return poleRepository.queryPageFilteContain(params);
	}
	
	public void addWorkunit(String workunit_id,String[] pole_ids) {	
		for(String pole_id:pole_ids){
			poleRepository.addWorkunit(workunit_id, pole_id);
		}
	}
	

	public void removeWorkunit(String workunit_id,String[] pole_ids) {	
		for(String pole_id:pole_ids){
			poleRepository.removeWorkunit(workunit_id, pole_id);
		}
	}
	
	
	public  void transform(String customer_id,String[] pole_ids) {
		if(customer_id==null || "".equals(customer_id) ||pole_ids ==null || pole_ids.length==0){
			return;
		}
		for(String pole_id:pole_ids){
			poleRepository.update(Cnd.update().set(M.Pole.customer_id, customer_id).andEquals(M.Pole.id, pole_id));
		}
	}
}
