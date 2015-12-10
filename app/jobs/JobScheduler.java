package jobs;

import play.libs.Akka;
import scala.concurrent.ExecutionContext;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

/**
 * Created by sasinda on 12/7/15.
 */
public class JobScheduler {

    public final static ExecutionContext ctx = Akka.system().dispatchers().lookup("akka.my-dispatcher");

    public static void init(){
        Akka.system().scheduler().schedule(
                Duration.create(0, TimeUnit.DAYS),
                Duration.create(1, TimeUnit.DAYS),
                new AdCleanerJob(),ctx
        );

        Akka.system().scheduler().schedule(
                Duration.create(0, TimeUnit.MINUTES),
                Duration.create(1, TimeUnit.MINUTES),
                new NotificationJob(),ctx
        );
    }
}
