package org.dromara.workflow.domain.vo;

import io.github.linpeilie.AutoMapperConfig__435;
import io.github.linpeilie.BaseMapper;
import org.dromara.workflow.domain.TestLeave;
import org.dromara.workflow.domain.TestLeaveToTestLeaveVoMapper;
import org.mapstruct.Mapper;

@Mapper(
    config = AutoMapperConfig__435.class,
    uses = {TestLeaveToTestLeaveVoMapper.class},
    imports = {}
)
public interface TestLeaveVoToTestLeaveMapper extends BaseMapper<TestLeaveVo, TestLeave> {
}
