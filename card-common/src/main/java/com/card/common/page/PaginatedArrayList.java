package com.card.common.page;

import lombok.Data;

import java.util.ArrayList;

/**
 * @author: yangzhanbang
 * Email: yangzhanbang@jd.com
 * Date: 2018/2/11
 * Description：
 */
@Data
public class PaginatedArrayList<T> {
	public static final int PAGE_SIZE_DEFAULT = 20;
	private int pageSize;
	private int index;
	private int totalItem;
	private int totalPage;
	private int startRow;
	private int endRow;

	public PaginatedArrayList() {
		this.paginate();
	}

	public PaginatedArrayList(int index, int pageSize) {
		this.index = index;
		this.pageSize = pageSize;
		this.paginate();
	}

	public boolean isFirstPage() {
		return this.index <= 1;
	}

	public boolean isMiddlePage() {
		return !this.isFirstPage() && !this.isLastPage();
	}

	public boolean isLastPage() {
		return this.index >= this.totalPage;
	}

	public boolean isNextPageAvailable() {
		return !this.isLastPage();
	}

	public boolean isPreviousPageAvailable() {
		return !this.isFirstPage();
	}

	public int getNextPage() {
		return this.isLastPage() ? this.totalItem : this.index + 1;
	}

	public int getPreviousPage() {
		return this.isFirstPage() ? 1 : this.index - 1;
	}

	private void paginate() {
		if (this.pageSize < 1) {
			this.pageSize = 20;
		}

		if (this.index < 1) {
			this.index = 1;
		}

		if (this.totalItem > 0) {
			this.totalPage = this.totalItem / this.pageSize + (this.totalItem % this.pageSize > 0 ? 1 : 0);
			if (this.index > this.totalPage) {
				this.index = this.totalPage;
			}

			this.endRow = this.index * this.pageSize;
			this.startRow = this.endRow - this.pageSize + 1;
			if (this.endRow > this.totalItem) {
				this.endRow = this.totalItem;
			}
		}
	}
}

