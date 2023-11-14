package com.lnt.priros.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.lnt.priros.model.response.ErrorResponse;
import com.lnt.priros.resource.ErrorCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;
    private final XmlMapper xmlMapper;

    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ErrorCode errorCode = (ErrorCode) request.getAttribute("exception");
        if (errorCode == null) {
            errorCode = ErrorCode.SERVICE_ERROR;
        }

        response.setStatus(errorCode.getStatus());

        String contentType = request.getContentType();
        if (StringUtils.isNotBlank(contentType)) {
            if (!StringUtils.contains(contentType.toUpperCase(), "=UTF-8"))
                contentType = String.format("%s;%s", contentType, "charset=UTF-8");
            response.setContentType(contentType);
            if (StringUtils.contains(contentType.toLowerCase(), "xml")) {
                response.getOutputStream().write(xmlMapper.writeValueAsBytes(new ErrorResponse(errorCode)));
                return;
            }
        }

        String string = objectMapper.writeValueAsString(new ErrorResponse(errorCode));
        response.getOutputStream().write(string.getBytes(StandardCharsets.UTF_8));

    }

}
