/**
 * 
 */
package com.namNguyen03.Chat.Backend.security.jwt;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
/**
 * @author nam
 *
 */
@Component
public class AuthEntryPointJwt  implements AuthenticationEntryPoint {
	 @Override
     public void commence(HttpServletRequest request, HttpServletResponse response,
             AuthenticationException authException) throws IOException, ServletException {
         response.sendError(401, "Unauthorized error");

 }
}
