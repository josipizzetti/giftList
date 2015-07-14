package br.com.giftList.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName="AutenticacaoFilter", urlPatterns="/logado/*") //esta interceptando todas as pags dentro de logado
public class AutenticacaoFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, //ve se o usuario tem login
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httprequest = (HttpServletRequest)request;
		HttpServletResponse httpresponse = (HttpServletResponse)response;
		
		HttpSession session = httprequest.getSession();
		
		Integer idUsuario = (Integer) session.getAttribute("id");
		
		if(idUsuario != null && idUsuario > 0){
			chain.doFilter(request, response);
		}else{
			httpresponse.sendRedirect(httprequest.getContextPath()+"/index.jsf");
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
