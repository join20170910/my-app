/*
package com.mycompany.app.service;

import com.mycompany.app.bean.cal.Calculation;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

*/
/**

 * 调用规则引擎，执行规则

 *//*


@Service

public class RuleService<T> {

    @Autowired private KieSession session;


    //个人所得税计算

    public T calculate(T calculation){

        session.insert(calculation);

        session.fireAllRules();

        session.dispose();

        return calculation;

    }

}
*/
