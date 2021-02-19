package com.ict.model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ict.db.DAO;
import com.ict.db.MVO;
import com.ict.db.VO;

public class ViewCartCommand implements Command{
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		MVO mvo = (MVO)request.getSession().getAttribute("m_vo");
		String id = mvo.getId();
		
		// 해당 id의 전체 카트 정보를 가져오기
		List<VO> clist = DAO.getAllCart(id);
		
		request.setAttribute("clist", clist);
		
		return "view/viewcart.jsp";
	}
}
