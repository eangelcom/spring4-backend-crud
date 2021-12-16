package com.thecompany.crud.common.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.thecompany.crud.api.product.ProductDto;

class BeanUtilTest {

	@InjectMocks
	BeanUtil beanUtil;
	
	@BeforeEach
    void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void whenCopyProperties_thenReturnCopiedDto() {
		
		ProductDto dest = new ProductDto();
		
		ProductDto orig = new ProductDto();
		orig.setSku("dummy");
	
		ProductDto actualDest = (ProductDto) beanUtil.copyProperties(dest, orig);
		
		assertEquals(orig.getSku(), actualDest.getSku());
	}
	
	@Test
	void givenNullDest_whenCopyProperties_thenReturnNull() {
		
		ProductDto dest = null;
		
		ProductDto orig = new ProductDto();
		orig.setSku("dummy");
	
		ProductDto actualDest = (ProductDto) beanUtil.copyProperties(dest, orig);
		
		assertNull(actualDest);
	}
	
	@Test
	void whenCsvToList_thenReturnList() {
		String csv = "dummy1,dummy2";
		List<String> expectedList = List.of("dummy1", "dummy2");
		
		List<String> actualList = beanUtil.csvToList(csv);
		
		assertEquals(expectedList, actualList);
	}
	
	@Test
	void givenNullCsv_whenCsvToList_thenReturnEmptyList() {
		String csv = null;
		List<String> expectedList = new ArrayList<>();
		
		List<String> actualList = beanUtil.csvToList(csv);
		
		assertEquals(expectedList, actualList);
	}
	
	@Test
	void givenEmptyCsv_whenCsvToList_thenReturnEmptyList() {
		String csv = "";
		List<String> expectedList = new ArrayList<>();
		
		List<String> actualList = beanUtil.csvToList(csv);
		
		assertEquals(expectedList, actualList);
	}
	
	@Test
	void whenCsvToList_thenReturnCsv() {
		List<String> list = List.of("dummy1", "dummy2");
		String expectedCsv = "dummy1,dummy2";
		
		String actualCsv = beanUtil.listToCsv(list);
		
		assertEquals(expectedCsv, actualCsv);
	}
	
	@Test
	void givenNullList_whenCsvToList_thenReturnEmptyCsv() {
		List<String> list = null;
		String expectedCsv = "";
		
		String actualCsv = beanUtil.listToCsv(list);
		
		assertEquals(expectedCsv, actualCsv);
	}
	
	@Test
	void givenEmptyList_whenCsvToList_thenReturnEmptyCsv() {
		List<String> list = new ArrayList<>();
		String expectedCsv = "";
		
		String actualCsv = beanUtil.listToCsv(list);
		
		assertEquals(expectedCsv, actualCsv);
	}
	
}
