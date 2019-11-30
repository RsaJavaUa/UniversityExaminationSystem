package web.filters;

import org.apache.log4j.Logger;

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
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class LocalizationFilter implements Filter {
    private static final Logger LOG = Logger.getLogger(LocalizationFilter.class);
    private static final String LOCALE = "locale";
    private static final String BUNDLE = "bundle";

    private String defaultLocale;
    private String defaultBundle;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.defaultLocale = filterConfig.getInitParameter(LOCALE);
        this.defaultBundle = filterConfig.getInitParameter(BUNDLE);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();

        setLocale(session);
        setBundle(session);

        chain.doFilter(request, response);
    }

    private void setLocale(HttpSession session) {
        String locale = (String) session.getAttribute(LOCALE);
        if (locale == null) {
            LOG.debug("Set locale to session");
            LOG.debug("Set locale to session");
            session.setAttribute(LOCALE, defaultLocale);
        }
    }

    private void setBundle(HttpSession session) {
        String bundle = (String) session.getAttribute(BUNDLE);
        if (bundle == null) {
            LOG.debug("Set bundle to session");
            session.setAttribute(BUNDLE, defaultBundle);
        }
    }


    @Override
    public void destroy() {

    }

    public String getDefaultLocale() {
        return defaultLocale;
    }

    public String getDefaultBundle() {
        return defaultBundle;
    }
}