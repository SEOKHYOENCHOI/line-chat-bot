package chatbot.config;

import chatbot.presentation.CronExpressions;
import chatbot.presentation.MessageHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.CronTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
@EnableAsync
@EnableScheduling
@RequiredArgsConstructor
public class SchedulingConfigurerConfiguration implements SchedulingConfigurer, CommandLineRunner {
    private final MessageHandler messageHandler;

    @Bean
    public Executor configureTasks() {
        return Executors.newScheduledThreadPool(5);
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.afterPropertiesSet();
    }

    @Override
    public void run(String... args) throws Exception {
        ScheduledTaskRegistrar taskRegistrar = new ScheduledTaskRegistrar();
        taskRegistrar.addCronTask(new CronTask(() -> messageHandler.notify(MessageHandler.START_OF_WORK_MESSAGE), CronExpressions.MON_12_55.getExpression()));
        taskRegistrar.addCronTask(new CronTask(() -> messageHandler.notify(MessageHandler.START_OF_WORK_MESSAGE), CronExpressions.TUE_FRI_09_25.getExpression()));
        taskRegistrar.addCronTask(new CronTask(() -> messageHandler.notify(MessageHandler.END_OF_WORK_MESSAGE), CronExpressions.MON_FRI_17_59.getExpression()));
        configureTasks(taskRegistrar);
    }
}