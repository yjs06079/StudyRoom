package com.g3.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.g3.comm.DBConnection;
import com.g3.dao.QuestionDAO;
import com.g3.dto.QuestionDTO;

public class QuestionService {

	private static QuestionService instance = new QuestionService();
	
	public static QuestionService getInstance()
	{
		return instance;
	}
	
	private QuestionService() {}

	public List<QuestionDTO> getList(int startrow, int endrow,String search, String searchtxt) {
		// TODO Auto-generated method stub
		
		DBConnection dbconn = DBConnection.getDBConn();
		Connection conn=null;
		List<QuestionDTO> list = new ArrayList<QuestionDTO>();
		
		try {
			conn=dbconn.getConnection();
			QuestionDAO dao = new QuestionDAO();
			
			list=dao.getList(conn,startrow,endrow,search,searchtxt);
		}catch(SQLException|NamingException e)
		{
			System.out.println(e);
		}finally {
			if(conn!=null) try { conn.close();} catch(SQLException e) {}
		}
		return list;
	}

	public int getTotalCount(String search, String searchtxt) {
		// TODO Auto-generated method stub
		
		DBConnection dbconn = DBConnection.getDBConn();
		Connection conn=null;
	       int totalcount=0;
	       
		   try {
	           conn=dbconn.getConnection();
		       conn.setAutoCommit(false);
		       QuestionDAO dao=QuestionDAO.getDAO();
	           
	          totalcount= dao.getTotalCount(conn,search, searchtxt);
	          System.out.println("totalcount!!! : "+totalcount);
		       
		       conn.commit();
		   } catch(SQLException|NamingException e)
		   {
			  try { conn.rollback();} catch(SQLException e2) {}
			   
		   }finally {
			   if(conn!=null) try { conn.close();} catch(SQLException e) {}
			   
		   }
		   return totalcount;
		}

	public QuestionDTO detail(int questionnum) {
		// TODO Auto-generated method stub
		
		DBConnection dbconn=DBConnection.getDBConn();
		Connection conn=null;
		QuestionDTO dto = new QuestionDTO();
		try {
			conn=dbconn.getConnection();
			QuestionDAO dao = new QuestionDAO();
			dto = dao.detail(conn, questionnum);
			
		}catch(SQLException|NamingException e)
		{
			System.out.println(e);
		}finally {
			if(conn!=null) try { conn.close();} catch(SQLException e) {}
		}
			
			return dto;
		}
	
	
}