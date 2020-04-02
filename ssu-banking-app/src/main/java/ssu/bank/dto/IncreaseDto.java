package ssu.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class IncreaseDto {
	private BigDecimal amount;
	private String currency;
}
