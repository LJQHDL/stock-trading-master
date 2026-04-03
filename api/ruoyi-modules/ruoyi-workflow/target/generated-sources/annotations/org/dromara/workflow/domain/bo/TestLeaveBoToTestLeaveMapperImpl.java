package org.dromara.workflow.domain.bo;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.processing.Generated;
import org.dromara.workflow.domain.TestLeave;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-04T00:25:46+0800",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.45.0.v20260224-0835, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class TestLeaveBoToTestLeaveMapperImpl implements TestLeaveBoToTestLeaveMapper {

    @Override
    public TestLeave convert(TestLeaveBo arg0) {
        if ( arg0 == null ) {
            return null;
        }

        TestLeave testLeave = new TestLeave();

        testLeave.setSearchValue( arg0.getSearchValue() );
        testLeave.setCreateDept( arg0.getCreateDept() );
        testLeave.setCreateBy( arg0.getCreateBy() );
        testLeave.setCreateTime( arg0.getCreateTime() );
        testLeave.setUpdateBy( arg0.getUpdateBy() );
        testLeave.setUpdateTime( arg0.getUpdateTime() );
        Map<String, Object> map = arg0.getParams();
        if ( map != null ) {
            testLeave.setParams( new LinkedHashMap<String, Object>( map ) );
        }
        testLeave.setEndDate( arg0.getEndDate() );
        testLeave.setId( arg0.getId() );
        testLeave.setLeaveDays( arg0.getLeaveDays() );
        testLeave.setLeaveType( arg0.getLeaveType() );
        testLeave.setRemark( arg0.getRemark() );
        testLeave.setStartDate( arg0.getStartDate() );
        testLeave.setStatus( arg0.getStatus() );

        return testLeave;
    }

    @Override
    public TestLeave convert(TestLeaveBo arg0, TestLeave arg1) {
        if ( arg0 == null ) {
            return arg1;
        }

        arg1.setSearchValue( arg0.getSearchValue() );
        arg1.setCreateDept( arg0.getCreateDept() );
        arg1.setCreateBy( arg0.getCreateBy() );
        arg1.setCreateTime( arg0.getCreateTime() );
        arg1.setUpdateBy( arg0.getUpdateBy() );
        arg1.setUpdateTime( arg0.getUpdateTime() );
        if ( arg1.getParams() != null ) {
            Map<String, Object> map = arg0.getParams();
            if ( map != null ) {
                arg1.getParams().clear();
                arg1.getParams().putAll( map );
            }
            else {
                arg1.setParams( null );
            }
        }
        else {
            Map<String, Object> map = arg0.getParams();
            if ( map != null ) {
                arg1.setParams( new LinkedHashMap<String, Object>( map ) );
            }
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
