package edu.vku.searchjob.security;

import edu.vku.searchjob.entity.Account;
import edu.vku.searchjob.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private IAccountRepository iAccountRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Account acc=iAccountRepository.findByEmail(email);
        if(acc==null){
            throw  new UsernameNotFoundException("user name not four");
        }else{
            return new CustomUser(acc);
        }

    }
}
