package com.mycompany.config;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SentinelConfig
 * @Deacription TODO  sentinel 使用 方法  申明 为 事件监听器  spring bean 加载 完后就运行这个ApplicationListener 监听器
 *  1 申明资源  在 controller 中 已申明
 *  2 定义规则
 * @Author apple
 * @Date 2020/7/21 21:01
 * @Version 1.0
 **/

// @Component
public class SentinelConfig_bak implements ApplicationListener<ContextRefreshedEvent> {

    //定义 规则
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        // 限流
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("createOrder");
       // set limit qps to 20
        rule.setCount(20);
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);

        // 熔断
        DegradeRule degradeRule = new DegradeRule();
        degradeRule.setResource("createOrder");
        degradeRule.setGrade(RuleConstant.DEGRADE_GRADE_RT);
        degradeRule.setCount(10);
        degradeRule.setTimeWindow(10);
        List<DegradeRule> degradeRules = new ArrayList<>();
        degradeRules.add(degradeRule);
        DegradeRuleManager.loadRules(degradeRules);






    }
}
