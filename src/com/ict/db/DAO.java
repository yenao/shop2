package com.ict.db;

import java.util.ArrayList;

import java.util.List;


import org.apache.ibatis.session.SqlSession;

public class DAO {
	private static SqlSession ss;

	private synchronized static SqlSession getSession() {
		if (ss == null) {
			ss = DBService.getFactory().openSession(false);
		}
		return ss;
	}
	public static List<VO> getList(String category){
		List<VO> list=new ArrayList<VO>();
		list=getSession().selectList("list", category);
		return list;
	}
	
	public static MVO getLogIn(MVO mvo) {
		MVO m_vo=new MVO();
		m_vo=getSession().selectOne("login", mvo);
		return m_vo;
	}
	public static VO getOneList(String idx) {
		VO vo= new VO();
		vo=getSession().selectOne("onelist", idx);
		return vo;
	}
	
	public static int getProductInsert(VO vo) {
		int result = 0;
		result = getSession().insert("product_add", vo);
		ss.commit();		
		return result;
	}
	
	// id와 제품번호를 받아서 카트리스틀 구하자
	public static CVO getCartList(String id, String p_num) {
		CVO cvo = null;
		CVO c_vo = new CVO();
		c_vo.setId(id);
		c_vo.setP_num(p_num);
		
		cvo = getSession().selectOne("chkcart", c_vo);
		return cvo;
	}
	
	public static int getCartInsert(CVO c_vo) {
		int result = 0;
		result = getSession().insert("cartinsert", c_vo);
		ss.commit();
		return result ;
	}
	
	public static int getCartUpdate(CVO cvo) {
		int result = 0;
		result = getSession().update("cartupdate", cvo);
		ss.commit();
		return result;
	}
	
	public static List<VO> getAllCart(String id) {
		List<VO> clist = null;
		clist = getSession().selectList("cartall", id);
		return clist;
	}
	
	public static int getCartAmountUpdate(CVO cvo) {
		int result = 0;
		result = getSession().update("cartAmountUpdate", cvo);
		ss.commit();
		return result ;
	}
	
	public static int getCartDelete(CVO cvo) {
		int result = 0;
		result = getSession().delete("cartDelete", cvo);
		ss.commit();
		return result;
	}
}
