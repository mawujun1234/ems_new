package com.mawujun.org;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mawujun.permission.User;
import com.mawujun.repository.cnd.Cnd;
import com.mawujun.service.AbstractService;
import com.mawujun.utils.M;


/**
 * @author mawujun qq:16064988 e-mail:mawujun1234@163.com 
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional(propagation=Propagation.REQUIRED)
public class PositionService extends AbstractService<Position, String>{

	@Autowired
	private PositionRepository positionRepository;
	@Autowired
	private PositionOrgAccessRepository positionOrgAccessRepository;
	@Autowired
	private PositionOrgUserRepository positionOrgUserRepository;
	@Autowired
	private OrgRepository orgRepository;
	
	@Override
	public PositionRepository getRepository() {
		return positionRepository;
	}
	//@Override
	public String create(Position position,Dim dim) {
		positionRepository.create(position);
		
////		//同时创建该角色可以访问的组织节点范围
////		把所有父节点加进来，然后再把所有的子节点也加进来
////		
//		if(position.getAccessRule()==AccessRule.this_org){
//			//this_org(position,dim);
//		} else {
//			//all_org(position,dim);
//		}
		
		return position.getId();
	}
	public void update(Position position,Dim dim) {
		//先清空所有的权限，然后重新加载
//		positionOrgAccessRepository.deleteBatch(Cnd.delete().andEquals(M.PositionOrgAccess.position.id, position.getId()));
//		if(position.getAccessRule()==AccessRule.this_org){
//			//this_org(position,dim);
//		} else {
//			//all_org(position,dim);
//		}
		this.getRepository().update(position);
	}
//	public void all_org(Position position,Dim dim){
//		addChildren(position.getId(),"root",dim);
//	}
//	/**
//	 * 当选择的规则是  本级组织单元的时候，就选择本级和本级以下的所有的组织单元，
//	 * 选择上级单元的思路是  在查询每一级组织单元的时候，都要通过权限进行判断，这级可以看到哪些
//	 * @author mawujun qq:16064988 mawujun1234@163.com
//	 */
//	public void this_org(Position position,Dim dim){
//		addParent(position.getId(),position.getOrg_id(),dim);
//		addChildren(position.getId(),position.getOrg_id(),dim);
//	}
//	
//	protected void addParent(String position_id,String orgno,Dim dim){
//		orgRepository.insert_positionorgaccess(position_id, orgno);
//		if(orgno.equals("root")){
//			return;
//		}
//		List<Org> parents=orgRepository.queryParent(orgno, dim);
//		for(Org org:parents){
//			addParent(position_id ,org.getOrg_id(), dim);	
//		}
//	}
//	
//	protected void addChildren(String position_id,String orgno,Dim dim) {
//		List<Org> children=orgRepository.queryChildren(orgno, dim);
//		for(Org child:children){
//			orgRepository.insert_positionorgaccess(position_id, child.getOrg_id());
//			if(!child.getOrgty().equals("SHOP")) {
//				addChildren(position_id ,child.getOrg_id(), dim);
//			}
//			
//		}
//	}
//	
//	protected void deleteChildren(String position_id,String orgno,Dim dim) {
//		List<Org> children=orgRepository.queryChildren(orgno, dim);
//		for(Org child:children){
//			orgRepository.delete_positionorgaccess(position_id, child.getOrg_id());
//			if(!child.getOrgty().equals("SHOP")) {
//				deleteChildren(position_id ,child.getOrg_id(), dim);
//			}
//			
//		}
//	}
	
	public void delete(Position position) {
		//先删除这个角色可以访问的Org
		positionOrgAccessRepository.deleteBatch(Cnd.delete().andEquals(M.PositionOrgAccess.position.id, position.getId()));
		positionOrgUserRepository.deleteBatch(Cnd.delete().andEquals(M.PositionOrgUser.position.id, position.getId()));
		super.delete(position);
	}
	
	public List<PositionOrgAccessVO> querySelectOrgs(String position_id,String orgType) {
		return positionRepository.querySelectOrgs(position_id,orgType);
	}
	public void selectStore(PositionOrgAccessVO vo){
		
		PositionOrgAccess positionOrgAccess=new PositionOrgAccess();
		positionOrgAccess.setOrg(orgRepository.load(vo.getOrg_id()));
		positionOrgAccess.setPosition(positionRepository.load(vo.getPosition_id()));	
		positionOrgAccess.setLook(vo.getLook());
		positionOrgAccess.setEdit(vo.getEdit());
		positionOrgAccessRepository.createOrUpdate(positionOrgAccess);
	}
	public void deselectStore(PositionOrgAccessVO vo) {
		PositionOrgAccess positionOrgAccess=new PositionOrgAccess();
		positionOrgAccess.setOrg(orgRepository.load(vo.getOrg_id()));
		positionOrgAccess.setPosition(positionRepository.load(vo.getPosition_id()));	
		positionOrgAccess.setLook(vo.getLook());
		positionOrgAccess.setEdit(vo.getEdit());
		positionOrgAccessRepository.createOrUpdate(positionOrgAccess);
	}
	
	public List<User> queryUsersByOrg(String org_id,Boolean look,Boolean edit) {
		return positionRepository.queryUsersByOrg(org_id,look,edit);
	}

}
