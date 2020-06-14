/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.strategies.htf;

import lapr.strategies.htf.utils.HistoricalTransactionFileStrategyCommonCSV;

/**
 * Represents a historical transaction file strategy for loading csv files with col separated by ";".
 * @author André Botelho and Ricardo Moreira.
 */
public class CSVTransactionFileStrategy extends HistoricalTransactionFileStrategyCommonCSV {
    public CSVTransactionFileStrategy() {
        super(";");
    }
}
