package com.zhyshko.service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhyshko.dto.AuthenticationResponse;
import com.zhyshko.dto.LoginRequest;
import com.zhyshko.dto.RefreshTokenRequest;
import com.zhyshko.dto.RegisterRequest;
import com.zhyshko.dto.UserToken;
import com.zhyshko.model.NotificationEmail;
import com.zhyshko.model.User;
import com.zhyshko.model.VerificationToken;
import com.zhyshko.repository.UserRepository;
import com.zhyshko.repository.VerificationTokenRepository;
import com.zhyshko.security.JwtProvider;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {

	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;
	private final VerificationTokenRepository verificationTokenRepository;
	private final MailService mailService;
	private final AuthenticationManager authenticationManager;
	private final JwtProvider jwtProvider;
	private final RefreshTokenService refreshTokenService;
	
	@Transactional
	public void verifyAccount(String token) {
		String username = verificationTokenRepository.findByToken(token).orElseThrow(()->new NullPointerException("Token optional has no value")).getUser().getUsername();
		User user = userRepository.findByUsername(username).orElseThrow(()->new NullPointerException("User optional has no value"));
		user.setEnabled(true);
		userRepository.save(user);
	}

	@Transactional
	public UserToken signup(RegisterRequest registerRequest) throws Exception {
		User user = User.builder().username(registerRequest.getUsername()).email(registerRequest.getEmail())
				.enabled(false).password(passwordEncoder.encode(registerRequest.getPassword())).build();
		userRepository.save(user);
		String token = generateVerificationToken(user);
		return UserToken.builder()
				.email(user.getEmail())
				.token(token)
				.build();

	}

	public void sendMail(UserToken credentials) {
		mailService.sendMail(new NotificationEmail("Account activation", credentials.getEmail(),
						"Click this link to activate your account:http://localhost:8080/api/auth/accountVerification/" + credentials.getToken()));

	}
	
	private String generateVerificationToken(User user) {
		String token = UUID.randomUUID().toString();
		VerificationToken verificationToken = new VerificationToken();
		verificationToken.setToken(token);
		verificationToken.setUser(user);
		verificationTokenRepository.save(verificationToken);
		return token;
	}
	
	public AuthenticationResponse login(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtProvider.generateToken(authentication);
		return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .username(loginRequest.getUsername())
                .build();
	}
	
	public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
        String token = jwtProvider.generateTokenWithUserName(refreshTokenRequest.getUsername());
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenRequest.getRefreshToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .username(refreshTokenRequest.getUsername())
                .build();
    }

    public boolean isLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
    }

}
