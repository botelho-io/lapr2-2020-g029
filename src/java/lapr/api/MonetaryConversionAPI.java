package lapr.api;

import java.io.Closeable;
import java.io.Serializable;

/**
 * The API used to convert between monetary units.
 */
public interface MonetaryConversionAPI extends Serializable, Closeable {
    Double convert(String country, Double euro);
}
