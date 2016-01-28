package com.xuwuji.vote.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface VoteMapper {

	@Update("update vote set count=count+1 where id=${id}")
	public void vote(@Param("id") int id);

	@Select("select count from vote where id=${id}")
	public HashMap<String, Integer> getCount(@Param("id") int id);

	@Select("select time from user where openid=\'${openId}\'")
	public List<HashMap<String, String>> check(@Param("openId") String openId);

	@Insert("insert into user (openid,time) values('${openId}','${time}')")
	public void add(@Param("openId") String openId, @Param("time") String time);

	@Select("select max(id) as total from user")
	public HashMap<String, Integer> totalVote();

}
