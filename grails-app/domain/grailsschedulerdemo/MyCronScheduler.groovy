package grailsschedulerdemo

class MyCronScheduler {
    String jobName
    String jobGroup
    String cronExp
    static mapping = {
        id generator:'identity', column: 'id'
        jobName column: 'job_name'
        cronExp column: 'cron_exp'
        jobGroup column: 'job_group'
    }

    static constraints = {
    }
}
