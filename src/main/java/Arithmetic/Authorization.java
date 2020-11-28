package Arithmetic;

/**
 * 
 * @author Julien Raynal
 * 
 * Enumeration to operate on different value of instructions.
 * If the new action can be merged with the previous one, then return STICK.
 * If the new action can be add to the instructions list, then return ALLOWED.
 * If the new action is not allowed, then return FORBIDDEN.
 *
 */
public enum Authorization {
	FORBIDDEN,
	ALLOWED,
	STICK
}
