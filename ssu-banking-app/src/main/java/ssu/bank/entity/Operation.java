package ssu.bank.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "operation")
public class Operation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private String date;
	@Column
	private String currency;
	@Column(name ="account_From")
	private String accountFrom;
	@Column(name ="account_To")
	private String accountTo;
	@Column
	private BigDecimal amount;
	@Column(name ="money_Before")
	private BigDecimal moneyBefore;
	@Column(name ="money_After")
	private BigDecimal moneyAfter;
}
