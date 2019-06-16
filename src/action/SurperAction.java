package action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

//所有action的父类
public class SurperAction extends ActionSupport implements ServletRequestAware,ServletResponseAware,ServletContextAware,Serializable {

    private static final long serialVersionUID = 1L;
    protected HttpServletRequest httpServletRequest;//请求对象
    protected HttpServletResponse httpServletResponse;//响应对象
    protected HttpSession session;//会话对象
    protected ServletContext servletContext;//全局对象

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
        this.session = httpServletRequest.getSession();
    }

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
    }

    @Override
    public void setServletContext(ServletContext application) {
        this.servletContext = application;
    }
}
