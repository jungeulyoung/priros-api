package com.lnt.priros.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.lnt.priros.exception.GlobalException;
import com.lnt.priros.mapper.UserMapper;
import com.lnt.priros.model.dto.AuthenticUser;
import com.lnt.priros.model.entity.User;
import com.lnt.priros.resource.ErrorCode;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService  implements UserDetailsService{

    private final UserMapper userMapper;    

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findById(username);
		if (user == null)
			throw new GlobalException(ErrorCode.USER_NOT_FOUND);		
        AuthenticUser authenticUser = AuthenticUser.build(user);
        return authenticUser;
    }
    
}
