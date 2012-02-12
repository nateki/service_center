package notifiers;

import play.*;
import play.mvc.*;
import java.util.*;
import models.*;
import javax.mail.internet.*;

public class Notifier extends Mailer {

    public static boolean welcome(WorkUpdate workUpdate) throws Exception {

        //setFrom("Me <me@me.com>");
        setFrom(new InternetAddress("admin@servicecenter.com", "Administrator"));
        setReplyTo(new InternetAddress("help@servicecenter.com", "Help"));
        setSubject("Welcome %s", workUpdate.customer.fullname);
        addRecipient(workUpdate.customer.email);
        return sendAndWait(workUpdate);
    }
}
