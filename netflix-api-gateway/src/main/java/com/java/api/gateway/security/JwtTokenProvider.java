package com.java.api.gateway.security;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.java.api.gateway.repository.JwtTokenRepository;
import com.java.api.gateway.service.CustomUserDetails;
import com.java.api.gateway.bean.JwtToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {
	private static final String AUTH = "auth";
	private static final String AUTHORIZATION = "Authorization";
	private String secretKey = "secret-key";
	private long validityInMilliseconds = 3600000; // 1h

	@Autowired
	private JwtTokenRepository jwtTokenRepository;

	@Autowired
	private UserDetailsService userDetailsService;

	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}

	public String createToken(String username, List<String> roles) {
		Claims claims = Jwts.claims().setSubject(username);
		claims.put(AUTH, roles);

		Date date = new Date();
		Date validity = new Date(date.getTime() + validityInMilliseconds);

		String token = Jwts.builder().setClaims(claims).setIssuedAt(date).setExpiration(validity)
				.signWith(SignatureAlgorithm.HS256, secretKey).compact();

		jwtTokenRepository.save(new JwtToken(token));
		return token;
	}

	public String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader(AUTHORIZATION);

		if (bearerToken != null) {
			return bearerToken;
		}
		return null;
	}

	public boolean validateToken(String token) throws JwtException, IllegalArgumentException {
		Jws<Claims> parseClaimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);

		Date expiration = parseClaimsJws.getBody().getExpiration();
		int compareTo = expiration.compareTo(new Date());
		if (compareTo < 0) {
			System.out.println("Token is expired : " + expiration);
		} else {
			System.out.println("Token is valid not at expired " + expiration);
		}
		return true;
	}

	public boolean isTokenPresentInDB(String token) {
		return jwtTokenRepository.findById(token).isPresent();
	}

	// user details with out database hit
	public UserDetails getUserDetails(String token) {
		String userName = getUsername(token);
		List<String> roleList = getRoleList(token);
		UserDetails userDetails = new CustomUserDetails(userName, roleList.toArray(new String[roleList.size()]));
		return userDetails;
	}

	public List<String> getRoleList(String token) {
		return (List<String>) Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get(AUTH);
	}

	public String getUsername(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
	}

	public Authentication getAuthentication(String token) {
		// using data base: uncomment when you want to fetch data from data base
		// UserDetails userDetails =
		// userDetailsService.loadUserByUsername(getUsername(token));
		// from token take user value. comment below line for changing it taking
		// from data base
		UserDetails userDetails = getUserDetails(token);
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}

}
