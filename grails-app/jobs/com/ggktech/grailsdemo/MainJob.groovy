package com.ggktech.grailsdemo

import grailsschedulerdemo.MyCronScheduler
import org.quartz.CronTrigger
import org.quartz.JobKey
import org.quartz.Trigger
import org.quartz.impl.matchers.GroupMatcher
import org.quartz.impl.triggers.CronTriggerImpl


/**
 * Created by Srinivas.Rampelli on 1/7/2016.
 */
class MainJob {
    def quartzScheduler

    static triggers = {
        cron name: 'mainTrigger', cronExpression: "0/10 0/1 * 1/1 * ? *"
    }

    def group = "MyGroup"

    def execute() {
        MyCronScheduler.findAll().each { it ->
            JobKey jobKey = new JobKey( it.jobName, "MyGroup")
            Trigger trigger = new CronTriggerImpl(it.jobName, "MyGroup")
            trigger.setJobKey(jobKey)
            if (quartzScheduler.checkExists(trigger.getKey())) {
                Trigger existingTrigger = quartzScheduler.getTrigger(trigger.getKey())
                if (existingTrigger != null) {
                    CronTrigger cronTrigger = (CronTrigger) existingTrigger
                    String cronExpr = cronTrigger.getCronExpression()
                    if (cronExpr != it.cronExp) {
                        trigger.cronExpression = it.cronExp
                        quartzScheduler.rescheduleJob(trigger.getKey(), trigger)
                        log.info "rescheduled job.."+it.jobName
                    }
                }
            } else {
                log.info "creating job..."
                trigger.cronExpression = it.cronExp
                quartzScheduler.scheduleJob(trigger)
            }
        }
    }
}
