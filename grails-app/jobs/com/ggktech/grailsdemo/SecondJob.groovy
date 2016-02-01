package com.ggktech.grailsdemo

/**
 * Created by Srinivas.Rampelli on 2/1/2016.
 */
class SecondJob {
    static triggers = {
        cron name: 'SecondJob', group:"MyGroup", cronExpression: "0 0/1 * 1/1 * ? *"
    }

    def execute() {
        log.info "----------------------------Second Job---------------------------"
    }
}
