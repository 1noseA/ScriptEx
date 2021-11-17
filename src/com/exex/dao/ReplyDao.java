package com.exex.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

public interface ReplyDao {

	@Insert("insert into reply values (#{repId}, #{repDate}, #{repName}, #{repContent}, #{comId})")
	public void insert(Reply reply);

	@ResultMap("replyResult")
	@Select("select * from reply")
	public List<Reply> findAllReply();

}
