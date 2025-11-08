package xrayfa.tun2socks.qualifier;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

/**
 * This annotation indicates that the thread or coroutine described is related to background work.
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface Background {
}
