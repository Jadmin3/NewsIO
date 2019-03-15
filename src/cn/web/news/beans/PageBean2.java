package cn.web.news.beans;

import cn.web.news.dao.TopicDao;

public class PageBean2 {
	private int pageNo;// 当前页
	private int allCount;// 总记录数
	private int allPages;// 总页面
	private int pagesize;// 每页显示的数据
	private int prev;// 上一页
	private int next;// 下一页

	public PageBean2(int pageno) {
		this.prev = pageNo;
		this.next = pageno;
		this.pageNo = pageno;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getAllCount() {
		TopicDao dao = new TopicDao();
		int acount = dao.getAllTopicsCount();
		this.allCount = acount;
		return allCount;
	}

	public void setAllCount(int allCount) {
		this.allCount = allCount;
	}

	public int getAllPages() {
		TopicDao dao = new TopicDao();
		int acount = dao.getAllTopicsCount();
		int last = (acount % pagesize == 0) ? acount / pagesize : acount / pagesize + 1;
		this.allPages = last;
		return allPages;
	}

	public void setAllPages(int allPages) {
		this.allPages = allPages;
	}

	public int getPagesize() {
		pagesize = 10;
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getPrev() {
		int prev = pageNo;
		if (pageNo <= 1) {
			pageNo = 1;

		} else if (pageNo >= allPages) {
			prev--;

		} else {
			prev--;

		}

		return prev;
	}

	public void setPrev(int prev) {
		this.prev = prev;
	}

	public int getNext() {

		int next = pageNo;
		if (pageNo <= 1) {
			pageNo = 1;
			next++;
		} else if (pageNo >= allPages) {

			next = allPages;
		} else {

			next++;
		}
		return next;
	}

		public void setNext(int next) {
		this.next = next;
	}
//修复了bug

}
