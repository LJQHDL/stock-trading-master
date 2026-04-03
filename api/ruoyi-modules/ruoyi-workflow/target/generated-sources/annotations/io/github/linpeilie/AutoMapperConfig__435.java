package io.github.linpeilie;

import org.dromara.workflow.domain.FlowCategoryToFlowCategoryVoMapper;
import org.dromara.workflow.domain.TestLeaveToTestLeaveVoMapper;
import org.dromara.workflow.domain.bo.FlowCategoryBoToFlowCategoryMapper;
import org.dromara.workflow.domain.bo.TestLeaveBoToTestLeaveMapper;
import org.dromara.workflow.domain.vo.FlowCategoryVoToFlowCategoryMapper;
import org.dromara.workflow.domain.vo.TestLeaveVoToTestLeaveMapper;
import org.mapstruct.Builder;
import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

@MapperConfig(
    componentModel = "spring-lazy",
    uses = {ConverterMapperAdapter__435.class, FlowCategoryVoToFlowCategoryMapper.class, FlowCategoryBoToFlowCategoryMapper.class, TestLeaveBoToTestLeaveMapper.class, TestLeaveVoToTestLeaveMapper.class, TestLeaveToTestLeaveVoMapper.class, FlowCategoryToFlowCategoryVoMapper.class},
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    builder = @Builder(buildMethod = "build", disableBuilder = true)
)
public interface AutoMapperConfig__435 {
}
