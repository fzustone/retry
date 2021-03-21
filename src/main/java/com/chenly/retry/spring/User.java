package com.chenly.retry.spring;



import lombok.Data;

import javax.persistence.*;

/**
 * @author chenly
 * @create 2021-03-21 17:49
 */
@Entity
@Table(name = "t_user")
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String url;

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", url='" + url + '\'' +
				'}';
	}
}
