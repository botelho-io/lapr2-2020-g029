/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.strategies.htf.utils;

import csvparser.FileCSV;
import csvparser.LineException;
import csvparser.LineExceptionStack;
import lapr.list.ListTask;
import lapr.list.ListTransaction;
import lapr.model.*;
import lapr.regist.RegistFreelancer;
import lapr.strategies.htf.HistoricalTransactionFileStrategy;
import lapr.utils.Expertise;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

/**
 *
 * @author Universidade
 */
public class HistoricalTransactionFileStrategyCommonCSV implements HistoricalTransactionFileStrategy {

    final static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("dd-MM-yy");
    // Map columns to their name to allow easy modification of data placement within file
    final static HashMap<field, Integer> colIdx;
    static {
        colIdx = new HashMap<>();
        colIdx.put(field.TransID,                    0);
        colIdx.put(field.TaskID,                     1);
        colIdx.put(field.TaskDescription,            2);
        colIdx.put(field.TaskAssignDuration,         3);
        colIdx.put(field.TaskCostPerHour,            4);
        colIdx.put(field.TaskCategory,               5);
        colIdx.put(field.ExecutionFinishDate,        6);
        colIdx.put(field.ExecutionDelay,             7);
        colIdx.put(field.ExecutionBriefDescription,  8);
        colIdx.put(field.FreelancerID,               9);
        colIdx.put(field.FreelancerName,            10);
        colIdx.put(field.FreelancerExpertise,       11);
        colIdx.put(field.FreelancerEMail,           12);
        colIdx.put(field.FreelancerNIF,             13);
        colIdx.put(field.FreelancerBankAccount,     14);
        colIdx.put(field.FreelancerAddress,         15);
        colIdx.put(field.FreelancerCountry,         16);
    }

    FileCSV file;
    String path;
    final ArrayList<Transaction> allTransactions;
    final ArrayList<Integer> lineRead;
    final String colSeparator;

    public HistoricalTransactionFileStrategyCommonCSV(String colSeparator) {
        allTransactions = new ArrayList<>();
        lineRead = new ArrayList<>();
        this.colSeparator = colSeparator;
    }

    @Override
    public void openFile(final String path) throws IOException {
        this.path = path;
        file = new FileCSV(path, "\n", colSeparator, true, 17);
    }

    @Override
    public void loadData() throws LineExceptionStack {
        final LineExceptionStack exceptions = new LineExceptionStack();

        // Run trough lines of file
        // Each line should produce exactly one transaction unique transaction
        // Each line should produce exactly one unique task
        // Each line may produce one freelancer
        for (int l = 0; l < file.getHeight(); l++) {
            final List<String> line = file.get(l);

            // Read freelancer
            final String freelancerID           = line.get(colIdx.get(field.FreelancerID         ));
            final String freelancerName         = line.get(colIdx.get(field.FreelancerName       ));
            final String strFreelancerExpertise = line.get(colIdx.get(field.FreelancerExpertise  ));
            final String freelancerEMail        = line.get(colIdx.get(field.FreelancerEMail      ));
            final String freelancerNIF          = line.get(colIdx.get(field.FreelancerNIF        ));
            final String freelancerBankAccount  = line.get(colIdx.get(field.FreelancerBankAccount));
            final String freelancerAddress      = line.get(colIdx.get(field.FreelancerAddress    ));
            final String freelancerCountry      = line.get(colIdx.get(field.FreelancerCountry    ));
            final Expertise freelancerExpertise;
            try {
                freelancerExpertise = Expertise.valueOf(strFreelancerExpertise.trim().toUpperCase());
            } catch (Exception ex) {
                exceptions.push(new LineException(strFreelancerExpertise + " is not a valid expertise", path, l+1) );
                continue;
            }
            final Freelancer freelancer = new Freelancer(
                    freelancerID,
                    freelancerName,
                    freelancerExpertise,
                    freelancerEMail,
                    freelancerNIF,
                    freelancerBankAccount,
                    freelancerAddress,
                    freelancerCountry
            );

            // Read the task
            final String taskID                = line.get(colIdx.get(field.TaskID            ));
            final String taskDescription       = line.get(colIdx.get(field.TaskDescription   ));
            final String strTaskAssignDuration = line.get(colIdx.get(field.TaskAssignDuration));
            final String strTaskCostPerHour    = line.get(colIdx.get(field.TaskCostPerHour   ));
            final String taskCategory          = line.get(colIdx.get(field.TaskCategory      ));
            final double taskAssignDuration;
            final double taskCostPerHour;
            try {
                taskAssignDuration = Double.parseDouble(strTaskAssignDuration);
            } catch (NullPointerException | NumberFormatException e) {
                exceptions.push(new LineException(strTaskAssignDuration + " is not a valid duration", path, l+1) );
                continue;
            }
            try {
                taskCostPerHour = Double.parseDouble(strTaskCostPerHour);
            } catch (NullPointerException | NumberFormatException e) {
                exceptions.push(new LineException(strTaskCostPerHour + " is not a valid cost", path, l+1) );
                continue;
            }
            final Task task = new Task(
                    taskID,
                    taskDescription,
                    taskAssignDuration,
                    taskCostPerHour,
                    taskCategory,
                    freelancer
            );

            // Read the task execution details
            final String strExecutionFinishDate    = line.get(colIdx.get(field.ExecutionFinishDate       ));
            final String strExecutionDelay         = line.get(colIdx.get(field.ExecutionDelay            ));
            final String executionBriefDescription = line.get(colIdx.get(field.ExecutionBriefDescription ));
            final LocalDate executionFinishDate;
            final double executionDelay;
            try {
                executionFinishDate = LocalDate.parse(strExecutionFinishDate, timeFormatter);
            } catch (DateTimeParseException e) {
                exceptions.push(new LineException(strExecutionFinishDate + " is not a valid execution date", path, l+1) );
                continue;
            }
            try {
                executionDelay = Double.parseDouble(strExecutionDelay);
            } catch (NullPointerException | NumberFormatException e) {
                exceptions.push(new LineException(strExecutionDelay + " is not a valid execution delay", path, l+1) );
                continue;
            }
            final TaskExecutionDetails taskExecutionDetails = new TaskExecutionDetails(
                    executionFinishDate,
                    executionDelay,
                    executionBriefDescription
            );

            // Read the transaction
            final String transID = line.get(colIdx.get(field.TransID));
            final Transaction transaction = new Transaction(
                    transID,
                    freelancer,
                    task,
                    new PaymentDetails(),
                    taskExecutionDetails
            );

            // Store data
            this.allTransactions.add(transaction);
            this.lineRead.add(l+1);
        }
        if (!exceptions.empty())
            throw exceptions;
    }

    @Override
    public List<Transaction> getData() {
        return Collections.unmodifiableList(allTransactions);
    }

    @Override
    public void saveData(final RegistFreelancer rf, final ListTask ltk, final ListTransaction ltr) throws LineExceptionStack {
        final LineExceptionStack exceptions = new LineExceptionStack();

        for (int i = 0; i < allTransactions.size(); i++) {
            final Transaction transaction = allTransactions.get(i);

            // Task
            Task task = transaction.getTask();
            if(ltk.validatesTask(task))
                ltk.registTask(task);
            else {
                task = ltk.getEqualTask(task);
                if(task.getExecutor() != null) {
                    exceptions.push(new LineException("Task already exists in the system, and is executed", path, lineRead.get(i)));
                    continue;
                }
            }

            // Freelancer
            Freelancer freelancer = transaction.getFreelancer();
            if(rf.validate(freelancer))
                rf.addFreelancer(freelancer);
            else {
                freelancer = rf.getEqualFreelancer(freelancer);
                transaction.setFreelancer(freelancer);
            }

            // Transaction
            if(ltr.validate(transaction) && ltr.addTransaction(transaction)) {
                // All went ok
                task.setExecutor(freelancer);
                transaction.setFreelancer(freelancer);
                transaction.setTask(task);
            } else {
                // oops
                exceptions.push(new LineException("Transaction already exists in the system but the freelancer and/or task may have been added", path, lineRead.get(i)));
            }
        }

        if (!exceptions.empty())
            throw exceptions;
    }
}
