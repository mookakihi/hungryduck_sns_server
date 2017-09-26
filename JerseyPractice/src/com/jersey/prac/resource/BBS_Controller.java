package com.jersey.prac.resource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import com.google.gson.Gson;

import dao.BBSListDAO;
import dto.BBSList_DTO;
import dto.BBS_DTO;
@Path("/bbs")
public class BBS_Controller {
	BBSList_DTO bbsList;
	BBSListDAO dao=new BBSListDAO();
	Connection conn=null;
	@GET
	// path 어노태이션으로 특정 URI에 대한 요청을 처리 할 수있다.
	@Produces(MediaType.TEXT_PLAIN+ ";charset=utf-8")
	// 요청에 데힌 응답 데이터의 형식을 명시할 수 있다.
	// Consumes 어노테이션으로 요청 데이터의 형식을 명시할 수 있다
	public String getBBS()  {
		Gson json=new Gson();
		bbsList=dao.getBBSList();
		String data=json.toJson(bbsList);
		System.out.println(data);

		return data;	
	}
	
	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN+"; charset=utf-8")
	
	public String postBBS(@FormParam(value="data") String data) {
		
		//String result="";
		//Gson json=new Gson();
		
		//dao.BbsPost_DAO(bbs_dto)
		// 받아온 다음에 삽입하면 됨... 
		// 이미지 구현 > String 데이터로 온 이미지를 스트림에 실어서 보낸다
		// 미리 지정해놓은 서버측 디렉터리에 실어놓는다
		// 인서트 할 때 url을 삽입
		// 내가 내일 해야하는 것 > DB 구조도 수정, 이미지 삽입해서 insert, 데이터 불러오기 완벽하게..
		
		
		System.out.println(data);
		return data;
	}
	
	
	
	
	
	
	
	}
