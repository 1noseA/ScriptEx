package com.exex.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

public interface CommentDao {

	@Insert("insert into comment (date, name, content) values (#{date}, #{name}, #{content})")
	@Options(useGeneratedKeys=true, keyColumn="id")
	public void insert(Comment comment);

	@ResultMap("commentResult")
	@Select("select * from comment")
	public List<Comment> findAllComment();

}
