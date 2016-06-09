package br.com.carmaix.exceptions;

/**
 * The Class CacheException.
 */
public class CacheException extends Exception {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new cache exception.
     * 
     * @param message
     *            the message
     */
    public CacheException(String message) {
        super(message);
    }

    /**
     * Instantiates a new cache exception.
     * 
     * @param t
     *            the t
     */
    public CacheException(Throwable t) {
        super(t);
    }

    /**
     * Instantiates a new cache exception.
     * 
     * @param message
     *            the message
     * @param t
     *            the t
     */
    public CacheException(String message, Throwable t) {
        super(message, t);
    }

}
