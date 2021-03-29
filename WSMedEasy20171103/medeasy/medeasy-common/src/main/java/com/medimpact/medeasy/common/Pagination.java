package com.medimpact.medeasy.common;


public class Pagination {
    // 总行数
    long totalRows;

    // 每页行数
    long pageSize = 20L;

    // 总页数
    long totalPages;

    // 当前页码
    long currentPage = 1L;

    // 下一页
    long nextPage;

    // 上一页
    long previousPage;

    // 是否有下一页
    boolean hasNext;

    // 是否有上一页
    boolean hasPrevious;

    // 当前页开始行
    long pageStartRow;

    // 当前页结束行
    long pageEndRow;

    /**
     * 构造函数。
     *
     * @param totalRows 总行数
     */
    public Pagination(long totalRows, long currentPage) {
        setPageController(totalRows, currentPage);
    }

    public Pagination(long totalRows, long currentPage, long pageSize) {
        this.pageSize = pageSize;
        this.setPageController(totalRows, currentPage);
    }

    public static void main(String args[]) {

        Pagination pc = new Pagination(100, 2);
        System.out.println(pc.description());
    }

    public void setPageController(long totalRows, long currentPage) {

        setTotalRowsAmount(totalRows);
        setCurrentPage(currentPage);
    }

    /**
     * 设置总行数。
     *
     * @param rows 总行数。
     */
    private void setTotalRowsAmount(long rows) {

        if (rows < 0) {
            totalRows = 0;
        } else {
            totalRows = rows;
        }

        if (totalRows % pageSize == 0) {
            totalPages = totalRows / pageSize;
        } else {
            totalPages = totalRows / pageSize + 1;
        }
    }

    public long getCurrentPage() {
        return currentPage;
    }

    /**
     * 设置当前页数。
     *
     * @param curPage
     */
    private void setCurrentPage(long curPage) {

        if (curPage <= 0) {
            currentPage = 1;
        } else if (curPage > totalPages) {
            currentPage = totalPages;
        } else {
            currentPage = curPage;
        }

        if (currentPage == 1) {
            hasPrevious = false;
        } else {
            hasPrevious = true;
        }

        if (currentPage == totalPages) {
            hasNext = false;
        } else {
            hasNext = true;
        }

        nextPage = currentPage + 1;
        previousPage = currentPage - 1;

        // 计算当前页开始行和结束行
        pageStartRow = (currentPage - 1) * pageSize + 1;

        // 记录索引从0开始
        pageStartRow -= 1;
        // 如果是最后一页
        if (currentPage == totalPages)
            pageEndRow = totalRows - ((currentPage - 1) * pageSize);
        else
            pageEndRow = pageStartRow + pageSize;

    }

    public boolean isHasNext() {
        return hasNext;
    }

    public boolean isHasPrevious() {
        return hasPrevious;
    }

    public long getNextPage() {
        return nextPage;
    }

    public long getPageSize() {
        return pageSize;
    }

    public long getPreviousPage() {
        return previousPage;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public long getTotalRows() {
        return totalRows;
    }

    public long getPageStartRow() {
        return pageStartRow;
    }

    public long getPageEndRow() {
        return pageEndRow;
    }

    public String description() {
        String description = "Total:" + this.totalRows + " items "
                + this.getTotalPages() + " pages,Current page:"
                + this.currentPage + " Previous " + this.hasPrevious + " Next:"
                + this.hasNext + " start row:" + this.pageStartRow
                + " end row:" + this.pageEndRow;
        return description;
    }
}
