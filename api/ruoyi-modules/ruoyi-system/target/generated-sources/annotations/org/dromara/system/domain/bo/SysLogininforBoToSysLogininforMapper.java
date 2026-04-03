package org.dromara.system.domain.bo;

import io.github.linpeilie.AutoMapperConfig__433;
import io.github.linpeilie.BaseMapper;
import org.dromara.system.domain.SysLogininfor;
import org.mapstruct.Mapper;

@Mapper(
    config = AutoMapperConfig__433.class,
    uses = {},
    imports = {}
)
public interface SysLogininforBoToSysLogininforMapper extends BaseMapper<SysLogininforBo, SysLogininfor> {
}
