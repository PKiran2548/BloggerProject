package com.bikkadit.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="posts")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postID ;
	
	@Column(name="post_title")
	private String postTitle ;
	
	@Column(name="containt")
	private String containt ;
	
	@Column(name="image_name")
	private String imageName ;
	
	@Column(name="image_add_date")
	private Date addedDate ;
	
	@ManyToOne	// many(post) belongs to one category --- one category have many post
	private Category category ;
	
	@ManyToOne // many post belongs to one user -- one user have many post
	private User user ;

}







