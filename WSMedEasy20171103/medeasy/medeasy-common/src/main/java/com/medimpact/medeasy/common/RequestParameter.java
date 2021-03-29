package com.medimpact.medeasy.common;
/**
 * @author Crystal E-mail:
 * @version 创建时间：2017年11月7日 类说明
 */

import java.io.Serializable;
import java.util.List;

import com.medimpact.medeasy.common.bean.security.SecUser;
import com.medimpact.medeasy.common.utils.NumberWrapperUtils;


/** 
* @author Crystal E-mail: 
* @version 创建时间：2017年9月15日 
* 类说明 
*/
public class RequestParameter extends StatisticParameter implements Serializable {
    /**
     * 最高20条
     */
    private final static long MAX_PAGEG_SIZE = 100L;
    
    private Long page;
   /* private Long rows=new Long(10);*/
    private Long rows;
    private String sidx;/*排序字段名*/
    private String sord;/*排序方式*/
    
    // 这两个属性方便copy
    private Long rowStart;
    private Long rowSize;
    
    private String searchField;
    private String searchString;  /*检索的具体值*/
    private String searchOper;
    private String filters;  
    //用于表记录的检索
	private String roleName;
	private SecUser secUser; 
	private int operateLevel;
	
    
   /* private SearchFilter filters;*/
        

   /*
	public SearchFilter getFilters() {
		return filters;
	}

	public void setFilters(SearchFilter filters) {
		this.filters = filters;
	}*/

	public String getFilters() {
		return filters;
	}

	public void setFilters(String filters) {
		this.filters = filters;
	}

	public String getSearchField() {
		return searchField;
	}

	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	public String getSearchOper() {
		return searchOper;
	}

	public void setSearchOper(String searchOper) {
		this.searchOper = searchOper;
	}

	/**
     * 兼容DataTable的分页方式
     */
    private Long start;
    private Long length;
    private int draw;
    
    

    public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public Long getRowStart() {
        if (NumberWrapperUtils.lessThanZero(getPage()) || NumberWrapperUtils.lessThanZero(getRows())) {
            return null;
        }
        return (getPage() - 1) * getRows();
    }

    public Long getRowSize() {
        return getRows();
    }

    public Long getPage() {
        return com.medimpact.medeasy.common.utils.NumberWrapperUtils.greaterThanZero(page) ? page : 1L;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getRows() {
        return rows;
    }

    public void setRows(Long rows) {
        this.rows = rows;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }
	

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}		

	public int getOperateLevel() {
		return operateLevel;
	}

	public void setOperateLevel(int operateLevel) {
		this.operateLevel = operateLevel;
	}

	public SecUser getSecUser() {
		return secUser;
	}

	public void setSecUser(SecUser secUser) {
		this.secUser = secUser;
	}

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		this.sidx = sidx;
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		this.sord = sord;
	}
	public void setRowStart(Long rowStart) {
		this.rowStart = rowStart;
	}

	public void setRowSize(Long rowSize) {
		this.rowSize = rowSize;
	}
	
}
