package lapr.controller;

import javafx.util.Pair;
import lapr.api.EmailAPI;
import lapr.model.*;
import lapr.regist.RegistOrganization;

import java.io.Serializable;
import java.util.*;

public class SendEmailTask extends TimerTask implements Serializable {
    /**
     * The email scheduler responsible for this task.
     */
    EmailScheduler m_oEmailScheduler;
    /**
     * Constructor.
     * @param emailScheduler The payment scheduler responsible for this task.
     */
    public SendEmailTask(EmailScheduler emailScheduler) {
        this.m_oEmailScheduler = emailScheduler;
    }
    /**
     * Called by tis tasks timer. Starts to send emails.
     */
    @Override
    public void run() {
        sendMessages(getMessages());
        m_oEmailScheduler.scheduleNextYear();
    }

    /**
     * All the freelancers with a mean task delay higher than MEAN_TASK_DELAY_MAXIMUM will be notified of their poor
     * performance.
     */
    private final static Double MEAN_TASK_DELAY_MAXIMUM = 3.0;

    /**
     * Sends emails with messages.
     * @param fre_msg A map that makes a freelancer correspond to its message or null to not send a message.
     */
    public static void sendMessages(Map<Freelancer, String> fre_msg){
        final EmailAPI email = AppPOE.getInstance().getApp().getEmailAPI();
        for(final Freelancer fre : fre_msg.keySet()) {
            final String message = fre_msg.get(fre);
            if(message != null) email.sendEmail(fre.getEmail(), message);
        }
    }

    /**
     * Gets the messages to be sent to the freelancers.
     * @return A map that makes a freelancer correspond to its message or null if there is nothing to send.
     */
    public static Map<Freelancer, String> getMessages() {
        final int CURREN_YEAR = Calendar.getInstance().get(Calendar.YEAR);
        // The regist of the organization
        final RegistOrganization ro = AppPOE.getInstance().getApp().getRegistOrganization();
        // Maps a freelancer to their executed transactions
        final Map<Freelancer, List<Transaction>> fre_trs = ro.getGroupedTransactionsInYear(CURREN_YEAR);
        // Maps a freelancer to [the average delay of the transaction; and the percentage of delayed transactions]
        final Map<Freelancer, Pair<Double, Double>> fre__ad_dt = new HashMap<>();

        // Overall statistical values
        int numberTransactions = 0;
        int numberDelayedTransactions = 0;

        // Calculate overall statistical values
        for(final Freelancer fre : fre_trs.keySet()) {
            // statistics for the freelancer
            final int transactions;
            int delayDays = 0;
            int delayedTransactions = 0;

            // Calculate freelancer statistics
            final List<Transaction> transactionList = fre_trs.get(fre);
            for(final Transaction trs : transactionList) {
                final int delay = trs.getExecutionDetails().getHoursDelay();
                delayDays += delay;
                if(delay > 0) delayedTransactions++;
            }
            transactions = transactionList.size();
            // save those values
            fre__ad_dt.put(fre, new Pair<>((double) delayDays / transactions, (double) delayedTransactions / transactions));

            // Calculate statistics for the overall values
            numberTransactions += transactions;
            numberDelayedTransactions += delayedTransactions;
        }

        // Make decisions on which freelancer to send e-mails to
        final Map<Freelancer, String> toReturn = new HashMap<>();

        final double percentageDelayedTransactions = (double) numberDelayedTransactions / numberTransactions;
        for(final Freelancer fre: fre__ad_dt.keySet()) {
            final Pair<Double, Double> pair = fre__ad_dt.get(fre);
            final double avgDelay = pair.getKey();
            final double pctDelayed = pair.getValue();
            String msg = "";
            if(avgDelay > MEAN_TASK_DELAY_MAXIMUM) {
                msg += String.format("we have noticed that you have a mean task delay higher than %f hours (%fhr), try to keep up! ", MEAN_TASK_DELAY_MAXIMUM, avgDelay);
            }
            if(pctDelayed > percentageDelayedTransactions) {
                msg += String.format("%s you have a percentage of delayed tasks higher than %d%% (%d%%), meaning that you are falling behind the average. ",(msg.isEmpty()?"we have noticed that":"Furthermore,"), (int)(percentageDelayedTransactions*100), (int)(pctDelayed*100));
            }
            if(!msg.isEmpty()) {
                msg = String.format("Hello there %s,\nThis e-mail is being sent to notify you of your poor performance as of %d.\nNamely, %s\n\nBest regards,\nThe T4J team.\n", fre.getName(), CURREN_YEAR, msg);
                toReturn.put(fre, msg);
            }
        }

        return toReturn;
    }
}
