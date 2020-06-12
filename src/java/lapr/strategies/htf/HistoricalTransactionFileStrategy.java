/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.strategies.htf;

import csvparser.LineException;
import csvparser.LineExceptionStack;
import lapr.list.ListTask;
import lapr.list.ListTransaction;
import lapr.model.Transaction;
import lapr.regist.RegistFreelancer;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author Universidade
 */
public interface HistoricalTransactionFileStrategy {
    void openFile(final String path) throws IOException;
    void loadData() throws LineExceptionStack;
    List<Transaction> getData();
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
