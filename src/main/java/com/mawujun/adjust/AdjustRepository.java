package com.mawujun.adjust;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.mawujun.repository.IRepository;
import com.mawujun.utils.page.Pager;
/**
 * @author mawujun qq:16064988 e-mail:16064988@qq.com 
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface AdjustRepository extends IRepository<Adjust, String>{
	
	public Pager<AdjustVO> queryPageVO(Pager<AdjustVO> pager);

	public AdjustListVO getAdjustListVOByEcode(@Param("ecode")String ecode,@Param("store_id")String store_id);
	
	public Pager<AdjustVO> query4InStore(Pager<AdjustVO> page);
	public List<AdjustListVO> query4InStoreList(@Param("adjust_id")String adjust_id);
	
	public List<AdjustList> query_borrow_in_adjustList(@Param("adjust_id")String adjust_id);
	
	public void updateAdjustIsAllReturn(@Param("adjust_id")String adjust_id);
	
	public List<AdjustVO> queryBorrowAdjuest(@Param("user_id")String user_id);


}
