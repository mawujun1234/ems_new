package com.mawujun.org;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.mawujun.repository.IRepository;
/**
 * @author mawujun qq:16064988 e-mail:mawujun1234@163.com 
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface OrgRepository extends IRepository<Org, String>{
	public OrgVO getBaseOrg(@Param("id")String id);
	
	public List<NodeVO> queryNodeVO(@Param("parent_id")String parent_id,@Param("dim")Dim dim);
	public List<NodeVO> queryOnlyOrg(@Param("parent_id")String parent_id,@Param("dim")Dim dim,@Param("exclude_id")String exclude_id);
	
	public List<Org> query4Combo(@Param("parent_no")String parent_no,@Param("channo")String channo,@Param("dim")Dim dim,@Param("user_id")String user_id);
	
	//public List<Org> queryParent(@Param("child_no")String child_no,@Param("dim")Dim dim);
	//public List<Org> queryChildren(@Param("parent_no")String parent_no,@Param("dim")Dim dim);
	
	//public void insert_positionorgaccess(@Param("position_id")String position_id,@Param("orgno")String orgno);
	//public void delete_positionorgaccess(@Param("position_id")String position_id,@Param("orgno")String orgno);
	
	//public List<NodeVO> queryOrgAccess(@Param("parent_no")String parent_no,@Param("dim")Dim dim,@Param("position_id")String position_id);
	
	
	public List<Org> queryStores4Combo(@Param("look")Boolean look,@Param("edit")Boolean edit,@Param("user_id")String user_id,@Param("orgtype")String orgtype);
	public List<Org> queryWorkunits4Combo(@Param("look")Boolean look,@Param("edit")Boolean edit,@Param("user_id")String user_id);
	public List<Org> queryRepaircenter4Combo(@Param("look")Boolean look,@Param("edit")Boolean edit,@Param("user_id")String user_id);
	

}
