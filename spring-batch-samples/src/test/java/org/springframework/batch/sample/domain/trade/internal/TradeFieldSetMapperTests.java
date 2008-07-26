package org.springframework.batch.sample.domain.trade.internal;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;
import org.springframework.batch.item.file.mapping.DefaultFieldSet;
import org.springframework.batch.item.file.mapping.FieldSet;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.support.AggregateItemReader;
import org.springframework.batch.sample.domain.trade.Trade;
import org.springframework.batch.sample.domain.trade.internal.TradeFieldSetMapper;
import org.springframework.batch.sample.support.AbstractFieldSetMapperTests;

public class TradeFieldSetMapperTests extends AbstractFieldSetMapperTests {

	private static final String CUSTOMER = "Mike Tomcat";

	private static final BigDecimal PRICE = new BigDecimal(1.3);

	private static final long QUANTITY = 7;

	private static final String ISIN = "fj893gnsalX";

	protected Object expectedDomainObject() {
		Trade trade = new Trade();
		trade.setIsin(ISIN);
		trade.setQuantity(QUANTITY);
		trade.setPrice(PRICE);
		trade.setCustomer(CUSTOMER);
		return trade;
	}

	protected FieldSet fieldSet() {
		String[] tokens = new String[4];
		tokens[TradeFieldSetMapper.ISIN_COLUMN] = ISIN;
		tokens[TradeFieldSetMapper.QUANTITY_COLUMN] = String.valueOf(QUANTITY);
		tokens[TradeFieldSetMapper.PRICE_COLUMN] = String.valueOf(PRICE);
		tokens[TradeFieldSetMapper.CUSTOMER_COLUMN] = CUSTOMER;

		return new DefaultFieldSet(tokens);
	}

	protected FieldSetMapper<Trade> fieldSetMapper() {
		@SuppressWarnings("unchecked")
		FieldSetMapper<Trade> mapper = new TradeFieldSetMapper();
		return mapper;
	}

	@Test
	public void testBeginRecord() throws Exception {
		assertEquals(AggregateItemReader.BEGIN_RECORD, fieldSetMapper().mapLine(
				new DefaultFieldSet(new String[] { "BEGIN" }), -1));
	}

	@Test
	public void testEndRecord() throws Exception {
		assertEquals(AggregateItemReader.END_RECORD, fieldSetMapper().mapLine(
				new DefaultFieldSet(new String[] { "END" }), -1));
	}

}