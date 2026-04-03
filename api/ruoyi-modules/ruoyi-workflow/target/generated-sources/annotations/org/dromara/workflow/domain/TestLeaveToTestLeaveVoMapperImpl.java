package org.dromara.workflow.domain;

import javax.annotation.processing.Generated;
import org.dromara.workflow.domain.vo.TestLeaveVo;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-13T13:36:08+0800",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.43.0.v20250819-1513, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class TestLeaveToTestLeaveVoMapperImpl implements TestLeaveToTestLeaveVoMapper {

    @Override
    public TestLeaveVo convert(TestLeave arg0) {
        if ( arg0 == null ) {
            return null;
        }

        TestLeaveVo testLeaveVo = new TestLeaveVo();

        testLeaveVo.setEndDate( arg0.getEndDate() );
        testLeaveVo.setId( arg0.getId() );
        testLeaveVo.setLeaveDays( arg0.getLeaveDays() );
        testLeaveVo.setLeaveType( arg0.getLeaveType() );
        testLeaveVo.setRemark( arg0.getRemark() );
        testLeaveVo.setStartDate( arg0.getStartDate() );
        testLeaveVo.setStatus( arg0.getStatus() );

        return testLeaveVo;
    }

    @Override
    public TestLeaveVo convert(TestLeave arg0, TestLeaveVo arg1) {
        if ( arg0 == null ) {
            return arg1;
        }

        arg1.setEndDate( arg0.getEndDate() );
        arg1.setId( arg0.getId() );
        arg1.setLeaveDays( arg0.getLeaveDays() );
        arg1.setLeaveType( arg0.getLeaveType() );
        arg1.setRemark( arg0.getRemark() );
        arg1.setStartDate( arg0.getStartDate() );
        arg1.setStatus( arg0.getStatus() );

        return arg1;
    }
}
