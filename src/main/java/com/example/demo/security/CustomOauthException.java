package com.example.demo.security;

import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.InvalidRequestException;
import org.springframework.security.oauth2.common.exceptions.InvalidScopeException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.common.exceptions.RedirectMismatchException;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedClientException;
import org.springframework.security.oauth2.common.exceptions.UnsupportedGrantTypeException;
import org.springframework.security.oauth2.common.exceptions.UnsupportedResponseTypeException;
import org.springframework.security.oauth2.common.exceptions.UserDeniedAuthorizationException;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = CustomOauthExceptionSerializer.class)
public class CustomOauthException extends OAuth2Exception {
	
	public static final String ACCOUNT_LOCKED = "account_locked";
	public static final String ACCOUNT_DISABLED = "account_disabled";
	public static final String ACCOUNT_EXPIRED = "account_expired";
	public static final String ACCOUNT_CREDENTIALS_EXPIRED = "account_credentials_expired";
	
	public static final String USER_NOT_FOUND = "user_not_found";
	
	
	public static int errorCode = 4000;
	public static final int ERROR_INVALID_CLIENT = 4001;
	public static final int ERROR_UNAUTHORIZED_CLIENT = 4002;
	public static final int ERROR_INVALID_GRANT = 4003;
	public static final int ERROR_INVALID_SCOPE = 4004;
	public static final int ERROR_INVALID_TOKEN = 4005;
	public static final int ERROR_INVALID_REQUEST = 4006;
	public static final int ERROR_REDIRECT_URI_MISMATCH = 4007;
	public static final int ERROR_UNSUPPORTED_GRANT_TYPE = 4008;
	public static final int ERROR_UNSUPPORTED_RESPONSE_TYPE = 4009;
	public static final int ERROR_ACCESS_DENIED = 4010;
	public static final int ERROR_ACCOUNT_LOCKED = 4011;
	public static final int ERROR_ACCOUNT_DISABLED = 4012;
	public static final int ERROR_ACCOUNT_EXPIRED = 4013;
	public static final int ERROR_CREDENTIALS_EXPIRED = 4014;
	
	public static final int ERROR_USER_NOT_FOUND = 4015;
	
	
    public CustomOauthException(String msg) {
        super(msg);
    }
    
    /**
	 * The HTTP error code associated with this error.
	 * 
	 * @return The HTTP error code associated with this error.
	 */
	public int getHttpErrorCode() {
		return errorCode;
	}
	
	/**
	 * Creates the appropriate subclass of OAuth2Exception given the errorCode.
	 * @param errorCode
	 * @param errorMessage
	 * @return
	 */
	public static OAuth2Exception create(String errorCode, String errorMessage) {
		if (errorMessage == null) {
			errorMessage = errorCode == null ? "OAuth Error" : errorCode;
		}
		if (OAuth2Exception.INVALID_CLIENT.equals(errorCode)) {
			CustomOauthException.errorCode = ERROR_INVALID_CLIENT;
			return new InvalidClientException(errorMessage);
		}
		else if (OAuth2Exception.UNAUTHORIZED_CLIENT.equals(errorCode)) {
			CustomOauthException.errorCode = ERROR_UNAUTHORIZED_CLIENT;
			return new UnauthorizedClientException(errorMessage);
		}
		else if (OAuth2Exception.INVALID_GRANT.equals(errorCode)) {
			CustomOauthException.errorCode = ERROR_INVALID_GRANT;
			return new InvalidGrantException(errorMessage);
		}
		else if (OAuth2Exception.INVALID_SCOPE.equals(errorCode)) {
			CustomOauthException.errorCode = ERROR_INVALID_SCOPE;
			return new InvalidScopeException(errorMessage);
		}
		else if (OAuth2Exception.INVALID_TOKEN.equals(errorCode)) {
			CustomOauthException.errorCode = ERROR_INVALID_TOKEN;
			return new InvalidTokenException(errorMessage);
		}
		else if (OAuth2Exception.INVALID_REQUEST.equals(errorCode)) {
			CustomOauthException.errorCode = ERROR_INVALID_REQUEST;
			return new InvalidRequestException(errorMessage);
		}
		else if (OAuth2Exception.REDIRECT_URI_MISMATCH.equals(errorCode)) {
			CustomOauthException.errorCode = ERROR_REDIRECT_URI_MISMATCH;
			return new RedirectMismatchException(errorMessage);
		}
		else if (OAuth2Exception.UNSUPPORTED_GRANT_TYPE.equals(errorCode)) {
			CustomOauthException.errorCode = ERROR_UNSUPPORTED_GRANT_TYPE;
			return new UnsupportedGrantTypeException(errorMessage);
		}
		else if (OAuth2Exception.UNSUPPORTED_RESPONSE_TYPE.equals(errorCode)) {
			CustomOauthException.errorCode = ERROR_UNSUPPORTED_RESPONSE_TYPE;
			return new UnsupportedResponseTypeException(errorMessage);
		}
		else if (OAuth2Exception.ACCESS_DENIED.equals(errorCode)) {
			CustomOauthException.errorCode = ERROR_ACCESS_DENIED;
			return new UserDeniedAuthorizationException(errorMessage);
		}else if (ACCOUNT_LOCKED.equals(errorCode)) {
			CustomOauthException.errorCode = ERROR_ACCOUNT_LOCKED;
			return new UserDeniedAuthorizationException(errorMessage);
		}else if (ACCOUNT_EXPIRED.equals(errorCode)) {
			CustomOauthException.errorCode = ERROR_ACCOUNT_EXPIRED;
			return new UserDeniedAuthorizationException(errorMessage);
		}else if (ACCOUNT_DISABLED.equals(errorCode)) {
			CustomOauthException.errorCode = ERROR_ACCOUNT_DISABLED;
			return new UserDeniedAuthorizationException(errorMessage);
		}else if (ACCOUNT_CREDENTIALS_EXPIRED.equals(errorCode)) {
			CustomOauthException.errorCode = ERROR_CREDENTIALS_EXPIRED;
			return new UserDeniedAuthorizationException(errorMessage);
		}else if (USER_NOT_FOUND.equals(errorCode)) {
			CustomOauthException.errorCode = ERROR_USER_NOT_FOUND;
			return new UserDeniedAuthorizationException(errorMessage);
		}
		else {
			return new OAuth2Exception(errorMessage);
		}
	}
}