package org.seusl.dto.email;


/**
 * @author Dulari Ranaweera
 *
 */
public interface EmailConfiguration {

	void sendEmail(String to, String subjects);
	
}
