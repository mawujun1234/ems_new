package com.mawujun.permission;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mawujun.org.OrgRepository;
import com.mawujun.org.PositionOrgUser;
import com.mawujun.org.PositionOrgUserRepository;
import com.mawujun.org.PositionRepository;
import com.mawujun.repository.cnd.Cnd;
import com.mawujun.service.AbstractService;
import com.mawujun.utils.M;
import com.mawujun.utils.page.Pager;

/**
 * @author mawujun qq:16064988 e-mail:mawujun1234@163.com
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserService extends AbstractService<User, String> {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PositionRepository positionRepository;
	@Autowired
	private OrgRepository orgRepository;
	@Autowired
	private PositionOrgUserRepository positionOrgUserRepository;
	@Autowired
	private RoleUserRepository userRoleRepository;
	

	@Override
	public UserRepository getRepository() {
		return userRepository;
	}

	public void createByRole(User user, String role_id) {
		super.create(user);
		RoleUser userRole = new RoleUser(user, roleRepository.get(role_id));
		userRoleRepository.create(userRole);
	}
	
	public void createByPosition(User user,String position_id,String org_id) {
		super.create(user);
		PositionOrgUser positionOrgUser = new PositionOrgUser(positionRepository.load(position_id),orgRepository.load(org_id),user);
		positionOrgUserRepository.create(positionOrgUser);
		
	}
	public void addToRole(String user_id,String role_id) {
		RoleUser userRole = new RoleUser(userRepository.load(user_id), roleRepository.load(role_id));
		userRoleRepository.create(userRole);
	}
	
	
	public void deleteFromRole(String user_id,String role_id) {
		userRoleRepository.delete(new RoleUser(userRepository.load(user_id), roleRepository.load(role_id)));

		//super.delete(user);
	}
	
	public void addToPosition(String user_id,String position_id,String org_id) {
		PositionOrgUser positionOrgUser = new PositionOrgUser(positionRepository.load(position_id),orgRepository.load(org_id),userRepository.load(user_id));
		positionOrgUserRepository.create(positionOrgUser);
	}
	public void deleteFromPosition(String user_id,String position_id,String org_id) {
		PositionOrgUser positionOrgUser = new PositionOrgUser(positionRepository.load(position_id),orgRepository.load(org_id),userRepository.load(user_id));
		positionOrgUserRepository.delete(positionOrgUser);
	}

	public UserVO getByLoginName(String loginName) {
		UserVO user = userRepository.getByLoginName(loginName);
		return user;
	}

	/**
	 * 获取用户的权限
	 * @author mawujun qq:16064988 mawujun1234@163.com
	 * @param username
	 * @return
	 */
	public Set<String> findPermissions(String user_id) {
		//这里最好再添加进去用户所属的数据权限，然后放到用户里面
		List<String> list = userRepository.findPermissions(user_id);
		Set<String> set = new HashSet<String>();
		set.addAll(list);
		return set;
	}
	public Pager<User> queryByRole(Pager<User> pager){
		return userRepository.queryByRole(pager);
	}
	public Pager<User> queryByPosition(Pager<User> pager){
		return userRepository.queryByPosition(pager);
	}
	@Override
	public void delete(User user) {
		userRoleRepository.deleteBatch(Cnd.delete().andEquals(M.RoleUser.user.id, user.getId()));
		//positionOrgUserRepository.deleteBatch(Cnd.delete().andEquals(M.PositionOrgUser.user.id, user.getId()));
		this.getRepository().delete(user);
	}

//	/**
//	 * 初始化该用户可访问的组织单元
//	 * @author mawujun qq:16064988 mawujun1234@163.com
//	 * @param userVO
//	 */
//	public List<Org> queryCurrentOrg(String user_id) {
//		//获取用户当前所属的组织单元
//		return userRepository.queryCurrentOrg(user_id);
//		
//	}
	
	public void deleteUserByLoginName(String loginName) {
		userRepository.deleteUserByLoginName(loginName);
	}
}
