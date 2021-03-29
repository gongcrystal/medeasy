package com.medimpact.medeasy.web.log;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.ss.formula.functions.Now;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import com.jolbox.bonecp.PreparedStatementHandle;
import com.medimpact.medeasy.common.bean.LogBi;
import com.medimpact.medeasy.common.constant.SYSCONSTANT;
import com.medimpact.medeasy.service.log.LogService;
import com.medimpact.medeasy.service.security.AccountHelper1;

@Aspect
public class LogAspect {

	/*
	 * @Resource private RsService rsS;
	 */

	@Resource
	private LogService logS;
	
	/*@Before("execution(* com.medimpact.medeasy.service.bp..*.*AntiSt(..))")
	public void preHandle(){
		System.out.println("before date:" + new Date());
	}*/

	/*@After("execution(* com.medimpact.medeasy.service.bp..*.*AntiSt(..))")*/
	
	@After("execution(* com.medimpact.medeasy.service..*.*AntiSt(..))")
	public void afterwords(JoinPoint joinPoint) {		

		String methodName = joinPoint.getSignature().getName();
		
		/*System.out.println("Trigger  LogService! methodName="+methodName);*/
		Map<String, Object> paramContent = analyzeParameter(joinPoint.getArgs(), methodName);

		
		if (paramContent != null) {
			boolean sensitiveAction = false;
			LogBi logBi = new LogBi();
			
			for (String cond : SYSCONSTANT.CONDS) {
				Object value = paramContent.get(cond);
				
				if (value == null) {
					continue;
				}
				// 有检索敏感信息
				String sensitiveV = value.toString();
				
				/*System.err.println("sensitiveV="+sensitiveV);*/

				if (!sensitiveV.equals("null") && !sensitiveV.isEmpty()) {

					switch (cond) {
					case SYSCONSTANT.COND_DR:
						logBi.setCond(1);
						sensitiveAction = true;
						logBi.setSearchedDrname(sensitiveV);
						break;
					case SYSCONSTANT.COND_DRUG:
						logBi.setCond(2);
						sensitiveAction = true;
						logBi.setSearchedDrugname(sensitiveV);
						break;	
						
					case SYSCONSTANT.COND_PATIENTTYPE: // for test
						logBi.setCond(3);
						sensitiveAction = true;

					default:
						break;
					}
				}
			}// end of loop for
			
			// 保存到数据库
			if(sensitiveAction){
				logBi.setMethod(methodName);
				logBi.setUsername(new AccountHelper1().getUserName());
				logBi.setStartSensitiveAction(new Date());
				logS.save(logBi);
			}			
		}		
	}

	public Map<String, Object> analyzeParameter(Object[] args, String mName) {
		if (args == null) {
			/*System.out.println("args == null");*/
			return null;
		}
		StringBuffer rs = new StringBuffer();
		Map<String, Object> map = new HashMap<>();
		/* rs.append(mName); */
		String className = null;
		int index = 1;

		for (Object info : args) {

			className = info.getClass().getName();
			className = className.substring(className.lastIndexOf(".") + 1);

			Method[] methods = info.getClass().getDeclaredMethods();

			for (Method method : methods) {
				String methodName = method.getName();

				if (methodName.indexOf("get") == -1) {// 不是get方法
					continue;
				}
				Object rsValue = null;
				try {

					rsValue = method.invoke(info);
				} catch (Exception e) {
					continue;
				}
				/* rs.append("(" + methodName + ":" + rsValue + ")"); */

				rs.append(methodName + ":" + rsValue + ",");
				/*System.err.println("methodName="+methodName+" "+rsValue);*/
				map.put(methodName, rsValue);
			}

			index++;

		}
		return map;
	}
}
