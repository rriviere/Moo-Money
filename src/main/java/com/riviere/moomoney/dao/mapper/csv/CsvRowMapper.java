package com.riviere.moomoney.dao.mapper.csv;

import java.util.HashMap;
import java.util.List;

/**
 * @author rriviere
 *
 */
public interface CsvRowMapper<T>  {

	T mapRow(List<HashMap<String, String>> records);

}
