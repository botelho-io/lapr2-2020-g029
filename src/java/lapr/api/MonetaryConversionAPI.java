package lapr.api;

/**
 * The API used to convert between monetary units.
 */
public interface MonetaryConversionAPI {
    Double convert(String country, Double euro);
}
