import com.cyber.blockchain.quartz.MusicJob;
import com.cyber.blockchain.quartz.QuartzManager;
import org.junit.Test;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import java.util.HashMap;
import java.util.Map;

public class JobTest {

    @Test
    public void test() throws Exception {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        QuartzManager quartzManager = new QuartzManager(scheduler);

        // 每1000毫秒执行一次 重复执行3次，共执行4次
        Map<String, Object> jobData = new HashMap<String, Object>();
        jobData.put("x", "1");
        quartzManager.addJob("myJob", "test", MusicJob.class, jobData, 1000L, 3);
        quartzManager.startScheduler();

        while (Thread.activeCount() > 0)
            Thread.yield();
    }

    @Test
    public void testModify() throws Exception {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        QuartzManager quartzManager = new QuartzManager(scheduler);

        Map<String, Object> jobData = new HashMap<String, Object>();
        jobData.put("x", "666");
        quartzManager.addJob("myJob", "test", MusicJob.class, jobData, "0/5 * * * * ?");
        quartzManager.startScheduler();

        Thread.sleep(5000);

        for (int i = 0; Thread.activeCount() > 0; i++) {
            if (i % 3 == 0) {
                Map<String, Object> jd = new HashMap<String, Object>();
                jd.put("x", String.valueOf(i));
                quartzManager.modifyJob("myJob", "test", jd);
            }
            Thread.sleep(1000);
        }
    }
}
