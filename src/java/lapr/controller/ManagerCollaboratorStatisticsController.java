package lapr.controller;

import lapr.model.*;
import lapr.model.Transaction;

import java.util.*;


import java.util.Collection;

public class ManagerCollaboratorStatisticsController {
    final App app;
    private final Organization org;
    Collection<Transaction> trs;

    /**
     * Constructor.
     */
    public ManagerCollaboratorStatisticsController() {
        app = AppPOE.getInstance().getApp();
        org = app.getRegistOrganization().getOrganizationByEmailUser(app.getAuthFacade().getCurrentSession().getEmailUser());
    }

    /**
     * Get freelancers in the system.
     * @return All the freelancers in the system.
     */
    public Collection<Freelancer> getFreelancers() {
        return org.getListTransaction().getFreelancersOfAllTransactions();
    }

    /**
     * Should be called before any of the other functions, besides getFreelancers.
     * Selects the freelancers whose transactions to analyse.
     * @param selected The freelancers whose transactions we want to analyse.
     * @return True if there are transactions in the list, false otherwise.
     */
    public boolean setFreelancers(final Set<Freelancer> selected) {
        trs = org.getListTransaction().getTransactionsOfFreelancers(selected);
        return !trs.isEmpty();
    }
    /**
     * @return The mean delay of the selected transactions.
     */
    public double getMeanDelays() {
        double acc = 0;
        for(final Transaction t : trs)
            acc += t.getExecutionDetails().getHoursDelay();
        return ((double) acc) / getNumberTransactions();
    }

    /**
     * @return The mean payments of the selected transactions.
     */
    public double getMeanPayments() {
        int acc = 0;
        for(final Transaction t : trs)
            acc += t.getAmount();
        return ((double) acc) / getNumberTransactions();
    }

    /**
     * @return The standard deviation of delays of the selected transactions.
     */
    public double getStandardDeviationDelays() {
        final double n = getNumberTransactions();
        if(n < 2) return 0;
        final double mean = getMeanDelays();
        double acc = 0;
        for(final Transaction t : trs)
            acc += Math.pow(t.getExecutionDetails().getHoursDelay() - mean, 2);
        return Math.sqrt(acc/(n-1));
    }

    /**
     * @return The standard deviation of payments of the selected transactions.
     */
    public double getStandardDeviationPayments() {
        final double n = getNumberTransactions();
        if(n < 2) return 0;
        final double mean = getMeanPayments();
        double acc = 0;
        for(final Transaction t : trs)
            acc += Math.pow(t.getAmount() - mean, 2);
        return Math.sqrt(acc/(n-1));
    }

    /**
     * Get data for a histogram of the delays of the task executions.
     * @param bucketSize The size (in hours) of the buckets.
     * @return A map that makes the index of the bucket correspond to it's "height".
     */
    public Map<Integer, Integer> getHistogramDataDelays(double bucketSize) {
        final HashMap<Integer, Integer> map = new HashMap<>();
        for(final Transaction t : trs) {
            final double delay = t.getExecutionDetails().getHoursDelay();
            final int bucket = (int) (delay / bucketSize);
            if(!map.containsKey(bucket))
                map.put(bucket, 1);
            else map.put(bucket, map.get(bucket)+1);
        }
        return map;
    }

    /**
     * Get data for a histogram of the payments (in euros) of the tasks.
     * @param bucketSize The size (in euros) of the buckets.
     * @return A map that makes the index of the bucket correspond to it's "height".
     */
    public Map<Integer, Integer> getHistogramDataPayments(double bucketSize) {
        final HashMap<Integer, Integer> map = new HashMap<>();
        for(final Transaction t : trs) {
            final double payment = t.getAmount();
            final int bucket = (int) (((int) payment) / bucketSize);
            if(!map.containsKey(bucket))
                map.put(bucket, 1);
            else map.put(bucket, map.get(bucket)+1);
        }
        return map;
    }

    /**
     * @return The number of the transactions selected.
     */
    public double getNumberTransactions() {
        return trs.size();
    }

    public Double getPaymentOf(final Freelancer f) {
        double acc = 0;
        for(final Transaction t: trs)
            if(t.getFreelancer() == f)
                acc += t.getAmount();
        return acc;
    }
}

