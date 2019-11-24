package filters;

import controllers.PathUtil;
import entities.User;
import enums.Role;
import org.apache.log4j.Logger;
import util.SecurityUtil;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class AuthenticationFilter implements Filter {

    public static final Logger LOGGER = Logger.getLogger(AuthenticationFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletReq = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String path = PathUtil.getLastPartOfPath(httpServletReq);
        if (isNotSecuredPage(path)) {
            LOGGER.debug("Page is not secured: " + path);
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        User user = (User) httpServletReq.getSession().getAttribute("User");

        if (user == null) {
            LOGGER.info("User not logged");
            httpServletResponse.sendRedirect("/registration");
            return;
        }

        if (hasNotPermission(path, user.getRole())) {
            httpServletResponse.sendRedirect("/403-error");
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }


    @Override
    public void destroy() {

    }

    private boolean hasNotPermission(String path, Role role) {
        return !SecurityUtil.hasPermission(path, role);
    }

    private boolean isNotSecuredPage(String path) {
        return !SecurityUtil.isSecurePage(path);
    }

}
