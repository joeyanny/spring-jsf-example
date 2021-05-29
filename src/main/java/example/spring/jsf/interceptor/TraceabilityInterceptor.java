package example.spring.jsf.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Interceptor which logs out request and response
 * details for traceability of requests.
 */
public class TraceabilityInterceptor extends HandlerInterceptorAdapter {

    private static Logger LOG = LogManager.getLogger(TraceabilityInterceptor.class);

    /**
     * Runs before any incoming request reaches the controller.
     * 
     * @return boolean Whether to continue with the execution chain
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        StringBuilder builder = new StringBuilder();
        builder.append("Incoming request received\n");
        builder.append(" - URI: " + request.getRequestURI() + "\n");
        builder.append(" - Path: " + request.getContextPath() + "\n");
        builder.append(" - Method: " + request.getMethod() + "\n");
        builder.append(" - Content type: " + request.getContentType() + "\n");

        LOG.trace(builder.toString());

        return true;
    }

    /**
     * Runs after a request has been handled by the app
     * 
     * @throws IOException 
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws IOException {
    	StringBuilder builder = new StringBuilder();
        builder.append("Request processsing completed\n");
        builder.append(" - URI: " + request.getRequestURI() + "\n");
        builder.append(" - Status code: " + response.getStatus() + "\n");

        LOG.trace(builder.toString());
    }
}
