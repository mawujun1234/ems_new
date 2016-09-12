package com.mawujun.org;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mawujun.service.AbstractService;
import com.mawujun.utils.bean.BeanUtils;


/**
 * @author mawujun qq:16064988 e-mail:mawujun1234@163.com 
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional(propagation=Propagation.REQUIRED)
public class OrgService extends AbstractService<Org, String>{

	@Autowired
	private OrgRepository orgRepository;
	@Autowired
	private OrgOrgRepository orgOrgRepository;
	@Autowired
	private PositionService positionService;
	
	@Override
	public OrgRepository getRepository() {
		return orgRepository;
	}
	
	public void create(OrgVO orgVO) {

		Org org=BeanUtils.copyOrCast(orgVO, Org.class);
		this.getRepository().create(org);
		
		OrgOrg orgorg=new OrgOrg();
		orgorg.setChild(org);
		orgorg.setDim(orgVO.getDim());
		orgorg.setParent(orgRepository.load(orgVO.getParent_id()));
		orgOrgRepository.create(orgorg);
	}
	/**
	 * 获取职位和组织节点
	 * @param parent_id
	 * @param dim
	 * @return
	 */
	public List<NodeVO> queryNodeVO(String parent_id,Dim dim) {
		return orgRepository.queryNodeVO(parent_id, dim);
	}

	public List<NodeVO> queryOnlyOrg(String parent_id,Dim dim){
		return orgRepository.queryOnlyOrg(parent_id, dim);
	}
	
	public List<Org> query4Combo(String parent_no,String channo,Dim dim,String user_id) {
		return orgRepository.query4Combo(parent_no, channo, dim,user_id);
	}
	
	public List<NodeVO> queryOrgAccess(String parent_no,Dim dim,String position_id) {
		return orgRepository.queryOrgAccess(parent_no, dim,position_id);
	}
	
	
	public void checkNodes(Dim dim,String position_id,String orgno,String orgty,Boolean checked,String parent_orgnos[]) {
		for(String parent_orgno:parent_orgnos){
			if(checked){
				orgRepository.insert_positionorgaccess(position_id, parent_orgno);
			} else {
				orgRepository.delete_positionorgaccess(position_id, parent_orgno);
			}
			
		}
		if(checked){
			//把该节点下的所有子节点授予这个角色
			//positionService.addChildren(position_id, orgno, dim);
		} else {
			//把该节点下的所有子节点授予这个角色
			//positionService.deleteChildren(position_id, orgno, dim);
		}
		
		
	}
}
