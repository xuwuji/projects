package com.xuwuji.vote.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import com.xuwuji.vote.mapper.VoteMapper;
import com.xuwuji.vote.util.MyBatisUtil;

@Service
public class VoteService {

	public void vote(int id) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		VoteMapper mapper = session.getMapper(VoteMapper.class);
		mapper.vote(id);
		session.commit();
		session.close();
	}

	public List<HashMap<String, String>> check(String id) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		VoteMapper mapper = session.getMapper(VoteMapper.class);
		List<HashMap<String, String>> list = mapper.check(id);
		session.commit();
		session.close();
		return list;
	}

	public void add(String openId, String time) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		VoteMapper mapper = session.getMapper(VoteMapper.class);
		mapper.add(openId, time);
		session.commit();
		session.close();
	}

	public HashMap<String, Integer> getCount(int id) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		VoteMapper mapper = session.getMapper(VoteMapper.class);
		HashMap<String, Integer> list = mapper.getCount(id);
		session.commit();
		session.close();
		return list;
	}

	public HashMap<String, Integer> getVoteTotal() {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		VoteMapper mapper = session.getMapper(VoteMapper.class);
		HashMap<String, Integer> list = mapper.totalVote();
		session.commit();
		session.close();
		return list;
	}
}
