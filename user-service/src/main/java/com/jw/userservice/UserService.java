package com.jw.userservice;

import com.jw.session.SessionDetails;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import static com.jw.userservice.UserDto.*;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements UserDetailsService
{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Long register(UserRegisterRequestDto requestDto)
    {
        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());
        if (checkDuplicateUser(requestDto.getEmail()))
            return null;

        UserEntity user = userRepository.save(requestDto.toEntity(encodedPassword));
        return user.getId();
    }

    public Boolean update(String email, UserUpdateRequestDto requestDto)
    {
        UserEntity user = userRepository.findByEmail(email).orElse(null);
        if (user == null)
            return false;

        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());
        user.update(requestDto.toEntity(encodedPassword));

        return true;
    }

    public Boolean withdraw(String email, String rawPassword)
    {
        UserEntity user = userRepository.findByEmail(email).orElse(null);
        if (user == null)
            return false;

        if (!passwordEncoder.matches(rawPassword, user.getPassword()))
            return false;

        userRepository.delete(user);
        return true;
    }

    public boolean checkDuplicateUser(String email)
    {
        UserEntity userEntity = userRepository.findByEmail(email).orElse(null);
        return userEntity != null;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        UserEntity userEntity = userRepository.findByEmail(email).orElse(null);
        if (userEntity == null)
            throw new UsernameNotFoundException(email);

        SessionDetails sessionDetails = new ModelMapper().map(userEntity, SessionDetails.class);
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("USER"));
        sessionDetails.setAuthorities(authorities);

        return sessionDetails;
    }
}
