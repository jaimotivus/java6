package moti.servlet3example.service;

/**
 * A component that provide init and destroy lifecycle in an application.
 * 
 * @author zedeng
 *
 * // No changes needed for Java 21 upgrade - this interface is already compatible
 */
public interface Service {
	void init();
	void destroy();
}