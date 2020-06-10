package lapr.controller;

import lapr.model.App;
import lapr.model.Freelancer;
import lapr.model.Transaction;
import lapr.regist.RegistFreelancer;
import lapr.regist.RegistOrganization;
import org.apache.commons.math3.distribution.NormalDistribution;

import java.util.*;

public class AdministratorStatisticsController {
    final App app;
    Collection<Transaction> trs;

    /**
     * Constructor.
     */
    public AdministratorStatisticsController() {
        app = AppPOE.getInstance().getApp();
    }

    /**
     * Get freelancers in the system.
     * @return All the freelancers in the system.
     */
    public Collection<Freelancer> getFreelancers() {
        return app.getRegistFreelancer().getFreelancers();
    }

    /**
     * Should be called before any of the other functions, besides getFreelancers.
     * Selects the freelancers whose transactions to analyse.
     * @param selected The freelancers whose transactions we want to analyse.
     * @return True if there are transactions in the list, false otherwise.
     */
    public boolean setFreelancers(final Set<Freelancer> selected) {
        trs = app.getRegistOrganization().getTransactionsOfFreelancers(selected);
        return !trs.isEmpty();
    }

    /**
     * @return The mean delay of the selected transactions.
     */
    public double getMeanDelays() {
        int acc = 0;
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
        final double mean = getMeanDelays();
        double acc = 0;
        for(final Transaction t : trs)
            acc += Math.pow(t.getExecutionDetails().getHoursDelay() - mean, 2);
        return Math.sqrt(acc/(getNumberTransactions()-1));
    }

    /**
     * @return The standard deviation of payments of the selected transactions.
     */
    public double getStandardDeviationPayments() {
        final double mean = getMeanPayments();
        double acc = 0;
        for(final Transaction t : trs)
            acc += Math.pow(t.getAmount() - mean, 2);
        return Math.sqrt(acc/(getNumberTransactions()-1));
    }

    /**
     * Get data for a histogram of the delays of the task executions.
     * @param bucketSize The size (in hours) of the buckets.
     * @return A map that makes the index of the bucket correspond to it's "height".
     */
    public Map<Integer, Integer> getHistogramDataDelays(double bucketSize) {
        final HashMap<Integer, Integer> map = new HashMap<>();
        for(final Transaction t : trs) {
            final int delay = t.getExecutionDetails().getHoursDelay();
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

    /**
     * Calculates the probability that the mean delay is less than the specified value.
     * @param value The value in hours to check the probability against.
     * @return The probability that the mean delay is less than the specified value.
     */
    public double getProbabilityMeanDelayLessThan(double value) {
        final double mean = 2;
        final double standardDeviation = 1.5;
        final double number = getNumberTransactions();
        final NormalDistribution p = new NormalDistribution(mean,standardDeviation/number);
        return 1.0 - p.cumulativeProbability(value);
    }
}
