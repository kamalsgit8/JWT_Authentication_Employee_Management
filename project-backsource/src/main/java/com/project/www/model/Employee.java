package com.project.www.model;

	import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
	
	@Entity
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	@Data
	@Table(name="employees")
	
	public class Employee 
	{
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long id;
	
		@Column
		private String firstName;
		@Column
		private String lastName;
		@Column
		private String email;
		@Column(nullable = false)
		@Size(max = 100)
		private String login;
	    @Column(nullable = false)
	    @Size(max = 100)
	    private String password;
	
	}