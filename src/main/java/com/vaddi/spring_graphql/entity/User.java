package com.vaddi.spring_graphql.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="users")
public class User {

	  @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long usrId;
	    private String name;
	    private String email;
	    private String phone;
	    private String password;
	    
	    @OneToMany(mappedBy = "user", cascade= CascadeType.ALL, orphanRemoval = true)
	    private List<Order>orders = new ArrayList<>();
}
