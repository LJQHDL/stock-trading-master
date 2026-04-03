package org.dromara.system.domain.vo;

import io.github.linpeilie.AutoMapperConfig__433;
import io.github.linpeilie.BaseMapper;
import org.dromara.system.domain.SysOss;
import org.dromara.system.domain.SysOssToSysOssVoMapper;
import org.mapstruct.Mapper;

@Mapper(
    config = AutoMapperConfig__433.class,
    uses = {SysOssToSysOssVoMapper.class},
    imports = {}
)
public interface SysOssVoToSysOssMapper extends BaseMapper<SysOssVo, SysOss> {
}
