package help.lixin.jenkins.schedule;

import com.cdancy.jenkins.rest.domain.crumb.Crumb;
import help.lixin.jenkins.properties.JenkinsProperties;
import help.lixin.jenkins.service.ICrumbIssuerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class CrumbSchedule {
    private Logger logger = LoggerFactory.getLogger(CrumbSchedule.class);

    private ICrumbIssuerService crumbIssuerService;
    private JenkinsProperties jenkinsProperties;

    public CrumbSchedule(JenkinsProperties jenkinsProperties, ICrumbIssuerService crumbIssuerService) {
        this.jenkinsProperties = jenkinsProperties;
        this.crumbIssuerService = crumbIssuerService;

        Thread crumbScheduleThread = new CrumbScheduleThread();
        crumbScheduleThread.setName("jenkins-curmb-thread");
        crumbScheduleThread.setDaemon(true);
        crumbScheduleThread.start();
    }


    class CrumbScheduleThread extends Thread {
        @Override
        public void run() {
            while (true) {
                Crumb crumb = crumbIssuerService.crumb();
                if (crumb.errors().size() > 0) { // FAIL
                    // LOGGER ERROR
                    logger.warn("jenkins crumb ERROR:[{}]", crumb.errors());
                } else { // SUCCESS
                    String value = crumb.value();
                    String s = crumb.sessionIdCookie();
                    // LOGGER SUCCESS
                    logger.info("jenkins crumb SUCCESS,value:[{}],cookie:[{}]", value, s);
                }
                try {
                    TimeUnit.MINUTES.sleep(jenkinsProperties.getCrumbRefreshinterval());
                } catch (InterruptedException ignore) {
                }
            }
        }
    }
}
