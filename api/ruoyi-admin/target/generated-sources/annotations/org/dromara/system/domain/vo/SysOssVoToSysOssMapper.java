package org.dromara.system.domain.vo;

import io.github.linpeilie.AutoMapperConfig__438;
import io.github.linpeilie.BaseMapper;
import org.dromara.system.domain.SysOss;
import org.dromara.system.domain.SysOssToSysOssVoMapper;
import org.mapstruct.Mapper;

@Mapper(
    config = AutoMapperConfig__438.class,
    uses = {SysOssToSysOssVoMapper.class},
    imports = {}
)
public interface SysOssVoToSysOssMapper extends BaseMapper<SysOssVo, SysOss> {
}
