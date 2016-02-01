package com.ggktech.grailsdemo

/**
 * Created by Srinivas.Rampelli on 2/1/2016.
 */
class FirstJob {
    static triggers = {
        cron name: 'FirstJob', group:"MyGroup" , cronExpression: "0 0/1 * 1/1 * ? *"
    }

    def execute() {
        log.info "----------------------------First Job---------------------------"
    }
}
