package com.xuwuji.vote.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xuwuji.vote.service.VoteService;
import com.xuwuji.vote.util.TimeUtil;

@Controller
public class VoteController {
	@Autowired
	VoteService service;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index() {
		return new ModelAndView("index");
	}

	@RequestMapping(value = "/vote", method = RequestMethod.GET)
	public String vote(@RequestParam("id") int id, @RequestParam("openId") String openId) {
		if (voted(openId)) {
			return "error";
		} else {
			String today = TimeUtil.currentTimewithoutMinutes();
			service.add(openId, today);
			service.vote(id);
			return "ok";
		}
	}

	@RequestMapping(value = "/count", method = RequestMethod.GET)
	public @ResponseBody String getCount(@RequestParam("id") int id) {
		HashMap<String, Integer> map = service.getCount(id);
		Integer count = 0;
		for (Entry<String, Integer> entry : map.entrySet()) {
			count = entry.getValue();
		}
		return String.valueOf(count);
	}

	@RequestMapping(value = "/total", method = RequestMethod.GET)
	public @ResponseBody String getVoteTotal() {
		HashMap<String, Integer> map = service.getVoteTotal();
		Integer count = 0;
		for (Entry<String, Integer> entry : map.entrySet()) {
			count = entry.getValue();
		}
		return String.valueOf(count);
	}

	private boolean voted(String openId) {
		List<HashMap<String, String>> list = service.check(openId);
		String today = TimeUtil.currentTimewithoutMinutes();
		HashSet<String> set = new HashSet();
		for (HashMap<String, String> map : list) {
			for (Entry<String, String> entry : map.entrySet()) {
				String date = entry.getValue();
				set.add(date);
			}
		}
		if (set.contains(today)) {
			return true;
		} else {
			return false;
		}
	}

}
