package co.codesharp.jwampsharp.api.rx;

import java.util.Map;

/**
 * Created by Elad on 7/11/2014.
 */
public interface WampEvent {
    Map<String, Object> getDetails();
    Object[] getArguments();
    Map<String, Object> getArgumentsKeywords();
}
