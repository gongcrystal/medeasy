package com.medimpact.medeasy.service.drugcategory;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.medimpact.medeasy.common.bean.DrugCategoryBi;
import com.medimpact.medeasy.common.exception.BizException;
import com.medimpact.medeasy.dao.drugcategory.DrugCategoryDao;

@Service
public class DrugCategoryServiceImpl implements DrugCategoryService{
	
	@Resource
	private DrugCategoryDao dao; 

	@Override
	public List<DrugCategoryBi> getDrugCategoryByName(List<String> names) throws BizException {
		// TODO Auto-generated method stub
		List<DrugCategoryBi> list = dao.getDrugCategoryByName(names);	
		if(list==null)
			return null;
		list.stream().forEach(v->{
			if(v!=null && v.getType()!=null){
				v.setSearch(v.getType()+"-"+v.getCode());
			}
		});		
					
		return list;
	}

}
