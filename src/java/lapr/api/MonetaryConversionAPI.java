package lapr.api;

import java.io.Serializable;

/**
 * The API used to convert between monetary units.
 */
public interface MonetaryConversionAPI extends Serializable {
    Double convert(String country, Double euro);
}
