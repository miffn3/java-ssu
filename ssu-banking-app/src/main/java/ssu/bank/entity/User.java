package ssu.bank.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private String username;
	@Column
	private String password;
	@Column
	private String phone;
	@Column
	private String address;

	public User(String username, String password, String phone, String address)
	{
		this.username = username;
		this.password = password;
		this.phone = phone;
		this.address = address;
	}
}
