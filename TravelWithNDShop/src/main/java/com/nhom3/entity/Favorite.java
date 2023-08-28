package com.nhom3.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity 
@Table(name = "favorites")
public class Favorite implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	@Temporal(TemporalType.DATE)
	@Column(name = "likedate")
	Date likedate = new Date();
	@ManyToOne
	@JoinColumn(name = "userid")
	Account account;
	@ManyToOne
	@JoinColumn(name = "productid")
	Product product;
}
