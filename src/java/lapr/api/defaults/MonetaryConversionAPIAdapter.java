package lapr.api.defaults;

import lapr.api.MonetaryConversionAPI;

/**
 * The API adapter used to convert between monetary units.
 */
public class MonetaryConversionAPIAdapter implements MonetaryConversionAPI {

    /**
     * Converts currency.
     *
     * @param country the country that the currency will be converted.
     * @param euro the currency to convert.
     * @return the currency converted to country of the freelancer.
     */
    @Override
    public Double convert(String country, Double euro) {
        switch(country.trim().toUpperCase()) {
            case "AMERICA":
            case "UNITED STATES":
            case "UNITED STATES OF AMERICA":
            case "USA": return euro * 1.14;

            case "UK":
            case "UNITED KINGDOM": return euro * 0.89;

            case "BR":
            case "BRAZIL": return euro * 5.57;

            case "JP":
            case "JAPAN": return euro * 121.75;

            case "CH":
            case "CHINA": return euro * 8.02;

            default: return euro;
        }
    }

    @Override
    public void close() {
    }
}
