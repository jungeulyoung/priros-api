package com.lnt.priros.security;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;
import com.lnt.priros.exception.GlobalException;
import com.lnt.priros.mapper.FirmMapper;
import com.lnt.priros.model.dto.AuthenticUser;
import com.lnt.priros.model.entity.Firm;
import com.lnt.priros.resource.ErrorCode;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    private PasswordEncoder passwordEncoder;
    private UserDetailsService userDetailsService;    
    private FirmMapper firmMapper;

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        Assert.notNull(passwordEncoder, "passwordEncoder cannot be null");
        this.passwordEncoder = passwordEncoder;
     }

   public void setUserDetailsService(UserDetailsService userDetailsService) {
      this.userDetailsService = userDetailsService;
   }  

   public void setFirmMapper(FirmMapper firmMapper) {
      this.firmMapper = firmMapper;
   }  

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
      if(authentication == null){
            throw new InternalAuthenticationServiceException("Authentication is null");
        }
        String username = authentication.getName();
        if(authentication.getCredentials() == null){
            throw new AuthenticationCredentialsNotFoundException("Credentials is null");
        }
    	String password = authentication.getCredentials().toString();
        UserDetails loadedUser = this.userDetailsService.loadUserByUsername(username);

        /* 실질적인 인증 */
        if(!passwordEncoder.matches(password, loadedUser.getPassword())){
            throw new GlobalException(ErrorCode.USER_NOT_FOUND);
        }

        AuthenticUser authenticUser = (AuthenticUser) loadedUser;

        Firm firm = firmMapper.findByFirmCode(authenticUser.getFirmCode());
        authenticUser.setFirm(firm);

        /* 인증 완료 */
        UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(loadedUser, null, loadedUser.getAuthorities());
        result.setDetails(authentication.getDetails());
        return result;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    
}
