package br.com.hdi.corretores.model;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CorretorComStatus {

	private String name;
	private String document;
	private String code;
	private Date createDate;
	private BigDecimal commissionRate;
	private Boolean active;
	
}
