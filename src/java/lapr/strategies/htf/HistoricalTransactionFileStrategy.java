/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.strategies.htf;

import csvparser.LineExceptionStack;
import lapr.lists.ListTask;
import lapr.lists.ListTransaction;
import lapr.model.Transaction;
import lapr.lists.RegistFreelancer;

import java.io.IOException;
import java.util.List;

/**
 * Represents a historical transaction file strategy for loading files.
 * @author André Botelho and Ricardo Moreira.
 */
public interface HistoricalTransactionFileStrategy {
    void openFile(final String path) throws IOException;
    /**
     * Load's data from the file into memory.
     * @throws LineExceptionStack If any error is encountered while loading the data.
     */
    void loadData() throws LineExceptionStack;

    /**
     * Gets the data loaded from file. (must call loadData before)
     * @return The data loaded from the file.
     */
    List<Transaction> getData();

    /**
     * Save's data from the file into the system.
     * @param  rf freelancer to be register.
     * @param  ltk task to be register.
     * @param  ltr transaction to be register.
     * @throws LineExceptionStack If any error is encountered while loading the data.
     */
    void saveData(final RegistFreelancer rf, final ListTask ltk, final ListTransaction ltr) throws LineExceptionStack;
    enum field {
        TransID,
        TaskID,
        TaskDescription,
        TaskAssignDuration,
        TaskCostPerHour,
        TaskCategory,
        ExecutionFinishDate,
        ExecutionDelay,
        ExecutionBriefDescription,
        FreelancerID,
        FreelancerName,
        FreelancerExpertise,
        FreelancerEMail,
        FreelancerNIF,
        FreelancerBankAccount,
        FreelancerAddress,
        FreelancerCountry
    }
}
