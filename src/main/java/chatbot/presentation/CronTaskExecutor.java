package chatbot.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

@RequiredArgsConstructor
public class CronTaskExecutor {
    private final TaskScheduler taskScheduler;

    public void setTask(Runnable task, String cronExp) {
        taskScheduler.schedule(task, new CronTrigger(cronExp));
    }
}
