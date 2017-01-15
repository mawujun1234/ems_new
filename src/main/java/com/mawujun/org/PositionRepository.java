package com.mawujun.org;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.mawujun.permission.User;
import com.mawujun.repository.IRepository;
/**
 * @author mawujun qq:16064988 e-mail:mawujun1234@163.com 
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface PositionRepository extends IRepository<Position, String>{
	//public List<Position> queryPositionByName(@Param("orgno")String orgno,@Param("name")String name);
	//public void delete_t_position_org_userByUser(@Param("user_id")String user_id);
	
	public List<PositionOrgAccessVO> querySelectOrgs(@Param("position_id")String position_id,@Param("orgType")String orgType);

	public List<User> queryUsersByOrg(@Param("org_id")String org_id,@Param("look")Boolean look,@Param("edit")Boolean edit);
}
