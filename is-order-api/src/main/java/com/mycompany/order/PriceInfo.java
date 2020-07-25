/**
 * 
 */
package com.mycompany.order;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author jojo
 *
 */
@Data
public class PriceInfo {
	
	private Long id;
	
	private BigDecimal price;

}
