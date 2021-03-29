package com.medimpact.medeasy.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/** 
* @author Crystal E-mail: 
* @version 创建时间：2017年11月7日 
* 类说明 
*/
@Controller
public class FuncCtrl {
	@RequestMapping({"/"})
	
	/*@RequestMapping(value = "/", method = RequestMethod.GET)*/
	/*public String index(HttpServletRequest request) {	*/
	public String index() {		
		
	    return "index";		
	}
	
	@RequestMapping({"/log"})
	public String log() {
	    return "/log/log";
	}
	
	@RequestMapping({"/welcome"})  
	public String welcome() {
	    return "/welcome";
	}
	
	
	@RequestMapping({"/invalid"})  
	public String invalidSession() {
	    return "/security/invalidSession";
	}
	
	@RequestMapping({"/timeout"})
	public String timeout() {
	    return "/timeout";
	}
	
	@RequestMapping({"/role/roleMng"})
	public String roleMng() {
	    return "/role/roleMng";
	}
	
	@RequestMapping({"/user"})
	public String user() {
	    return "/security/user";
	}
	
	@RequestMapping({"/changePsDetail"})
	public String changePsDetail() {
	    return "/security/changePsDetail";
	}
	@RequestMapping({"/resetPasswd"})
	public String resetPasswd() {
	    return "/security/resetPasswd";
	}
	
	@RequestMapping({"/test"})
	public String test() {
	    return "test";
	}
	
	@RequestMapping({"/iframe"})
	public String iframetest() {
	    return "iframetest";
	}
	
	@RequestMapping({"/iframe1"})
	public String iframetest1() {
	    return "iframetest1";
	}
	
	@RequestMapping({"/a"})
	public String iframea() {
	    return "a";
	}
	
	@RequestMapping({"/home"})
	public String home() {
	    return "home";
	}
	
	@RequestMapping({"/b"})
	public String iframeb() {
	    return "b";
	}
	@RequestMapping({"/c"})
	public String iframec() {
	    return "c";
	}
	
	@RequestMapping({"/druguse"})
	public String drugUse() {
	    return "/druguse/druguse";
	}
	
	@RequestMapping({"/bp/review"})
	public String realtimeReview() {
	    return "/bp/realtimereview/realtimeReview";
	}
	
	@RequestMapping({"/bp/js531"})
	public String js531() {
	    return "/bp/jiangsu531/js531";
	}
	
	
	@RequestMapping({"/mypage"})
	public String myPage() {
	    return "/security/mypage";
	}
	
	@RequestMapping({"/login"})
	public String login() {
	    return "/security/login";
	}
	
	@RequestMapping({"/denied"})
	public String denied() {
	    return "/security/denied";
	}
	
	/*@RequestMapping({"/logout"})
	public String logout() {
	    return "/security/logout";
	}*/	

	
	@RequestMapping(value = "/ts/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Logout!");
		CookieClearingLogoutHandler cookieClearingLogoutHandler = new CookieClearingLogoutHandler(
				AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		new SecurityContextLogoutHandler().logout(request, response, auth);
		SecurityContextHolder.clearContext();
		cookieClearingLogoutHandler.logout(request, response, null);
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}		
		return "redirect:/login";
	}
	
	@RequestMapping({"/basicdt/drRank"})
	public String drRank() {
	    return "/basicdt/dr_rank";
	}
	
	@RequestMapping({"/drug/drug_apply"})
	public String drugApply() {
	    return "/drugstatistics/drug_apply";
	}


	@RequestMapping({"/common/allSimpleSelect"})
	public String simpleSelect(){
		return "/common/allSimpleSelect";
	}

	@RequestMapping({"/bp/drugparticularchangesana"})
	public String drugParticularChangesAna(){
		return "/bp/drugparticularchangesana/main";
	}

	@RequestMapping({"/bp/rdustatistics"})
	public String rduStatistics(){
		return "/bp/rdustatistics/rduStatistics";
	}
	@RequestMapping({ "/bp/herbal" })
	public String herbalMedicineUsedStatistics(){
		return "/bp/herbalmedicineusedstatistics/herbalMedicineUsedStatistics";
	}

	@RequestMapping({ "/bp/workSheet" })
	public String drugUseWorkSheet() {
		return "/bp/basedrugusedworksheet/baseDrugUsedWorkSheet";
	}

	@RequestMapping({ "/bp/basedrugusedstatistics" })
	public String baseDrugUsedStatistics() {
	    return "/bp/basedrugusedstatistics/baseDrugUsedStatistics";
	}
	
	@RequestMapping({"/bp/basedrugusedworksheet"})
	public String baseDrugUsedWorkSheet(){
		return "/bp/basedrugusedworksheet/baseDrugUsedWorkSheet";
	}
	@RequestMapping({"/bp/rxevawroksheet"})
	public String rxEvaWrokSheet(){
		return "/bp/rxevawroksheet/rxEvaWrokSheet";
	}
	@RequestMapping({ "/bp/rxevst" })
	public String rxEvaStatistics() {
		return "/bp/rxevastatistics/rxEvaStatistics1";
	}
	@RequestMapping({"/bp/abxcomprehensiveindex"})
	public String abxComprehensiveIndex(){
		return "/bp/abxcomprehensiveindex/abxComprehensiveIndex";
	}
	@RequestMapping({"/bp/abxdddstatistics"})
	public String abxDDDStatistics(){
		return "/bp/abxdddstatistics/abxDDDStatistics";
	}
	@RequestMapping({"/bp/abxusedstatistics"})
	public String abxUsedStatistics(){
		return "/bp/abxusedstatistics/abxUsedStatistics";
	}
	@RequestMapping({"/bp/abxunauthorizedusedana"})
	public String abxUnauthorizedUsedAna(){
		return "/bp/abxunauthorizedusedana/abxUnauthorizedUsedAna";
	}

	@RequestMapping({"/bp/datatabletest"})
	public String datatableTest(){
		return "/bp/datatabletest/dt";
	}
}
