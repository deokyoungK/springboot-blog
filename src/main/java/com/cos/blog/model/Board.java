package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //빌더 패턴!!
@Entity //User클래스가 Mysql에 테이블이 생성이 된다.
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id;

	@Column(nullable = false, length = 100)
	private String title; 
	
	@Lob //대용량데이터
	private String content; //섬머노트 라이브러리 <html>태그가 섞여서 디자인됨.
	
	private int count; //조회수

	@ManyToOne(fetch=FetchType.EAGER) //Board를 select하면 user는 한명이니깐 조인해서 바로 가져온다.
	@JoinColumn(name="userId")
	private User user; //DB는 오브젝트를 저장할 수 없다. FK,자바는 오브젝트를 저장할 수 있다.
	
	@OneToMany(mappedBy="board",fetch=FetchType.EAGER, cascade = CascadeType.REMOVE) //mappedBy-> 연관관계의 주인이 아니다(난 FK가 아니에요)DB에 칼럼을 만들지 마세요.
	@JsonIgnoreProperties({"board"}) //board를 통해서 접근하면 막아준다.
	@OrderBy("id desc")
	private List<Reply> replys;
	
	@CreationTimestamp
	private Timestamp createDate;
	
}
