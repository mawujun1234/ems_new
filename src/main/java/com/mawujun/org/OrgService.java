package com.mawujun.org;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mawujun.exception.BusinessException;
import com.mawujun.permission.ShiroUtils;
import com.mawujun.repository.cnd.Cnd;
import com.mawujun.service.AbstractService;
import com.mawujun.utils.M;
import com.mawujun.utils.bean.BeanUtils;
import com.mawujun.utils.help.ReportCodeHelper;
import com.mawujun.utils.string.StringUtils;


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
	/**
	 * 获取或生成上下级的reportCode
	 * @param parent_id
	 * @param child_id
	 * @return 第一个是parent的reportcode，第二个是child的reportcode
	 */
	private String[] queryReportCode(String parent_id,Dim dim){
		//获取当前父节点下的最大reportcode
				String maxReportCode=(String)orgOrgRepository.queryMax(Cnd.max(M.OrgOrg.child_reportCode).andEquals(M.OrgOrg.parent.id, parent_id).andEquals(M.OrgOrg.dim, dim));
				//获取父节点的reportcode
				String parent_reportCode=(String)orgOrgRepository.queryMax(Cnd.max(M.OrgOrg.child_reportCode).andEquals(M.OrgOrg.child.id, parent_id).andEquals(M.OrgOrg.dim, dim));
				if(!StringUtils.hasText(parent_reportCode)){
					parent_reportCode=ReportCodeHelper.generate4(null, false);
				}
				String child_reportCode=null;
				if(!StringUtils.hasText(maxReportCode)){
					child_reportCode=ReportCodeHelper.generate4(parent_reportCode, true);
				} else {
					child_reportCode=ReportCodeHelper.generate4(maxReportCode, false);
				}
				return new String[]{parent_reportCode,child_reportCode};
	}
	
	public void create(OrgVO orgVO) {
		Org org=BeanUtils.copyOrCast(orgVO, Org.class);
		//org.setReportCode(ReportCodeHelper.generate4(, false));
		this.getRepository().create(org);
		
		Org parent=orgRepository.load(orgVO.getParent_id());
//		//获取当前父节点下的最大reportcode
//		String maxReportCode=(String)orgOrgRepository.queryMax(Cnd.max(M.OrgOrg.child_reportCode).andEquals(M.OrgOrg.parent.id, parent.getId()));
//		String parent_reportCode=(String)orgOrgRepository.queryMax(Cnd.max(M.OrgOrg.child_reportCode).andEquals(M.OrgOrg.child.id, parent.getId()));
//		if(!StringUtils.hasText(parent_reportCode)){
//			parent_reportCode=ReportCodeHelper.generate4(null, false);
//		}
//		String child_reportCode=null;
//		if(!StringUtils.hasText(maxReportCode)){
//			child_reportCode=ReportCodeHelper.generate4(parent_reportCode, true);
//		} else {
//			child_reportCode=ReportCodeHelper.generate4(maxReportCode, false);
//		}
		String[] reportcodes=queryReportCode(parent.getId(),orgVO.getDim());
		
		OrgOrg orgorg=new OrgOrg();
		orgorg.setChild(org);
		orgorg.setDim(orgVO.getDim());
		orgorg.setParent(parent);
		orgorg.setParent_reportCode(reportcodes[0]);
		orgorg.setChild_reportCode(reportcodes[1]);
		orgOrgRepository.create(orgorg);
	}
	/**
	 * d当reportcode出现混乱的时候，进行初始化,出还刷reportcode和reportlevel
	 */
	public void initReportCode(){
		for(Dim dim:Dim.values()){
			//root是根节点的id
			initReportCode("root",dim,null);
		}
	}
	private void initReportCode(String parent_id,Dim dim,String parent_reportcode){
		List<OrgOrg> rootParent=orgOrgRepository.query(Cnd.select().andEquals(M.OrgOrg.parent.id, parent_id).andEquals(M.OrgOrg.dim, dim));
		//String 
		if(parent_reportcode==null){
			parent_reportcode=ReportCodeHelper.generate4(parent_reportcode, false);
		}
		String child_reportcode=ReportCodeHelper.generate4(parent_reportcode, true);
		for(OrgOrg parent:rootParent){
			parent.setParent_reportCode(parent_reportcode);
			parent.setChild_reportCode(child_reportcode);

			initReportCode(parent.getChild().getId(),dim,child_reportcode);
			//这行要放在最后
			child_reportcode=ReportCodeHelper.generate4(child_reportcode, false);
		}
	}
	
	public void delete(Org org,Dim dim){
		//如果下面还有职位，就不能删除
		int count=positionService.queryCount(Cnd.select().andEquals(M.Position.org_id, org.getId()));
		if(count>0){
			throw new BusinessException("该组织单元下还有职位挂着，不能删除！");
		}
		orgOrgRepository.deleteBatch(Cnd.delete().andEquals(M.OrgOrg.child.id, org.getId()).andEquals(M.OrgOrg.dim, dim));
		this.delete(org);
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

	public List<NodeVO> queryOnlyOrg(String parent_id,Dim dim,String exclude_id){
		return orgRepository.queryOnlyOrg(parent_id, dim,exclude_id);
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
	
	public void updateParentOrg(String org_id,String old_parent_id,String new_parent_id,Dim dim) {
		Org child=orgRepository.get(org_id);
		Org new_parent=orgRepository.load(new_parent_id);
		
		//删除原有的组织上下级关系
		OrgOrg orgorg=new OrgOrg();
		orgorg.setChild(child);
		orgorg.setParent(orgRepository.get(old_parent_id));
		orgorg.setDim(dim);
		orgOrgRepository.delete(orgorg);
		
		//构建新的上下级关系
		String[] reportcodes=queryReportCode(new_parent_id,dim);
		OrgOrg neworgorg=new OrgOrg();
		neworgorg.setChild(child);
		neworgorg.setParent(new_parent);
		neworgorg.setDim(dim);
		neworgorg.setParent_reportCode(reportcodes[0]);
		neworgorg.setChild_reportCode(reportcodes[1]);
		orgOrgRepository.create(neworgorg);
		
		//初始化reportcode
		initReportCode(org_id, dim,reportcodes[1]);
		
	}
	/**
	 * 不区分在建仓库和备品备件仓库
	 * @author mawujun qq:16064988 mawujun1234@163.com
	 * @param look
	 * @param edit
	 * @return
	 */
	public List<Org> queryStores4Combo(Boolean look,Boolean edit) {
		return orgRepository.queryStores4Combo(look, edit,ShiroUtils.getUserId(),null);
	}
	/**
	 * 获取在建仓库或者备品备件仓库
	 * @author mawujun qq:16064988 mawujun1234@163.com
	 * @param look
	 * @param edit
	 * @param orgtype
	 * @return
	 */
	public List<Org> queryStores4Combo(Boolean look,Boolean edit,OrgType orgtype) {
		return orgRepository.queryStores4Combo(look, edit,ShiroUtils.getUserId(),orgtype.toString());
	}
	public List<Org> queryWorkunits4Combo(Boolean look,Boolean edit) {
		return orgRepository.queryWorkunits4Combo(look, edit,ShiroUtils.getUserId());
	}
	public List<Org> queryRepaircenter4Combo(Boolean look,Boolean edit) {
		return orgRepository.queryRepaircenter4Combo(look, edit,ShiroUtils.getUserId());
	}
}
