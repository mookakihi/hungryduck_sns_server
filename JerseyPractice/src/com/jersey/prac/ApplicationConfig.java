package com.jersey.prac;
import javax.ws.rs.*;
import org.glassfish.jersey.server.ResourceConfig;
@ApplicationPath("/rest")
public class ApplicationConfig extends ResourceConfig{
	public ApplicationConfig() {
		packages("com.jersey.prac.resource");
		
		
	}
	//rest로 들어오는 모든 요청은 com.jersey.prac.resource로 분기하는 역할을 담당하는 클래스 간다.
}

/*
 * 1. maven project 생성
 * 2. pom.xml
 * 3. web.xml건들지말고 ApplicationConfig 
 * 4. 관련 패키지 생성하고 Path 분기 생성해줌
 * http://jsonobject.tistory.com/206
 */