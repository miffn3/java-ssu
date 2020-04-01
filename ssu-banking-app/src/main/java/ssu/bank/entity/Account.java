package ssu.bank.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "client_Id")
	private Long clientId;
	@Column
	private BigDecimal amount;
	@Column(name = "acc_Code")
	private String accCode;

	public Account(Long id, Long clientId, String accCode) {
		this.id = id;
		this.clientId = clientId;
		this.amount = BigDecimal.ZERO;
		this.accCode = accCode;
	}
}
