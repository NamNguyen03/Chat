/**
 * 
 */
package com.namNguyen03.Chat.Backend.service.model.share;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author nam
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageResponseModel<T> {
	private long pageCurrent;
	private long totalPage;
	private List<T> items = new ArrayList<>();

	

}
